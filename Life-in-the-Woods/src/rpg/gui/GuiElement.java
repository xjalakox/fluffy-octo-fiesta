package rpg.gui;

import java.awt.Graphics;

public abstract class GuiElement {
	protected int x;
	protected int y,w,h;
	private boolean visible;
	
	public GuiElement(int x, int y, int w,int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		visible = true;
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean b) {
		visible = b;
	}
 }
