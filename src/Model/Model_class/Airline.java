package Model.Model_class;

public class Airline{
    
    private int airlineID;
    private String name;
    
    public Airline() {
    }
    public Airline(int airlineID, String name) {
        this.airlineID = airlineID;
        this.name = name;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
    
}

