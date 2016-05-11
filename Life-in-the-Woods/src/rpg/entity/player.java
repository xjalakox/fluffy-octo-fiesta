package rpg.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Handler;
import rpg.Id;
import rpg.KeyInput;
import rpg.gui.TextDraw;
import rpg.level.Level;
import rpg.tile.Tile;

public class Player extends Entity {
	int frame = 0, frameDelay = 0;
	public boolean changeLevel = false;
	private KeyInput key;
	private Level level;
	private int anim;

	public Player(int x, int y, int w, int h, Id id, Level l, KeyInput key) {
		super(x, y, w, h, id);
		this.level = l;
		this.key = key;
		changeLevel = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if(!KeyInput.left&&!KeyInput.up&&!KeyInput.down&&!KeyInput.right){
			g.drawImage(Game.player[anim*9].getBufferedImage(), x, y, w, h, null);
		}else if(!KeyInput.left&&!KeyInput.right&&!KeyInput.up){
			g.drawImage(Game.player[18+frame].getBufferedImage(), x,y,w,h, null);
			anim = 2;
		}else if(!KeyInput.left&&!KeyInput.right&&!KeyInput.down){
			g.drawImage(Game.player[0+frame].getBufferedImage(), x,y,w,h, null);
			anim = 0;
		}else if(!KeyInput.up&&!KeyInput.right&&!KeyInput.down){
			g.drawImage(Game.player[9+frame].getBufferedImage(), x,y,w,h, null);
			anim = 1;
		}else if(!KeyInput.left&&!KeyInput.up&&!KeyInput.down){
			g.drawImage(Game.player[27+frame].getBufferedImage(), x,y,w,h, null);
			anim = 3;
		}

		g.drawRect(getX()+7, getY()+65, getW()-14, getH()-65);
	}

	@Override
	public void tick() {
		if(key.coordinate){
			System.out.println("X: " + getX() + "Y: " + getY());
		}
		if(!collision()) {
			if(KeyInput.key_enable){
				if(KeyInput.up) {
					if(key.running){
						y -=6; 
					}else{
						y-=3;
					}
					animate();
				} else if(KeyInput.down) {
					if(key.running){
						y +=6; 
					}else{
						y+=3;
					}
					animate();
				} else if(KeyInput.right) {
					if(key.running){
						x +=6; 
					}else{
						x+=3;
					}
					animate();
				} else if(KeyInput.left) {
					if(key.running){
						x -=6; 
					}else{
						x-=3;
					}
					animate();
				}else{
				}
			}
		}
	}

	private boolean collision() {
		for(Tile t : level.tiles){
			if(t.getId()==Id.door){
				if(getBounds().intersects(t.getBoundsBottom())&& !changeLevel) {
					Handler.g.setX(level.getPlayer().getX());
					Handler.g.setY(level.getPlayer().getY());
					Game.changeLevel(Level.MAP_NOROOF,0,-100);
					changeLevel = true;
					System.out.println("change level in");
				}
				if(getBounds().intersects(t.getBoundsTop())&& !changeLevel) {
					Game.changeLevel(Level.MAP,0,100);
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
						TextDraw.drawText(1);
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
	
	public void animate(){
		frameDelay++;
        if(frameDelay>=4&&key.running){
            frame++;
            if(frame>=9) {
               frame = 1;
            }
        frameDelay = 0;
        }else
        if(frameDelay>=8){
        	 frame++;
             if(frame>=9) {
                frame = 1;
             }
         frameDelay = 0;
        }	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getX()+7, getY()+65, getW()-14, getH()-65);
	}
	
	public Rectangle getBoundsBottom(){
		return new Rectangle(getX()+7, getY()+65, getW()-14, getH()-65);
	}
	
	public Rectangle getBoundsTop(){
		return new Rectangle(getX()*2, getY()*2-10, getW()*2, getH()*2-54);
	}
	
	public Rectangle getBoundsRight(){
		return new Rectangle(getX()*2+64, getY()*2, getW()*2-54, getH()*2);
	}
	
	public Rectangle getBoundsLeft(){
		return new Rectangle(getX()*2-10, getY()*2, getW()*2-54, getH()*2);
	}
}