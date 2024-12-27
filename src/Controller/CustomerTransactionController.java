package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Transaksi;
import Model.Model_class.User;
import Model.Model_enum.ActiveTicket;
import Model.Model_enum.StatusPembayaran;
import Model.Model_enum.Usia;

public class CustomerTransactionController {
    private DatabaseHandler dbHandler;

    public CustomerTransactionController(){
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }
    public List<Transaksi> getTransactions() {
        List<Transaksi> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaksi"; 
        
        try (Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                transaksi.setTransaksiID(rs.getInt("transaksiID"));
                transaksi.setStatus(StatusPembayaran.valueOf(rs.getString("payment_confirmation")));
                transaksi.setTicket(ActiveTicket.valueOf(rs.getString("active_ticket")));
                transaksi.setPrice(rs.getInt("price_transaction"));
                transaksi.setName(rs.getString("name"));
                transaksi.setUser(user);
                transaksi.setNik(rs.getInt("nik"));
                transaksi.setAge(Usia.valueOf(rs.getString("age")));
                transaksi.setDate_transaksi(rs.getDate("date_transaksi").toLocalDate());
                transactions.add(transaksi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
}
