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
	
	// Recording-related variables
	private boolean isRecording = false;
	private List<RecordedNote> recordedNotes = new ArrayList<>();
	private long startTime;
	private int duration;
	
	
	// GUI components
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton onOffBtn;
	JLabel bpmLabel;
	private JButton btnRecord;
	private JButton btnStop;
	private JButton btnPlay;
	
	

    public Piano() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 532);
        setTitle("Piano");
        
        // Initialize the content pane
        contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Create the menu bar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Main menu
        menu = new JMenu("Menu");
        menuBar.add(menu);
        
        // Submenu for making notes
        noteMenu= new JMenu("Make note");
        menuBar.add(noteMenu);

        // Menu items
        mainMenu = new JMenuItem("Main");
        mainMenu.addActionListener(this);
        menu.add(mainMenu);
        
        noteMenuItem = new JMenuItem("note");
        noteMenuItem.addActionListener(this);
        noteMenu.add(noteMenuItem);
        
        // Buttons for recording and playing
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
		

        // Piano keys panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(71, 209, 723, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton Csharp = new JButton("");
		Csharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		Csharp.setForeground(new Color(0, 0, 0));
		Csharp.setBackground(new Color(0, 0, 0));
		Csharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		
		Csharp.setBounds(28, 6, 34, 73);
		panel.add(Csharp);
		
		JButton Dsharp = new JButton("");
		Dsharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		Dsharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		Dsharp.setBounds(66, 6, 34, 73);
		panel.add(Dsharp);
		
		JButton C = new JButton("");
		C.setName("C");
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		C.setBounds(6, 6, 42, 107);
		panel.add(C);
		
		JButton E = new JButton("");
		E.setName("E");
		E.setBounds(80, 6, 42, 107);
		E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("E.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		panel.add(E);
		
		JButton D= new JButton("");
		D.setName("D");
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		D.setBounds(43, 6, 42, 107);
		panel.add(D);
		
		JButton Fsharp = new JButton("");
		Fsharp.setName("F#");
		Fsharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		Fsharp.setBounds(139, 6, 34, 73);
		Fsharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(Fsharp);
		
		JButton F = new JButton("");
		F.setName("F");
		F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		F.setBounds(117, 6, 42, 107);
		panel.add(F);
		
		JButton Gsharp= new JButton("");
		Gsharp.setName("G#");
		Gsharp.setBounds(177, 6, 34, 73);
		Gsharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		Gsharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("G#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		
		panel.add(Gsharp);
		
		JButton G = new JButton("");
		G.setName("G");
		G.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("G.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		
		G.setBounds(154, 6, 42, 107);
		panel.add(G);
		
		JButton Asharp= new JButton("");
		Asharp.setName("A#");
		Asharp.setBounds(214, 6, 34, 73);
		Asharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("A#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		Asharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		
		panel.add(Asharp);
		
		JButton A= new JButton("");
		A.setName("A");
		A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("A.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		A.setBounds(191, 6, 42, 107);
		panel.add(A);
		
		JButton B= new JButton("");
		B.setName("B");
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("B.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		B.setBounds(228, 6, 42, 107);
		panel.add(B);
		
		JButton C2sharp= new JButton("");
		C2sharp.setName("C2#");
		C2sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C2#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		C2sharp.setBounds(287, 6, 34, 73);
		C2sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(C2sharp);
		
		
		
		JButton C2 = new JButton("");
		C2.setName("C2");
		C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		
		
		C2.setBounds(265, 6, 42, 107);
		panel.add(C2);
		
		
		
		
		JButton D2sharp= new JButton("");
		D2sharp.setBounds(325, 6, 34, 73);
		
		D2sharp.setName("D2#");
		D2sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D2#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		D2sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(D2sharp);
		
		JButton D2= new JButton("");
		D2.setName("D2");
		D2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		D2.setBounds(302, 6, 42, 107);
		panel.add(D2);
		
		
		JButton E2 = new JButton("");
		E2.setName("E2");
		E2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("E2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		E2.setBounds(339, 6, 42, 107);
		panel.add(E2);
		
		JButton F2sharp= new JButton("");
		F2sharp.setName("F2#");
		F2sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F2#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		F2sharp.setBounds(399, 6, 34, 73);
		F2sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(F2sharp);
		
		JButton F2 = new JButton("");
		F2.setName("F2");
		F2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		F2.setBounds(376, 6, 42, 107);
		panel.add(F2);
		
		JButton G2sharp = new JButton("");
		G2sharp.setName("G2#");
		G2sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("G2#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		G2sharp.setBounds(436, 6, 34, 73);
		G2sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(G2sharp);
		
		JButton G2 = new JButton("");
		G2.setName("G2");
		G2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("G2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		G2.setBounds(413, 6, 42, 107);
		panel.add(G2);
		
		JButton A2sharp = new JButton("");
		A2sharp.setName("A2#");
		A2sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("A2#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		A2sharp.setBounds(472, 6, 34, 73);
		A2sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(A2sharp);
		
		JButton A2 = new JButton("");
		A2.setName("A2");
		A2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("A2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		A2.setBounds(450, 6, 42, 107);
		panel.add(A2);
		
		JButton B2 = new JButton("");
		B2.setName("B2");
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("B2.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		B2.setBounds(487, 6, 42, 107);
		panel.add(B2);
		
		JButton C3sharp = new JButton("");
		C3sharp.setName("C3#");
		C3sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C3#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		C3sharp.setBounds(547, 6, 34, 73);
		C3sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(C3sharp);
		
		JButton C3 = new JButton("");
		C3.setName("C3");
		C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("C3.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		C3.setBounds(524, 6, 42, 107);
		panel.add(C3);
		
		JButton D3sharp = new JButton("");
		D3sharp.setName("D3#");
		D3sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D3#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		D3sharp.setBounds(584, 6, 34, 73);
		D3sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(D3sharp);
		
		JButton D3 = new JButton("");
		D3.setName("D3");
		D3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("D3.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		D3.setBounds(561, 6, 42, 107);
		panel.add(D3);
		
		JButton E3 = new JButton("");
		E3.setName("E3");
		E3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("E3.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		E3.setBounds(598, 6, 42, 107);
		panel.add(E3);
		
		JButton F3sharp = new JButton("");
		F3sharp.setName("F3#");
		F3sharp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F3#.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		F3sharp.setBounds(658, 6, 34, 73);
		F3sharp.setIcon(new ImageIcon(GUI.class.getResource("/img/blackKey.png")));
		panel.add(F3sharp);
		
		JButton F3 = new JButton("");
		F3.setName("F3");
		F3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("F3.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		F3.setBounds(635, 6, 42, 107);
		panel.add(F3);
		
		JButton G3 = new JButton("");
		G3.setName("G3");
		G3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSound("G3.wav");
				   if (isRecording) {
					   String pitchName = ((JButton) e.getSource()).getName();
			           recordNote(pitchName);
			            System.out.printf("%s", pitchName);
			        }
			}

			
		});
		G3.setBounds(672, 6, 42, 107);
		panel.add(G3); 
		

		// Metronome panel
		JPanel metroPanel = new JPanel();
		metroPanel.setBackground(Color.WHITE);
		metroPanel.setBounds(720, 6, 89, 149);
		contentPane.add(metroPanel);
		metroPanel.setLayout(null);

		// Initialize and start the metronome
		metronome = new Metronome(); // Pass 'this' as BpmChangeListener
	    metronome.setMetronomeListener(this);
	    metronome.start();
	    metronome.end();


	    // Button for toggling metronome on/off
		onOffBtn = new JButton("onOffBtn");
		onOffBtn.setBackground(new Color(255, 255, 255));
		
		onOffBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/metronome.png")));
		onOffBtn.setBounds(6, 6, 77, 84);
		onOffBtn.addActionListener(new ToggleHandler());
		metroPanel.add(onOffBtn);
	
		// Set the frame to be visible
        setVisible(true);
    }

    protected void playSound(String string) {
		try {
			URL soundUrl = getClass().getResource("/sound/"+string);
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
	                RecordedNote note = new RecordedNote(pitchName, timestamp,"/sound/"+pitchName+".wav");
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
	        playSound(pitchName+".wav"); // Adjust the sound file path accordingly
	    }
	 
	 
	private void printRecordedNotes() {
	    System.out.println("녹음된 음악 정보:");
	    for (RecordedNote note : recordedNotes) {
	        System.out.println("음높이: " + note.pitch);
	        System.out.println("녹음 시간: " + note.timestamp);
	        System.out.println("사운드 파일: " + note.soundFile);
	    }
	}
	

	private void recordNote(String pitch) {
	    long timestamp = System.currentTimeMillis() - startTime;

	    String soundFile = "/sound/" + pitch + ".wav";
	    RecordedNote note = new RecordedNote(pitch, timestamp, soundFile);
	    recordedNotes.add(note);
	}


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
	            playSound(note.pitch+".wav");

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
//            	metronome.startMetronome();
            	
                metronome = new Metronome();
                metronome.setMetronomeListener(Piano.this);
             
                metronome.start();
            }
        }
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

    // Implementation of MetronomeListener interface
	@Override
	public void onMetronomeTick() {
		// Handle metronome tick event
		// TODO Auto-generated method stub
		
	}
}
