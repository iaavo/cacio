package net.java.openjdk.cacio.provolone.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.java.openjdk.awt.peer.web.WebScreen;
import net.java.openjdk.cacio.provolone.PTPScreen;
import net.java.openjdk.cacio.servlet.transport.Transport;

public class ImageStreamer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		disableCaching(response);

		WebScreen screen = PTPScreen.getInstance();

		if (screen != null) {
			Transport transport = screen.pollForScreenUpdates(15000);
			response.setContentType(transport.getContentType());
			transport.writeToStream(response.getOutputStream());
		}
	}

	protected void disableCaching(HttpServletResponse response) {
		response.setHeader("Expires", "Sat, 1 May 2000 12:00:00 GMT");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
	}
}
