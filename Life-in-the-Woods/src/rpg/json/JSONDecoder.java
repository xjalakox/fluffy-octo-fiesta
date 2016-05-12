package rpg.json;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONDecoder {
	
	public static JSONObject loadMapData(String file) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
			return obj;
		} catch (Exception e) {
			System.out.println("Konnte nicht parsen");
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject loadInventory(String file) {
		JSONParser parser = new JSONParser();
		try {
			return (JSONObject) parser.parse(new FileReader(file));
		} catch(Exception e) {
			System.out.println("Inv nicht geladen!");
			e.printStackTrace();
		}
		return null;
	}
	
}
