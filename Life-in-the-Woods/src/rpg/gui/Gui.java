package rpg.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import rpg.MouseInput;

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
}
