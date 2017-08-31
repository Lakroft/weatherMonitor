package weather_monitor.com.lakroft.weathermonitor;

import java.sql.SQLException;
import java.util.Timer;

import weather_monitor.com.lakroft.weathermonitor.modules.City;
import weather_monitor.com.lakroft.weathermonitor.modules.OpenweatherSourse;
import weather_monitor.com.lakroft.weathermonitor.modules.WundergroundSource;

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, InterruptedException
    {
    	Conn.init();
    	Conn.createTable();
    	Conn.readTable();
    	Conn.close();
    	
    	int sleepTime = 60000; // 1 minute beetween requests
    	Timer time = new Timer();
    	ScheduledTask st = new ScheduledTask();
    	st.cities.add(new City("Novosibirsk", "RU"));
    	st.sources.add(WundergroundSource.class.getName());
    	st.sources.add(OpenweatherSourse.class.getName());
    	
    	time.schedule(st, 0, sleepTime);
    	Thread.sleep((long) (sleepTime*9.5)); // As default, make 10 requests
    	
    	Conn.init();
    	Conn.createTable();
    	Conn.readTable();
    	Conn.close();
    	System.out.println("End of Prorgamm");
    	System.exit(0);
    }
}
