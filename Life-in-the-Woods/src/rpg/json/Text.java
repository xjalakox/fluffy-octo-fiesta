package rpg.json;

public class Text {
	private int textid;
	private String[] texts;
	
	public Text(String[] texte, int i) {
		this.texts = texte;
		this.textid = i;
	}
	
	public String[] getTexte() {
		return texts;
	}
	
	public int getTextId() {
		return textid;
	}
	
	public String getText(int i) {
		return texts[i];
	}
	
	public void setTextId(int i) {
		textid = i;
	}
}
