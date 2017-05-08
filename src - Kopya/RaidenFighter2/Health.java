package RaidenFighter2;

public class Health extends Sprite {
	
	public Health(int x,int y){
		super(x,y);
		
		
		initHealth();
	}
	
	public void initHealth(){
		
		loadLabel("health.png");
		
		getLabelDimensions();
	}

}
