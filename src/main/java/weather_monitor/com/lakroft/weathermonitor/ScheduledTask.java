package weather_monitor.com.lakroft.weathermonitor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.google.gson.Gson;

import weather_monitor.com.lakroft.weathermonitor.modules.City;
import weather_monitor.com.lakroft.weathermonitor.modules.DataSource;
import weather_monitor.com.lakroft.weathermonitor.modules.OpenweatherSourse;
import weather_monitor.com.lakroft.weathermonitor.modules.WeatherData;
import weather_monitor.com.lakroft.weathermonitor.modules.WundergroundSource;

public class ScheduledTask extends TimerTask {
	
	public List<City> cities = new ArrayList<City>();
	public List<String> sources = new ArrayList<String>();
	
	Date now;
	
	@Override
	public void run() {
		now = new Date();
		System.out.println("Date and time now: " + now);
		try {
			Conn.init();
			Conn.createTable();
			for(String sour : sources) {
				for(City city : cities) {
					
					String url = null;
					if(sour.equals(WundergroundSource.class.getName())) {
						url = WundergroundSource.getURLstring(city);
					}
					if(sour.equals(OpenweatherSourse.class.getName())) {
						url = OpenweatherSourse.getURLstring(city);
					}
					byte[] data = null;
					String jsonAnser = null;
					
					try {
						URL urlUse = new URL(url);
						HttpURLConnection conn = null;
						conn = (HttpURLConnection) urlUse.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("Content-length",  "0");
						conn.setUseCaches(false);
						conn.setAllowUserInteraction(false);
						conn.setConnectTimeout(1000);
						conn.setReadTimeout(1000);
						conn.connect();
						
						if(conn.getResponseCode()==201 || conn.getResponseCode()==200)
						{
							//String jsonReply = conn.getResponseMessage();
							//System.out.println(jsonReply);
							InputStream is = conn.getInputStream();
	
							byte[] buffer = new byte[8192]; 
							int bytesRead;
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							while ((bytesRead = is.read(buffer)) != -1) {
								baos.write(buffer, 0, bytesRead);
							}
							data = baos.toByteArray();
							jsonAnser = new String(data, "UTF-8");
							//System.out.print(jsonAnser);
						} else {
							System.out.println("Not Connected: " +  conn.getResponseMessage());
						}
						
						Gson gson = new Gson();
						DataSource dataObj = null;
						
						if(sour.equals(WundergroundSource.class.getName())) {
							dataObj = gson.fromJson(jsonAnser, WundergroundSource.class);
						}
						if(sour.equals(OpenweatherSourse.class.getName())) {
							dataObj = gson.fromJson(jsonAnser, OpenweatherSourse.class);
						}
						WeatherData weather = dataObj.getData();
						
						Conn.insertToTable(weather);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
