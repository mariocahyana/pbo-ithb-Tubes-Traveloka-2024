package Model.Model_class;

import Model.Model_enum.StatusPembayaran;
import Model.Model_enum.Usia;
import Model.Model_enum.ActiveTicket;

import java.time.LocalDate;

public class Transaksi {
    private int transaksiID;
	private StatusPembayaran status;
    private ActiveTicket ticket;
    private int price;
    private String name;
    private User user;
    private Flight flight;
    private int nik;
    private Usia age;
    private LocalDate date_transaksi;

    @Override
    public String toString(){
        return "[" + transaksiID + status + ticket + price + name + user + flight + nik + age + date_transaksi + "]";
    }
    public Transaksi() {
        
    }

    public Transaksi(int transaksiID, StatusPembayaran status, ActiveTicket ticket, int price, String name,
            User user, Flight flight, int nik, Usia age, LocalDate date_transaksi) {
        this.transaksiID = transaksiID;
        this.status = status;
        this.ticket = ticket;
        this.price = price;
        this.name = name;
        this.user = user;
        this.flight = flight;
        this.nik = nik;
        this.age = age;
        this.date_transaksi = date_transaksi;
    }

    public int getTransaksiID() {
        return transaksiID;
    }

    public void setTransaksiID(int transaksiID) {
        this.transaksiID = transaksiID;
    }

    public StatusPembayaran getStatus() {
        return status;
    }

    public void setStatus(StatusPembayaran status) {
        this.status = status;
    }

    public ActiveTicket getTicket() {
        return ticket;
    }

    public void setTicket(ActiveTicket ticket) {
        this.ticket = ticket;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public LocalDate getDate_transaksi() {
        return date_transaksi;
    }

    public void setDate_transaksi(LocalDate date_transaksi) {
        this.date_transaksi = date_transaksi;
    }
    
}
