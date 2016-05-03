package rpg.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import rpg.Game;
import rpg.Handler;
import rpg.Id;
import rpg.KeyInput;
import rpg.gui.TextDraw;
import rpg.tile.Tile;

public class player extends Entity {
	int frame = 0, frameDelay = 0;
	private KeyInput key;
	private int anim;

	public player(int x, int y, int w, int h, Id id, Handler handler, KeyInput key) {
		super(x, y, w, h, id, handler);
		this.key = key;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if(!key.left&&!key.up&&!key.down&&!key.right){
			g.drawImage(Game.player[anim*9].getBufferedImage(), x, y, w, h, null);
		}else if(!key.left&&!key.right&&!key.up){
			g.drawImage(Game.player[18+frame].getBufferedImage(), x,y,w,h, null);
			anim = 2;
		}else if(!key.left&&!key.right&&!key.down){
			g.drawImage(Game.player[0+frame].getBufferedImage(), x,y,w,h, null);
			anim = 0;
		}else if(!key.up&&!key.right&&!key.down){
			g.drawImage(Game.player[9+frame].getBufferedImage(), x,y,w,h, null);
			anim = 1;
		}else if(!key.left&&!key.up&&!key.down){
			g.drawImage(Game.player[27+frame].getBufferedImage(), x,y,w,h, null);
			anim = 3;
		}

		//g.drawRect(getX()+7, getY()+65, getW()-14, getH()-65);
	}

	@Override
	public void tick() {
		if(key.coordinate){
			System.out.println("X: " + getX() + "Y: " + getY());
		}
		if(!collision()) {
			if(KeyInput.key_enable){
			if(key.up) {
				if(key.running)y -=6; else y-=3;
				animate();
			} else if(key.down) {
				if(key.running)y +=6; else y+=3;
				animate();
			} else if(key.right) {
				if(key.running)x +=6; else x+=3;
				animate();
			} else if(key.left) {
				if(key.running)x -=6; else x-=3;
				animate();
			}
		}
	}
}

	private boolean collision() {
		for(Tile t : rpg.Game.handler.tile){
			if(t.getId()==Id.door){
				if(getBounds().intersects(t.getBoundsBottom())){
					for(Entity en:Game.handler.entity) {
						if(en.getId()==Id.player){
							key.enterdoor2 = true;
						}
					}
				}
			}else if(t.getId()==Id.obj){
				if(getBounds().intersects(t.getBoundsBottom())){
					key.up = false;
				}
				if(getBounds().intersects(t.getBoundsRight())){
					key.left = false;
				}
				if(getBounds().intersects(t.getBoundsLeft())){
					key.right = false;
				}
				if(getBounds().intersects(t.getBoundsTop())){
					key.down = false;
				}
			}
		}
		
		for(Entity en:Game.handler.entity) {
			if(en.getId()==Id.blacksmith){
				if(getBounds().intersects(en.getBoundsBottom())){
					if(key.talk_npc){
						en.facing = 2;
						TextDraw.drawText(1);
					}
					key.up = false;
				}
				if(getBounds().intersects(en.getBoundsRight())){
					if(key.talk_npc){
						en.facing = 3;
					}
					key.left = false;
				}
				if(getBounds().intersects(en.getBoundsLeft())){
					if(key.talk_npc){
						en.facing = 1;
					}
					key.right = false;
				}
				if(getBounds().intersects(en.getBoundsTop())){
					if(key.talk_npc){
						en.facing = 0;
					}
					key.down = false;
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