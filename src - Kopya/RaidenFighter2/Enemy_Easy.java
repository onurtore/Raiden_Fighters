package RaidenFighter2;

public class Enemy_Easy extends Enemy{

	private final int ENEMY_SPEED = +2;
	
	public Enemy_Easy(int x, int y) {
		super(x, y);

		initEnemy();
	}
	
	public void initEnemy(){

		loadLabel("enemy2.png");
		getLabelDimensions();
		
		dodge = 1;
	}
	
	public void move(){
		y += ENEMY_SPEED;
		if(y > 1008){
			vis = false;
		}
	}

}
