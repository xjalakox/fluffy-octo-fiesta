package rpg.inventory.items;

import rpg.inventory.Item;
import rpg.inventory.Item.ItemTyp;

public class test1 extends Item {
	
	public test1(int anzahl) {
		super("test1", 4, ItemTyp.FOOD, anzahl);
	}
}
