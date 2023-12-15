package getA;

import javax.sound.sampled.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

//public class Metronome extends Thread {
//    static double bpm = 60;
//    private boolean keepRunning;
//    private MetronomeListener listener;
//	private bpmChangeListener bpmChangeListener;
//
////    public Metronome(double bpm, Piano piano) {
////        this.bpm = bpm;
////        this.keepRunning = true;
////
////    }
//    
//    
//    public Metronome() {
//        this.keepRunning = true;
//        this.listener = null;
//        this.bpmChangeListener = null;
//    }
//    
//    public boolean isRunning() {
//        return keepRunning;
//    }
//    public void setMetronomeListener(MetronomeListener listener) {
//        this.listener = listener;
//    }
//
//
//    public void end() {
//        keepRunning = false;
//        System.out.println("STOPPED");
//    }
//
//    @Override
//    public void run() {
//        while (keepRunning) {
//            try {
//                Thread.sleep((long) (1000 * (60.0 / bpm)));
//                playMetronomeSound();
//                if (listener != null) {
//                    listener.onMetronomeTick();
//                }
//            } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("RUNNING");
//        }
//    }
//
//    private void playMetronomeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        URL soundUrl = Metronome.class.getResource("/sound/beep.wav");
//        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
//        Clip clip = AudioSystem.getClip();
//        clip.open(audioInputStream);
//        clip.start();
//    }
//
//    public class PlusHandler implements ActionListener {
//        @Override
//        public void actionPerformed(java.awt.event.ActionEvent e) {
//            bpm++;
//            updateBPMLabel();
//        }
//    }
//
//    public class MinusHandler implements ActionListener {
//        @Override
//        public void actionPerformed(java.awt.event.ActionEvent e) {
//            bpm--;
//            updateBPMLabel();
//        }
//    }
//    public void setBpm(double bpm) {
//        this.bpm = bpm;
//    }
//    
//    
//
// // Add this method to your GUI class
//    public void updateBPMLabel() {
//       gui.bpmLabel.setText("BPM: " + Metronome.bpm);
//    }
//
//}
public class Metronome extends Thread {
    static double bpm = 60;
    private boolean keepRunning;
    private MetronomeListener metronomeListener;
    private BpmChangeListener bpmChangeListener;

    public Metronome() {
        this.keepRunning = false;
        this.metronomeListener = null;
        this.bpmChangeListener = null;
    }

    public void setMetronomeListener(MetronomeListener listener) {
        this.keepRunning = true;
        this.metronomeListener = listener;
    }

    public void setBpmChangeListener(BpmChangeListener listener) {
        this.bpmChangeListener = listener;
    }

    public boolean isRunning() {
        return keepRunning;
    }

    public void end() {
        keepRunning = false;
        System.out.println("STOPPED");
    }

    @Override
    public void run() {
        while (keepRunning) {
            try {
                Thread.sleep((long) (1000 * (60.0 / bpm)));
                playMetronomeSound();
                if (metronomeListener != null) {
                    metronomeListener.onMetronomeTick();
                }
            } catch (InterruptedException | UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }

            System.out.println("RUNNING");
        }
    }

    private void playMetronomeSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL soundUrl = Metronome.class.getResource("/sound/beep.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
        if (bpmChangeListener != null) {
            bpmChangeListener.onBpmChange(bpm);
        }
    }
}

