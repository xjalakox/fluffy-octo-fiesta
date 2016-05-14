package rpg.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public static void saveInventory(String file, JSONObject obj) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write(obj.toJSONString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("Inv konnte nicht gespeichert werden!");
			e.printStackTrace();
		}
	}
	
}
