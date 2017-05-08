package RaidenFighter2;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class playSound {

	playSound(){
		Sound();
		
	}


	public static synchronized void Sound() {
	  new Thread(new Runnable() {
	  // The wrapper thread is unnecessary, unless it blocks on the
	  // Clip finishing; see comments.
	    public void run() {
	      try {
	        Clip clip = AudioSystem.getClip();
	        URL url = this.getClass().getResource("RaidenFighte2.wav");
	        AudioInputStream aiStream = AudioSystem.getAudioInputStream(url);   
	        
	        
	        clip.open(aiStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        } catch (Exception e) {
	        System.err.println(e.getMessage());
	      }
	    }
	  }).start();
	}


}