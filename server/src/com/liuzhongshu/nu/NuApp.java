package com.liuzhongshu.nu;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
	
	
	private  ContextHandler getNuContextHandle() throws AWTException
	{
        ContextHandler nuContext = new ContextHandler();
		Robot robot = new Robot();
        nuContext.setContextPath("/nu");
        nuContext.setResourceBase(".");
        nuContext.setClassLoader(Thread.currentThread().getContextClassLoader());	 
        nuContext.setHandler(new NuWebHandler(robot));
        return nuContext;
	}
	

	private ContextHandler getEmbedContextHandle() throws IOException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		File tempDir = FileUtil.createTempDirectory();
		File tempFile = File.createTempFile("tempzip", Long.toString(System.nanoTime()));
		tempDir.deleteOnExit();
		tempFile.deleteOnExit();
		
		FileOutputStream os = new FileOutputStream(tempFile);
		InputStream is = getClass().getResourceAsStream("/embed.zip");
		FileUtil.copyPipe(is, os, 2048);
		is.close();
		os.close();
		
		FileUtil.unzip(tempFile, tempDir);
		
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });	 
        resource_handler.setResourceBase(tempDir.getAbsolutePath());
        
        ContextHandler fileContext = new ContextHandler();
        fileContext.setContextPath("/");
        fileContext.setResourceBase(".");
        fileContext.setClassLoader(classLoader);	 
        fileContext.setHandler(resource_handler);
		
        return fileContext;
	}
	
	
	private ContextHandler getExtendContextHandle()
	{
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });	 
        resource_handler.setResourceBase("./plugins");
        
        ContextHandler fileContext = new ContextHandler();
        fileContext.setContextPath("/plugins");
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
			NuApp app = new NuApp();
			
			app.registerJmDns();
						
			Server server = new Server(NU_HTTP_PORT);
	       	
	        ContextHandler nuContext = app.getNuContextHandle();
	        ContextHandler embedContext = app.getEmbedContextHandle();
	        ContextHandler extendContext = app.getExtendContextHandle();

	        ContextHandlerCollection contexts = new ContextHandlerCollection();
	        contexts.setHandlers(new Handler[] {  nuContext, embedContext, extendContext });
	        
	        server.setHandler(contexts);
	        server.start();
	        server.join();
	        
	        app.unrigesterJmDns();
	}

	private  void unrigesterJmDns() throws IOException {
		jmDNS.unregisterAllServices();
		jmDNS.close();
		
	}

	private  void registerJmDns() throws IOException {
		jmDNS = JmDNS.create();
		jmServiceInfo = ServiceInfo.create("_nuservice._tcp.local.",
                "NuWebService", NU_HTTP_PORT, "");
		jmDNS.registerService(jmServiceInfo);
		
	}

}
