package main;

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
	private PreparedStatement preparedStatement = null; // used for making queries
	private static ResultSet resultSet = null; // used for getting info
	
	private static String[] wordsList = new String[14];
	private static String[] defsList = new String[14];
	
	private DictionaryDatabase() {
		
	}
	
	public static void main(String[] args) {
		DictionaryDatabase.connectToDB();
	}
	
	/**
	 * This method must run to connect to the database.
	 */
	private static void connectToDB() {
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM student");
			writeResultSet(resultSet);
		
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	// used to update the database with the new word and def
	public void update(String word, String def) {
		
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
	
	// remove static
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
			for (int i=0; resultSet.next(); i++) {
				wordsList[i] = resultSet.getString("word");
			}
//			while(resultSet.next()) {
//				wordsArr.add(resultSet.getString("word"));
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		wordsList = (String[]) wordsArr.toArray();
		close();
		return wordsList;
	}
	
	public static String[] getDefs() {
		try {
			connection = get_connection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT def FROM dictionary");
			for (int i=0; resultSet.next(); i++) {
				defsList[i] = resultSet.getString("def");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return defsList;
	}

}
