package RaidenFighter2;

public class Missile extends Sprite {

	private final int BOARD_WIDTH = Board.WIDTH;
	private final int MISSILE_SPEED = -1;

	public Missile(int x, int y) {
		super(x,y);
		
		initMissile();
		
	}
	
	private void initMissile(){
		
		loadLabel("missile.png");
		
		getLabelDimensions();
		
	}
	
	public void move(){
		y += MISSILE_SPEED;
		if(y < 0){
			vis = false;
		}
	}
	
}
