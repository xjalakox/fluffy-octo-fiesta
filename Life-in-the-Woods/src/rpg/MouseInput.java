package rpg;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseBtn = MouseEvent.NOBUTTON;
	private static int mouseWheel = 0;
	
	public static int getX() {
		return mouseX;
		//System.out.println("christian ist gay");
	}
	
	public static int getY() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseBtn;
	}
	
	public static int getWheelRotation() {
		return mouseWheel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseBtn = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseBtn = MouseEvent.NOBUTTON;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		mouseWheel = (int) e.getPreciseWheelRotation();
	}

	public static void setWheelRotation(int i) {
		mouseWheel = i;		
	}

}
