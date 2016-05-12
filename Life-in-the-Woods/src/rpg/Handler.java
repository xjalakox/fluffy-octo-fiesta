package rpg;

import rpg.Saves.Inventory;
import rpg.Saves.SaveGame;
import rpg.Saves.Texts;
import rpg.audio.SoundManager;

public class Handler {

	
	public static SoundManager manager = new SoundManager();
	public static SaveGame g;
	public static Inventory inv = new Inventory();
	public Texts texts;
	
	public Handler() {
		g = new SaveGame();
		texts = new Texts();
	}
	
	public void ChangeMusic(int newMusicID, int oldMusicID, boolean running){
		if(oldMusicID==0){
			manager.playSound(newMusicID);
		}else if(oldMusicID==newMusicID&&!running){
			manager.playSound(newMusicID);
		}else if(oldMusicID==newMusicID){
			
		}else{
			manager.stopSound(oldMusicID);
			manager.playSound(newMusicID);
		}
		//if(!Game.DEBUG) manager.fadeInSound(newMusicID);
	}
}
