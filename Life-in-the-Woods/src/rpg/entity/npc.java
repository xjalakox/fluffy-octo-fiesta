package rpg.entity; 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Id;
import rpg.json.Text;
import rpg.level.Level;
import rpg.quest.Quests;
import rpg.tile.Tile;

public class npc extends Entity {
	private int a,b = 0;
	protected Level level;
	protected String name;
	protected Text texte;
	
	protected Quests quest;
	
	public npc(int x, int y, int w, int h, Id id, String name,Level l, Quests quests) {
		super(x, y, w, h, id);
		quest = quests;
		level = l;
		this.name = name;
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
		g.setColor(Color.CYAN);
		g.drawRect(getX()-25, getY()-25, getW()+50, getH()+50);
		
	}

	@Override
	public void tick() {
		if(!collision()&&!touchplayer()){
			if(a==0){
				b = (int)(Math.random() * 8);
				if(b!=0&&b!=1&&b!=2&&b!=3) {
					a = (int)(Math.random() * 200) + 25;
				}else{
					a = 50;
				}
			}else{
				if(b==0){
					facing = 3;
					x +=1;
				}else if(b==1){
					facing = 1;
					x -=1;
				}else if(b==2){
					facing = 2;
					y +=1;
				}else if(b==3){
					facing = 0;
					y -=1;
				}else{
					
				}
				a--;
			}
		}

	}
	
	private boolean touchplayer() {
		
		for(Entity en:  level.entities) {
			if(en.getId()==Id.player){
				if(getBoundsTop().intersects(en.getBounds())){
					return true;
				}
				if(getBoundsBottom().intersects(en.getBounds())){
					return true;
				}
				if(getBoundsLeft().intersects(en.getBounds())){
					return true;
				}
				if(getBoundsRight().intersects(en.getBounds())){
					return true;
				}
			}
		}
		return false;
		
	}
	
	private boolean collision() {
			
		for(Tile t : level.tiles){
			if(t.getId()==Id.door||t.getId()==Id.obj){
				if(getBoundsTop().intersects(t.getBounds())){
					if(b==3) a = 0;
				}
				if(getBoundsBottom().intersects(t.getBounds())){
					if(b==2) a = 0;
				}
				if(getBoundsLeft().intersects(t.getBounds())){
					if(b==1) a = 0;
				}
				if(getBoundsRight().intersects(t.getBounds())){
					if(b==0) a = 0;
				}
			}
		}
		for(Entity en:  level.entities) {
			if(en.getId()!=getId()){
				if(getBoundsTop().intersects(en.getBounds())){
					if(b==3) a = 0;
				}
				if(getBoundsBottom().intersects(en.getBounds())){
					if(b==2) a = 0;
				}
				if(getBoundsLeft().intersects(en.getBounds())){
					if(b==1) a = 0;
				}
				if(getBoundsRight().intersects(en.getBounds())){
					if(b==0) a = 0;
				}
			}
		}
		
		
		return false;
	}
	
	public  void talk() {
		
	}
	
	public void setTextId(int textid){
		this.texte.setTextId(textid);
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
		return new Rectangle(getX()-25, getY()-25, getW()+50, getH()+50);
	}
	
	

}
