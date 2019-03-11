package main.java.Dictionary.Customs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddDefWindow {
	
	private static final int WINWIDTH = 300;
	private static final int WINHEIGHT = 320;
	private static String[] wordsList = new String[20];
	private static String[] defsList = new String[20];
	private static String word;
	private static String def;

	private AddDefWindow() {
		
	}
	
	public static void run(DictionaryCustomsApp dca) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(dca);
            }
        });
	}
	
	private static void createAndShowGUI(DictionaryCustomsApp dca) {
		
		JDialog defFrame = new JDialog(dca, "Wordsy",true);
		defFrame.setSize(WINWIDTH, WINHEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        defFrame.setBounds((int)(screenSize.getWidth()/1.5) + 100, (int)(screenSize.getHeight()/3) + 100, WINWIDTH, WINHEIGHT);
        defFrame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(15,15,15,15));
		
		JLabel wordLbl = new JLabel();
		wordLbl.setText("Word:");
		
		JTextField wordTextField = new JTextField();
		wordTextField.setPreferredSize(new Dimension(WINWIDTH,15));
		
		JLabel defLbl = new JLabel();
		defLbl.setText("Definition:");
		
		JTextField defTextField = new JTextField();
		defTextField.setPreferredSize(new Dimension(WINWIDTH,120));
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setSize(new Dimension(WINWIDTH,30));
		JButton cancelButton = new JButton();
		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				defFrame.dispose();
			}
			
		});
		
		JButton okButton = new JButton();
		okButton.setText("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DictionaryDatabase.update(wordTextField.getText(), defTextField.getText());
				defFrame.dispose();
			}
			
		});
		controlsPanel.add(okButton);
		controlsPanel.add(cancelButton);
		controlsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		controlsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panel.add(wordLbl);
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		panel.add(wordTextField);
		panel.add(Box.createRigidArea(new Dimension(5,10)));
		panel.add(defLbl);
		panel.add(Box.createRigidArea(new Dimension(5,5)));
		panel.add(defTextField);
		panel.add(Box.createRigidArea(new Dimension(5,10)));
		panel.add(controlsPanel);
		
		defFrame.getContentPane().add(panel);
		defFrame.setVisible(true);
	}
	
}
