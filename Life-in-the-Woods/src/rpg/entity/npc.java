package rpg.entity; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Id;
import rpg.KeyInput;
import rpg.level.Level;
import rpg.quest.Quest;
import rpg.quest.Quests;
import rpg.tile.Tile;

public class npc extends Entity {
	private int a,b = 0;
	private Level level;
	
	public npc(int x, int y, int w, int h, Id id,Quests quests) {
		super(x, y, w, h, id);
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
		if(!collision())
		if(a==0){
			a = (int)(Math.random() * 75) + 25;
			b = (int)(Math.random() * 3);
		}else{
			if(b==0) x +=1;
			if(b==1) x -=1;
			if(b==2) y +=1;
			if(b==3) y -=1;
			a--;
		}

	}
	
	private boolean collision() {
		for(Tile t : level.tiles){
			if(t.getId()==Id.door){
				if(getBounds().intersects(t.getBoundsBottom())&& !changeLevel) {
					Game.changeLevel(Level.MAP_NOROOF);
					changeLevel = true;
					System.out.println("change level in");
					//Game.handler.ChangeMusic(1,1,true);	
				}
				if(getBounds().intersects(t.getBoundsTop())&& !changeLevel) {
					Game.changeLevel(Level.MAP_NOROOF);
					changeLevel = true;
					System.out.println("change level out");
				}

			}else if(t.getId()==Id.obj){
				if(getBounds().intersects(t.getBoundsBottom())){
					KeyInput.up = false;
				}
				if(getBounds().intersects(t.getBoundsRight())){
					KeyInput.left = false;
				}
				if(getBounds().intersects(t.getBoundsLeft())){
					KeyInput.right = false;
				}
				if(getBounds().intersects(t.getBoundsTop())){
					KeyInput.down = false;
				}
			}
		}
		for(Entity en:  level.entities) {
			if(en.getId()==Id.blacksmith){
				if(getBounds().intersects(en.getBoundsBottom())){
					if(key.talk_npc){
						en.facing = 2;
					}
					KeyInput.up = false;
				}
				if(getBounds().intersects(en.getBoundsRight())){
					if(key.talk_npc){
						en.facing = 3;
					}
					KeyInput.left = false;
				}
				if(getBounds().intersects(en.getBoundsLeft())){
					if(key.talk_npc){
						en.facing = 1;
					}
					KeyInput.right = false;
				}
				if(getBounds().intersects(en.getBoundsTop())){
					if(key.talk_npc){
						en.facing = 0;
					}
					KeyInput.down = false;
				}
			}
		}
		
		return false;
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
