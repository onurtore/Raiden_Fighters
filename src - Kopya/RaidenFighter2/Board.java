package RaidenFighter2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuComponent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

//The Board is a panel where the game takes place

public class Board extends JPanel implements ActionListener  {

	private int ICRAFT_X = 0;
	private int ICRAFT_Y = 0;
	
	private int boardWidth = 0;
	private int boardHeight = 0;
	
	
	private int PassTimeMS = 0;
	private int PassTimeSC = 0;
	private int PassTimeMN = 0;
	
	private Timer timer;
	private Craft craft;

	
	private final int DELAY = 20;
	
	
	JFrame mainFrame;
	Image backgroundImage;
	int BackgroundImagePosX = -400;
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	//I dont like the idea of Enemy_Bomb class seperated from the Enemy class, but time is limited so...
	ArrayList<Enemy_Bomb> enemy_bombs = new ArrayList<Enemy_Bomb>();
	
	Random rand = new Random();

	LaserBar myLaserBar = new LaserBar();
	
	JLabel background =  new JLabel();
	
	public Board(int width,int height,JFrame mainApp){
		
		
		this.mainFrame = mainApp;
		
		boardWidth = width;
		boardHeight = height;
		
	//	System.out.println(boardHeight);
		
		
		
		
		ICRAFT_X = this.boardWidth / 2 - 50  ;
		ICRAFT_Y = this.boardHeight - 200;
		
		
		initBoard();
	}
	
	private void initBoard(){
		
		addKeyListener(new TAdapter());
		
		addBackground();
		
		setFocusable(true);

	//	setBackground(Color.BLACK);
	//	setLayout(new FlowLayout());	
	//	setLayout(null);
		setDoubleBuffered(true);
		craft = new Craft(ICRAFT_X,ICRAFT_Y,this);
		Border border = LineBorder.createGrayLineBorder();

		craft.label.setBorder(border);
		craft.label.setSize(50,50);
		add(craft.getLabel());
		
		
		for(int i = 0 ; i < craft.getHealth().size() ; i ++) {
			
			add( craft.getHealth().get(i).getLabel()) ;
		}
		
		add(this.myLaserBar.getAlive_laser());
		
		timer = new Timer(DELAY,this);
		timer.start();
		
		
		this.requestFocus ();


		
	}
	
	
	public void doDrawing(){
	
		
		

		craft.getLabel().setLocation(craft.getX(),craft.getY());
		
		
		ArrayList ms = craft.getMissiles();
		
		for(Object m1 : ms){
			Missile m = (Missile) m1;
			m.getLabel().setLocation(m.x, m.y);
			
			
		}
		
		int lifeCounter = craft.getLife();
		
		for(int i = 0 ; i < lifeCounter ; i ++) {
			
			
			craft.getHealth().get(i).getLabel().setLocation( ( i * 50 ) , this.boardHeight-175);
		}
		
		
		
	
		
		
		for(Object m1 : this.enemies){
			Enemy m = (Enemy) m1;
			m.getLabel().setLocation(m.x, m.y);
			
			
		}
		
		
		if(craft.isLaserOn()){
			
			craft.laser.getLabel().setLocation(craft.laser.x,craft.laser.y);
		}
		
		if(craft.getNewLaserTime() == 0){
			myLaserBar.getAlive_laser().setLocation( (lifeCounter * 50) , this.boardHeight - 175);
		}
		else{
			myLaserBar.getAlive_laser().setLocation( (lifeCounter * 500) , this.boardHeight - 175);
					
		}
		
		for(Enemy_Bomb b : this.enemy_bombs){
			b.getLabel().setLocation(b.x,b.y);
			
			
		}
		
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {

		
		
		PassTimeMS++;
		
		if(this.PassTimeMS == 58){
		
			//System.out.println(PassTimeSC);
			PassTimeSC++;
			PassTimeMS = 0;
		}
		
		if(this.PassTimeSC == 60){
			PassTimeMN++;
		}
		
		
		
		updateMissiles();
		updateLaser();
		updateCraft();
		updateEnemy();
		updateBombing();

		
		checkCollisions();
		
		repaint();
		
		if(craft.isDead() == true){
			gameOver();
			mainFrame.remove(this);
			return;
		}
		
		doDrawing();
	}
	
	
	
	@Override
	public void paintComponent(Graphics g){
		this.BackgroundImagePosX++;
		g.drawImage(backgroundImage,0,BackgroundImagePosX,null);
		System.out.println(this.BackgroundImagePosX);
		if(this.BackgroundImagePosX == -70){
			this.BackgroundImagePosX = -400;
		}
	}
	
	private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyReleased(KeyEvent e){
			craft.keyReleased(e);
			
		}
		@Override
		public void keyPressed(KeyEvent e){
			craft.keyPressed(e);
		}
	}
	
