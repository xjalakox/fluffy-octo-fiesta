package rpg.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Texts {
	
	public static Text[] texte;
	private String[] namen;
	
	public Texts() {
		texte = new Text[50];
		namen = new String[] {
				"Schmied", "Oma"
		};
		loadTexts();
		saveTexts();
	}
	
	private void loadTexts() {
		
		int d = 0;
		
		for(String name : namen) {
			int textid = 0;
			String[] texts = new String[50];
			JSONObject decode = JSONDecoder.loadData("res/Savegames/texts.json");
			JSONObject textObj = (JSONObject) decode.get(name);
			textid = (int)((long)textObj.get("id"));
			if(textObj != null) {
				JSONArray text = (JSONArray) textObj.get("text");
				for(int i = 0; i < text.size(); i++) {
					if(text.get(i) == null) continue;
					texts[i] = (String) text.get(i);
				}
			}
			texte[d++] = new Text(texts, textid);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void saveTexts() {
		int d = 0;
		JSONObject saves = new JSONObject();
		for(String name : namen) {
			JSONObject person = new JSONObject();
			JSONArray texts = new JSONArray();
			for(int i = 0; i < texte[d].getTexte().length; i++) {
				if(texte[d].getText(i) == null) continue;
				texts.add(texte[d].getText(i));
			}
			
			person.put("text", texts);
			person.put("id", texte[d].getTextId());
			saves.put(name, person);
			d++;
		}
		JSONDecoder.saveData("res/Savegames/texts.json", saves);
	}
}
