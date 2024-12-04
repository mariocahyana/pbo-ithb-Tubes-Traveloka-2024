package Model.Model_class;

import Model.Model_enum.*;

public class Flight {
    private String flightID;
    private Airplane airplane;
    private int countTicket;
    private Airport origin;
    private Airport destination;
    private String departureTime;
    private String arrivalTime;
    private FlightClass flightClass;
    private double price;
    public Flight() {
    }
    public Flight(String flightID, Airplane airplane, int countTicket, Airport origin, Airport destination,
            String departureTime, String arrivalTime, FlightClass flightClass, double price) {
        this.flightID = flightID;
        this.airplane = airplane;
        this.countTicket = countTicket;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightClass = flightClass;
        this.price = price;
    }
    public String getFlightID() {
        return flightID;
    }
    public void setFlightID(String flightID) {
        this.flightID = flightID;
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
}