package rpg.gui;

import static rpg.inventory.Item.ItemTyp.EQUIP;
import static rpg.inventory.Item.ItemTyp.FOOD;
import static rpg.inventory.Item.ItemTyp.QUEST;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import rpg.inventory.Item;

public class GuiMenu extends GuiElement {

	private Item item;
	private List<GuiMenuItem> options;
	
	public GuiMenu(int x, int y, int w, int h, Item i) {
		super(x, y, w, h);
		options = new ArrayList<GuiMenuItem>();
		item = i;
		init();
	}
	
	public void init() {
		if(item.getTyp() == FOOD) {
			options.add(new GuiMenuItem(x + 50, y + 35 + options.size()*50, 80, 50, "Eat", new GuiAction() {
				public void action() {
					//System.out.println("ISS item " + item.getName());
					if(item.getAnzahl() > 0) {
						item.verringereAnzahl();
					}
				}
			}));
			options.add(new GuiMenuItem(x + 50, y + 35 + options.size()*50, 80, 50, "DROP", new GuiAction() {
				public void action() {
					//System.out.println("Drop item " + item.getName());
					if(item.getAnzahl() > 0) {
						item.verringereAnzahl();
					}
				}
			}));
			
		} else if(item.getTyp() == EQUIP) {
			
		} else if(item.getTyp() == QUEST) {
			
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0xd7d7d7));
		int menuX = x + 32,
			menuY = y + 20;
		g.fillRect(menuX, menuY, 130, 230);
		g.setColor(new Color(0x000000));
		for(GuiMenuItem option : options) {
			option.render(g);
		}
	}

	@Override
	public void tick() {
		for(GuiMenuItem option : options) {
			option.tick();
		}
	}

}
