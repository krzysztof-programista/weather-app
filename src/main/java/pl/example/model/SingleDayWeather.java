package pl.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class SingleDayWeather {

    private final Collection<DataPackage> threeHourWeatherList;
    private int maxTemp = Integer.MIN_VALUE;
    private int minTemp = Integer.MAX_VALUE;
    private final LocalDate date;;
    private final String icon;

    public SingleDayWeather(Collection<DataPackage> threeHourWeatherList) {
        this.threeHourWeatherList = threeHourWeatherList;
        findMaxMinTemp();
        date = getThreeHourWeatherList().iterator().next().getDate();
        icon = findMiddleIcon(threeHourWeatherList);
    }

    private void findMaxMinTemp() {
        for(DataPackage dataPackage :threeHourWeatherList){
            int temperature = (int) dataPackage.getTemperature();
            maxTemp = Math.max(maxTemp,temperature);
            minTemp = Math.min(minTemp, temperature);
        }
    }

    private String findMiddleIcon(Collection<DataPackage> threeHourWeatherList){
        System.out.println(threeHourWeatherList.size() / 2);
        int middleIndex = threeHourWeatherList.size() / 2;
        DataPackage middleElement = null;
        Iterator<DataPackage> iterator = threeHourWeatherList.iterator();

        for (int currentIndex = 1; currentIndex <= middleIndex + 1; currentIndex++) {
            middleElement = iterator.next();
        }
        return middleElement.getIcon();
    }

    public Collection<DataPackage> getThreeHourWeatherList() {
        return threeHourWeatherList;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleDayWeather that = (SingleDayWeather) o;
        return maxTemp == that.maxTemp && minTemp == that.minTemp && Objects.equals(threeHourWeatherList, that.threeHourWeatherList) && Objects.equals(date, that.date) && Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threeHourWeatherList, maxTemp, minTemp, date, icon);
    }

    @Override
    public String toString() {
        return "SingleDayWeather{" +
                "threeHourWeatherList=" + threeHourWeatherList +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", date=" + date +
                ", icon='" + icon + '\'' +
                '}';
    }
}

