package RaidenFighter2;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LaserBar {

	
	protected JLabel dead_laser;
	protected JLabel alive_laser;


	public LaserBar() {
		
		initLaserBar();
		
	}
	
	private void initLaserBar(){
		
	
	
		URL url = this.getClass().getResource("laser0_alive.png");
		ImageIcon gifIcon = new ImageIcon(url);
	
		//Get the scaled image
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
		alive_laser = new JLabel(gifIcon);
		
		url = this.getClass().getResource("laser0_dead.png");
		gifIcon = new ImageIcon(url);
		
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT));
		dead_laser = new JLabel(gifIcon);
	}

	public JLabel getDead_laser() {
		return dead_laser;
	}

	public void setDead_laser(JLabel dead_laser) {
		this.dead_laser = dead_laser;
	}

	public JLabel getAlive_laser() {
		return alive_laser;
	}

	public void setAlive_laser(JLabel alive_laser) {
		this.alive_laser = alive_laser;
	}

}
