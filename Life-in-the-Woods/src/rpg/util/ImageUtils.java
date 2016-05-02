package rpg.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	private ImageUtils() {}
	
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			System.out.print("Lade Bild " + path + " ...");
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Failed");
		}
		System.out.println("Success");
		return image;
	}
}
