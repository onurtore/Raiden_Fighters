package RaidenFighter2;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Laser extends Sprite {

	private final int BOARD_WIDTH = Board.WIDTH;
	private final int MISSILE_SPEED = -1;

	
	
	public Laser(int x, int y) {
		super(x,y);
		
		initLaser();
		
	}
	
	private void initLaser(){
		
		loadLabel("laser0.png");
		
		getLabelDimensions();
		
		

	
	}
	
	private void remove(){
		vis = false;
	}
	
	@Override 
	protected void loadLabel(String imageName){
		URL url = this.getClass().getResource(imageName);
		ImageIcon gifIcon = new ImageIcon(url);

		//Get the scaled image
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(50,950, Image.SCALE_DEFAULT));
		label = new JLabel(gifIcon);
		
	}
	
	
}
