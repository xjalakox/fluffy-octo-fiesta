package rpg.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import rpg.quest.testquest;

public class Savegame {
	
	private String name;
	private int x,y;
	
	public Savegame(){
		loadSavegame();
	}
	
	
	
	
	private void loadSavegame() {
		JSONObject obj = JSONDecoder.loadData("Savegames/savegame.json");
		
		name = (String)(obj.get("name"));
		x = (int)((long)obj.get("x"));
		y = (int)((long)obj.get("y"));
	}
	
	public String getname(){
		return this.name;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	@SuppressWarnings("unchecked")
	public void saveSavegame() {
		
		JSONObject saves = new JSONObject();
		
		saves.put("name", name);
		saves.put("x", x);
		saves.put("y", y);
		

		
		JSONDecoder.saveData("Savegames/savegame.json", saves);
	}
}
