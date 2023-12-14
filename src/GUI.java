import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton onOffBtn;
	private JLabel bpmLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 532);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Get a Piano");
		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewJgoodiesLabel.setForeground(Color.BLACK);
		lblNewJgoodiesLabel.setBounds(381, 6, 113, 28);
		contentPane.add(lblNewJgoodiesLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(71, 379, 723, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton blackKey1 = new JButton("");
		blackKey1.setForeground(new Color(0, 0, 0));
		blackKey1.setBackground(new Color(0, 0, 0));
		blackKey1.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		blackKey1.setBounds(28, 6, 34, 73);
		panel.add(blackKey1);
		
		JButton blackKey2 = new JButton("");
		blackKey2.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		blackKey2.setBounds(66, 6, 34, 73);
		panel.add(blackKey2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("wor/sound/FX_piano01.mp3");
			}

			private void playSound(String string) {
				try {
		            // Open an audio input stream
					URL piano01Url = GUI.class.getResource("/beep.wav");
		            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(piano01Url);

		            // Get a Clip resource
		            Clip clip = AudioSystem.getClip();

		            // Open audio clip and load samples from the audio input stream
		            clip.open(audioInputStream);

		            // Start playing the clip
		            clip.start();
		        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(6, 6, 42, 107);
		panel.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(80, 6, 42, 107);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(43, 6, 42, 107);
		panel.add(btnNewButton_1);
		
		JButton blackKey3 = new JButton("");
		blackKey3.setBounds(139, 6, 34, 73);
		blackKey3.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey3);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(117, 6, 42, 107);
		panel.add(btnNewButton_3);
		
		JButton blackKey4 = new JButton("");
		blackKey4.setBounds(177, 6, 34, 73);
		blackKey4.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey4);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setBounds(154, 6, 42, 107);
		panel.add(btnNewButton_1_2);
		
		JButton blackKey5 = new JButton("");
		blackKey5.setBounds(214, 6, 34, 73);
		blackKey5.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey5);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.setBounds(191, 6, 42, 107);
		panel.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("");
		btnNewButton_2_1_1.setBounds(228, 6, 42, 107);
		panel.add(btnNewButton_2_1_1);
		
		JButton blackKey6 = new JButton("");
		blackKey6.setBounds(287, 6, 34, 73);
		blackKey6.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey6);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBounds(265, 6, 42, 107);
		panel.add(btnNewButton_4);
		
		JButton blackKey7 = new JButton("");
		blackKey7.setBounds(325, 6, 34, 73);
		blackKey7.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey7);
		
		JButton btnNewButton_1_3 = new JButton("");
		btnNewButton_1_3.setBounds(302, 6, 42, 107);
		panel.add(btnNewButton_1_3);
		
		JButton btnNewButton_2_2 = new JButton("");
		btnNewButton_2_2.setBounds(339, 6, 42, 107);
		panel.add(btnNewButton_2_2);
		
		JButton blackKey8 = new JButton("");
		blackKey8.setBounds(399, 6, 34, 73);
		blackKey8.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey8);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.setBounds(376, 6, 42, 107);
		panel.add(btnNewButton_3_1);
		
		JButton blackKey9 = new JButton("");
		blackKey9.setBounds(436, 6, 34, 73);
		blackKey9.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey9);
		
		JButton btnNewButton_1_2_1 = new JButton("");
		btnNewButton_1_2_1.setBounds(413, 6, 42, 107);
		panel.add(btnNewButton_1_2_1);
		
		JButton blackKey10 = new JButton("");
		blackKey10.setBounds(472, 6, 34, 73);
		blackKey10.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey10);
		
		JButton btnNewButton_2_1_2 = new JButton("");
		btnNewButton_2_1_2.setBounds(450, 6, 42, 107);
		panel.add(btnNewButton_2_1_2);
		
		JButton btnNewButton_2_1_1_1 = new JButton("");
		btnNewButton_2_1_1_1.setBounds(487, 6, 42, 107);
		panel.add(btnNewButton_2_1_1_1);
		
		JButton blackKey11 = new JButton("");
		blackKey11.setBounds(547, 6, 34, 73);
		blackKey11.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey11);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.setBounds(524, 6, 42, 107);
		panel.add(btnNewButton_5);
		
		JButton blackKey12 = new JButton("");
		blackKey12.setBounds(584, 6, 34, 73);
		blackKey12.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey12);
		
		JButton btnNewButton_1_4 = new JButton("");
		btnNewButton_1_4.setBounds(561, 6, 42, 107);
		panel.add(btnNewButton_1_4);
		
		JButton btnNewButton_2_3 = new JButton("");
		btnNewButton_2_3.setBounds(598, 6, 42, 107);
		panel.add(btnNewButton_2_3);
		
		JButton blackKey13 = new JButton("");
		blackKey13.setBounds(658, 6, 34, 73);
		blackKey13.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(blackKey13);
		
		JButton btnNewButton_3_2 = new JButton("");
		btnNewButton_3_2.setBounds(635, 6, 42, 107);
		panel.add(btnNewButton_3_2);
		
		JButton btnNewButton_1_2_2 = new JButton("");
		btnNewButton_1_2_2.setBounds(672, 6, 42, 107);
		panel.add(btnNewButton_1_2_2);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(720, 6, 89, 149);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		
		onOffBtn = new JButton("onOffBtn");
		onOffBtn.setBackground(new Color(255, 255, 255));
		onOffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		onOffBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/metronome.png")));
		onOffBtn.setBounds(6, 6, 77, 84);
		panel_1.add(onOffBtn);
		
		JButton plusBtn = new JButton("+");
		plusBtn.setBounds(6, 89, 41, 29);
		panel_1.add(plusBtn);
		
		JButton minusBtn = new JButton("-");
		minusBtn.setBounds(42, 89, 41, 29);
		panel_1.add(minusBtn);
		
		bpmLabel = new JLabel("BPM: " + Metronome.bpm);
		bpmLabel.setBounds(6, 123, 77, 20);
		panel_1.add(bpmLabel);
		
		toggleHandler handler = new toggleHandler();
	    onOffBtn.addActionListener(handler);

	    plusHandler plusHandle = new plusHandler();
	    plusBtn.addActionListener(plusHandle);

	    minusHandler minusHandle = new minusHandler();
	    minusBtn.addActionListener(minusHandle);
	    
	    
	}
	
	private class toggleHandler implements ActionListener {
	    private Metronome metro;

	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent e) {
	        if (e.getActionCommand().equals("onOffBtn")) {
	            if (metro == null) {
	                metro = new Metronome();
	                Thread t = new Thread(metro);
	                t.start();
	            } else {
	                metro.end();
	                metro = null;
	            }
	        }
	    }

	}

	private class plusHandler implements ActionListener
	{
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent e)
	    {
	        Metronome.bpm++;
	        updateBPMLabel();
	    }
	}

	private class minusHandler implements ActionListener
	{
	    @Override
	    public void actionPerformed(java.awt.event.ActionEvent e)
	    {
	        Metronome.bpm--;
	        updateBPMLabel();
	    }
	}
	// Add this method to update the BPM label
    private void updateBPMLabel() {
        bpmLabel.setText("BPM: " + Metronome.bpm);
    }
	
	 }

	class Metronome extends Thread
	{
	private AtomicBoolean keepRunning;
	static double bpm = 60;

	public Metronome()
	{
	    keepRunning = new AtomicBoolean(true);
	}

	public void end()
	{
	    keepRunning.set(false);
	    System.out.println("STOPPED");
	}

	@Override
	public void run()
	{
	    while (keepRunning.get())
	    {
	        try
	        {
	            Thread.sleep((long)(1000*(60.0/bpm)));
	            playMetronomeSound();
	        }
	        catch(InterruptedException e)
	        {
	            e.printStackTrace();
	        }

	        System.out.println("RUNNING");
	    }
	}
	private void playMetronomeSound() {
        try {
        	URL soundUrl = GUI.class.getResource("/beep.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
