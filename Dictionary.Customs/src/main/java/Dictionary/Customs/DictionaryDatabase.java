package main.java.Dictionary.Customs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DictionaryDatabase {
	
	private static Connection connection;
	private static Statement statement = null; // used for getting info
	private static PreparedStatement preparedStatement = null; // used for making queries
	private static ResultSet resultSet = null; // used for getting info
	
	private static ArrayList<String> wordsList = new ArrayList<>();
	private static ArrayList<String> defsList = new ArrayList<>();
	
	private DictionaryDatabase() {
		
	}
	
	// used to update the database with the new word and def
	public static void update(String word, String def) {
		try {
			connection = get_connection();
			String queryUpdate = "INSERT INTO dictionary (word, def) VALUES (\'" + word + "\', \'" + def + "\')";
			preparedStatement = connection.prepareStatement(queryUpdate);
			preparedStatement.execute();
			close();
			DictionaryCustomsApp.updateList();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void delete(String word) {
		try {
			connection = get_connection();
			String queryDelete = "DELETE FROM dictionary WHERE word=\'" + word + "\'";
			preparedStatement = connection.prepareStatement(queryDelete);
			preparedStatement.execute();
			close();
			DictionaryCustomsApp.updateList();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	private static void close() {
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
	
	public static String[] getWords() {
//		ArrayList<String> wordsArr = new ArrayList<>();
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT word FROM dictionary");
			wordsList = new ArrayList<>();
			for (int i=0; resultSet.next(); i++) {
				wordsList.add(resultSet.getString("word"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return (String []) wordsList.toArray(new String[0]);
	}
	
	public static String[] getDefs() {
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT def FROM dictionary");
			defsList = new ArrayList<>();
			for (int i=0; resultSet.next(); i++) {
				defsList.add(resultSet.getString("def"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return (String []) defsList.toArray(new String[0]);
	}
	
	public static String getDescription() {
		String descr = null;
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT descr FROM wordsy");
			if (resultSet.next()) {
				descr = resultSet.getString("descr");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return descr;
	}
	
	public static String getTitle() {
		String title = null;
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT title FROM wordsy");
			if (resultSet.next()) {
				title = resultSet.getString("title");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return title;
	}

}
