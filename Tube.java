package flappybird;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Tube {
	
	public int centerX, centerY;
	public Rectangle topTube, bottomTube;
	public int tubeWidth = 70;
	public int gapWidth = 160;
	
	public boolean counted;
	
	private static int scrollSpeed;
	
	public Tube(int cX, int cY) {
		scrollSpeed = 3;
		centerX = cX;
		centerY = cY;
		topTube = new Rectangle(centerX - tubeWidth/2, 0, tubeWidth, centerY - gapWidth/2);
		bottomTube = new Rectangle(centerX - tubeWidth/2, centerY + gapWidth/2, tubeWidth, 600 - (centerY + gapWidth/2));
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ImageUtils.TOPTUBE, centerX - tubeWidth/2, (centerY - gapWidth/2) - 400, tubeWidth, 400, null);
		g2d.drawImage(ImageUtils.BOTTOMTUBE, centerX - tubeWidth/2, centerY + gapWidth/2, tubeWidth, 400, null);
	}
	
	public void update(Bird bird) {
		centerX -= scrollSpeed;
		topTube = new Rectangle(centerX - tubeWidth/2, 0, tubeWidth, centerY - gapWidth/2);
		bottomTube = new Rectangle(centerX - tubeWidth/2, centerY + gapWidth/2, tubeWidth, 600 - (centerY + gapWidth/2));
	
		if(bird.bounds.intersects(topTube) || bird.bounds.intersects(bottomTube)) {
			bird.dead = true;
			scrollSpeed = 0;
		}
	}
	
}
