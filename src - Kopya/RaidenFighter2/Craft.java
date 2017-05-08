package RaidenFighter2;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Craft extends Sprite {


	
	private int score;
	private int difficult;
	private int life;
	private int dx;
	private int dy;
	private int speed;
	private int level;
	
	private int NewLaserTime = 0;
	private int LaserDuration = 5;
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private boolean isDead;
	private boolean isLaserOn;
	
	
	
	public JPanel GamePanel;
	
	private ArrayList<Health> health;
	private ArrayList<Missile> missiles;
	
	Laser laser;
	
	Craft(int x,int y,JPanel myPanel){
		super(x,y);
	
		
		GamePanel = myPanel;
		
		initCraft();
	}
	
	private void initCraft() {
		
		health = new ArrayList<Health>();
		missiles = new ArrayList<Missile>();
		
	
		
		isDead = false;
		score = 0;
		difficult = 0;
		life = 3;
		speed = 5;
		isLaserOn = false;
		
		for (int i = 0 ; i < life ; i++){
			
			health.add(new Health(50,50));
			
		}
		
		
		loadLabel("jet1.png");
		getLabelDimensions();

	}


	public void move(){
		
		int oldX = x;
		int oldY = y;
		
		x += dx;
		
		if(x < 5 || x > 740){
			x = oldX;
		}
		
		y += dy;
		
		if(y < 3 || y > 868){
			
			y = oldY;
		}
	
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public JLabel getLabel(){
		return label;
	}
	
	public void keyPressed(KeyEvent e ){
		
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT){
			dx = -1 * speed;
		}
		if(key == KeyEvent.VK_RIGHT){
			dx = 1 * speed;
		}
		if(key == KeyEvent.VK_UP){
			dy = -1 * speed;
		}
			
		if(key == KeyEvent.VK_DOWN){
			dy = 1 * speed;
		}
		
		
	}
	public void keyReleased(KeyEvent e ){
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE){
			fire();
		}
		
		if(key == KeyEvent.VK_V){
			
			//Laser already in use
			if(isLaserOn == true){
				return;
			}
			//Laser Still need to refresh
			if(NewLaserTime != 0){
				return;
			}
			
			
			fireLaser();
		}
		
		if(key == KeyEvent.VK_LEFT){
			dx = 0;
		}
		if(key == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		if(key == KeyEvent.VK_UP){
			dy = 0;
		}
			
		if(key == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		
	}
	
	public ArrayList<Missile> getMissiles(){
		return missiles;
	}
	
	public void fire(){

		Missile newMissile = new Missile(this.x,this.y);
		Border border = LineBorder.createGrayLineBorder();
		newMissile.label.setBorder(border);
		newMissile.label.setBounds(this.x,this.y,50,50);
		missiles.add(newMissile);
		GamePanel.add(newMissile.label);
	}
	
	public void fireLaser(){
		
		
		
		laser = new Laser(this.x,this.y);
		Border border = LineBorder.createGrayLineBorder();
		laser.label.setBorder(border);
		laser.label.setBounds(this.x,this.y-900,50,1000);
		this.isLaserOn = true;
		GamePanel.add(laser.label);
		NewLaserTime = 30;
		LaserDuration = 5;
	}
	
	
	public void getDamage(){
		
		if(life == 1){
			this.isDead = true;
		}
		else{
			this.life = this.life -1;
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDifficult() {
		return difficult;
	}

	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}

	public ArrayList<Health> getHealth() {
		return health;
	}

	public void setHealth(ArrayList<Health> health) {
		this.health = health;
	}

	public void setMissiles(ArrayList<Missile> missiles) {
		this.missiles = missiles;
	}
	
	public void increaseScore(int value){
		
		this.score += value;
	}

	public boolean isLaserOn() {
		return isLaserOn;
	}

	public void setLaserOn(boolean isLaser) {
		this.isLaserOn = isLaser;
	}
	
	public void laserMove(){
		this.laser.x = this.x;
		this.laser.y = this.y - 900;

	}

	public int getNewLaserTime() {
		return NewLaserTime;
	}

	public void setNewLaserTime(int newLaserTime) {
		NewLaserTime = newLaserTime;
	}

	public int getLaserDuration() {
		return LaserDuration;
	}

	public void setLaserDuration(int laserDuration) {
		LaserDuration = laserDuration;
	}

	public Laser getLaser() {
		return laser;
	}

	public void setLaser(Laser laser) {
		this.laser = laser;
	}
	
}
