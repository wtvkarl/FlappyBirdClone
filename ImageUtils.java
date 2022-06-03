package flappybird;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static BufferedImage BACKGROUND;
	public static BufferedImage GROUND;
	public static BufferedImage BIRD;
	public static BufferedImage TOPTUBE;
	public static BufferedImage BOTTOMTUBE;
	
	
	public static void preloadAssets() {
		try {
			BACKGROUND = ImageIO.read(new File("res/background.png"));
			GROUND = ImageIO.read(new File("res/ground.png"));
			BIRD = ImageIO.read(new File("res/bird.png"));
			TOPTUBE = ImageIO.read(new File("res/topTube.png"));
			BOTTOMTUBE = ImageIO.read(new File("res/bottomTube.png"));
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
	}
	
}
