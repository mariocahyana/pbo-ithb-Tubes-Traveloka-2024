package Model.Model_class;

import Model.Model_enum.StatusPembayaran;
import Model.Model_enum.ActiveTicket;

public class Transaksi {
    private String transaksiID;
	private StatusPembayaran status;
    private ActiveTicket ticket;
    private int price;
    public Transaksi() {
    }
    public Transaksi(String transaksiID, StatusPembayaran status, ActiveTicket ticket, int price) {
        this.transaksiID = transaksiID;
        this.status = status;
        this.ticket = ticket;
        this.price = price;
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
    
}
