package weather_monitor.com.lakroft.weathermonitor.modules;

public class WeatherData {
	protected String city;
	protected double temperature;
	protected double windV;
	protected String windDirection;
	protected int humidity;
	protected double pressure;
	
	public WeatherData (
			String city,
			double temperature,
			double windV,
			String windDirection,
			int humidity,
			double pressure
	) {
		this.city = city;
		this.temperature = temperature;
		this.windV = windV;
		this.windDirection = windDirection;
		this.humidity = humidity;
		this.pressure = pressure;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getWindV() {
		return windV;
	}

	public void setWindV(double windV) {
		this.windV = windV;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
}
