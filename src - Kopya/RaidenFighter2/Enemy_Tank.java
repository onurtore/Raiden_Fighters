package RaidenFighter2;

public class Enemy_Tank extends Enemy{

	private final int ENEMY_SPEED = +1;
	
	public Enemy_Tank(int x, int y) {
		super(x, y);

		initEnemy();
	}
	
	public void initEnemy(){

		loadLabel("enemy5.png");
		getLabelDimensions();
		
		dodge = 2;
	}
	
	public void move(){
		y += ENEMY_SPEED;
		if(y > 1008){
			vis = false;
		}
	}

}
