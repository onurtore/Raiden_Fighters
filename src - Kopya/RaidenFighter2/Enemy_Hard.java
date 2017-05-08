package RaidenFighter2;

public class Enemy_Hard extends Enemy{

	private final int ENEMY_SPEED = +4;
	
	public Enemy_Hard(int x, int y) {
		super(x, y);

		initEnemy();
	}
	
	public void initEnemy(){

		loadLabel("enemy4.png");
		getLabelDimensions();
		
		dodge = 3;
	}
	
	public void move(){
		y += ENEMY_SPEED;
		if(y > 1008){
			vis = false;
		}
	}

}
