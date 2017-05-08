package RaidenFighter2;

import java.io.Serializable;

public class PlayerInfo implements Serializable,Comparable<PlayerInfo> {



	private static final long serialVersionUID = 1L;

	String username;
	String password;
	
	int highScore;
	int level;
	

	
	PlayerInfo(String name,String password){
		
		this.username = name;
		this.password = password;
		this.highScore = 0;
		this.level = 0;
		
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public int getHighScore() {
		return highScore;
	}



	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}



	public int getLevel() {
		return level;
	}



	public void setLevel(int level) {
		this.level = level;
	}



	@Override
	public int compareTo(PlayerInfo o) {

		System.out.println("Nabersdgksgjsmglsgmslgslgmglgslmglgmslsmg");
		return Integer.compare(o.highScore, this.highScore);
	}



	
	
}
