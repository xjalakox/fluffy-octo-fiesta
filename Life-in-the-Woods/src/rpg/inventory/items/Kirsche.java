package rpg.inventory.items;

import rpg.inventory.Item;

public class Kirsche extends Item {

	public Kirsche(int anzahl) {
		super("Kirsche", 1, ItemTyp.FOOD, anzahl);
	}

	public void action() {

	}
}
