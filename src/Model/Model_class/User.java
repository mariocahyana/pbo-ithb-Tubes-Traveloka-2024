package Model.Model_class;

import Model.Model_enum.StatusUser;
import Model.Model_interface.FlightService;

public class User implements FlightService{
    private int userID;
    private String nama;
    private String password;
    private String email;
    private String noTelp;
    private String balance;
    private StatusUser status;

    public User() {
    
    }

    public User(int userID, String nama, String password, String email, String noTelp, String balance,
            StatusUser status) {
        this.userID = userID;
        this.nama = nama;
        this.password = password;
        this.email = email;
        this.noTelp = noTelp;
        this.balance = balance;
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
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
