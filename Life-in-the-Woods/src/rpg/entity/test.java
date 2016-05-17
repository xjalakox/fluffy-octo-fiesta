package rpg.entity;

import rpg.Game;
import rpg.Id;
import rpg.gui.TextDraw;
import rpg.json.Texts;
import rpg.level.Level;

public class test extends npc {

	public test(int x, int y, int w, int h, Level l) {
		super(x, y, w, h, Id.test, "Schmied", l,Game.quests.quests[0]);
		texte = Texts.texte[0];
	}

	public void talk() {
		TextDraw.drawText(texte.getTexte()[texte.getTextId()]);
	}
	
}
