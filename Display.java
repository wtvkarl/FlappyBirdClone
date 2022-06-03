package flappybird;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;


/**
 * NOTE: WHEN EXPORTING JAR FILES, ANY GRAPHICAL IMAGES NEED TO BE IN A FOLDER 
 * 		 ALONGSIDE THE JAR FILE OR ELSE JAVA CANNOT ACCESS THE IMAGES
 * */

public class Display extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;
		
	private static boolean running;
	private static Thread gameThread;
		
	private Game game;
	private InputHandler ih;
	
	public Display() {
		ih = new InputHandler();
		this.addKeyListener(ih);
		this.setPreferredSize(new Dimension(400,600));
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		game = new Game();
	}
	
	public void startGameThread() {
		running = true;
		gameThread = new Thread(this, "gameThread");
		gameThread.start();
	}
	
	public void stopGameThread() {
		running = false;
		try {
			gameThread.join();
		}
		catch(InterruptedException ie) {}
	}

	@Override
    public void run() {
        int targetUpdates = 60;
        double nsPerSecond = 1_000_000_000.0;
        double drawInterval = nsPerSecond/targetUpdates;
        double delta = 0;
        long then = System.nanoTime();
        long now;
        boolean shouldRender = false;
        
        while(running){
            now = System.nanoTime();
            delta += (now - then) / drawInterval;
            then = now;
            
            if(delta >= 1){
                update();
                delta--;
                shouldRender = true;
            }
            
            if(shouldRender){
                repaint();
                shouldRender = false;
            }
            
        }
    }
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)(g);
		game.draw(g2d);
	}
	
	public void update() {
		game.update();
	}
}
