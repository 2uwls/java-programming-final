package getA;

import javax.sound.sampled.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

//Definition of the Metronome class, extending Thread
public class Metronome extends Thread {
	// Static variable to store the beats per minute (bpm)
    static double bpm = 60;
    // Boolean variable to control the thread execution
    private boolean keepRunning;
    // Interface variable for handling metronome events
    private MetronomeListener metronomeListener;

    // Constructor for the Metronome class
    public Metronome() {
    	// Initialize variables
        this.keepRunning = true;
        this.metronomeListener = null;
    }
    
    // Method to set the MetronomeListener for handling metronome events
    public void setMetronomeListener(MetronomeListener listener) {
        
        this.metronomeListener = listener;
    }
    
    // Method to check if the metronome is running
    public boolean isRunning() {
        return keepRunning;
    }
    
    // Method to start the metronome
    public void startMetronome() {
        if (!keepRunning) {
            keepRunning = true;
            start(); // Start the thread
        }
    }

    // Method to stop the metronome
    public void end() {
        keepRunning = false;
        System.out.println("STOPPED");
    }

    // Overridden run method of the Thread class
    @Override
    public void run() {
    	// Main loop for the metronome thread
        while (keepRunning) {
            try {
            	// Sleep for the calculated duration between beats
                Thread.sleep((long) (1000 * (60.0 / bpm)));
                // Play metronome sound
                playMetronomeSound();
                // Notify the listener about a metronome tick event
                if (metronomeListener != null) {
                    metronomeListener.onMetronomeTick();
                }
            } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }

            System.out.println("RUNNING");
        }
    }

    // Method to play the metronome sound
    private void playMetronomeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	// Load the metronome sound file
    	URL soundUrl = Metronome.class.getResource("/sound/beep.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
        // Create and start a new audio clip
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
   



   
}

