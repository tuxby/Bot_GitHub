package by.tux.Lesson_25;

public class City
{
    private String name;
    private String lat;
    private String lon;

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public City(String name, String lat, String lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
}
