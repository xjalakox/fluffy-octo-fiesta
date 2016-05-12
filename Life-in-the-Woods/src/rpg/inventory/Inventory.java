package rpg.inventory;

import java.io.File;

import org.json.simple.JSONObject;

import rpg.inventory.items.*;
import rpg.json.JSONDecoder;

public class Inventory {
	private Item[] items;
	private int maxSlots, currentSlots;

	public Inventory() {
		maxSlots = 63;
		items = new Item[63];
		currentSlots = 0;
		loadInventory();
	}

	private void loadInventory() {
		JSONObject obj = JSONDecoder.loadInventory("res/Savegames/inventory.json");
		for (int i = 0; i < items.length; i++) {
			JSONObject slot = (JSONObject) obj.get(String.valueOf(i));
			if((int)slot.get("ID") == 0) items[i] = new Himbeere((int) slot.get("Anzahl"));
			if((int)slot.get("ID") == 1) items[i] = new Kirsche((int) slot.get("Anzahl"));
			if((int)slot.get("ID") == 4) items[i] = new test1((int) slot.get("Anzahl"));
			if((int)slot.get("ID") == 5) items[i] = new test2((int) slot.get("Anzahl"));
			if((int)slot.get("ID") == 6) items[i] = new test3((int) slot.get("Anzahl"));
			
			System.out.println(slot.get("ID"));
			System.out.println(slot.get(slot.get("Anzahl")));
		}
	}

	public void add(Item i) {

	}

	public void remove(int index) {

	}

}
