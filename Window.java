package flappybird;

import javax.swing.JFrame;

public class Window {
	
	public Window() {
		ImageUtils.preloadAssets();
		
		JFrame window = new JFrame("Flappy Bird");
		Display display = new Display();
		window.setIconImage(ImageUtils.BIRD);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(display);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		display.startGameThread();

	}
	
}
