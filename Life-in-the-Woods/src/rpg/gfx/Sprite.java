package rpg.gfx;

import java.awt.image.BufferedImage;

public class Sprite {
	
	
	
	public SpriteSheet sheet;
	
	public BufferedImage image;
	
	public Sprite(SpriteSheet sheet, int x, int y, int a, int b) {
		image = sheet.getSprite(x, y, a, b);
	}
	
	public BufferedImage getBufferedImage() {
		return image;
	}
}
