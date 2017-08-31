package weather_monitor.com.lakroft.weathermonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import weather_monitor.com.lakroft.weathermonitor.modules.WeatherData;

public class Conn {
	public static Connection conn;
	public static Statement statement;
	public static ResultSet resultSet;
	
	public static void init() throws ClassNotFoundException, SQLException {
		conn = null;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:WeatherDB.s3db");
		
		System.out.println("DataBase connected");
	}
	
	public static void createTable() throws ClassNotFoundException, SQLException {
		statement = conn.createStatement();
		statement.execute(
				"CREATE TABLE if not exists 'weather' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " 'city' TEXT, 'temperature' REAL, 'windV' REAL, 'windDirection' TEXT, "
				+ "'humidity' INTEGER, 'pressure' INTEGER, 'date' TEXT);"
		);
		
		System.out.println("Table created or already exists");
	}
	
	public static void insertToTable(WeatherData data) throws SQLException {
		statement.execute("INSERT INTO 'weather' (city, temperature, windV, windDirection, humidity, pressure, date) VALUES ('" + 
				data.getCity() + "', " + 
				data.getTemperature() + ", " +
				data.getWindV() + ", '" +
				data.getWindDirection() + "', " +
				data.getHumidity()  + ", " +
				data.getPressure() + ", " +
				"datetime('now')); ");
		System.out.println("Inserted");
	}
	
	public static void readTable() throws ClassNotFoundException, SQLException {
		resultSet = statement.executeQuery("SELECT * FROM weather");
		System.out.println("Table entryes:");
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String city = resultSet.getString("city");
			String temperature = resultSet.getString("temperature");
			System.out.println(
					"\tENTRY: id = " + id + 
					"; city = " + city +
					"; temperature = " + temperature);
		}
		System.out.println("End of table");
	}
	
	public static void close() throws ClassNotFoundException, SQLException {
		resultSet.close();
		statement.close();
		conn.close();
		
		System.out.println("Connections closed");
	}
}
