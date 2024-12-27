package Model.Model_class;

public class Airport{
    private String airportID, city;

    public Airport() {
    }

    public Airport(String airportID, String city) {
        this.airportID = airportID;
        this.city = city;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}

