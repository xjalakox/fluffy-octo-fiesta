package rpg.gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import rpg.KeyInput;
import rpg.inventory.Inventory;

public class Gui {

	private List<GuiElement> elements = new ArrayList<GuiElement>();

	public Gui() {
	}

	public void addGuiElement(GuiElement element) {
		elements.add(element);
	}

	public void removeGuiElement(GuiElement element) {
		elements.remove(element);
	}

	public void render(Graphics g) {
		for (GuiElement element : elements) {
			if (element.isVisible()) {
				element.render(g);
			}
		}
	}

	public void tick() {
		for (GuiElement element : elements) {
			if (element.isVisible()) {
				element.tick();
			}
		}

	}

	public void init() {
		try {
			GuiMap map = new GuiMap(230, 20, 1220, 930);
			map.setVisible(false);
			addGuiElement(map);
			
			GuiInventory inventory = new GuiInventory(230, 20, 1220, 930);
			inventory.setVisible(false);
			addGuiElement(inventory);

			GuiButton quest_bg = new GuiButton(0, 0, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Gui/quest_bg.png")),
					new GuiAction() {
						public void action() {
						}
					});
			quest_bg.setVisible(false);
			addGuiElement(quest_bg);
			
			GuiButton cancel_button = new GuiButton(1750, 20, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Buttons/cancel.png")),
					new GuiAction() {
						public void action() {
							System.exit(0);
						}
					});
			cancel_button.setVisible(false);
			addGuiElement(cancel_button);

			GuiButton health_button = new GuiButton(20, 950, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Buttons/health.png")),
					new GuiAction() {
						public void action() {

						}
					});
			addGuiElement(health_button);

			GuiButton menu_button = new GuiButton(1770, 930, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Buttons/menu.png")),
					new GuiAction() {
						public void action() {

						}
					});
			addGuiElement(menu_button);

			GuiButton inventory_button = new GuiButton(1620, 930, 0, 0,	ImageIO.read(Gui.class.getResourceAsStream("/Buttons/inventory.png")), new GuiAction() {
						public void action() {
							inventory.setVisible(!inventory.isVisible());
							inventory.updateInventory();
						}
					});
			addGuiElement(inventory_button);

			GuiButton quest_button = new GuiButton(1460, 935, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Buttons/quest.png")),
					new GuiAction() {
						public void action() {
							quest_bg.setVisible(!quest_bg.isVisible());
						}
					});
			addGuiElement(quest_button);

			GuiButton map_button = new GuiButton(30, 840, 0, 0, ImageIO.read(Gui.class.getResourceAsStream("/Buttons/map_button.png")),
					new GuiAction() {
						public void action() {
							map.setVisible(!map.isVisible());
							KeyInput.key_enable = !KeyInput.key_enable;
						}
					});
			addGuiElement(map_button);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Inventory getInventory() {
		for(GuiElement element : elements) {
			if(element instanceof GuiInventory) {
				return ((GuiInventory)element).getInv();
			}
		}
		return null;
	}
}
