package getA;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {
    JFrame main;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu InstrumentMenu;
    private JMenuItem pianoMenu;
    private JMenuItem drumMenu;

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

    public GUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 842, 532);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 132, 22);
        contentPane.add(menuBar);

        InstrumentMenu = new JMenu("Instrument");
        menuBar.add(InstrumentMenu);

        pianoMenu = new JMenuItem("Piano");
        pianoMenu.addActionListener(this); // ActionListener 추가
        InstrumentMenu.add(pianoMenu);

        drumMenu = new JMenuItem("Drum");
        drumMenu.addActionListener(this); // ActionListener 추가
        InstrumentMenu.add(drumMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pianoMenu) {
            new Piano();
//            disposeMain();
        } else if (e.getSource() == drumMenu) {
            new Drum();
            disposeMain();
        }
    }

    private void disposeMain() {
        dispose(); // 현재 창 닫기
    }
}
