package rpg.json;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONDecoder {
	
	private static String file1 = "res/Maps/map1.json";
	
	public static void main(String[] args) {
		JSONObject map1 = loadMapData(file1);
		
		JSONObject data2 = (JSONObject) ((JSONArray)map1.get("tilesets")).get(1);
		
		System.out.println(data2);
		
		JSONArray test = (JSONArray)((JSONObject)((JSONArray)map1.get("layers")).get(0)).get("data");
		
		for(int i=0;i<test.size();i++){
		//	System.out.println(test.get(i));
		}
		
//		long test = map1.getTilewidth();
//		System.out.println(test);
//		int[] test2 = map1.getLayers();
//		System.out.println(test2[6]);
	}
	
	public static JSONObject loadMapData(String file) {
		//MapData data = null;
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(file));
			return obj;
			//String bgColor = (String) obj.get("backgroundcolor");
			//long height = (long) obj.get("height");
			
			
			//long nextObjId = (long) obj.get("nextobjectid");
			//String orientation = (String) obj.get("orientation");
			//String renderorder = (String) obj.get("renderorder");
			//long tileHeight = (long) obj.get("tileheight");
			
			//Object[] tileSets =((ArrayList)obj.get("tilesets")).toArray();
			
			
			//JSONArray layers = (JSONArray) obj.get("layers");
			//JSONObject objekt = (JSONObject) layers.get(0);
			//Object[] dataArr = ((JSONArray) objekt.get("data")).toArray();
			
			/*Iterator i = layers.iterator();
			
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				System.out.println(innerObj.get("data"));
			}*/
			
			//long tilewidth = (long)obj.get("tilewidth");
			//long version = (long)obj.get("version");
			//long width = (long)obj.get("width");
			
			//data = new MapData(bgColor, height, layers, nextObjId, orientation, renderorder, tileHeight, tileSets, tilewidth, version, width);
		} catch (Exception e) {
			System.out.println("Konnte nicht parsen");
			e.printStackTrace();
		}
		return null;
	}
	
}
