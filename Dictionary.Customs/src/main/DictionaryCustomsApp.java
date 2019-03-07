package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DictionaryCustomsApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int WINWIDTH = 500;
	private static final int WINHEIGHT = 500;
	
	// establish conenction to mysql database
	Connection connection;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	// front page components
	
	JFrame frame;
	JPanel titlePanel;
	FlowLayout titleFlowLayout;
	JLabel titleLbl;
	JPanel coverPanel;
	JButton coverAddButton;
	JPanel controlsPanel;
	JButton controlsFwdButton;
	JButton controlsBackButton;
	JPanel mainPanel;
	Border coverBorder;
	

	private void createAndShowGUI() {
		
        //Create and set up the window.
        frame = new JFrame("Wordsy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((int)(screenSize.getWidth()/1.5), (int)(screenSize.getHeight()/3), WINWIDTH, WINHEIGHT);
        frame.setResizable(false);
        
        // create a title panel
        titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(WINWIDTH, 50));      
        titleFlowLayout = new FlowLayout();
        titleFlowLayout.setAlignment(FlowLayout.LEFT);
        titlePanel.setLayout(titleFlowLayout);
        
        // add the cover panel
        coverPanel = new JPanel();
        coverPanel.setPreferredSize(new Dimension(WINWIDTH, 400));
        coverPanel.setLayout(new FlowLayout());
        coverBorder = BorderFactory.createMatteBorder(2,0,2,0,Color.GRAY);
        coverPanel.setBorder(coverBorder);
        
        // add the controls panel
        controlsPanel = new JPanel();
        controlsPanel.setPreferredSize(new Dimension(WINWIDTH, 50));
        controlsPanel.setLayout(new BorderLayout());
        
        // added components to the title panel
        titleLbl = new JLabel("Wordsy");
        titlePanel.add(titleLbl);
        
        // add the components to cover panel
        coverAddButton = new JButton();
        coverAddButton.setText("Add to dictionary...");
        coverPanel.add(coverAddButton);
        
        // add the components to controls panel
        controlsBackButton = new JButton();
        controlsFwdButton = new JButton();
        controlsBackButton.setText("<<");
        controlsFwdButton.setText(">>");
        controlsPanel.add(controlsBackButton, BorderLayout.WEST);
        controlsPanel.add(controlsFwdButton, BorderLayout.EAST);

        
        // all panels go into this main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(WINWIDTH, WINHEIGHT));
        mainPanel.add(titlePanel);
        mainPanel.add(coverPanel);
        mainPanel.add(controlsPanel);
 
        //Add the titlePanel.
        frame.setContentPane(mainPanel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	/**
	 * This method must run to connect to the database.
	 */
	private void connectToDB() {
		try {
			connection = get_connection();
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
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/friends","root","root");
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return connection;
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
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
	
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DictionaryCustomsApp().createAndShowGUI();
            }
        });
	}
}
