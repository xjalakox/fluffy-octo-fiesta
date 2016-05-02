package rpg.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rpg.MouseInput;
import rpg.util.ImageUtils;

public class GuiMap extends GuiElement {
	
	private BufferedImage image;
	private BufferedImage border;
	private int w, h;
	private int mapX, mapY;
	private int zoom;
	
	public GuiMap(int x, int y, int w, int h) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.zoom = 1;
		this.mapX = 1 * zoom;
		this.mapY = 1 * zoom;
		this.image = ImageUtils.loadImage("res/Gui/map.png");
		this.border = ImageUtils.loadImage("res/Gui/border.png");
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(border, x, y, w, h, null);
		g.drawImage(image, x + 29, y + 28, x + w - 29, y + h - 28, mapX * zoom, mapY * zoom, 1160 - mapX * zoom, 876 - mapY * zoom, null);
	}

	@Override
	public void tick() {
		if(this.zoom < 1 ) {
			this.zoom = 1;
			return;
		}
		else if(this.zoom > 260) {
			this.zoom = 260;
			return;
		}
		this.zoom += (-MouseInput.getWheelRotation())*20;
		System.out.println(zoom);
		MouseInput.setWheelRotation(0);
	}

}
