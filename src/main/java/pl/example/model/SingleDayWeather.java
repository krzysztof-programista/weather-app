package pl.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SingleDayWeather {

    private Collection<DataPackage> threeHourWeatherList = new ArrayList<DataPackage>();

    public SingleDayWeather(Collection<DataPackage> threeHourWeatherList) {
        this.threeHourWeatherList = threeHourWeatherList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleDayWeather that = (SingleDayWeather) o;
        return Objects.equals(threeHourWeatherList, that.threeHourWeatherList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threeHourWeatherList);
    }

    @Override
    public String toString() {
        return "SingleDayWeather{" +
                "threeHourWeatherList=" + threeHourWeatherList +
                '}';
    }
}

