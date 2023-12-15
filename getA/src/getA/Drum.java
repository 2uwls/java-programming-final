package getA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Drum extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem mainMenu;
	private JMenu noteMenu;
	private JMenuItem noteMenuItem;

    public Drum() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 532);
        setTitle("Drum");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Menu");
        menuBar.add(menu);
        
//        noteMenu = new JMenu("Make note");
//        menuBar.add(noteMenu);

        mainMenu = new JMenuItem("Main");
        mainMenu.addActionListener(this);
        menu.add(mainMenu);
        
//        noteMenuItem = new JMenuItem("note");
//        noteMenuItem.addActionListener(this);
//        noteMenu.add(noteMenuItem);       
        

        // Add drum-related components here

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenu) {
            new GUI().setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Drum frame = new Drum();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
