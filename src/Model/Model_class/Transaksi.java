package Model.Model_class;

import Model.Model_enum.StatusPembayaran;
import Model.Model_enum.Usia;
import Model.Model_enum.ActiveTicket;

import java.util.Date;

public class Transaksi {
    private String transaksiID;
	private StatusPembayaran status;
    private ActiveTicket ticket;
    private int price;
    private String name;
    private User user;
    private Flight flight;
    private int nik;
    private Usia age;
    private Date date_transaksi;

    public Transaksi() {
        
    }

    public Transaksi(String transaksiID, StatusPembayaran status, ActiveTicket ticket, int price, String name,
            User user, Flight flight, int nik, Usia age, Date date_transaksi) {
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

    public String getTransaksiID() {
        return transaksiID;
    }

    public void setTransaksiID(String transaksiID) {
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

    public Date getDate_transaksi() {
        return date_transaksi;
    }

    public void setDate_transaksi(Date date_transaksi) {
        this.date_transaksi = date_transaksi;
    }
    
}
