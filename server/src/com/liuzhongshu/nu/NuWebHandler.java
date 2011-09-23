package com.liuzhongshu.nu;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;


public class NuWebHandler extends AbstractHandler {

	private int dimX = 0, dimY = 0;
	
	public NuWebHandler(Robot r)
	{
		m_robot = r;
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		dimX = dim.width;
		dimY = dim.height;
	}
	
	private List<Integer> translateKeys(String value) throws Exception
	{
		List<Integer> keys = new ArrayList<Integer>();
		if (value == null || value.length() == 0)
		{
			return keys;
		}
		else
		{
			String [] keynames = value.split("-");
			for (int i=0; i < keynames.length; i++)
			{
				int key = translateKey(keynames[i]);
				if (key > 0)
					keys.add(key);
			}
			return keys;
		}
	}

	private List<Integer> translateText(String value) throws Exception
	{
		List<Integer> keys = new ArrayList<Integer>();
		if (value == null || value.length() == 0)
		{
			return keys;
		}
		else
		{
			for (int i=0; i < value.length(); i++)
			{
				int key = translateKey(value.substring(i, i+1));
				if (key > 0)
					keys.add(key);
			}
			return keys;
		}
	}	
	
	private int translateKey(String keyname) throws Exception {
		KeyCode keycode = KeyCode.toCode("_" + keyname);
		
		switch (keycode)
		{
			case _0: return KeyEvent.VK_0;
			case _1: return KeyEvent.VK_1;
			case _2: return KeyEvent.VK_2;
			case _3: return KeyEvent.VK_3;
			case _4: return KeyEvent.VK_4;
			case _5: return KeyEvent.VK_5;
			case _6: return KeyEvent.VK_6;
			case _7: return KeyEvent.VK_7;
			case _8: return KeyEvent.VK_8;
			case _9: return KeyEvent.VK_9;
			case _a: return KeyEvent.VK_A;
			case _b: return KeyEvent.VK_B;
			case _c: return KeyEvent.VK_C;
			case _d: return KeyEvent.VK_D;
			case _e: return KeyEvent.VK_E;
			case _f: return KeyEvent.VK_F;
			case _g: return KeyEvent.VK_G;
			case _h: return KeyEvent.VK_H;
			case _i: return KeyEvent.VK_I;
			case _j: return KeyEvent.VK_J;
			case _k: return KeyEvent.VK_K;
			case _l: return KeyEvent.VK_L;
			case _m: return KeyEvent.VK_M;
			case _n: return KeyEvent.VK_N;
			case _o: return KeyEvent.VK_O;
			case _p: return KeyEvent.VK_P;
			case _q: return KeyEvent.VK_Q;
			case _r: return KeyEvent.VK_R;
			case _s: return KeyEvent.VK_S;
			case _t: return KeyEvent.VK_T;
			case _u: return KeyEvent.VK_U;
			case _v: return KeyEvent.VK_V;
			case _w: return KeyEvent.VK_W;
			case _x: return KeyEvent.VK_X;
			case _y: return KeyEvent.VK_Y;
			case _z: return KeyEvent.VK_Z;
			case _shift: return KeyEvent.VK_SHIFT;
			case _ctrl: return KeyEvent.VK_CONTROL;
			case _alt: return KeyEvent.VK_ALT;
			case _win: return KeyEvent.VK_WINDOWS;
			case _cmd: return KeyEvent.VK_META;     	//mac command key
			case _esc: return KeyEvent.VK_ESCAPE;
			case _f1: return KeyEvent.VK_F1;			//mac???
			case _f2: return KeyEvent.VK_F2;
			case _f3: return KeyEvent.VK_F3;
			case _f4: return KeyEvent.VK_F4;
			case _f5: return KeyEvent.VK_F5;
			case _f6: return KeyEvent.VK_F6;
			case _f7: return KeyEvent.VK_F7;
			case _f8: return KeyEvent.VK_F8;
			case _f9: return KeyEvent.VK_F9;
			case _f10: return KeyEvent.VK_F10;
			case _f11: return KeyEvent.VK_F11;
			case _f12: return KeyEvent.VK_F12;
			case _tab: return KeyEvent.VK_TAB;
			case _cap: return KeyEvent.VK_CAPS_LOCK;
			case _fn: return KeyEvent.VK_F;				//mac???
			case _up: return KeyEvent.VK_UP;
			case _down: return KeyEvent.VK_DOWN;
			case _left: return KeyEvent.VK_LEFT;
			case _right: return KeyEvent.VK_RIGHT;
			case _pgup: return KeyEvent.VK_PAGE_UP;
			case _pgdn: return KeyEvent.VK_PAGE_DOWN;
			case _home: return KeyEvent.VK_HOME;
			case _end: return KeyEvent.VK_END;
			case _insert: return KeyEvent.VK_INSERT;
			case _delete: return KeyEvent.VK_DELETE;
			case _rbracket: return KeyEvent.VK_CLOSE_BRACKET;
			case _enter: return KeyEvent.VK_ENTER;
			case _space: return KeyEvent.VK_SPACE;
			case _backspace: return KeyEvent.VK_BACK_SPACE;
			case _minus: return KeyEvent.VK_MINUS;
			case _equals: return KeyEvent.VK_EQUALS;
			case _comma: return KeyEvent.VK_COMMA;
			case _period: return KeyEvent.VK_PERIOD;
			case _slash: return KeyEvent.VK_SLASH;
			case _backslash: return KeyEvent.VK_BACK_SLASH;
			case _semicolon: return KeyEvent.VK_SEMICOLON;
			case _quote: return KeyEvent.VK_QUOTE;
			default:  return 0; 
		}
	}
	
