package rpg;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rpg.audio.SoundManager;
import rpg.entity.Entity;
import rpg.entity.npc;
import rpg.entity.player;
import rpg.json.JSONDecoder;
import rpg.tile.Backg;
import rpg.tile.BackgroundTile;
import rpg.tile.Tile;
import rpg.tile.door;
import rpg.tile.obj;

public class Handler {
	public static List<Entity> entity = new ArrayList<Entity>();
	public static List<Tile> tile = new ArrayList<Tile>();
	public static List<BackgroundTile> btile = new ArrayList<BackgroundTile>();
	
	public static SoundManager manager = new SoundManager();

	public static SaveGame g = new SaveGame();
	public static Texts texts = new Texts();
	
	public static player player;
	
	public void render(Graphics g){
		for(Tile ti:tile){
			if(Game.getVisisbleArea()!=null&&ti.getBounds().intersects(Game.getVisisbleArea())){
                ti.render(g);
            }
		}
		for(Entity en:entity){

				if(Game.getVisisbleArea()!=null&&en.getBounds().intersects(Game.getVisisbleArea())){
					en.render(g);
	            }
			
		}
		for(BackgroundTile bti:btile){
			if(Game.getVisisbleArea()!=null&&bti.getBounds().intersects(Game.getVisisbleArea())){
                bti.render(g);
            }
		}
	}
	
	public void tick(){
		for(Tile ti:tile){
			ti.tick();
		}
		for(Entity en:entity){

				if(Game.getVisisbleArea()!=null&&en.getBounds().intersects(Game.getVisisbleArea())){
					en.tick();
	            }
			
		}
		for(BackgroundTile bti:btile){
			bti.tick();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < tile.size(); i++) {
			if (tile.get(i).isRemoved()) tile.remove(i);
		}
		for(int i = 0; i < entity.size(); i++) {
			if(entity.get(i).isRemoved()) entity.remove(i);
		}
		for(int i = 0; i < btile.size(); i++) {
			if(btile.get(i).isRemoved()) btile.remove(i);
		}
	}
	
	public void addEntity(Entity en){
		entity.add(en);
	}
	
	public void addTile(Tile ti) {
		tile.add(ti);
	}
	
	public void addBTile(BackgroundTile bti){
		btile.add(bti);
	}
	
	public void clearLevel() {
       for(int i = 0; i < tile.size(); i++) {
    	   tile.get(i).remove();
       }
       for(int i = 0; i < entity.size(); i++) {
    	   entity.get(i).remove();
       }
       for(int i = 0; i < btile.size(); i++) {
    	   btile.get(i).remove();
       }
    }
	
	public void ChangeLevel(String levelPath, int x,int y){
		Game.handler.clearLevel();
		Game.handler.createLevel(levelPath,x,y);
	}
	
	public void ChangeMusic(int newMusicID, int oldMusicID, boolean running){
		if(oldMusicID==0){
		//	manager.playSound(newMusicID);
		}else if(oldMusicID==newMusicID&&!running){
		//	manager.playSound(newMusicID);
		}else if(oldMusicID==newMusicID){
			
		}else{
	//		manager.stopSound(oldMusicID);
		//	manager.playSound(newMusicID);
		}
		if(!Game.DEBUG) manager.fadeInSound(newMusicID);
	}

	
	public void createLevel(String file,int x,int y){

		
		addEntity(new npc(2500,2500,60,84,Id.blacksmith,this));
		addEntity(new player(x,y,60,84,Id.player,this, Game.key)); 

		
		JSONObject map1 = JSONDecoder.loadMapData(file);
		
		
		int a = 0;
		int b = 0;

		for(int j=0;j<=21;j++){
			long opacity = (long)((JSONObject)((JSONArray)map1.get("layers")).get(j)).get("opacity");
				if(opacity==1){
					JSONArray data = (JSONArray)((JSONObject)((JSONArray)map1.get("layers")).get(j)).get("data");
					//System.out.println("Welt wird geladen: " + j);
					for(int i=0;i<data.size();i++){
						long ids = (long) data.get(i);
						if(i % 100 == 0){
							b++;
							a=0;
						}
						
						if(ids==0){
							
						}else if(ids==1661||ids==1693&&j!=2){
							addBTile(new Backg(a*32,b*32,32,32,Id.render_player_first,this,false,(long) data.get(i)));
						}else if(ids>=320&&ids<=360&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.nocollision,this,false,(long) data.get(i)));
						}else if(ids==193&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.ground,this,false,(long) data.get(i)));
						}else if(ids>=2297&&ids<=2488&&j!=2){
							addTile(new door(a*32,b*32,32,32,Id.door,this,true,(long) data.get(i)));
						}else if(ids<=4000&&ids>=0&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.obj,this,true,(long) data.get(i)));
						}else{
							addTile(new obj(a*32,b*32,32,32,Id.nocollision,this,false,(long) data.get(i)));
						}
						a++;
					}
					b=0;
				}
		}
		
		
		
		boolean visible = Loadingscreen.frame.isVisible();
		if(visible=true){
			Loadingscreen.frame.Close();
		}
	}	
}
