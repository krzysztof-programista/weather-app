package pl.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class DataPackage {

    private double temperature;
    private int pressure;
    private String icon;
    private int cloudiness;
    private double windSpeed;
    private double precipitationProbability;
    private LocalDate date;

    public DataPackage(double temperature, int pressure, String icon, int cloudiness, double windSpeed, double precipitationProbability, LocalDate date) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.icon = icon;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.precipitationProbability = precipitationProbability;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataPackage that = (DataPackage) o;
        return Double.compare(temperature, that.temperature) == 0 && pressure == that.pressure && cloudiness == that.cloudiness && Double.compare(windSpeed, that.windSpeed) == 0 && Double.compare(precipitationProbability, that.precipitationProbability) == 0 && Objects.equals(icon, that.icon) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, pressure, icon, cloudiness, windSpeed, precipitationProbability, date);
    }

    @Override
    public String toString() {
        return "DataPackage{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", icon='" + icon + '\'' +
                ", cloudiness=" + cloudiness +
                ", windSpeed=" + windSpeed +
                ", precipitationProbability=" + precipitationProbability +
                ", date=" + date +
                '}';
    }
}

