package getA;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
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
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//Class definition for the 'Makenote' JFrame
public class Makenote extends JFrame implements ActionListener, MetronomeListener {

	// Serialization ID
	private static final long serialVersionUID = 1L;
	
	// GUI components
	private JPanel contentPane;
	private JButton onOffBtn;
	private JLabel bpmLabel;
	//for note panel
	private JButton[][] buttons;
    private ImageIcon defaultImg = new ImageIcon(Makenote.class.getResource("/img/defaultNote.png"));
    private ImageIcon beginImg = new ImageIcon(Makenote.class.getResource("/img/cahngeNote.png"));
    private String[] clickedButtons = {"  ", "  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", };
	private JTextArea textArea;
	private String[] labels = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", 
            "C2"};
	private Metronome metronome;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Makenote frame = new Makenote();
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
	public Makenote() {
		

		
		// Set up the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 453);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
        
		// GUI components initialization
		JLabel lblNewJgoodiesLabel = new JLabel("Get a Piano");
		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewJgoodiesLabel.setForeground(Color.BLACK);
		lblNewJgoodiesLabel.setBounds(381, 6, 113, 28);
		contentPane.add(lblNewJgoodiesLabel);
	

		// Metronome control panel
		JPanel metroPanel = new JPanel();
		metroPanel.setBackground(Color.WHITE);
		metroPanel.setBounds(736, 266, 106, 149);
		contentPane.add(metroPanel);
		metroPanel.setLayout(null);
		
		metronome = new Metronome(); // Pass 'this' as BpmChangeListener
	    metronome.setMetronomeListener(this);
	    metronome.start();
	    metronome.end();			

		
		onOffBtn = new JButton("");
		onOffBtn.setBackground(new Color(255, 255, 255));
	
		onOffBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/metronome.png")));
		onOffBtn.setBounds(6, 6, 94, 84);
		onOffBtn.addActionListener(new ToggleHandler());
		metroPanel.add(onOffBtn);
		
		

		
		// Note panel
		JPanel notePanel = new JPanel();
		notePanel.setBounds(37, 143, 698, 272);
		contentPane.add(notePanel);
		notePanel.setLayout(null);
		
		//label panel
		JPanel instrumentLabelPanel = new JPanel();
		instrumentLabelPanel.setBounds(16, 143, 19, 272);
		contentPane.add(instrumentLabelPanel);
		instrumentLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Text area for displaying clicked buttons
		JPanel scorePanel = new JPanel();
		scorePanel.setBounds(16, 59, 719, 82);
		contentPane.add(scorePanel);
		scorePanel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(6, 5, 711, 71);
		scorePanel.add(textArea);
		
		// Play control panel
		JPanel playStatusPanel = new JPanel();
		playStatusPanel.setBackground(Color.WHITE);
		playStatusPanel.setBounds(736, 211, 106, 56);
		contentPane.add(playStatusPanel);
		playStatusPanel.setLayout(null);
		

		JLabel playStatusLabel = new JLabel("Play");
		playStatusLabel.setBounds(59, 15, 47, 25);
		playStatusPanel.add(playStatusLabel);
		playStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton playBtn = new JButton("");
		playBtn.setBounds(6, 6, 47, 42);
		playStatusPanel.add(playBtn);
		playBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/play.png")));
		
		// File control panel
		JPanel panel = new JPanel();
		panel.setBounds(736, 143, 106, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton makeFileBtn = new JButton("MakeFile");
		makeFileBtn.setHorizontalAlignment(SwingConstants.LEADING);
		makeFileBtn.setBounds(0, 6, 106, 55);
		panel.add(makeFileBtn);
		

		// Play button action listener
		playBtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Check if already playing
		        if (playStatusLabel.getText().equals("Play")) {
		            playStatusLabel.setText("Playing");
		            playStatusLabel.setForeground(Color.RED); // Set text color to red
		            setPanel2ButtonsEnabled(false);

		            // Start a new thread for playing sounds
		            new Thread(() -> {
		            	// Thread for playing sounds
		                for (int col = 0; col < 32; col++) {
		                    String note = clickedButtons[col];

		                    if (!note.equals("  ")) {
		                        try {
		                            URL soundUrl = GUI.class.getResource("/sound/" + note + ".wav");
		                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
		                            Clip clip = AudioSystem.getClip();
		                            clip.open(audioInputStream);

		                            // Start playing the clip
		                            clip.start();

		                            // Sleep for the duration of the sound
		                            Thread.sleep((long) (300 * (60 / Metronome.bpm)));
		                        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e1) {
		                            e1.printStackTrace();
		                        }
		                    } else {
		                        try {
		                            // Sleep for an empty beat
		                            Thread.sleep((long) (500 * (60 / Metronome.bpm)));
		                        } catch (InterruptedException e1) {
		                            e1.printStackTrace();
		                        }
		                    }
		                }

		                // After playing, set playBtn text to "Play" and enable notePanel buttons
		                SwingUtilities.invokeLater(() -> {
		                    playStatusLabel.setText("Play");
		                    playStatusLabel.setForeground(Color.BLACK); // Set text color back to black
		                    setPanel2ButtonsEnabled(true);
		                });
		            }).start();
		        }
		    }
		});

		for (String label : labels) {
		    JLabel labelComponent = new JLabel(label);
		    instrumentLabelPanel.add(labelComponent);
		}

		
		// Create buttons array
        buttons = new JButton[32][13];
        
        int buttonWidth = notePanel.getWidth() / 32;
        int buttonHeight = notePanel.getHeight() / 13;

     // Populate buttons in notePanel
        for (int col = 0; col < 32; col++) {
            for (int row = 0; row < 13; row++) {
                buttons[col][row] = createButton(col, row, buttonWidth, buttonHeight);
                 notePanel.add(buttons[col][row]);
            
            }
        }
        
     // File button action listener
        makeFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
	    
	}
	
	// Method to save the clicked buttons to a file
	private void saveToFile() {
        try {
            // Choose a file to save
            JFileChooser fileChooser = new JFileChooser();
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                // Write the content of textArea to the selected file using FileOutputStream
                try (FileOutputStream fos = new FileOutputStream(fileToSave);
                     PrintWriter writer = new PrintWriter(fos)) {
                    writer.println(textArea.getText());
                }

                JOptionPane.showMessageDialog(this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
	
	// Add a method to enable/disable notePanel buttons
	private void setPanel2ButtonsEnabled(boolean enabled) {
	    for (int col = 0; col < 32; col++) {
	        for (int row = 0; row < 13; row++) {
	            buttons[col][row].setEnabled(enabled);
	        }
	    }
	}
	
	
	// Method to play the sound of a specific note
    private void playNoteSound(String note) {
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

 // Method to create a button in notePanel
	private JButton createButton(int col, int row, int buttonWidth, int buttonHeight) {
        JButton button = new JButton(defaultImg);

        int x = col * buttonWidth;
        int y = row * buttonHeight;
        button.setBounds(x, y, buttonWidth, buttonHeight);

     // Add ActionListener to handle button clicks
        button.addActionListener(e -> handleButtonClick(col, row));

        return button;

    }

	// Method to handle button clicks in notePanel
	private void handleButtonClick(int col, int row) {
	    JButton currentButton = buttons[col][row];

	    if (currentButton.getIcon().equals(beginImg)) {
	        // If the icon is beginImg, change it back to defaultImg
	        currentButton.setIcon(defaultImg);
	        clickedButtons[col] = "  ";
	    } else if (currentButton.getIcon().equals(defaultImg)) {
	        // If the icon is defaultImg, change it to beginImg
	        currentButton.setIcon(beginImg);
	        String noteName = getNoteName(row);

	        if (!clickedButtons[col].equals("  ")) {
	            // Display an error message if there is already a note at this position
	            JOptionPane.showMessageDialog(this, "Note already exists for this note.", "Error", JOptionPane.ERROR_MESSAGE);
	            // Change the icon back to defaultImg
	            currentButton.setIcon(defaultImg);
	        } else {
	            clickedButtons[col] = noteName;
	        }
	    }

	    // Update textPane in scorePanel
	    updateTextPane();
	}

	// Method to update the textPane in scorePanel
	private void updateTextPane() {
	    StringBuilder text = new StringBuilder();

	    // Iterate through clickedButtons and append corresponding note names to text
	    for (String info : clickedButtons) {
	        text.append(info).append(" ");
	    }

	    // Update textPane with the generated text
	    textArea.setText(text.toString());
	}
	
	// Method to get the note name based on the row
	private String getNoteName(int row) {
	    row = Math.max(0, Math.min(row, labels.length - 1));

	    return labels[row];
	}
	
	// ActionListener for the metronome toggle
	private class ToggleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (metronome.isRunning()) {
                metronome.end();
            } else {
                metronome = new Metronome();
                metronome.setMetronomeListener(Makenote.this);
                metronome.start();
            }
        }
    }
	public void onBpmChange(double bpm) {
        // Update your BPM label or any other UI component
        bpmLabel.setText("BPM: " + bpm);
    }
	
	// MetronomeListener interface method (unused in this implementation)
	@Override
	public void onMetronomeTick() {
		// TODO Auto-generated method stub
		
	}

	// ActionListener interface method (unused in this implementation)
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}





	
	
	
