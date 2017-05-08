package RaidenFighter2;

public class Enemy_Beginner extends Enemy{

	private final int ENEMY_SPEED = +2;
	
	public Enemy_Beginner(int x, int y) {
		super(x, y);

		initEnemy();
	}
	
	public void initEnemy(){

		loadLabel("enemy1.png");
		getLabelDimensions();

		dodge = 0;
	}
	
	public void move(){
		y += ENEMY_SPEED;
		if(y > 1008){
			vis = false;
		}
	}

}
