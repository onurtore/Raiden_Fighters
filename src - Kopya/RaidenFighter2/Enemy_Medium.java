package RaidenFighter2;

public class Enemy_Medium extends Enemy{

	private final int ENEMY_SPEED = +3;
	
	public Enemy_Medium(int x, int y) {
		super(x, y);

		initEnemy();
	}
	
	public void initEnemy(){

		loadLabel("enemy3.png");
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
