package net.java.openjdk.cacio.monitor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.imageio.ImageIO;

import net.java.openjdk.awt.peer.web.BlitScreenUpdate;
import net.java.openjdk.awt.peer.web.GridDamageTracker;
import net.java.openjdk.awt.peer.web.ScreenUpdate;
import net.java.openjdk.awt.peer.web.TreeImagePacker;
import net.java.openjdk.cacio.provolone.PTPGraphics;

public class CacioMonitorServerBurster {

	private final BufferedImage image;

	private final Thread thread;

	private final GridDamageTracker tracker;

	public static final int PORT = 3141;

	public static final double FPS = 0.1;

	public CacioMonitorServerBurster(BufferedImage imgBuffer,
			GridDamageTracker tracker) {
		this.image = imgBuffer;
		this.tracker = tracker;
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

						List<ScreenUpdate> screenUpdates = this.tracker
								.groupDamagedAreas(this.image);

						if (screenUpdates != null) {
							// Send number of images
							System.out.println("Send images");
							os.write(intToByteArray(screenUpdates.size()));
							for (ScreenUpdate screenUpdate : screenUpdates) {
								BlitScreenUpdate update = (BlitScreenUpdate) screenUpdate;
								update.evacuate();
								// Send x position of image
								os.write(intToByteArray(update.getPackedX()));
								// Send y position of image
								os.write(intToByteArray(update.getPackedY()));
								// Send size of the image
								System.out.println("Send size");
								ByteArrayOutputStream bScrn = new ByteArrayOutputStream();
								ImageIO.write(update.getImage(), "PNG", bScrn);
								byte data[] = bScrn.toByteArray();
								bScrn.close();
								int size = data.length;
								os.write(intToByteArray(size));
								System.out.println("size sended");
								// Send Image data
								os.write(data);
							}
							os.flush();
						}
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
