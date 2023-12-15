// Import necessary packages
package getA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

//Define the GUI class that extends JFrame and implements ActionListener
public class GUI extends JFrame implements ActionListener {
	// JFrame instance variable
    JFrame main;
    // Serialization ID
    private static final long serialVersionUID = 1L;
    
	// Components for the GUI
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu InstrumentMenu;
    private JMenuItem pianoMenu;
    private JMenuItem drumMenu;
    private JLabel backgroundLabel; // JLabel for background image

    // Main method to launch the GUI
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	// Create an instance of the GUI class and make it visible
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    // Constructor for the GUI class
    public GUI() {
    	// Set default close operation and layout for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 532);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Create a menu bar and add it to the content pane
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 132, 22);
        contentPane.add(menuBar);

        // Create a menu for instruments and add it to the menu bar	
        InstrumentMenu = new JMenu("Instrument");
        menuBar.add(InstrumentMenu);

        // Create menu items for piano and drum, and add them to the instrument menu
        pianoMenu = new JMenuItem("Piano");
        pianoMenu.addActionListener(this); // addActionListener
        InstrumentMenu.add(pianoMenu);

        drumMenu = new JMenuItem("Drum");
        drumMenu.addActionListener(this); // add ActionListener
        InstrumentMenu.add(drumMenu);
        
        // Load the background image from the resources
        ImageIcon backgroundImage = new ImageIcon(GUI.class.getResource("/img/main.png"));

        
        // Create a JLabel to hold the background image
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Add the background JLabel to the content pane as the bottom layer
        contentPane.add(backgroundLabel);
    }

    // ActionListener implementation for menu item clicks
    @Override
    public void actionPerformed(ActionEvent e) {
    	// Check which menu item was clicked and create a new instance of the corresponding class
        if (e.getSource() == pianoMenu) {
            new Piano(); // Create a new Piano object
        } else if (e.getSource() == drumMenu) {
            new Drum(); // Create a new Drum object
        }
    }


}
