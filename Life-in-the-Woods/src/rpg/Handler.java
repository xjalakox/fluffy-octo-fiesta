package rpg;

import rpg.audio.SoundManager;
import rpg.json.Savegame;

public class Handler {

	
	public static SoundManager manager = new SoundManager();
	public static Savegame g = new Savegame();
	
	public Handler() {
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
