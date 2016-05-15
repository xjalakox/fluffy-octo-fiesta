package rpg;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener, FocusListener {

	private boolean[] keyStates;
	public static boolean up;
	public static boolean left;
	public static boolean down;
	public static boolean right;
	public boolean running;
	public boolean escape;
	public boolean coordinate;
	public boolean enterdoor;
	public boolean enterdoor2;
	public boolean enterdoor3;
	public boolean enterdoor4;
	public boolean talk_npc;
	public static boolean inventory;
	public static boolean debug;
	public static boolean key_enable = true;

	public KeyInput() {
		keyStates = new boolean[65536];
	}

	public void tick() {
		if (!down && !left && !right)
			up = keyStates[KeyEvent.VK_UP] || keyStates[KeyEvent.VK_W];
		if (!up && !left && !right)
			down = keyStates[KeyEvent.VK_DOWN] || keyStates[KeyEvent.VK_S];
		if (!up && !down && !right)
			left = keyStates[KeyEvent.VK_LEFT] || keyStates[KeyEvent.VK_A];
		if (!up && !down && !left)
			right = keyStates[KeyEvent.VK_RIGHT] || keyStates[KeyEvent.VK_D];
		debug = keyStates[KeyEvent.VK_J];
		inventory = keyStates[KeyEvent.VK_E];
		running = keyStates[KeyEvent.VK_SHIFT];
		escape = keyStates[KeyEvent.VK_ESCAPE];
		talk_npc = keyStates[KeyEvent.VK_ENTER];
		coordinate = keyStates[KeyEvent.VK_0];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyStates[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyStates[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		for (int i = 0; i < keyStates.length; i++) {
			keyStates[i] = false;
		}
	}
}
