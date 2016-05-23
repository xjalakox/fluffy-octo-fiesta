package rpg.entity;

import static rpg.Handler.g;

import rpg.Game;
import rpg.Handler;
import rpg.Id;
import rpg.fight.Fight;
import rpg.gui.TextDraw;
import rpg.json.Texts;
import rpg.level.Level;

public class test extends npc {
	private Fight fight;

	public test(int x, int y, int w, int h,int SpriteId, Level l,Fight fight) {
		super(x, y, w, h, SpriteId,Id.test, "Schmied", l,Game.quests.quests[0]);
		texte = Texts.texte[0];
		this.fight = fight;	
	}

	public void talk() {
		TextDraw.drawText(texte.getTexte()[texte.getTextId()]);
	}
	
	public void startfight() {
		System.out.println(g.getLevel());
		
		Handler.g.setXp(g.getXp()+fight.getXp());
		g.saveSavegame();
	}
	
}
