
package weather_monitor.com.lakroft.weathermonitor.modules;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import weather_monitor.com.lakroft.weathermonitor.modules.DataSource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WundergroundSource extends DataSource {
	private static final String APP_KEY = "63278a695da4d91c";
	
    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public WeatherData getData() {
		
		String city = getCurrentObservation().getDisplayLocation().getCity();
		double temperature = getCurrentObservation().getTempC();
		double windV = getCurrentObservation().getWindKph();
		String windDirection = getCurrentObservation().getWindDir();
		String humStr = getCurrentObservation().getRelativeHumidity();
		humStr = humStr.replaceAll("%", "");
		String presStr = getCurrentObservation().getPressureMb();
		
		WeatherData data = new WeatherData(
				city, 
				temperature, 
				windV, 
				windDirection, 
				Integer.parseInt(humStr), 
				Double.parseDouble(presStr)
		);
		return data;
	}

	public static String getURLstring(City city) {
		String URL = "http://api.wunderground.com/api/" + APP_KEY + 
		             "/conditions/q/" + city.getCountry() + 
		             "/" + city.getCityName() + ".json";
		return URL;
	}
	
}
