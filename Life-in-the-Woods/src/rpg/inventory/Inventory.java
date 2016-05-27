package rpg.inventory;

import org.json.simple.JSONObject;

import rpg.inventory.items.Himbeere;
import rpg.inventory.items.Kirsche;
import rpg.json.JSONDecoder;

public class Inventory {
	private Item[] items;

	public Inventory() {
		items = new Item[63];
		loadInventory();
	}

	private void loadInventory() {
		JSONObject obj = JSONDecoder.loadData("Savegames/inventory.json");

		for (int i = 0; i < items.length; i++) {
			int slot = i + 1;
			JSONObject item = (JSONObject) obj.get(String.valueOf(slot));
			if(item == null) continue;
			long id = (long) item.get("ID");
			int anzahl = (int)((long)item.get("Anzahl"));
			if (id == 0) {
				items[i] = new Himbeere(anzahl);
			} else if(id == 1) {
				items[i] = new Kirsche(anzahl);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void saveInventory() {
		JSONObject slots = new JSONObject();
		int slot = 1;
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				JSONObject item = new JSONObject();
				item.put("ID", items[i].getImgId());
				item.put("Anzahl", items[i].getAnzahl());
				slots.put(String.valueOf(slot), item);
				slot++;
			}
		}
		JSONDecoder.saveData("Savegames/Inventory.json", slots);
	}

	public void add(Item i) {
		int gefunden = find(i.getImgId());
		if (gefunden != -1) {
			// Anzahl erhöhen
			items[gefunden].erhoeheAnzahl();
		} else {
			// Neu einfügen
			int freeSlot = getFreeSlot();
			if (freeSlot != -1) {
				items[freeSlot] = i;
			}
		}
	}

	public void remove(int index) {

	}

	public int find(int id) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && items[i].getImgId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public Item hasItem(String name) {
		for(int i = 0; i < items.length; i++) {
			if(items[i] != null && items[i].getName().equals(name)) {
				return items[i];
			}
		}
		return null;
	}

	public int getFreeSlot() {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public Item[] getItems() {
		return this.items;
	}
}
