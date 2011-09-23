package com.liuzhongshu.nu;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.net.wifi.WifiInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.liuzhongshu.android.util.HttpConnection;

public class Andnu extends Activity {
	private static final long SINGLE_CLICK_THRESHHOLD = 200;
	private static final long LONG_CLICK_THRESHHOLD = 2000;

	private JmDNS jmDNS;
	private android.net.wifi.WifiManager.MulticastLock mLock; 
	private ServiceInfo jmSI[] = new ServiceInfo[0];
	
	private AlertDialog mDlgMsg = null;	
	private View mMainView = null;
	
	private String mServerAddr = null;
	private int mServerPort = 80;
	Handler mHttpHandler = null;
	
	int mLastX = -1;
	int mLastY = -1;
	private long mLastT;
	private long mDownT;
	
	public void showMsgDlg(String msg)
	{
		if (mDlgMsg != null && mDlgMsg.isShowing())
			mDlgMsg.dismiss();
		
		mDlgMsg = new AlertDialog.Builder(this).setMessage(msg).show();
	}
	
	public void showWaitDlg(String msg)
	{
		if (mDlgMsg != null && mDlgMsg.isShowing())
			mDlgMsg.dismiss();
		
		mDlgMsg = ProgressDialog.show(this, "", msg, true);
	}
	
	public void logBgScreen(String msg) {
		Time now = new Time();
		now.setToNow();
		
		((TextView) findViewById(R.id.textLog)).setText(now.format("%H:%M ") + msg);
	}
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mHttpHandler = new Handler() {
        	public void handleMessage(Message message) {
        	    switch (message.what) {
        	    	case HttpConnection.DID_ERROR:
        	    		Exception e = (Exception) message.obj;
        	    		logBgScreen(e.getMessage());
        	    		break;
        	    }
        	  }
        	};
        	
        mMainView = findViewById(R.id.layoutMain);	
        mMainView.setFocusable(true);
        mMainView.setFocusableInTouchMode(true);
        
