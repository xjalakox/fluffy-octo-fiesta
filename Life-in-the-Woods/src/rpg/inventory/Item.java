package rpg.inventory;

public abstract class Item {
	
	public enum ItemTyp {
		FOOD,
		EQUIP,
		QUEST
	}
	
	
	private String name;
	private int img_id;
	private int anzahl;
	private ItemTyp typ;
	
	public Item(String name, int id, ItemTyp typ, int anzahl) {
		this.name = name;
		this.img_id = id;
		this.typ = typ;
		this.anzahl = anzahl;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ItemTyp getTyp() {
		return this.typ;
	}
	
	public int getImgId() {
		return this.img_id;
	}
 }
