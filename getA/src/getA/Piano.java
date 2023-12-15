package getA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



//import com.jgoodies.forms.factories.DefaultComponentFactory;


public class Piano extends JFrame implements ActionListener, MetronomeListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem mainMenu;
	private JMenu noteMenu;
	private JMenuItem noteMenuItem;
	private Metronome metronome;
	
	private boolean isRecording = false; 

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton onOffBtn;
	JLabel bpmLabel;
	private JButton btnRecord;
	private JButton btnStop;
	private JButton btnPlay;
	
	private List<RecordedNote> recordedNotes = new ArrayList<>();
	private long startTime;
	private int duration;
	
	
	
	

    public Piano() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 532);
        setTitle("Piano");
        
        contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        noteMenu= new JMenu("Make note");
        menuBar.add(noteMenu);

        mainMenu = new JMenuItem("Main");
        mainMenu.addActionListener(this);
        menu.add(mainMenu);
        
        noteMenuItem = new JMenuItem("note");
        noteMenuItem.addActionListener(this);
        noteMenu.add(noteMenuItem);
        
        btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isRecording()) {
                    return;
                }

                startRecording();
				
			}
		});
        btnRecord.setBounds(594, 10, 117, 29);
        contentPane.add(btnRecord);

        btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!isRecording()) {
                    return;
                }

                stopRecording();
                printRecordedNotes();
        	}
        });
        btnStop.setBounds(594, 39, 117, 29);
        contentPane.add(btnStop);

        btnPlay = new JButton("Play");
        btnPlay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (recordedNotes.isEmpty()) {
                    return;
                }

                playNotes();
        	}
        });
        btnPlay.setBounds(594, 69, 117, 29);
        contentPane.add(btnPlay);
		
//		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Get a Piano");
//		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
//		lblNewJgoodiesLabel.setForeground(Color.BLACK);
//		lblNewJgoodiesLabel.setBounds(381, 6, 113, 28);
//		contentPane.add(lblNewJgoodiesLabel);
		
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
		btnNewButton.setName("btnNewButton");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("wor/sound/FX_piano01.mp3");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
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
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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

		
		  metronome = new Metronome(); // Pass 'this' as BpmChangeListener
	        metronome.setMetronomeListener(this);
	        metronome.start();

	        bpmLabel = new JLabel("BPM: " + metronome.getId()); // Initialize with the initial BPM value
	        bpmLabel.setBounds(6, 123, 77, 20);
	        panel_1.add(bpmLabel);
	        
		onOffBtn = new JButton("onOffBtn");
		onOffBtn.setBackground(new Color(255, 255, 255));

		
		onOffBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/metronome.png")));
		onOffBtn.setBounds(6, 6, 77, 84);
		onOffBtn.addActionListener(new ToggleHandler());
		panel_1.add(onOffBtn);
		
		JButton plusBtn = new JButton("+");
		plusBtn.setBounds(6, 89, 41, 29);
		panel_1.add(plusBtn);
		
		JButton minusBtn = new JButton("-");
		minusBtn.setBounds(42, 89, 41, 29);
		panel_1.add(minusBtn);
