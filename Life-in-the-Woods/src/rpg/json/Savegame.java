package rpg.json;

import org.json.simple.JSONObject;


public class Savegame {

	private String name;
	private int x, y, xp, leben, mana, def, dmg, dodge;
	private int level = 1;

	public Savegame() {
		loadSavegame();
	}

	private void loadSavegame() {
		JSONObject obj = JSONDecoder.loadData("Savegames/savegame.json");

		name = (String) (obj.get("name"));
		x = (int) ((long) obj.get("x"));
		y = (int) ((long) obj.get("y"));
		xp = (int) ((long) obj.get("xp"));
		leben = (int) ((long) obj.get("leben"));
		mana = (int) ((long) obj.get("mana"));
		def = (int) ((long) obj.get("def"));
		dmg = (int) ((long) obj.get("dmg"));
		dodge = (int) ((long) obj.get("dodge"));
		
		System.out.println(getLevel());

	}
	
	public int getLevel() {
		int i = 83;
		while(xp - i >= 0){
			level++;
			i*=1.111111111;
			System.out.println("Level: " + level + " XP: " + i);
		}
		return level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public String getname() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setname(String name) {
		this.name = name;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@SuppressWarnings("unchecked")
	public void saveSavegame() {

		JSONObject saves = new JSONObject();

		saves.put("name", name);
		saves.put("x", x);
		saves.put("y", y);
		saves.put("xp",xp);
		saves.put("leben", leben);
		saves.put("mana", mana);
		saves.put("def", def);
		saves.put("dmg",dmg);
		saves.put("dodge",dodge);

		JSONDecoder.saveData("Savegames/savegame.json", saves);
	}
}
