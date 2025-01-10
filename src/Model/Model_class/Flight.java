package Model.Model_class;

import Model.Model_enum.*;

public class Flight {
    private int flightID;
    private String flightName;
    private Airplane airplane;
    private int countTicket;
    private Airport origin;
    private Airport destination;
    private String airplaneName;
    private String originCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private FlightClass flightClass;
    private double price;
    private String seat_row;
    public Flight(int flightID, String flightName, Airplane airplane, int countTicket, Airport origin,
            Airport destination, String airplaneName, String originCity, String destinationCity, String departureTime,
            String arrivalTime, FlightClass flightClass, double price, String seat_row) {
        this.flightID = flightID;
        this.flightName = flightName;
        this.airplane = airplane;
        this.countTicket = countTicket;
        this.origin = origin;
        this.destination = destination;
        this.airplaneName = airplaneName;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightClass = flightClass;
        this.price = price;
        this.seat_row = seat_row;
    }
    public int getFlightID() {
        return flightID;
    }
    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
    public String getFlightName() {
        return flightName;
    }
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
    public Airplane getAirplane() {
        return airplane;
    }
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
    public int getCountTicket() {
        return countTicket;
    }
    public void setCountTicket(int countTicket) {
        this.countTicket = countTicket;
    }
    public Airport getOrigin() {
        return origin;
    }
    public void setOrigin(Airport origin) {
        this.origin = origin;
    }
    public Airport getDestination() {
        return destination;
    }
    public void setDestination(Airport destination) {
        this.destination = destination;
    }
    public String getAirplaneName() {
        return airplaneName;
    }
    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }
    public String getOriginCity() {
        return originCity;
    }
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }
    public String getDestinationCity() {
        return destinationCity;
    }
    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public FlightClass getFlightClass() {
        return flightClass;
    }
    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Flight() {
    }
    public String getSeat_row() {
        return seat_row;
    }
    public void setSeat_row(String seat_row) {
        this.seat_row = seat_row;
    }
    
}