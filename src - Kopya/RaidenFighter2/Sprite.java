package RaidenFighter2;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean vis;
	protected JLabel label;
	
	public Sprite(int x,int y){
		
		this.x = x;
		this.y = y;

		vis = true;
	}
	
	protected void loadLabel(String imageName){
		URL url = this.getClass().getResource(imageName);
		ImageIcon gifIcon = new ImageIcon(url);

		//Get the scaled image
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
		label = new JLabel(gifIcon);
		
	}
	
	protected void getLabelDimensions(){
		width = label.getWidth();
		height = label.getHeight();
	}
	
	
	public JLabel getLabel(){
		return label;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isVisible(){
		return vis;
	}
	
	public void setVisible(Boolean visible){
		vis = visible;
	}
	
}
