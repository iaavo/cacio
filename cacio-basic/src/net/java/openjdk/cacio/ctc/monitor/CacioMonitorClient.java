package net.java.openjdk.cacio.ctc.monitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class CacioMonitorClient {

	public static void main(String[] args) {
		new CacioMonitorClient();
	}

	private JLabel panel;
	private Thread thread;
	private JFrame frame;
	private String status;

	public CacioMonitorClient() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame = new JFrame();
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				setupGui(frame);
				frame.pack();
				frame.setSize(640, 480);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						receiveImages();
					}
				});
				thread.start();
			}
		});
	}
	
	private void setStatus(String status) {
		this.status = status;
		frame.setTitle("Cacio-tta-monitor - " + this.status);
	}
	
	private String appendStatus(String status) {
		this.status += status;
		frame.setTitle("Cacio-tta-monitor - " + this.status);
		return this.status;
	}

	private void setupGui(JFrame frame) {
		frame.setLayout(new BorderLayout());
		panel = new JLabel();
		panel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setVerticalAlignment(SwingConstants.CENTER);
		panel.setOpaque(true);
		panel.setBackground(Color.BLACK);
		frame.add(panel, BorderLayout.CENTER);
	}

	private void receiveImages() {
		while (true) {
			Socket socket = null;
			try {
				// Connect
				while (socket == null) {
					try {
						panel.setIcon(null);
						setStatus("Trying to connect...");
						socket = new Socket("127.0.0.1",
								CacioMonitorServer.PORT);
						appendStatus("Success");
					} catch (Exception e1) {
						appendStatus("Fail");
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// Receive Images
				while (socket != null && !socket.isClosed()
						&& socket.isConnected()) {
					try {
						setStatus("Receive Image...");
						InputStream is = socket.getInputStream();
						// Receive size of the image
						int size = readInt(is);
						if (size < 0) {
							throw new IOException("Insufficient data in stream");
						}
						appendStatus(" Bytes=" + size);
						// Receive Image data
						byte[] data = new byte[size];
						int index = 0;
						while (index < size) {
							int bytesRead = is.read(data, index, size - index);
							if (bytesRead < 0) {
								throw new IOException(
										"Insufficient data in stream");
							}
							index += bytesRead;
						}
						appendStatus(" received");
						// Set the image
						BufferedImage image = ImageIO
								.read(new ByteArrayInputStream(data));
						panel.setIcon(new ImageIcon(image));
						panel.repaint();
						appendStatus(" and painted.");
					} catch (SocketException e) {
						try {
							socket.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} catch (Exception e) {
						appendStatus("Fail");
					}
					// Wait for next image
					try {
						Thread.sleep(1/CacioMonitorServer.FPS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} finally {
				try {
					setStatus("Disconnected");
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

	private int readInt(InputStream in) throws IOException {
		int ch1 = in.read();
		int ch2 = in.read();
		int ch3 = in.read();
		int ch4 = in.read();
		if ((ch1 | ch2 | ch3 | ch4) < 0)
			throw new EOFException();
		return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0));
	}
}
