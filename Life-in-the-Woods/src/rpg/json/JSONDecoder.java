package rpg.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONDecoder {

	public static JSONObject loadData(String file) {
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader("res/" + file));
			return obj;
		} catch (Exception e) {
			System.out.println("Konnte " + file + " nicht laden");
			e.printStackTrace();
		}
		return null;
	}

	public static void saveData(String file, JSONObject obj) {
		FileWriter fw;
		try {
			fw = new FileWriter("res/" + file);
			fw.write(obj.toJSONString());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			System.out.println("Konnte " + file + " nicht speichern");
			e.printStackTrace();
		}
	}
}
