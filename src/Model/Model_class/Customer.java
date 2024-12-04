package Model.Model_class;

import Model.Model_interface.FlightService;

public class Customer extends User implements FlightService{
    private String customerID, email, phoneNum;
    private double balance;
    public Customer() {
    }
    public Customer(String nama, String password, String customerID, String email, String phoneNum, double balance) {
        super(nama, password);
        this.customerID = customerID;
        this.email = email;
        this.phoneNum = phoneNum;
        this.balance = balance;
    }
    public String getCustomerID() {
        return customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    @Override
    public void searchFlights() {
        
    }
    @Override
    public void bookFlights() {

    }
    @Override
    public void chooseSeat() {

    }
    @Override
    public void rescheduleTicket() {

    }
}

