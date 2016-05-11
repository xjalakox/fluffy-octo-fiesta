package rpg.gui;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import rpg.KeyInput;

public class Gui {
	
	private List<GuiElement> elements = new ArrayList<GuiElement>();
	
	public Gui(){
	}
	
	public void addGuiElement(GuiElement element) {
		elements.add(element);
	}
	
	public void removeGuiElement(GuiElement element){
		elements.remove(element);
	}

	public void render(Graphics g) {
		for(GuiElement element : elements) {
			if(element.isVisible()) {
				element.render(g);
			}
		}
	}
	
	public void tick() {
		for(GuiElement element : elements) {
			if(element.isVisible()) {
				element.tick();
			}
		}
		
	}

	public void init() {
		try {
			GuiMap map = new GuiMap(230, 20, 1220, 930);
			map.setVisible(false);
			addGuiElement(map);
			
			GuiButton inventory = new GuiButton(230, 20, ImageIO.read(new File("res/Inventory/inv.png")), new GuiAction() {
				public void action() {

				}
			});
			inventory.setVisible(false);
			addGuiElement(inventory);
			
			GuiButton player = new GuiButton(1100, 290, ImageIO.read(new File("res/Inventory/player.png")), new GuiAction() {
				public void action() {

				}
			});
			player.setVisible(false);
			addGuiElement(player);
			
			GuiButton cancel_button = new GuiButton(1750, 20, ImageIO.read(new File("res/Buttons/cancel.png")), new GuiAction() {
				public void action() {
					System.exit(0);
				}
			});
			cancel_button.setVisible(false);
			addGuiElement(cancel_button);
			
			GuiButton health_button = new GuiButton(20, 950, ImageIO.read(new File("res/Buttons/health.png")), new GuiAction() {
				public void action() {
					
				}
			});
			addGuiElement(health_button);
			
			
			GuiButton menu_button = new GuiButton(1770, 930, ImageIO.read(new File("res/Buttons/menu.png")), new GuiAction() {
				public void action() {
					
				}
			});
			addGuiElement(menu_button);
			
			GuiButton inventory_button = new GuiButton(1620, 930, ImageIO.read(new File("res/Buttons/inventory.png")), new GuiAction() {
				public void action() {
					inventory.setVisible(!inventory.isVisible());
					player.setVisible(!player.isVisible());
				}
			});
			addGuiElement(inventory_button);
			
			GuiButton quest_button = new GuiButton(1460, 935, ImageIO.read(new File("res/Buttons/quest.png")), new GuiAction() {
				public void action() {
					
				}
			});
			addGuiElement(quest_button);
			
			GuiButton map_button = new GuiButton(30, 840, ImageIO.read(new File("res/Buttons/map_button.png")), new GuiAction() {
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
}
