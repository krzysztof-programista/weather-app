package pl.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class DataPackage {

    private double temperature;
    private int pressure;
    private int humidity;
    private String icon;
    private int cloudiness;
    private double windSpeed;
    private double precipitationProbability;
    private LocalDate date;
    private LocalTime time;

    public DataPackage(double temperature, int pressure, int humidity, String icon, int cloudiness, double windSpeed, double precipitationProbability, LocalDate date, LocalTime time) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.icon = icon;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.precipitationProbability = precipitationProbability;
        this.date = date;
        this.time = time;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getIcon() {
        return icon;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPrecipitationProbability() {
        return precipitationProbability;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPackage that = (DataPackage) o;
        return Double.compare(temperature, that.temperature) == 0 && pressure == that.pressure && humidity == that.humidity && cloudiness == that.cloudiness && Double.compare(windSpeed, that.windSpeed) == 0 && Double.compare(precipitationProbability, that.precipitationProbability) == 0 && Objects.equals(icon, that.icon) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, pressure, humidity, icon, cloudiness, windSpeed, precipitationProbability, date, time);
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", icon='" + icon + '\'' +
                ", cloudiness=" + cloudiness +
                ", windSpeed=" + windSpeed +
                ", precipitationProbability=" + precipitationProbability +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}