package rpg.fight;

public class Fight {
	int leben,def,dmg,dodge,xp;

	public Fight(int leben, int def, int dmg, int dodge, int xp){
		this.leben = leben;
		this.def = def;
		this.dmg = dmg;
		this.dodge = dodge;
		this.xp = xp;
	}

	public int getLeben() {
		return leben;
	}

	public void setLeben(int leben) {
		this.leben = leben;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	
	public void giveXp(int xp) {
		
	}
	
	public void giveBounty(int bountyid, int bountyquantity){
		
	}
}