        mMainView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	onFingerTouch(event);
                return true;
              }});
        
        mMainView.setOnKeyListener(new View.OnKeyListener(){
			public boolean onKey(View v, int keyCode, KeyEvent event)
			{
				logBgScreen( "action:" + event.getAction() + 
						 	 ",char:" + event.getCharacters() + 
						 	 ",keycode:" + event.getKeyCode());
				
				return onKeyEvent(keyCode, event);
			}

		});
        
        new FindServerTask().execute();
		
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		menu.add(0, 1, 0, "keyboard");
		menu.add(0, 2, 0, "find again");
		//menu.add(0, 3, 0, "preferences");
		menu.add(0, 4, 0, "quit");
		
		return true;
	} 
    
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			//switch keyboard
			InputMethodManager inputMgr = (InputMethodManager)getSystemService(Andnu.INPUT_METHOD_SERVICE);
			inputMgr.toggleSoftInput(0, 0);
			break;
			
		case 2:
			new FindServerTask().execute();
			break;
			
		case 4:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}    

    private void sendKey(String key) 
    {
    	if (mServerAddr != null && key !=null && key.length() > 0) {
    		  String url = "http://" + mServerAddr + ":" + mServerPort + "/nu?event=sendkey&value=" 
    				  	 + URLEncoder.encode(key);
    		  new HttpConnection(mHttpHandler).get(url);
    	}
    }

    private void sendText(String text) 
    {
    	if (mServerAddr != null && text != null && text.length() > 0) {
    		  String url = "http://" + mServerAddr + ":" + mServerPort + "/nu?event=sendtext&value=" 
    				  	 +  URLEncoder.encode(text);
    		  new HttpConnection(mHttpHandler).get(url);
    	}
    }

    private void sendMouseMove(int x, int y) 
    {
    	if (mServerAddr != null && (x != 0 || y != 0)) {
    		  String url = "http://" + mServerAddr + ":" + mServerPort + "/nu?event=mousemove&value=" + x + "," + y;
    		  new HttpConnection(mHttpHandler).get(url);
    	}
    }
    
    private void sendMouseClick(String value)
    {
    	if (mServerAddr != null && value != null) {
  		  String url = "http://" + mServerAddr + ":" + mServerPort + "/nu?event=click&value=" + value;
  		  new HttpConnection(mHttpHandler).get(url);
    	}
    	
    }	
    
    private void sendScroll(String value)
    {
    	if (mServerAddr != null && value != null) {
  		  String url = "http://" + mServerAddr + ":" + mServerPort + "/nu?event=scroll&value=" + value;
  		  new HttpConnection(mHttpHandler).get(url);
    	}
    	
    }
    
    
    private void processMoveAction( int x, int y, boolean up)
    {    	
		long now = System.currentTimeMillis();

		if (mLastX != -1)
		{
			int diffx = x - mLastX;        		
			int diffy = y - mLastY;
			if (up)
			{
				if (now - mDownT < SINGLE_CLICK_THRESHHOLD)
					sendMouseClick("left");
				else if (now - mLastT > LONG_CLICK_THRESHHOLD)
					sendMouseClick("right");
				else
					sendMouseMove(diffx, diffy);
				
				mLastX = -1;
			}
			else if ((diffx > 5 || diffy > 5) && (now - mLastT > 300))
			{				
				sendMouseMove(diffx, diffy);
				mLastX = x;
				mLastY = y;
				mLastT = now;
			}
		}
    }
    
    
    public void onFingerTouch(MotionEvent event) {
        int action = event.getAction();
        int x = (int)event.getX();
        int y = (int)event.getY();
        
        if (action == MotionEvent.ACTION_DOWN) {
    		mLastX = x;
    		mLastY = y;
    		mLastT = System.currentTimeMillis(); 
    		mDownT = mLastT;
        }
        else {
        	processMoveAction(x, y, action == MotionEvent.ACTION_UP);
        }
    }
    
    
    protected boolean onKeyEvent(int keyCode, KeyEvent event) {
    	int code = event.getKeyCode();
    	int action = event.getAction();
    	
    	if (action == KeyEvent.ACTION_MULTIPLE)
    	{
    		sendText(event.getCharacters());
    		return true;
    	}
    	else if (action == KeyEvent.ACTION_DOWN) {
    		
    		if (code == KeyEvent.KEYCODE_VOLUME_UP || code == KeyEvent.KEYCODE_VOLUME_DOWN) {
    			sendScroll(code == KeyEvent.KEYCODE_VOLUME_UP ? "-1" : "1");
    			return true;
    		}
    		
    		String nuKey = AndKeyCode.getNuKey(code);		
    		if (nuKey != null)
    		{
    			sendKey(nuKey);
    			return true;
    		}
    	}
		return false;
	}
 
    
    private class FindServerTask extends AsyncTask<Void, Void, Void> {
    	@Override
    	protected void onPreExecute() {
            showWaitDlg("Finding server...");
    	}

    	@Override
    	protected void onPostExecute(Void para) {
    		if (jmSI.length > 0){
    			mServerAddr = jmSI[0].getHostAddress(); 
    			mServerPort = jmSI[0].getPort();
    			logBgScreen("found server at:" + mServerAddr);    			
    		}   			
    		else {
    			mServerAddr = "192.168.1.100";
    			mServerPort = 8080;
    			logBgScreen("unable find server! set to:" + mServerAddr);
    		}   
    		mDlgMsg.dismiss();
            mMainView.requestFocus();    		
    	}

		@Override
		protected Void doInBackground(Void... params) {
            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) getSystemService(android.content.Context.WIFI_SERVICE);
            WifiInfo wifiinfo = wifi.getConnectionInfo();
            int intaddr = wifiinfo.getIpAddress();

            byte[] byteaddr = new byte[] { (byte) (intaddr & 0xff), (byte) (intaddr >> 8 & 0xff), (byte) (intaddr >> 16 & 0xff), (byte) (intaddr >> 24 & 0xff) };

            android.net.wifi.WifiManager.MulticastLock lock = wifi.createMulticastLock("mylockthereturn");
            lock.setReferenceCounted(true);
            lock.acquire();

            try {
                InetAddress addr = InetAddress.getByAddress(byteaddr);
    			jmDNS = JmDNS.create(addr, "andnu");
    			jmSI = jmDNS.list("_nuservice._tcp.local.");
    			
    			jmDNS.close();
    			jmDNS=null;
    		} catch (IOException e) {    			
    		}

            lock.release();            
            return null;
		}


    }
}