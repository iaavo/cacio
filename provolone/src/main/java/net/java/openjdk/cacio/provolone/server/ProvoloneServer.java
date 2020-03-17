package net.java.openjdk.cacio.provolone.server;

import javax.swing.plaf.metal.MetalLookAndFeel;

import net.java.openjdk.cacio.provolone.PTPGraphicsEnvironment;
import net.java.openjdk.cacio.provolone.PTPToolkit;
import net.java.openjdk.cacio.server.CacioSessionListener;
import net.java.openjdk.cacio.servlet.AppStartServlet;
import net.java.openjdk.cacio.servlet.EventReceiveServlet;
import net.java.openjdk.cacio.servlet.ImageStreamer;
import net.java.openjdk.cacio.servlet.ResourceLoaderServlet;
import net.java.openjdk.cacio.servlet.SessionInitializeServlet;
import net.java.openjdk.cacio.servlet.WebSocketServlet;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class ProvoloneServer {

	public ProvoloneServer() throws Exception {
		this(8080, 125);
	}

	/**
	 * Initializes buitin http-server on the specified port, and sets system
	 * properties.
	 * 
	 * @param port
	 * @throws Exception
	 */
	public ProvoloneServer(int port, int maxThreads) throws Exception {
		applySystemProperties();

		Server server = new Server(port);
		server.setThreadPool(new QueuedThreadPool(maxThreads));

		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		context.setResourceBase("bin/");
		context.getSessionHandler().getSessionManager()
				.setMaxInactiveInterval(90);
		context.getSessionHandler()
				.addEventListener(new CacioSessionListener());

		ResourceHandler handler = new ResourceHandler();
		handler.setResourceBase("bin");
		handler.setServer(server);

		// context.addServlet(new ServletHolder(new ImgBenchServlet()),
		// "/ImageStreamer");

		context.addServlet(new ServletHolder(new ImageStreamer()),
				"/ImageStreamer");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { handler, context });
		server.setHandler(handlers);

		server.start();
		server.join();
	}

	protected void applySystemProperties() {
		System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("awt.toolkit", PTPToolkit.class.getName());
        System.setProperty("java.awt.graphicsenv", PTPGraphicsEnvironment.class.getName());
        System.setProperty("swing.defaultlaf", MetalLookAndFeel.class.getName());
        System.setProperty("java.awt.headless", "false");
	}
}
