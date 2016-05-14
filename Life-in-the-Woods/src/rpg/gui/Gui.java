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

			/*GuiButton inventory = new GuiButton(230, 20, 0, 0, ImageIO.read(new File("res/Inventory/inv.png")),
					new GuiAction() {
						public void action() {

							Point p = MouseInfo.getPointerInfo().getLocation();
							int x = p.x;
							int y = p.y;

							Point points[] = new Point[63];

							int z = 0;

							for (int i = 0; i < 9; i++) {
								for (int j = 0; j < 7; j++) {
									points[z] = new Point(300 + j * 80, 90 + i * 80);
									z++;
								}
							}
							for (int i = 0; i < points.length; i++) {
								if (x >= points[i].getX() && x <= points[i].getX() + 75 && y >= points[i].getY()
										&& y <= points[i].getY() + 74) {

								} else {

								}
							}
						}
					});
			inventory.setVisible(false);
			addGuiElement(inventory);*/
			
			GuiInventory inventory = new GuiInventory(230, 20, 1220, 930);
			inventory.setVisible(false);
			addGuiElement(inventory);
			 
			/*
			int b = 0;
			int c = 0;

			for (int i = 0; i < items.length; i++) {
				int a = inv.getItem(i);
				if (a == 0)
					continue;
				items[i] = new GuiButton(300 + b * 80, 90 + c * 80, 75, 74, Game.inv_items[a].getBufferedImage(),
						new GuiAction() {
							public void action() {
								System.out.println(a);
							}
						});
				b++;
				if (b == 7) {
					b = 0;
					c += 1;
				}
				items[i].setVisible(false);
				addGuiElement(items[i]);
			}
*/
			GuiButton cancel_button = new GuiButton(1750, 20, 0, 0, ImageIO.read(new File("res/Buttons/cancel.png")),
					new GuiAction() {
						public void action() {
							System.exit(0);
						}
					});
			cancel_button.setVisible(false);
			addGuiElement(cancel_button);

			GuiButton health_button = new GuiButton(20, 950, 0, 0, ImageIO.read(new File("res/Buttons/health.png")),
					new GuiAction() {
						public void action() {

						}
					});
			addGuiElement(health_button);

			GuiButton menu_button = new GuiButton(1770, 930, 0, 0, ImageIO.read(new File("res/Buttons/menu.png")),
					new GuiAction() {
						public void action() {

						}
					});
			addGuiElement(menu_button);

			GuiButton inventory_button = new GuiButton(1620, 930, 0, 0,
					ImageIO.read(new File("res/Buttons/inventory.png")), new GuiAction() {
						public void action() {
							inventory.setVisible(!inventory.isVisible());
							inventory.updateInventory();
						}
					});
			addGuiElement(inventory_button);

			GuiButton quest_button = new GuiButton(1460, 935, 0, 0, ImageIO.read(new File("res/Buttons/quest.png")),
					new GuiAction() {
						public void action() {

						}
					});
			addGuiElement(quest_button);

			GuiButton map_button = new GuiButton(30, 840, 0, 0, ImageIO.read(new File("res/Buttons/map_button.png")),
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
}
