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

	private Robot m_robot = null;
	private int m_lastX = 100;
	private int m_lastY = 100;
	private int m_dimX = 0;
	private int m_dimY = 0;
	
	public NuWebHandler(Robot r)
	{
		m_robot = r;
		getDimension();
	}
	
	private void getDimension() {
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		Dimension dim = toolkit.getScreenSize();
		m_dimX = dim.width;
		m_dimY = dim.height;		
	}
	
	private void checkDimension() {
		if (m_lastX >= m_dimX - 1 || m_lastY >= m_dimY - 1 )
			getDimension();
	}
	
	private int translateKey(String keyname) throws Exception {
		
		return KeyCode.toCode(keyname);
	}
	
	private void sendKey(String value) throws Exception
	{
		List<Integer> keys = new ArrayList<Integer>(); 
		String [] keynames = value.split("-");
		for (int i=0; i < keynames.length; i++)
		{
			int key = translateKey(keynames[i]);
			if (key > 0) {
				keys.add(key);
			}
		}
		
		for (int key: keys) {
			m_robot.keyPress(key);
		}
		for (int i=keys.size() - 1; i >= 0; i--) {
			m_robot.keyRelease(keys.get(i));
		}
	}

	private void sendText(String value) throws Exception
	{
		for (int i=0; i < value.length(); i++)
		{
			String t = value.substring(i, i+1);
			
			int key = translateKey(t.toLowerCase());
			boolean shift = t.equals(t.toUpperCase());
			
			if (key > 0) {
				
				if (shift)
					m_robot.keyPress(KeyEvent.VK_SHIFT);

				m_robot.keyPress(key);
				m_robot.keyRelease(key);
				
				if (shift)
					m_robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}	
	}
	
	private int normalizeX(int x) {
		if (x < 0)
			return 0;
		else if (x >= m_dimX)
			return m_dimX -1;
		else
			return x;
	}

	private int normalizeY(int y) {
		if (y < 0)
			return 0;
		else if (y >= m_dimY)
			return m_dimY -1;
		else
			return y;
	}	
	
	private void mouseTo(String value)
	{
		String cords[] = value.split(",");
		int x = normalizeX(Integer.parseInt(cords[0]));
		int y = normalizeY(Integer.parseInt(cords[1]));
		
		m_lastX = x;
		m_lastY = y;
		m_robot.mouseMove(x, y);
		checkDimension();
	}
	


	private void mouseMove(String value)
	{
		String cords[] = value.split(",");
		int x = normalizeX(Integer.parseInt(cords[0]) + m_lastX);
		int y = normalizeY(Integer.parseInt(cords[1]) + m_lastY);
		m_lastX = x;
		m_lastY = y;
		m_robot.mouseMove(x, y);
		checkDimension();
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
					       	"http://your.server/nu?event=click&value=left<br/>" +
					       	"http://your.server/nu?event=mouseto&value=100,100<br/>" +
					       	"http://your.server/nu?event=mousemove&value=10,-10<br/>" +
					       	"http://your.server/nu?event=scroll&value=2<br/>" +
					       	"http://your.server/nu?event=sendtext&value=textstring<br/>" +
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
	
}