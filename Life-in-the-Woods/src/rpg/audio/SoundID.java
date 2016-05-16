package rpg.audio;

public enum SoundID {
    //template      (   id, name,           vol),
     
    //Music
    intro           (   1,  "intro",        2),
    world1          (   2,  "athletic",     2),

     
    //Effects
    brickeffect     (   3,  "brick",        1),
    click     		(   4,  "click",         1),
    coineffect      (   5,  "coin",         1),
    coin_appear     (   6,  "coin_appear",  1),
    editor_menu     (   7,  "editor_menu",  1),
    akbar           (   8,  "akbar",        1);
	
     
    private int id;
    private String prefix = "res/Sound/";
    private String suffix = ".wav";
    private String name;
    // musicvol // effectvol // mastervol//
    private int[] volList = { 6, 6, 6};
    private int vol;
     
    SoundID(int id, String name, int vol) {
        this.id =id;
        this.name = name;
         
         
        for(int i = 0; i < volList.length; i++) {
            if(vol == i) {
                this.vol = volList[i];
            }
        }
    }
     
    public int getID() {
        return this.id;
    }
    public  String getPath() {
        return this.prefix + this.name + this.suffix;
    }
    public int getVolume() {
        return this.vol;
    }
}