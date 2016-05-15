package rpg.level;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rpg.Game;
import rpg.Handler;
import rpg.Id;
import rpg.Loadingscreen;
import rpg.entity.Entity;
import rpg.entity.Player;
import rpg.entity.npc;
import rpg.json.JSONDecoder;
import rpg.tile.Backg;
import rpg.tile.BackgroundTile;
import rpg.tile.Tile;
import rpg.tile.door;
import rpg.tile.obj;

public class Level {
	
	public static final int MAP = 0;
	public static final int MAP_NOROOF = 1;
	
	public static Level map1 = new Level("res/Maps/map1_roof.json");
	public static Level map2 = new Level("res/Maps/map1_noroof.json");
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<BackgroundTile> btile = new ArrayList<BackgroundTile>();
	public List<Tile> tiles = new ArrayList<Tile>();
	
	private JSONObject levelData;	
	
	public Level(String path) {
		loadLevel(path);
		generateLevel(path);
		addEntities(path);
	}
	
	private void loadLevel(String path) {
		try {
			System.out.println("Lade level: " + path);
			levelData = JSONDecoder.loadMapData(path);
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Fehler beim Laden von level: " + path);
		}
	}

	private void generateLevel(String path) {
		int a = 0;
		int b = 0;

		for(int j=0;j<=25;j++){
			long opacity = (long)((JSONObject)((JSONArray)levelData.get("layers")).get(j)).get("opacity");
				if(opacity==1){
					JSONArray data = (JSONArray)((JSONObject)((JSONArray)levelData.get("layers")).get(j)).get("data");
					
					for(int i=0;i<data.size();i++){
						long ids = (long) data.get(i);
						if(i % 100 == 0){
							b++;
							a=0;
						}
						
						
						if(ids==0){
							
						}else if(ids==1661||ids==1693&&j!=2){
							addBTile(new Backg(a*32,b*32,32,32,Id.render_player_first,false,(long) data.get(i)));
						}else if(ids>=320&&ids<=360&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.nocollision,false,(long) data.get(i)));
						}else if(ids==193&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.ground,false,(long) data.get(i)));
						}else if(ids>=2297&&ids<=2488&&j!=2&&j!=2306){
							addTile(new door(a*32,b*32,32,32,Id.door,true,(long) data.get(i)));
						}else if(ids<=4000&&ids>=0&&j!=2){
							addTile(new obj(a*32,b*32,32,32,Id.obj,true,(long) data.get(i)));
						}else{
							System.out.println(ids);
							System.out.println(a);
							System.out.println(b);
							addTile(new obj(a*32,b*32,32,32,Id.nocollision,false,(long) data.get(i)));
						}
						a++;
					}
					b=0;
				}
		}
		
		
		boolean visible = Loadingscreen.frame.isVisible();
		if(visible==true){
			Loadingscreen.frame.Close();
		}
	}
	
	public void addEntities(String path) {
		switch (path) {
		case "res/Maps/map1_roof.json":
			addEntity(new Player(Handler.g.getX(), Handler.g.getY(), 64, 84, Id.player, this,Game.key));
		//	addEntity(new npc(2500,2500,64,84,Id.blacksmith));
			break;
		case "res/Maps/map1_noroof.json":
			addEntity(new Player(Handler.g.getX(), Handler.g.getY(), 64, 84, Id.player, this,Game.key));
		//	addEntity(new npc(1792,2100,64,84,Id.blacksmith));
			break;
		}
	}

	private void addBTile(Backg bt) {
		this.btile.add(bt);
	}
	
	private void addTile(Tile t) {
		this.tiles.add(t);
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}
	
	public void update() {
		for(Tile t : tiles){
			t.tick();
		}
		for(Entity e : entities){
			if(getVisisbleArea()!=null&&e.getBounds().intersects(getVisisbleArea())){
				e.tick();
			}
		}
		for(BackgroundTile bti:btile){
			bti.tick();
		}
	}
	
	public void render(Graphics g) {
		for(Tile t : tiles) {
			if(getVisisbleArea()!=null&&t.getBounds().intersects(getVisisbleArea())){
				t.render(g);
			}
		}
		for(Entity e : entities) {
			if(getVisisbleArea()!=null&&e.getBounds().intersects(getVisisbleArea())){
				e.render(g);
			}
		}
		for(BackgroundTile bt : btile) {
			if(getVisisbleArea()!=null&&bt.getBounds().intersects(getVisisbleArea())){
				bt.render(g);
			}
		}
	}
	
	public Rectangle getVisisbleArea() {
		return new Rectangle(getPlayer().getX()-960, getPlayer().getY()-540, 1920, 1080);
    }

	public Player getPlayer() {
		return (Player)entities.get(0);
	}
}
