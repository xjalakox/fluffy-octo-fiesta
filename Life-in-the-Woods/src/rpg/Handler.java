package rpg;

import java.awt.Graphics;

public class Handler {
	
	//public Level level;
	public KeyInput key;
	
	//public static SoundManager manager = new SoundManager();

	public static SaveGame g = new SaveGame();
	public static Texts texts = new Texts();
	
	public Handler(KeyInput key) {
		this.key = key;
		//this.level = Level.map1;
	}
	
	public void tick() {
	//	level.update();
	}
	
	public void render(Graphics g) {
		//level.render(g);
	}
	
	/*public void changeLevel(int id) {
		c
	}
 	/*	
	public void render(Graphics g,int id){
		
		
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
	
	public void tick(int id){
			for(Tile ti:tile){
				ti.tick();
			}
			for(Entity en:entity){
					if(Game.getVisisbleArea()!=null&&en.getBounds().intersects(Game.getVisisbleArea()) && !en.isRemoved()){
						en.tick();
		            }
			}
			for(BackgroundTile bti:btile){
				bti.tick();
			}
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
     	    tile.remove(i);
        }
        for(int i = 0; i < entity.size(); i++) {
        	 entity.remove(i);
        }
        for(int i = 0; i < btile.size(); i++) {
    	    btile.remove(i);
        }
    }
	
	public void ChangeLevel(String levelPath, int x,int y){
		clearLevel();
		///createLevel(levelPath,x,y);
	}
	*/
	/*public void ChangeMusic(int newMusicID, int oldMusicID, boolean running){
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

	/*
	public void createLevel(String file,int x,int y){

		
		addEntity(new npc(2500,2500,60,84,Id.blacksmith,this));
		player = new player(x,y,60,84,Id.player,this, Game.key);
		addEntity(player); 

		
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
						}else if(ids>=2297&&ids<=2488&&j!=2&&j!=2302){
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
	*/
}
