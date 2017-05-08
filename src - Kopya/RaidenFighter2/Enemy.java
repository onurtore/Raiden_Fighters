package RaidenFighter2;

import java.util.Random;

public class Enemy extends Sprite{

	private final int ENEMY_SPEED = 1;

	protected int dodge = 0;

	
	boolean inDodge = false;
	
	Random rand = new Random();

	int  n = rand.nextInt(2) + 1;
	
	
	
	public Enemy(int x, int y) {
		super(x, y);

		
	}

	public void move(){
		y += ENEMY_SPEED;
		if(y > 1008){
			vis = false;
		}
	}


	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
		
		if(inDodge == true){
			inDodge = false;
			dodge--;
			
			n = rand.nextInt(2) + 1;
		}
	}

	public void dodgeMove() {

		
		int oldX = x;
		
		if(this.dodge == 0){
			
			move();
		}
		else{
			
			y += ENEMY_SPEED;
			if(y > 1008){
				vis = false;
			}
			
			if(n == 1 ){ 
				
				x += ENEMY_SPEED;
							
			}
			if(n == 2 ){
				x -= ENEMY_SPEED;
					
			}
			
			
			if(x < 5 || x > 740){
				x = oldX;
			}
			
			inDodge = true;
 
		}
		
	}
	
	


	
	
	
}
