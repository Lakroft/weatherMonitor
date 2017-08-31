
package weather_monitor.com.lakroft.weathermonitor.modules;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenweatherSourse extends DataSource  {
	private static final String APP_KEY = "ea90508f588c9481b8088d5e27c4f8cf";
	
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

	@Override
	public WeatherData getData() {
		// TODO Auto-generated method stub
		// Outputs in default units. Convert to Metric
		String city = getName();
		double temperature = getMain().getTemp() - 273.15;
		double windV = getWind().getSpeed();
		String windDirection = String.valueOf(getWind().getDeg());
		int humidity = getMain().getHumidity();
		double pressure = hPaToMmHg(getMain().getPressure());
		
		WeatherData data = new WeatherData(
				city, 
				temperature, 
				windV, 
				windDirection, 
				humidity, 
				pressure
		);
		return data;
	}
	
	public static String getURLstring(City city) {
		String URL = "http://api.openweathermap.org/data/2.5/weather?q=" +
		             city.getCityName() + "&appid=" + APP_KEY;
		return URL;
	}
	
	public static double hPaToMmHg (double hPa) {
		double mmHg = hPa * 0.750062;
		return mmHg;
	}
	
	public static String degToWindDir(int deg) {
		// http://moreprom.ru/article.php?id=57
		return null;
	}
}
