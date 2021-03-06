package rpg.tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Id;

public class door extends Tile {

	public door(int x, int y, int w, int h, Id id,boolean solid, long arrayzahl) {
		super(x, y, w, h, id, solid, arrayzahl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.sprites[(int) arrayzahl].getBufferedImage(), x*2,y*2,w*2,h*2, null);
		g.setColor(Color.GREEN);
		//g.drawRect(getX()*2, getY()*2+50, getW()*2, getH()*2-50);
		g.drawRect(getX()*2, getY()*2+44, getW()*2, getH()*2-54);
	}

	@Override
	public void tick() {
		

	}
	
	
	public Rectangle getBounds(){
		return new Rectangle(getX()*2,getY()*2,getW()*2,getH()*2);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()*2+64, getY()*2, getW()*2-54, getH()*2);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()*2-10, getY()*2, getW()*2-54, getH()*2);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()*2, getY()*2-10, getW()*2, getH()*2-54);
	}

	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()*2, getY()*2+44, getW()*2, getH()*2-54);
	}

}
