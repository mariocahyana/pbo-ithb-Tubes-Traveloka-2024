package Model.Model_class;

public class Airplane {
    private String aiplaneID;
    private Airline airline;
    private int seat;
    public Airplane() {
    }
    public Airplane(String aiplaneID, Airline airline, int seat) {
        this.aiplaneID = aiplaneID;
        this.airline = airline;
        this.seat = seat;
    }
    public String getAiplaneID() {
        return aiplaneID;
    }
    public void setAiplaneID(String aiplaneID) {
        this.aiplaneID = aiplaneID;
    }
    public Airline getAirline() {
        return airline;
    }
    public void setAirline(Airline airline) {
        this.airline = airline;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }

    
}
