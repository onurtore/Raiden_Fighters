package RaidenFighter2;
//This was planned as nested class in Enemy class but, i dont have enough
//time to figure it out right now.

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy_Bomb extends Sprite {

	private final int BOARD_WIDTH = Board.WIDTH;
	private final int MISSILE_SPEED = +3;

	public Enemy_Bomb(int x, int y) {
		super(x,y);
		
		initEnemy_Bomb();
		
	}
	
	private void initEnemy_Bomb(){
		
		loadLabel("missile.png");
		
		getLabelDimensions();
		
	}
	
	public void move(){
		y += MISSILE_SPEED;
		if(y < 0){
			vis = false;
		}
	}
	//I want little bit smaller than the bombs from the craft.
	@Override
	protected void loadLabel(String imageName){
		URL url = this.getClass().getResource(imageName);
		ImageIcon gifIcon = new ImageIcon(url);

		//Get the scaled image
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(20,20, Image.SCALE_DEFAULT));
		label = new JLabel(gifIcon);
		
	}
	
}