	private void updateCraft(){
		craft.move();
	}
	
	
	private void updateMissiles(){
		ArrayList<Missile> ms = craft.getMissiles();
		
		
		
		
		for(int i = 0; i < ms.size(); i++){
			Missile m = (Missile) ms.get(i);
			
		
			
			if(m.isVisible()) {
				
				m.move();
			}
			else{
				ms.remove(i);
				this.remove(m.getLabel());
			}
		
		}
	}
	
	private void updateLaser(){
		
		
		if(this.craft.isLaserOn()){
			
			this.craft.laserMove();
			
			if( this.PassTimeMS == 0){
				this.craft.setLaserDuration(this.craft.getLaserDuration()-1);
			}
			
			if(this.craft.getLaserDuration() == 0){
				this.craft.setLaserOn(false);
				this.remove(craft.laser.label);
			}
			
			
		}
		
		if(!this.craft.isLaserOn()){
			if(this.craft.getNewLaserTime() != 0){
				
				if(this.PassTimeMS == 0){
					this.craft.setNewLaserTime(this.craft.getNewLaserTime() -1 );
				}
			}
		}
		
	
		

	
		
		
	}
	
	
	private void updateEnemy(){
		
		if( ( this.PassTimeSC  % 4 == 0 ) && ( this.PassTimeMS == 0 )   ){
			
			boolean valid = true;
			
			int n; 
			
			while(true){
				valid = true;
				n = rand.nextInt(this.boardWidth - 100 ) + 50;
			
				for( Enemy m : this.enemies){
					
					int x = m.x;
					
					if(Math.abs(n - x) < 50 ){
						valid = false;
					}
					
				}
				if(valid){
					break;
				}
			
			
			
			}
			Menu myMenuBar = (Menu) this.mainFrame.getJMenuBar();
			
			PlayerInfo myPlayer = myMenuBar.getMyPlayer();
			
			int random_enemy = 0;
			
			if(myPlayer.getLevel() == 0){
				 random_enemy = rand.nextInt(3)  + 1;
			}
			if(myPlayer.getLevel() == 1){
				 random_enemy = rand.nextInt(5)  + 1;
			}
			
			
			
			Enemy newEnemy = null;
			
			switch(random_enemy){

			case 1:
				 newEnemy = new Enemy_Beginner(n,0);
				 break;
			case 2:
				newEnemy = new Enemy_Easy(n,0);
				break;
			case 3:
				newEnemy = new Enemy_Medium(n,0);
				break;
			case 4:
				newEnemy = new Enemy_Hard(n,0);
				break;
			case 5:
				newEnemy = new Enemy_Tank(n,0);
				break;
			default:				
				System.out.println(random_enemy);
				break;
			}
			
			
			Border border = LineBorder.createGrayLineBorder();
			newEnemy.label.setBorder(border);
			newEnemy.label.setBounds(n,0,50,50);
			this.enemies.add(newEnemy);
			add(newEnemy.getLabel());
		
		
		}
		
		
		boolean dodge = false;
		
		ArrayList<Missile> ms = craft.getMissiles();
		
		for(int i = 0; i < this.enemies.size(); i++){
			dodge = false; 
			Enemy m = (Enemy) enemies.get(i);
			Rectangle r1 = m.getLabel().getBounds();
			
			
			for(Missile mis : ms){
				Rectangle r2 = mis.getLabel().getBounds();
			
				if(Math.abs(r1.x - r2.x) < 50){
					dodge = true;
					break;
				}
			}
			
			if(m.isVisible()){
			 	if(dodge){
			 		m.dodgeMove();
			 	}
			 	else{
			 		m.move();
			 	}
			}
			
			else{
				this.enemies.remove(i);
				this.remove(m.label);
			}
			
		}
		
			
			
	}
	
	
	public void updateBombing(){
			
		if( ( this.PassTimeSC  % 1 == 0 ) && ( this.PassTimeMS == 0 )   ){
		
			for(Enemy m : enemies){
				Enemy_Bomb myEnemyBomb = new Enemy_Bomb(m.x,m.y);
				Border border = LineBorder.createGrayLineBorder();
				myEnemyBomb.label.setBorder(border);
				myEnemyBomb.label.setBounds(m.x,m.y,30,30);
				this.enemy_bombs.add(myEnemyBomb);
				add(myEnemyBomb.getLabel());
				
			}
		
		}
		
		for(int i = 0 ;  i< enemy_bombs.size(); i++){
			Enemy_Bomb b = (Enemy_Bomb) enemy_bombs.get(i);
			
			if(b.isVisible()){
			 	b.move();
			 	
			}
			
			else{
				this.enemy_bombs.remove(i);
				this.remove(b.label);
			}

		}
		
		
		
		
		
		
		

		
	}
	
