package rpg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import rpg.MouseInput;

public class GuiMenuItem extends GuiElement {
	
	private Rectangle rect;
	private String text;
	private GuiAction action;
	
	private boolean inside, pressed, ignorePressed;
	
	public GuiMenuItem(int x, int y, int w, int h, String text, GuiAction action) {
		super(x, y,w,h);
		this.text = text;
		this.action = action;
			this.rect = new Rectangle(x, y, w, h);
		inside = false;
		pressed = false;
		ignorePressed = false;
	}
	
	public void render(Graphics g) {
		g.drawString(text, (int)rect.getX() + 10,(int) rect.getY() + 25);
		g.setColor(Color.BLUE);
		g.drawRect(x, y, w, h);
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
