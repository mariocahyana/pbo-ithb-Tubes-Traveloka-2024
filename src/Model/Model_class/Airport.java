package Model.Model_class;

public class Airport{
    private int airportID;
    private String city;

    public Airport() {
    }

    public Airport(int airportID, String city) {
        this.airportID = airportID;
        this.city = city;
    }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

