package weather_monitor.com.lakroft.weathermonitor.modules;

public class City {
	protected String cityName;
	protected String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public City(String name, String country) {
		this.cityName = name;
		this.country = country;
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
