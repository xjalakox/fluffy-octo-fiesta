package rpg;

import rpg.audio.SoundManager;

public class Handler {

	
	public SoundManager manager;
	public static SaveGame g;
	public Texts texts;
	
	public Handler() {
		manager = new SoundManager();
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
		if(!Game.DEBUG) manager.fadeInSound(newMusicID);
	}
}
