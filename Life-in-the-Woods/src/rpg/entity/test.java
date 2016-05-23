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
	private int Bounty, Bounty_amount;
	private boolean won;

	public test(int x, int y, int w, int h, int SpriteId, Level l, Fight fight) {
		super(x, y, w, h, SpriteId, Id.test, "Schmied", l, Game.quests.quests[0]);
		texte = Texts.texte[0];
		this.fight = fight;
	}

	public void talk() {
		TextDraw.drawText(texte.getTexte()[texte.getTextId()]);
	}

	public void startfight() {
		boolean fighting = true;

		Bounty = 1;
		Bounty_amount = 10;

		while (fighting) {
			if (g.getDodge() >= fight.getDodge()) {
				if (fight.getDef() < g.getDmg()) {
					fight.setLeben(fight.getLeben() - (g.getDmg() - fight.getDef()));
					System.out.println("Leben des Gegners: " + fight.getLeben());
				} else {
					fight.setLeben(fight.getLeben() - 1);
					System.out.println("Leben des Gegners: " + fight.getLeben());
				}

				if (fight.getLeben() <= 0) {
					won = true;
					System.out.print("Kampf vorbei!");
					fighting = false;
				}

				if (g.getDef() < fight.getDmg()) {
					g.setLeben(g.getLeben() - (fight.getDmg() - g.getDef()));
					System.out.println("Leben des Spielers: " + g.getLeben());
				} else {
					g.setLeben(g.getLeben() - 1);
					System.out.println("Leben des Spielers: " + g.getLeben());
				}

				if (g.getLeben() <= 0) {
					won = false;
					System.out.print("Kampf vorbei!");
					fighting = false;
				}

			} else {
				
				if (g.getDef() < fight.getDmg()) {
					g.setLeben(g.getLeben() - (fight.getDmg() - g.getDef()));
					System.out.println("Leben des Spielers: " + g.getLeben());
				} else {
					g.setLeben(g.getLeben() - 1);
					System.out.println("Leben des Spielers: " + g.getLeben());
				}

				if (g.getLeben() <= 0) {
					won = false;
					g.setLeben(100);
					System.out.print("Kampf vorbei!");
					fighting = false;
				}

				if (fight.getDef() < g.getDmg()) {
					fight.setLeben(fight.getLeben() - (g.getDmg() - fight.getDef()));
					System.out.println("Leben des Gegners: " + fight.getLeben());
				} else {
					fight.setLeben(fight.getLeben() - 1);
					System.out.println("Leben des Gegners: " + fight.getLeben());
				}

				if (fight.getLeben() <= 0) {
					won = true;
					System.out.print("Kampf vorbei!");
					fighting = false;
				}
			}
		}

		if (won) {
			System.out.println("gewonnen");
			g.saveSavegame();
		} else {
			System.out.println(" verloren");
		}
	}

}
