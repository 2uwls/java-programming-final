// Metronome.java
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

public class Metronome extends Thread {
    private AtomicBoolean keepRunning;
    static double bpm = 60;

    public Metronome() {
        keepRunning = new AtomicBoolean(true);
    }

    public void end() {
        keepRunning.set(false);
        System.out.println("STOPPED");
    }

    @Override
    public void run() {
        while (keepRunning.get()) {
            try {
                Thread.sleep((long) (1000 * (60.0 / bpm)));
                playMetronomeSound();
            } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }

            System.out.println("RUNNING");
        }
    }

    private void playMetronomeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL soundUrl = Metronome.class.getResource("/beep.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
