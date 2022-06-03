package flappybird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
	
	private static int lastKeyCode = -1;
	public static boolean jumpButtonPressed;
	public static boolean resetButtonPressed;

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(lastKeyCode == e.getKeyCode()) {
			jumpButtonPressed = false;
			return;
		}
		
		if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
			resetButtonPressed = true;
		}
		
		if (lastKeyCode == -1 || lastKeyCode != e.getKeyCode()) {
			lastKeyCode = e.getKeyCode();
			if(lastKeyCode == 32)
				jumpButtonPressed = true;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 32) {
			jumpButtonPressed = false;
		}
		
		if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
			resetButtonPressed = false;
		}
		lastKeyCode = -1;
	}
}
