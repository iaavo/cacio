package net.java.openjdk.cacio.provolone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import net.java.openjdk.awt.peer.web.BlitScreenUpdate;
import net.java.openjdk.awt.peer.web.ScreenUpdate;
import net.java.openjdk.awt.peer.web.TreeImagePacker;
import net.java.openjdk.cacio.servlet.transport.Transport;

public class PNGTransport extends Transport {

	public PNGTransport(String contentType) {
		super("image/png");
	}

	@Override
	public void prepareUpdate(List<ScreenUpdate> pendingUpdateList,
			TreeImagePacker packer, List<Integer> cmdData) {
		
	}

	@Override
	public void writeToStream(OutputStream os) throws IOException {
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
	}
}
