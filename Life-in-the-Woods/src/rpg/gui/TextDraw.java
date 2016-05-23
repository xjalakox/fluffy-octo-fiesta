package rpg.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import rpg.Game;

public class TextDraw {

	private static String text = "";
	private static String draw = "";
	private static String draw2 = "";

	private static Image scrolltext_bg;
	private ImageObserver observer;
	private Boolean test = false;

	private int cur = 0;
	private Color textc = new Color(138, 60, 34);
	
	public static void drawText(String string) {
		try {
			scrolltext_bg = ImageIO.read(TextDraw.class.getResourceAsStream("/Scrolltext/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		text = string;
	}

	private int time = 0;
	private boolean drawing;

	public void render(Graphics g) {
		if (drawing) {
			g.drawImage(scrolltext_bg, 875, 740, observer);
			g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
			g.setColor(textc);
			if (test) {
				g.drawString(draw2, 900, 800);
			}
			g.drawString(draw, 900, 850);
		}
	}

	public void tick() {
		if (!text.isEmpty() && text.length() > cur) {
			time++;
			drawing = true;
			if (time % 3 == 0) {
				time = 0;
				char c = text.charAt(cur);
				if (c == '$') {
					test = true;
					draw2 = draw;
					draw = "";
				} else {
					draw += c;
				}
				cur++;
			}
		}else if(drawing&&time>59) {
			drawing = false;
			time++;
			text = "";
			cur = 0;
			draw = "";
			draw2 = "";
		}else{
			Game.level.getPlayer().talking_with_npc = false;
			time++;
		}
	}
}
