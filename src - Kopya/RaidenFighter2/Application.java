package RaidenFighter2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import sun.audio.*;

public  class Application extends JFrame {


	int width = 0;
	int height = 0;
	
	
	
	public Application(){
		
		initUI();
	}
	
	private void initUI() {
		

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 1 / 4;
		height = screenSize.height / 16  * 9 ;
		setSize(width,height);
		setPreferredSize(new Dimension(width,height));
		
		//add(new Board(width,height));
		setJMenuBar(new Menu(this,width,height));

		//setLayout(null);
		setTitle("Raiden Fighters II");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Passing null to the setLocationRelativeTo() method centers the windows on the screen
        setLocationRelativeTo(null);
	}
	
	public static void main(String[] args){
		
		playSound mySound = new playSound();
		
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run(){
				Application ex = new Application();
				ex.setVisible(true);
			}
		});
	}
	
	

}
