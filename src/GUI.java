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
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton onOffBtn;
	private JLabel bpmLabel;
	//for note panel
	private JButton[][] buttons;
    private ImageIcon defaultImg = new ImageIcon(GUI.class.getResource("/img/pinoNoteBg1.png"));
    private ImageIcon beginImg = new ImageIcon(GUI.class.getResource("/img/pinoNoteBgBegin.png"));
    private String[] clickedButtons = {"  ", "  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", "  ", "  ", "  ", "  ","  ","  ","  ","  ", };
	private JTextArea textArea;
	private String[] labels = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", 
            "C2"};



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
		setBounds(100, 100, 842, 453);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
        
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Get a Piano");
		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewJgoodiesLabel.setForeground(Color.BLACK);
		lblNewJgoodiesLabel.setBounds(381, 6, 113, 28);
		contentPane.add(lblNewJgoodiesLabel);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(736, 266, 106, 149);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		
		onOffBtn = new JButton("");
		onOffBtn.setBackground(new Color(255, 255, 255));
		onOffBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		onOffBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/metronome.png")));
		onOffBtn.setBounds(6, 6, 94, 84);
		panel_1.add(onOffBtn);
		
		JButton plusBtn = new JButton("+");
		plusBtn.setBounds(16, 89, 41, 29);
		panel_1.add(plusBtn);
		
		JButton minusBtn = new JButton("-");
		minusBtn.setBounds(52, 89, 41, 29);
		panel_1.add(minusBtn);
		
		bpmLabel = new JLabel("BPM: " + Metronome.bpm);
		bpmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bpmLabel.setBounds(6, 123, 97, 20);
		panel_1.add(bpmLabel);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(37, 143, 698, 272);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(16, 143, 19, 272);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(16, 59, 719, 82);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(6, 5, 711, 71);
		panel_4.add(textArea);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(736, 211, 106, 56);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel playStatusLabel = DefaultComponentFactory.getInstance().createLabel("Play");
		playStatusLabel.setBounds(59, 15, 47, 25);
		panel_5.add(playStatusLabel);
		playStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton playBtn = new JButton("");
		playBtn.setBounds(6, 6, 47, 42);
		panel_5.add(playBtn);
		playBtn.setIcon(new ImageIcon(GUI.class.getResource("/img/play.png")));
		
		JPanel panel = new JPanel();
		panel.setBounds(736, 143, 106, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton makeFileBtn = new JButton("MakeFile");
		makeFileBtn.setHorizontalAlignment(SwingConstants.LEADING);
		makeFileBtn.setBounds(0, 6, 106, 55);
		panel.add(makeFileBtn);
		

		
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

		                // After playing, set playBtn text to "Play" and enable panel_2 buttons
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
		 panel_3.add(DefaultComponentFactory.getInstance().createLabel(label));
		}
		
		// Create buttons array
        buttons = new JButton[32][13];
        
        int buttonWidth = panel_2.getWidth() / 32;
        int buttonHeight = panel_2.getHeight() / 13;

     // Populate buttons in panel_2
        for (int col = 0; col < 32; col++) {
            for (int row = 0; row < 13; row++) {
                buttons[col][row] = createButton(col, row, buttonWidth, buttonHeight);
                panel_2.add(buttons[col][row]);
                //buttons[col][row].addActionListener(e -> handlePanel2ButtonClick(col, row));
            
            }
        }
        
        

		toggleHandler handler = new toggleHandler();
	    onOffBtn.addActionListener(handler);

	    plusHandler plusHandle = new plusHandler();
	    plusBtn.addActionListener(plusHandle);

	    minusHandler minusHandle = new minusHandler();
	    minusBtn.addActionListener(minusHandle);
	    
	 // Add an ActionListener to makeFileBtn
        makeFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
	    
	}
	
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
	
	// Add a method to enable/disable panel_2 buttons
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

	//button handler
	private JButton createButton(int col, int row, int buttonWidth, int buttonHeight) {
        JButton button = new JButton(defaultImg);

        int x = col * buttonWidth;
        int y = row * buttonHeight;
        button.setBounds(x, y, buttonWidth, buttonHeight);

     // Add ActionListener to handle button clicks
        button.addActionListener(e -> handleButtonClick(col, row));

        return button;

    }


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

	    // Update textPane in panel_4
	    updateTextPane();
	}

	
	private void updateTextPane() {
	    StringBuilder text = new StringBuilder();
	    
	    // Sort clickedButtons based on row in descending order
	    //clickedButtons.sort((a, b) -> Integer.compare(b[1], a[1]));

	    // Iterate through clickedButtons and append corresponding note names to text
	    for (String info : clickedButtons) {
	        text.append(info).append(" ");
	    }

	    // Update textPane with the generated text
	    textArea.setText(text.toString());
	}
	
	private String getNoteName(int row) {
	    row = Math.max(0, Math.min(row, labels.length - 1));

	    return labels[row];
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



	
	
	//thread
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

	
	
	//
	

}
	
	
