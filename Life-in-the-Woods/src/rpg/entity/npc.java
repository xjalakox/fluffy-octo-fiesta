package rpg.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Handler;
import rpg.Id;

public class npc extends Entity {

	public npc(int x, int y, int w, int h, Id id, Handler handler) {
		super(x, y, w, h, id, handler);
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.player[9*facing].getBufferedImage(), x, y, w, h, null);
		g.setColor(Color.red);
		g.drawRect(getX()+7, getY()+85, getW()-14, getH()-65);
		g.setColor(Color.green);
		g.drawRect(getX()+7, getY(), getW()-14, getH()-65);
		g.setColor(Color.blue);
		g.drawRect(getX()+64, getY()+15, getW()-54, getH()-30);
		g.setColor(Color.YELLOW);
		g.drawRect(getX()-10, getY()+15, getW()-54, getH()-30);
		
	}

	@Override
	public void tick() {
		

	}
	
	public void moveUp(){

	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+7, getY()+85, getW()-14, getH()-65);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()+7, getY(), getW()-14, getH()-65);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()+64, getY()+15, getW()-54, getH()-30);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()-10, getY()+15, getW()-54, getH()-30);
	}


	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX()+7, getY()+65, getW()-14, getH()-65);
	}

}
