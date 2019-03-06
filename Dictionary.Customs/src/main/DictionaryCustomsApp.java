package main;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	// establish conenction to mysql database
	Connection connection = get_connection();
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

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
		
		DictionaryCustomsApp hi = new DictionaryCustomsApp();
		hi.displayDBInfo();
		
	}
	
	public void displayDBInfo() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM student");
			writeResultSet(resultSet);
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	private static Connection get_connection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friends","root","rootpass");
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return connection;
	}
	
	private static void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String name = resultSet.getString("name");
            String major = resultSet.getString("major");
            System.out.println("Name: " + name);
            System.out.println("Major: " + major);
        }
    }
}
