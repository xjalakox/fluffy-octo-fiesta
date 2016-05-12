package rpg.Saves;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
 
public class Inventory {
     
    private Scanner scan;
    private File textFiles = new File("res/Savegames/inventory.txt");

    private int anzahl[] = new int[70];
    
    private int ids[] = new int[70];
    
     
     
     
    public Inventory() {
        if(textFiles.exists()) {
            try {
                scan = new Scanner(textFiles);
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
                for(int i=0;i<=70;i++){
                	this.ids[i] = scan.nextInt();
                }
                for(int i=0;i<=70;i++){
                	this.anzahl[i] = scan.nextInt();
                }
            } catch (Exception e) {
                //setToDefault();
            }
        } else {
            setToDefault();
        }
    }
     
    public void write() {
        try {
            Formatter formatter = new Formatter(textFiles);
            formatter.format("%s", ids[0]);
            for(int i=1;i<70;i++){
            	formatter.format(" %n%s", ids[i]);
            }
            formatter.format("%n%s", anzahl[0]);
            for(int i=1;i<70;i++){
            	formatter.format(" %n%s", anzahl[i]);
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public void setToDefault() {

        this.ids[0] = 0;
        this.ids[1] = 1;
        this.ids[2] = 2;
        
        this.anzahl[0] = 0;
        this.anzahl[1] = 5;
                                 
        write();
    }
    
    public int getItem(int platz){
    	return this.ids[platz];
    }
    
    public void setItem(int platz,int id){
    	this.ids[platz] = id;
    	write();
    }
     
}