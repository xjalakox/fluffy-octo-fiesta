package rpg.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Id;

public abstract class Entity {
	public int facing;
	public int x,y,w,h;
	public int velX,velY;

	public Id id;
	
	public boolean removed;

	public boolean animate;
	public boolean test;
	
	 public Entity(int x, int y, int w, int h, Id id){
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.id = id;
			this.removed = false;
		}
	 
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean isRemoved() {
		return this.removed;
	}
	
	public void remove() {
		this.removed = true;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getW() {
		return w;
	}
	
	public void setW(int w) {
		this.w = w;
	}
	
	public int getH() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public Id getId() {
		return id;
	}
	
	public void setId(Id id) {
		this.id = id;
	}
	

	/*public Rectangle getBounds(){
		return new Rectangle(getX()+7, getY()+65, getW()-14, getH()-65);
	}*/
	
	public abstract Rectangle getBounds();

	public abstract Rectangle getBoundsTop();

	public abstract Rectangle getBoundsBottom();

	public abstract Rectangle getBoundsLeft();


	public abstract Rectangle getBoundsRight();
	
}