	public void checkCollisions(){
		Rectangle r3 = craft.getLabel().getBounds();
		
		for(Enemy enemy : this.enemies){
			Rectangle r2 = enemy.getLabel().getBounds();
			
			if(r3.intersects(r2)){
				
				craft.getDamage();
				
				this.remove(craft.getHealth().get(craft.getHealth().size() -1).label);
				craft.getHealth().remove(craft.getHealth().size() -1);
				
				enemy.setVisible(false);
			}
		}
		
		for(Enemy_Bomb b : this.enemy_bombs){
			Rectangle r2 = b.getLabel().getBounds();
			
			if(r3.intersects(r2)){
				craft.getDamage();
				
				this.remove(craft.getHealth().get(craft.getHealth().size() -1).label);
				craft.getHealth().remove(craft.getHealth().size() -1);
				
				b.setVisible(false);
			}
		}
		
		
		ArrayList<Missile> ms = craft.getMissiles();
		
		for(Missile m : ms){
			Rectangle r1 = m.getLabel().getBounds();
			
			for( Enemy enemy : this.enemies){
				Rectangle r2 = enemy.getLabel().getBounds();
				
				
				if(r1.intersects(r2)){
					craft.increaseScore(50);
					m.setVisible(false);
					enemy.setVisible(false);
				}
				
			}
			for(Enemy_Bomb b : this.enemy_bombs){
				Rectangle r2 = b.getLabel().getBounds();
				
				if(r1.intersects(r2)){
					m.setVisible(false);
					b.setVisible(false);
				}
			}
			
			
			
		}
		
		if(!this.craft.isLaserOn()){
			return;
		}
		
		r3 = this.craft.laser.getLabel().getBounds();
		
		for(Enemy enemy : this.enemies){
			Rectangle r2 = enemy.getLabel().getBounds();
			
			if(r3.intersects(r2)){
								
				enemy.setVisible(false);
			}
		}
		
		for(Missile m : ms){
			Rectangle r1 = m.getLabel().getBounds();
			if(r3.intersects(r1)){
				m.setVisible(false);
				
			}
		}
		
		for(Enemy_Bomb b : this.enemy_bombs ){
			Rectangle r1 = b.getLabel().getBounds();
			
			if(r3.intersects(r1)){
				b.setVisible(false);
			}
		}
		
		
		
		
	}
	
	private void gameOver(){
		
		savePlayer();
		
		timer.stop();
		
		mainFrame.getContentPane().remove(this);
		mainFrame.validate();
		mainFrame.repaint();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width * 1 / 4;
		
		int height = screenSize.height / 16  * 9 ;
	
		Menu myMenu = (Menu) mainFrame.getJMenuBar();
		
		mainFrame.getContentPane().add(myMenu.gifLabel);
	
		mainFrame.revalidate();
		
		mainFrame.repaint();
		
		myMenu.requestFocusInWindow();
		
	}
	
	private void savePlayer(){
		
		Menu myMenu = (Menu) mainFrame.getJMenuBar();
		
		myMenu.savePlayer(craft.getLevel(), craft.getScore());
		
		
	}

	
	private void addBackground(){
		/*URL url = this.getClass().getResource("background2.jpg");
		ImageIcon gifIcon = new ImageIcon(url);

		//Get the scaled image
		gifIcon.setImage(gifIcon.getImage().getScaledInstance(1000,1000, Image.SCALE_DEFAULT));
		background = new JLabel(gifIcon);
		*/
		try {
			
			backgroundImage = javax.imageio.ImageIO.read(new File("C:\\Users\\Onur Berk Töre\\workspace\\Raidan_Fighters_2\\bin\\RaidenFighter2\\background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
