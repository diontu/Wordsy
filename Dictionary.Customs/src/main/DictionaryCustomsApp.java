package main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DictionaryCustomsApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WINWIDTH = 500;
	private static final int WINHEIGHT = 400;

	private static void createAndShowGUI() {
		
        //Create and set up the window.
        JFrame frame = new JFrame("Dictionary Customs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create a main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(WINWIDTH, WINHEIGHT));
        
        // added a label
        JLabel label2 = new JLabel("Hello World");
        
        // added components to mainPanel
        mainPanel.add(label2);
 
        //Add the testPanel.
        frame.getContentPane().add(mainPanel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
