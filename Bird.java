package flappybird;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bird{
	
	public int xPos, yPos;
	public Rectangle bounds;
	
	private static final int SKY_LIMIT = -9;
	private static final int GROUND_LVL = 464;
	private static final int JUMP_POWER = -9;
	private int gravity = 1;
	public int yVel;
	
	public boolean dead;
	
	public Bird(int x, int y) {
		xPos = x;
		yPos = y;
		bounds = new Rectangle(x+5, y, 45, 36);
		yVel = 0;
	}
	
	public void jump() {
		if(!dead)
			yVel = JUMP_POWER;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ImageUtils.BIRD, xPos, yPos, 50, 36, null);
	}
	
	public void update() {
		yVel += gravity;
		yPos += yVel;
		
		if(yPos > GROUND_LVL) {
			yPos = GROUND_LVL;
			yVel = 0;
		}
		
		if(yPos < SKY_LIMIT) {
			yPos = SKY_LIMIT;
			yVel = 0;
		}
		
		bounds = new Rectangle(xPos+5, yPos, 45, 36);
	}
}
