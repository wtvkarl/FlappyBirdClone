package flappybird;

import java.awt.Graphics2D;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 600;
	private static final int GROUND_HEIGHT = 100;
	private static final int MAX_TUBE_HEIGHT = 100;
	private static final int MIN_TUBE_HEIGHT = 400;
	
	private int bgScrollSpeed = 1;
	private int groundScrollSpeed = 3;
	
	private int backgroundX;
	private int groundX;
	
	private ArrayList<Tube> tubes;
	private Bird bird;
	
	private Timer tubeSpawner;
	private TimerTask task;
	
	private int score = 0;
	private int highScore = 0;
	
	public Game() {
		highScore = getHighScore();
		
		backgroundX = 0;
		groundX = 0;
		bird = new Bird(100, 200);
		tubes = new ArrayList();
		
		tubeSpawner = new Timer();
		task = new TimerTask() {
			@Override
			public void run() {
				spawnRandomTube(MIN_TUBE_HEIGHT, MAX_TUBE_HEIGHT);
			}
		};
		tubeSpawner.scheduleAtFixedRate(task, 0, 1250);
	}
	
	private void spawnRandomTube(int min, int max) {
		int cy = (int)((Math.random() * (max - min)) + min);
		tubes.add(new Tube(500, cy));
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(ImageUtils.BACKGROUND, backgroundX, 0, WIDTH, HEIGHT, null);
		if(backgroundX < 0) {
			g2d.drawImage(ImageUtils.BACKGROUND, backgroundX + WIDTH, 0, WIDTH, HEIGHT, null);
		}
		
		for(Tube tube : tubes) {
			tube.draw(g2d);
		}
		
		g2d.drawImage(ImageUtils.GROUND, groundX, 500, WIDTH, GROUND_HEIGHT, null);
		if(groundX < 0) {
			g2d.drawImage(ImageUtils.GROUND, groundX + WIDTH, 500, WIDTH, GROUND_HEIGHT, null);
		}
		
		
		bird.draw(g2d);
		
		g2d.drawString("High Score: " + highScore, 20,20);
		
		if(bird.dead) {
			g2d.drawString("Game Over! Press R to reset.", 125, 50);
			
		}
		else {
			g2d.drawString(score+"", 200, 50);
		}
		
	}
	
	public void update() {
		if(bird.dead) {
			score = 0;
			bgScrollSpeed = 0;
			groundScrollSpeed = 0;
			if(InputHandler.resetButtonPressed) {
				bird.dead = false;
				bgScrollSpeed = 1;
				groundScrollSpeed = 3;
				tubes.clear();;
				
				backgroundX = 0;
				groundX = 0;
				bird = new Bird(100, 200);
				tubes = new ArrayList<Tube>();
			}
			return;
		}
		
		backgroundX -= bgScrollSpeed;
		if(backgroundX <= -400) {
			backgroundX = 0;
		}
		
		groundX -= groundScrollSpeed;
		if(groundX <= -400) {
			groundX = 0;
		}
		
		if(InputHandler.jumpButtonPressed)
			bird.jump();
		
		bird.update();
		
		if(!bird.dead)
			for(Tube tube : tubes) {
				tube.update(bird);
				
				if(!tube.counted && tube.centerX < 50) {
					tube.counted = true;
					score++;
					
					if(score > highScore) {
						highScore = score;
						setHighScore(score);
					}
				}
			}
		
	}
	
	public int getHighScore() {
		String text = "";
		try {
            FileReader reader = new FileReader("score/score.txt");
            char character;
 
            while(Character.isDigit(character = (char)reader.read())) {
            	text += (char)character;
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	
		return (text.isBlank()) ? 0 : Integer.parseInt(text);
	}
	
	public void setHighScore(int newScore) {
		try {
            FileWriter writer = new FileWriter("score/score.txt", false);
            writer.write("");
            writer.write(Integer.toString(newScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
