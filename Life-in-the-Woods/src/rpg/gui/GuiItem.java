package rpg.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import rpg.Game;
import rpg.MouseInput;
import rpg.inventory.Item;

public class GuiItem extends GuiElement {
	
	private int invWidth = 74, 
				invHeight = 75,
				anzahl;
	
	private Item item;
	
	private boolean inside, pressed, ignorePressed;
	public boolean renderMenu;

	private Rectangle rect;
	public GuiMenu menu;
	
	private BufferedImage itemImage;

	public GuiItem(int x, int y, Item i) {
		super(x, y, 34, 34);
		item = i;
		menu = new GuiMenu(x + 10, y + 10, 100, 200, item);
		itemImage = Game.inv_items[i.getImgId()].getBufferedImage();
		this.anzahl = i.getAnzahl();
		this.rect = new Rectangle(x, y, invWidth, invHeight);
		inside = false;
		pressed = false;
		ignorePressed = false;
	}

	public void render(Graphics g) {
		if(item.getAnzahl() == 0) return;
		g.drawImage(itemImage, x, y, invWidth, invHeight, null);
		g.setColor(new Color(0x000000));
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString(String.valueOf(anzahl), x+12, y+12);
	}
	
	public void tick() {
		if(item.getAnzahl() == 0) {
			renderMenu = false;
			return;
		}
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
					// MENU anzeigen
					renderMenu = true;
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
		if(renderMenu) {
			menu.tick();
			if(MouseInput.getX() < menu.getX() || MouseInput.getY() < menu.getY() || MouseInput.getY() > menu.getY()+menu.getHeight() + 20 || MouseInput.getX() > menu.getX()+menu.getWidth() + 30) {
				renderMenu = false;
			}
		}
				
	}

}
