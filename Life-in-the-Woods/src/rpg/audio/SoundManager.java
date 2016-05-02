package rpg.audio;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
 
public class SoundManager {
     
    private Clip[] clipList = new Clip[10]; 
    private Sound[] soundList = new Sound[10];
    private FloatControl[] gainControl = new FloatControl[10];
     
    public void playSound(int id) {
        try {
            soundList[id] = new Sound(id);
            clipList[id] = AudioSystem.getClip();
            clipList[id].open(AudioSystem.getAudioInputStream(new File(soundList[id].getPath())));
            gainControl[id] = 
            (FloatControl) clipList[id].getControl(FloatControl.Type.MASTER_GAIN);
            gainControl[id].setValue(soundList[id].getVolume());
            clipList[id].start();
          
        } catch (Exception e) {}
    }
     
    public void stopSound(int id) {
        try {
            clipList[id].stop();
        } catch(Exception ex) {} 
    }
    
    public void fadeInSound(int id){
    	for(int i=0;i<=20;i++){
    		gainControl[id].setValue(soundList[id].getVolume()+i-20);
    		try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void fadeOutSound(int id){
    	for(int i=0;i<=20;i++){
    		gainControl[id].setValue(soundList[id].getVolume()-i);
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }

    ///// PRIVATE INNER CLASS /////
    private class Sound {
         
        private String path;
        private int vol;
         
        public Sound(int id) {
            for(SoundID s: SoundID.values()) {
                if(id == s.getID()) {
                    this.path = s.getPath();
                    this.vol = s.getVolume();
                }
            }
        }
        public String getPath() {
            return this.path;
        }
        public int getVolume() {
            return this.vol;
        }
		public void setVolume(int vol) {
            this.vol = vol;
        }
    }
    
 
     
    public void setVolume(int id, float volume) {
        gainControl[id].setValue(volume);
    }
     
}