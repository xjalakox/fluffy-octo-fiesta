package rpg.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MenuInput implements KeyListener {
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}
	
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
