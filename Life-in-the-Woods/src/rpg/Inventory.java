package rpg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inventory {
	public BufferedImage inv = null;
	
	public Inventory(){

	}

	public void render(Graphics g) {
		try {
			inv = ImageIO.read(new File("res/Inventory/inv_bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(inv, 100, 100, inv.getWidth(), inv.getHeight(), null);
	
	}
}
