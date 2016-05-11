package rpg;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
 
public class SaveGame {
     
    private Scanner scan;
    private File savegame = new File("res/Savegames/savegame.txt");
     
    private String name;
    
    private int x, y;
     
     
     
    public SaveGame() {
        if(savegame.exists()) {
            try {
                scan = new Scanner(savegame);
                read();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            setToDefault();
            System.out.println("SetToDefault() Amina");
        }
         
    }
     
    public void read() {
        if(scan.hasNext()){
            try{
                this.name = scan.next();
                this.x = scan.nextInt();
                this.y = scan.nextInt();
            } catch (Exception e) {
                //setToDefault();
            }
        } else {
            setToDefault();
        }
    }
     
    public void write() {
        try {
            Formatter formatter = new Formatter(savegame);
            formatter.format
            ("%s %n%s %n%s" ,name,x,y);
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public void setToDefault() {
         
        this.name = "test";
                                 
        write();
    }
 
    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
        write();
    }

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
		write();
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
		write();
	}
     
}