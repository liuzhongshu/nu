package com.liuzhongshu.nu;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;



public class NuApp {

	final static int NU_HTTP_PORT = 8080;
	private static JmDNS jmDNS;
	private static ServiceInfo jmServiceInfo;
	
	private static ContextHandler getNuContextHandle() throws AWTException
	{
        ContextHandler nuContext = new ContextHandler();
		Robot robot = new Robot();
        nuContext.setContextPath("/nu");
        nuContext.setResourceBase(".");
        nuContext.setClassLoader(Thread.currentThread().getContextClassLoader());	 
        nuContext.setHandler(new NuWebHandler(robot));
        return nuContext;
	}
	
	private static ContextHandler getFileContextHandle()
	{
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });	 
        resource_handler.setResourceBase("./web");
        
        ContextHandler fileContext = new ContextHandler();
        fileContext.setContextPath("/");
        fileContext.setResourceBase(".");
        fileContext.setClassLoader(Thread.currentThread().getContextClassLoader());	 
        fileContext.setHandler(resource_handler);
		
        return fileContext;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception  {
			//jmdns register
			registerJmDns();
			
			Server server = new Server(NU_HTTP_PORT);
	       	
	        ContextHandler nuContext = getNuContextHandle();
	        ContextHandler fileContext = getFileContextHandle();

	        ContextHandlerCollection contexts = new ContextHandlerCollection();
	        contexts.setHandlers(new Handler[] {  nuContext, fileContext });
	        
	        server.setHandler(contexts);
	        server.start();
	        server.join();
	        
	        unrigesterJmDns();
	}

	private static void unrigesterJmDns() throws IOException {
		jmDNS.unregisterAllServices();
		jmDNS.close();
		
	}

	private static void registerJmDns() throws IOException {
		jmDNS = JmDNS.create();
		jmServiceInfo = ServiceInfo.create("_nuservice._tcp.local.",
                "NuWebService", NU_HTTP_PORT, "");
		jmDNS.registerService(jmServiceInfo);
		
	}

}
