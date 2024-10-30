package Model_class;

import java.util.ArrayList;
import Model_interface.FlightService;

public class Customer extends User implements FlightService{
    private int customerID;
    private String email, phoneNum;
    private double balance;
    private ArrayList<Booking> bookingList;
    private ArrayList<Log> logID;
    private ArrayList<Transaksi> transaksiList;
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getphoneNum() {
        return phoneNum;
    }
    public void setphoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public double getbalance() {
        return balance;
    }
    public void setbalance(double balance) {
        this.balance = balance;
    }
    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }
    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }
    public ArrayList<Log> getLogID() {
        return logID;
    }
    public void setLogID(ArrayList<Log> logID) {
        this.logID = logID;
    }
    public ArrayList<Transaksi> getTransaksiList() {
        return transaksiList;
    }
    public void setTransaksiList(ArrayList<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }
    @Override
    public void searchFlights() {
        
    }
    @Override
    public void bookFlights() {

    }
    @Override
    public void flightNumber(){

    }
}

