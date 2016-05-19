package rpg.entity;

import rpg.Game;
import rpg.Id;
import rpg.gui.TextDraw;
import rpg.json.Texts;
import rpg.level.Level;

public class Schmied extends npc {

	public Schmied(int x, int y, int w, int h,int SpriteId, Level l) {
		super(x, y, w, h,SpriteId, Id.blacksmith, "Schmied", l,Game.quests.quests[0]);
		texte = Texts.texte[0];
	}

	public void talk() {
		TextDraw.drawText(texte.getTexte()[texte.getTextId()]);
	}
	
}
