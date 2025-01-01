package Model.Model_class;

public class Airplane {
    private int airplaneID;
    private Airline airline;
    private String airlineName;
    private String airplaneName;
    private int seat;
    public Airplane() {
    }
    public Airplane(int airplaneID, Airline airline, String airlineName, String airplaneName, int seat) {
        this.airplaneID = airplaneID;
        this.airline = airline;
        this.airlineName = airlineName;
        this.airplaneName = airplaneName;
        this.seat = seat;
    }
    public int getAirplaneID() {
        return airplaneID;
    }
    public void setAirplaneID(int airplaneID) {
        this.airplaneID = airplaneID;
    }
    public Airline getAirline() {
        return airline;
    }
    public void setAirline(Airline airline) {
        this.airline = airline;
    }
    public String getAirlineName() {
        return airlineName;
    }
    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }
    public String getAirplaneName() {
        return airplaneName;
    }
    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    
}