//		
//		bpmLabel = new JLabel("BPM: " + Metronome.bpm);
//		bpmLabel.setBounds(6, 123, 77, 20);
//		panel_1.add(bpmLabel);
		
		
		
        
        
        
        
        
        

        // Add piano-related components here

        setVisible(true);
    }

    protected void playSound(String string) {
		try {
			URL soundUrl = getClass().getResource("/sound/beep.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
		
	}

	private boolean isRecording() {
	    return btnRecord.getText().equals("녹음 중지");
	}

	  private void startRecording() {
	        isRecording = true;
	        btnRecord.setText("Stop Recording");
	        recordedNotes.clear();
	        startTime = System.currentTimeMillis();
	    }

	    private void stopRecording() {
	        isRecording = false;
	        btnRecord.setText("Record");
	    }

	    public void actionPerformed(ActionEvent e) {
	        Object source = e.getSource();

	        if (source == mainMenu) {
	            new GUI().setVisible(true);
	            dispose();
	        } else if (source == noteMenuItem) {
	        	new Makenote().setVisible(true);
	            dispose();
	        } else if (source == btnRecord) {
	        	if (isRecording) {
	                stopRecording();
	                printRecordedNotes();
	            } else {
	                startRecording();
	            }
	        } else if (source == btnStop) {
	        	if (isRecording) {
	                stopRecording();
	                printRecordedNotes();
	            }
	        } else if (source == btnPlay) {
	        	if (!recordedNotes.isEmpty()) {
	                playNotes();
	            }
	        } else if (source instanceof JButton) {
	            // Handle button clicks (assuming all buttons are JButtons)
	            JButton clickedButton = (JButton) source;
	            String pitchName = clickedButton.getName();

	            if (isRecording) {
	                long timestamp = System.currentTimeMillis() - startTime;
	                RecordedNote note = new RecordedNote(pitchName, timestamp, "/sound/beep.wav");
	                recordedNotes.add(note);
	                System.out.printf("%s", pitchName);
	            } else {
	                playButtonSound(e);
	            }
	        }
	    }


	 private void playButtonSound(ActionEvent e) {
	        JButton clickedButton = (JButton) e.getSource();
	        String pitchName = clickedButton.getName();
	        playSound("wor/sound/FX_piano01.mp3"); // Adjust the sound file path accordingly
	    }
	 
	 
	private void printRecordedNotes() {
	    System.out.println("녹음된 음악 정보:");
	    for (RecordedNote note : recordedNotes) {
	        System.out.println("음높이: " + note.pitch);
	        System.out.println("녹음 시간: " + note.timestamp);
	        System.out.println("사운드 파일: " + note.soundFile);
	    }
	}
	
//	 private void recordNote(String pitch) {
//		    long timestamp = System.currentTimeMillis() - startTime;
//
//		    GUI.class.getResource("/beep.wav");
//	        RecordedNote note = new RecordedNote(pitch, timestamp, "./beep.wav");
//	        recordedNotes.add(note);
//	    }
	private void recordNote(String pitch) {
	    long timestamp = System.currentTimeMillis() - startTime;

	    // Get the URL of the resource
	    URL resourceUrl = getClass().getResource("/sound/beep.wav");

	 


	    if (resourceUrl == null) {
	        System.out.println("Resource not found: tqtq/beep.wav");
	        return;
	    }

	    // Convert the URL to a file path
	    String filePath;
//	    try {
//	        filePath = Paths.get(resourceUrl.toURI()).toString();
//	    } catch (URISyntaxException e) {
//	        e.printStackTrace();
//	        return;
//	    }

	    RecordedNote note = new RecordedNote(pitch, timestamp, "/sound/beep.wav");
//	    System.out.println(filePath);
	    recordedNotes.add(note);
	}

//	private void playNotes() {
//	    long previousTimestamp = 0; // Initialize to 0 for the first note
//
//	    for (RecordedNote note : recordedNotes) {
//	        try {
//	            // Use getClass().getResourceAsStream to access resources in the classpath
//	            InputStream soundStream = getClass().getResourceAsStream(note.soundFile);
//
//	            if (soundStream == null) {
//	                System.out.println("Resource not found: " + note.soundFile);
//	                continue;
//	            }
//
//	            // Open the audio input stream
//	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundStream);
//
//	            // Print debugging information
//	            System.out.println("Playing note: " + note.pitch);
//	            System.out.println("Timestamp: " + note.timestamp);
//	            System.out.println("Sound file: " + note.soundFile);
//
//	            // Calculate the delay based on the difference between the current and previous timestamps
//	            long delay = note.timestamp - previousTimestamp;
//	            
//	            // Update the previous timestamp for the next iteration
//	            previousTimestamp = note.timestamp;
//
//	            // Delay the thread to play the note at the correct timestamp
//	            Thread.sleep(delay);
//
//	            // Continue with the rest of your code for playing the sound...
//	            // ...
//
//	        } catch (UnsupportedAudioFileException | IOException | InterruptedException ex) {
//	            ex.printStackTrace();
//	        }
//	    }
//	}
	private void playNotes() {
	    long previousTimestamp = 0; // Initialize to 0 for the first note

	    for (RecordedNote note : recordedNotes) {
	        try {
	            // Use getClass().getResourceAsStream to access resources in the classpath
	            InputStream soundStream = getClass().getResourceAsStream(note.soundFile);

	            if (soundStream == null) {
	                System.out.println("Resource not found: " + note.soundFile);
	                continue;
	            }

	            // Open the audio input stream
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundStream);

	            // Print debugging information
	            System.out.println("Playing note: " + note.pitch);
	            System.out.println("Timestamp: " + note.timestamp);
	            System.out.println("Sound file: " + note.soundFile);

	            // Calculate the delay based on the difference between the current and previous timestamps
	            long delay = note.timestamp - previousTimestamp;
	            
	            // Update the previous timestamp for the next iteration
	            previousTimestamp = note.timestamp;

	            // Delay the thread to play the note at the correct timestamp
	            Thread.sleep(delay);

	            // Continue with the rest of your code for playing the sound...
	            // ...
	            playSound(note.soundFile);

	        } catch (UnsupportedAudioFileException | IOException | InterruptedException ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	private class ToggleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (metronome.isRunning()) {
                metronome.end();
            } else {
                metronome = new Metronome();
                metronome.setMetronomeListener(Piano.this);
                metronome.start();
            }
        }
    }
	public void onBpmChange(double bpm) {
        // Update your BPM label or any other UI component
        bpmLabel.setText("BPM: " + bpm);
    }
	


	
	
	

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Piano frame = new Piano();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	@Override
	public void onMetronomeTick() {
		// TODO Auto-generated method stub
		
	}
}
