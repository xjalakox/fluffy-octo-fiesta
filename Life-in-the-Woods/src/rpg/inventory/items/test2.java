package rpg.inventory.items;

import rpg.inventory.Item;
import rpg.inventory.Item.ItemTyp;

public class test2 extends Item {

	public test2(int anzahl) {
		super("test2", 5, ItemTyp.FOOD, anzahl);
	}

}
