package rpg.menu;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class CustomCursor {
	
	public static BufferedImage cursorImage;
	public static Cursor cursor;
	
	public CustomCursor(String CursorName){
		 try {
             cursorImage = ImageIO.read(CustomCursor.class.getResourceAsStream("/Cursor/" + "cursor_" + CursorName + ".png"));
             for (int i = 0; i < cursorImage.getHeight(); i++) {
                 int[] rgb = cursorImage.getRGB(0, i, cursorImage.getWidth(), 1, null, 0, cursorImage.getWidth() * 4);
                 for (int j = 0; j < rgb.length; j++) {
                     int alpha = (rgb[j] >> 24) & 255;
                     if (alpha < 250) {
                         alpha = 0;
                     } else {
                         alpha = 255;
                     }
                     rgb[j] &= 0x00ffffff;
                     rgb[j] = (alpha << 24) | rgb[j];
                 }
                 cursorImage.setRGB(0, i, cursorImage.getWidth(), 1, rgb, 0,
                         cursorImage.getWidth() * 4);
             }
             cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "CustomCursor");
             
             

         } catch (Exception exp) {
             exp.printStackTrace();
         }
	}
}
