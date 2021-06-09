package pacman;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public void play(String path) {
		
		try {
			
			File musicPath = new File(path);
			
			if(musicPath.exists()) {
				
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else {
				
				System.out.println("Can\'t find file");
			}
			
		}
		catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	public void loopMusic(String path) {
		
		try {
		
			File musicPath = new File(path);
			
			if(musicPath.exists()) {
				
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else {
				
				System.out.println("Can\'t find file");
			}
			
		}
		catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	

}
