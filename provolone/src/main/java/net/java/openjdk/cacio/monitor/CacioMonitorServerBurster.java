package net.java.openjdk.cacio.monitor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import net.java.openjdk.cacio.provolone.PTPScreen;
import net.java.openjdk.cacio.servlet.transport.Transport;

public class CacioMonitorServerBurster {

	private final Thread thread;

	public static final int PORT = 3141;

	public static final double FPS = 30;

	public CacioMonitorServerBurster(int port, int j) {
		this.thread = new Thread(new Runnable() {
			@Override
			public void run() {
				sendImages();
			}
		});
		this.thread.start();
	}

	protected void sendImages() {
		while (true) {
			Socket socket = null;
			try {
				// Connect
				while (socket == null) {
					ServerSocket serverSocket = null;
					try {
						serverSocket = new ServerSocket(PORT);
						socket = serverSocket.accept();
					} catch (Exception e1) {
					} finally {
						if (serverSocket != null) {
							try {
								serverSocket.close();
							} catch (IOException e) {
							}
						}
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}
				// Send Images
				while (socket != null && !socket.isClosed()
						&& socket.isConnected()) {
					try {
						OutputStream os = socket.getOutputStream();
						
						Transport encoder = PTPScreen.getInstance().pollForScreenUpdates(15000);
						
						encoder.writeToStream(os);
						
						os.flush();
						
						System.out.println(encoder.asString());
						// Wait for next image
						try {
							Thread.sleep((long) (1000 / FPS));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (SocketException e) {
						e.printStackTrace();
						try {
							socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} finally {
				try {
					if (socket != null) {
						socket.close();
						socket = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private byte[] intToByteArray(int value) {
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}
}
