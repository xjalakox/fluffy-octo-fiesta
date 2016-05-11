package rpg.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Id;

public abstract class Tile {
	public int facing;
	public int x,y,w,h;
	public int velX,velY;
	
	public long arrayzahl;
	
	public boolean solid;
	private boolean removed;

	public Id id;
	
	
	 Tile(int x, int y, int w, int h, Id id,boolean solid,long arrayzahl){
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.id = id;
			this.solid = solid;
			this.arrayzahl = arrayzahl;
			this.removed = false;
		}
	 
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public void remove() {
		this.removed = true;
	}
	
	public boolean isRemoved() {
		return this.removed;
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
     

	public boolean isSolid() {
		return solid;
	}

	public Id getId() {
		return id;
	}
	
	public int getW(){
		return w;
	}
	
	public void setW(int w){
		this.w = w;
	}
	
	public int getH(){
		return h;
	}
	
	public void setH(int h){
		this.h = h;
	}
	
	public abstract Rectangle getBounds();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsBottom();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();
}
