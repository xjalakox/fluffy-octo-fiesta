package rpg.inventory.items;

import rpg.inventory.Item;
import rpg.inventory.Item.ItemTyp;

public class Himbeere extends Item {

	public Himbeere(int anzahl) {
		super("Himbeere", 0, ItemTyp.FOOD, anzahl);
	}

}
