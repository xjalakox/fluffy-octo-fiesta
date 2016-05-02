package rpg.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import rpg.MouseInput;

public class GuiButton extends GuiElement{
	
	private Rectangle rect;
	private BufferedImage image;
	private GuiAction action;
	
	private boolean inside, pressed, ignorePressed;
	
	public GuiButton(int x, int y, BufferedImage image, GuiAction action) {
		super(x, y);
		this.image = image;
		this.action = action;
		this.rect = new Rectangle(x, y, image.getWidth(), image.getHeight());
		inside = false;
		pressed = false;
		ignorePressed = false;
	}
	
	public void render(Graphics g) {
		g.drawImage(image, (int)rect.getX(),(int) rect.getY(), null);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public void tick() {
		boolean leftMouseBtn = MouseInput.getButton() == MouseEvent.BUTTON1;
		if(rect.contains(new Point(MouseInput.getX(), MouseInput.getY()))) {
			if(!inside) {
				if(leftMouseBtn) {
					ignorePressed = true;
				} else {
					ignorePressed = false;
				}
				// Auf dem Button
			}
			inside = true;
			if(!pressed && !ignorePressed && leftMouseBtn) {
				pressed = true;
				// Auf Button geklickt
			} else if(MouseInput.getButton() == MouseEvent.NOBUTTON) {
				if(pressed) {
					pressed = false;
					this.action.action();
					// Button losgelassen
				}
				ignorePressed = false;
			}
		} else {
			if(inside) {
				pressed = false;
				// Button verlassen
			}
			inside = false;
		}
	}
}
