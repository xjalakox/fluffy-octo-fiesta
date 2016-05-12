package rpg.Saves;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
 
public class Texts {
     
    private Scanner scan;
    private File textFiles = new File("res/Savegames/texts.txt");

    private String text[] = new String[250];
    
     
     
     
    public Texts() {
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
                for(int i=0;i<=250;i++){
                	this.text[i] = scan.nextLine();
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
            formatter.format("%s", text[0]);
            for(int i=1;i<250;i++){
            	formatter.format(" %n%s", text[i]);
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    public void setToDefault() {

        this.text[0] = "a";
        this.text[1] = "b";
        this.text[2] = "c";
                                 
        write();
    }
    
    public String getText(int id){
    	return this.text[id];
    }
    
    public void setTest(int id,String text){
    	this.text[id] = text;
    	write();
    }
     
}