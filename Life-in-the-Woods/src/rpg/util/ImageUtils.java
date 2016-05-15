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
			System.out.println("Lade Bild: " + path);
			image = ImageIO.read(ImageUtils.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.err.println("Fehler beim Laden von Bild: " + path);
		}
		return image;
	}
}
