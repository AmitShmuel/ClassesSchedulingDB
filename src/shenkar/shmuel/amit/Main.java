package shenkar.shmuel.amit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			createTable();
			System.out.println("Table created successfully!");
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
	
	public static void createTable() throws Exception {
		
		Connection conn = getConnection();
		
		Statement statement = conn.createStatement();
		
		//dropTables(statement);
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+
							   "classroom(building int, number int, floor int NOT NULL, "+
							   "PRIMARY KEY(building, number))");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
				   			   "course(name varchar(10) NOT NULL, number int, semester char NOT NULL, "+
				   			   "year int NOT NULL, hours int NOT NULL, "+
				   			   "PRIMARY KEY(number))");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
	   			   			   "lecturer(name_first varchar(10) NOT NULL, name_last varchar(10) NOT NULL, "+
	   			   			   "id int, birthdate date NOT NULL, age int NOT NULL, "+
	   			   			   "PRIMARY KEY(id))");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
	   			   			   "phone(lecturerID int, number varchar(10), "+
	   			   			   "PRIMARY KEY(lecturerID))");
		
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
	   			   			   "time_table(building int, class_number int, "+
	   			   			   "course_number int, lecturerID int, day varchar(10), hour int, "+
	   			   			   "PRIMARY KEY(course_number, lecturerID, building, class_number, day, hour))");
	}
	
	
	public static Connection getConnection() {
		
		try {
			String driver = "com.mysql.jdbc.Driver"; 
			String url = "jdbc:mysql://localhost:3306/shenkar?useSSL=false&&allowMultiQueries=true";
			String username = "root";
			String password = "14563";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			System.out.println("Connected");
			return conn;
		}
		catch(Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static void dropTables(Statement s){
		
		try {
			s.executeUpdate("DROP DATABASE IF EXISTS shenkar; CREATE DATABASE shenkar");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
