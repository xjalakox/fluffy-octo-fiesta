package rpg.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rpg.inventory.Inventory;
import rpg.inventory.Item;
import rpg.util.ImageUtils;

public class GuiInventory extends GuiElement {
	
	private BufferedImage inv;
	private BufferedImage player;
	
	private GuiItem[] buttons;
	
	private GuiItem[] equipment;
	
	private Inventory inventory;
	
	private boolean updateInv;
	
	public GuiInventory(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		buttons = new GuiItem[63];
		equipment = new GuiItem[4];
		inventory = new Inventory();
		loadButtons();
		updateInv = false;
		
		inv = ImageUtils.loadImage("res/Inventory/inv.png");
		player = ImageUtils.loadImage("res/Inventory/player.png");
	}
	
	public void loadButtons() {
		Item[] items = inventory.getItems();
		int x = 0,
			y = 0;
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null||items[i].getAnzahl()==0)continue;
			buttons[i] = new GuiItem(300 + x * 81, 90 + y * 80, items[i]);
			buttons[i].setVisible(this.isVisible());
			x++;
			if(x == 7) {
				y++;
				x = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(inv, x, y, w, h, null);
		g.drawImage(player, x + 880, y + 270, 185, 346, null);
		
		for(GuiItem btn : buttons) {
			if(btn == null) continue;
			btn.render(g);
		}
		for(GuiItem btn : buttons) {
			if(btn == null || !btn.renderMenu) continue;
			btn.menu.render(g);
		}
		
		for(GuiItem equip : equipment) {
			if(equip == null) continue;
			equip.render(g);
		}
		
	}

	@Override
	public void tick() {
		for(GuiItem btn : buttons) {
			if(btn == null) continue;
			btn.tick();
			
		}
		if(updateInv) {
			loadButtons();
			updateInv = false;
		}
		inventory.saveInventory();
	}
	
	public void updateInventory() {
		updateInv = true;
	}

}
