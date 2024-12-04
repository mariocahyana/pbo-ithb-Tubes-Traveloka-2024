package Model.Model_class;

import Model.Model_enum.Usia;

public class Booking {
    private String bookingID, name;
    private Customer customer;
    private Flight flight;
    private int nik;
    private Usia age;
    public Booking() {
    }
    public Booking(String bookingID, String name, Customer customer, Flight flight, int nik, Usia age) {
        this.bookingID = bookingID;
        this.name = name;
        this.customer = customer;
        this.flight = flight;
        this.nik = nik;
        this.age = age;
    }
    public String getBookingID() {
        return bookingID;
    }
    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public int getNik() {
        return nik;
    }
    public void setNik(int nik) {
        this.nik = nik;
    }
    public Usia getAge() {
        return age;
    }
    public void setAge(Usia age) {
        this.age = age;
    }

    
}