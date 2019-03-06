package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class DictionaryCustomsApp extends JFrame{
	
	// Specify the look and feel to use by defining the LOOKANDFEEL constant
    // Valid values are: null (use the default), "Metal", "System", "Motif",
    // and "GTK"
	final static String LOOKANDFEEL = "System";

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
//	private static void initLookAndFeel() {
//        String lookAndFeel = null;
//       
//        if (LOOKANDFEEL != null) {
//            if (LOOKANDFEEL.equals("Metal")) {
//                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
//              //  an alternative way to set the Metal L&F is to replace the 
//              // previous line with:
//              // lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
//            }
//            
//            else if (LOOKANDFEEL.equals("System")) {
//                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//            } 
//            else if (LOOKANDFEEL.equals("Motif")) {
//                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
//            } 
//            else if (LOOKANDFEEL.equals("GTK")) { 
//                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
//            } 
//            else {
//                System.err.println("Unexpected value of LOOKANDFEEL specified: "
//                                   + LOOKANDFEEL);
//                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
//            }
//
//            try {
//                UIManager.setLookAndFeel(lookAndFeel);
//            } 
//            
//            catch (ClassNotFoundException e) {
//                System.err.println("Couldn't find class for specified look and feel:"
//                                   + lookAndFeel);
//                System.err.println("Did you include the L&F library in the class path?");
//                System.err.println("Using the default look and feel.");
//            } 
//            
//            catch (UnsupportedLookAndFeelException e) {
//                System.err.println("Can't use the specified look and feel ("
//                                   + lookAndFeel
//                                   + ") on this platform.");
//                System.err.println("Using the default look and feel.");
//            } 
//            
//            catch (Exception e) {
//                System.err.println("Couldn't get specified look and feel ("
//                                   + lookAndFeel
//                                   + "), for some reason.");
//                System.err.println("Using the default look and feel.");
//                e.printStackTrace();
//            }
//        }
//    }

	private static void createAndShowGUI() {
		
		//Set the Look and feel
//		initLookAndFeel();
		
		//Make sure we have nice window decorations.
//        JFrame.setDefaultLookAndFeelDecorated(true);
		
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
