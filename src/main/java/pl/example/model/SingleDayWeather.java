package pl.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class SingleDayWeather {

    private final double tempInCelsius;
    private final LocalDate date;

    public SingleDayWeather(double tempInCelsius, LocalDate date) {
        this.tempInCelsius = tempInCelsius;
        this.date = date;
    }

    public double getTempInCelsius() {
        return tempInCelsius;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleDayWeather that = (SingleDayWeather) o;
        return Double.compare(tempInCelsius, that.tempInCelsius) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tempInCelsius, date);
    }

    @Override
    public String toString() {
        return "SingleDayWeather{" +
                "tempInCelsius=" + tempInCelsius +
                ", date=" + date +
                '}';
    }
}