	private void sendKey(String value) throws Exception
	{
		List<Integer> keys = translateKeys(value); 
		
		for (int key: keys)
		{
			m_robot.keyPress(key);
		}
		for (int i=keys.size() - 1; i >= 0; i--)
		{
			m_robot.keyRelease(keys.get(i));
		}
	}

	private void sendText(String value) throws Exception
	{
		List<Integer>keys = translateText(value); 
		
		for (int key: keys)
		{
			m_robot.keyPress(key);
			m_robot.keyRelease(key);
		}
	}
	
	private void mouseTo(String value)
	{
		String cords[] = value.split(",");
		int x = normalizeX(Integer.parseInt(cords[0]));
		int y = normalizeY(Integer.parseInt(cords[1]));
		
		m_lastX = x;
		m_lastY = y;
		m_robot.mouseMove(x, y);
	}
	
	private int normalizeX(int x) {
		if (x < 0)
			return 0;
		else if (x >= dimX)
			return dimX -1;
		else
			return x;
	}

	private int normalizeY(int y) {
		if (y < 0)
			return 0;
		else if (y >= dimY)
			return dimY -1;
		else
			return y;
	}

	private void mouseMove(String value)
	{
		String cords[] = value.split(",");
		int x = normalizeX(Integer.parseInt(cords[0]) + m_lastX);
		int y = normalizeY(Integer.parseInt(cords[1]) + m_lastY);
		m_lastX = x;
		m_lastY = y;
		m_robot.mouseMove(x, y);
	}
	
	private void mouseClick(String value)
	{
		if("left".equals(value))
		{
			m_robot.mousePress(InputEvent.BUTTON1_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		if("leftdouble".equals(value))
		{
			m_robot.mousePress(InputEvent.BUTTON1_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON1_MASK);
			m_robot.mousePress(InputEvent.BUTTON1_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		else if ("right".equals(value))
		{
			m_robot.mousePress(InputEvent.BUTTON3_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON3_MASK);
		}
		else if ("rightdouble".equals(value))
		{
			m_robot.mousePress(InputEvent.BUTTON3_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON3_MASK);
			m_robot.mousePress(InputEvent.BUTTON3_MASK);
			m_robot.mouseRelease(InputEvent.BUTTON3_MASK);			
		}
	}
	
	private void scroll(String value) {
		int distance = Integer.parseInt(value);
		m_robot.mouseWheel(distance);
	}
	
	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException 
	{
		String event= baseRequest.getParameter("event");
		String value= baseRequest.getParameter("value");
		System.out.println(event + ' ' + value);
		String responseMsg = "success";
		try
		{
			if ("sendkey".equals(event))
			{
				sendKey(value);
			}
			if ("sendtext".equals(event))
			{
				sendText(value);
			}
			else if ("mousemove".equals(event))
			{
				mouseMove(value);
			}
			else if ("mouseto".equals(event))
			{
				mouseTo(value);
			}
			else if("click".equals(event))
			{
				mouseClick(value);
			}
			else if("scroll".equals(event))
			{
				scroll(value);
			}
			else
			{
				responseMsg="URL parameter error! <br/>" + 
					        "  right usage:<br/>" +
					       	"http://your.server/nu?event=mouseto&value=100,100<br/>" +
					       	"http://your.server/nu?event=mousemove&value=10,-10<br/>" +
					       	"http://your.server/nu?event=sendkey&value=shift-alt-c<br/>";
			}
		}
		catch(Exception e)
		{
			responseMsg = e.toString();
		}
		
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println(responseMsg);
			
	}
	
	private Robot m_robot = null;
	private int m_lastX = 100;
	private int m_lastY = 100;
}