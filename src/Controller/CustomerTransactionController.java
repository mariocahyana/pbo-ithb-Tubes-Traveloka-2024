package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Flight;
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
                Flight flight = new Flight();
                flight.setFlightID(rs.getInt("flightID"));
                user.setUserID(rs.getInt("userID"));
                transaksi.setTransaksiID(rs.getInt("transaksiID"));
                transaksi.setStatus(StatusPembayaran.valueOf(rs.getString("payment_confirmation")));
                transaksi.setTicket(ActiveTicket.valueOf(rs.getString("active_ticket")));
                transaksi.setPrice(rs.getInt("price_transaction"));
                transaksi.setName(rs.getString("name"));
                transaksi.setUser(user);
                transaksi.setFlight(flight);
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
    public List<Transaksi> getTransactions(boolean active, int id) {
        List<Transaksi> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaksi WHERE payment_confirmation = 'PAYNOW' AND userID = ?"; 
        
       try (Connection conn = dbHandler.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set parameter untuk placeholder ?
        stmt.setInt(1, id);

        // Eksekusi query setelah parameter diset
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                User user = new User();
                Flight flight = new Flight();
                flight.setFlightID(rs.getInt("flightID"));
                user.setUserID(rs.getInt("userID"));
                transaksi.setTransaksiID(rs.getInt("transaksiID"));
                transaksi.setStatus(StatusPembayaran.valueOf(rs.getString("payment_confirmation")));
                transaksi.setTicket(ActiveTicket.valueOf(rs.getString("active_ticket")));
                transaksi.setPrice(rs.getInt("price_transaction"));
                transaksi.setName(rs.getString("name"));
                transaksi.setUser(user);
                transaksi.setFlight(flight);
                transaksi.setNik(rs.getInt("nik"));
                transaksi.setAge(Usia.valueOf(rs.getString("age")));
                transaksi.setDate_transaksi(rs.getDate("date_transaksi").toLocalDate());
                transactions.add(transaksi);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return transactions;
}
    public List<Transaksi> getTransactions(int id) {
        List<Transaksi> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaksi WHERE userID = ?"; 
        
       try (Connection conn = dbHandler.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set parameter untuk placeholder ?
        stmt.setInt(1, id);

        // Eksekusi query setelah parameter diset
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                User user = new User();
                Flight flight = new Flight();
                flight.setFlightID(rs.getInt("flightID"));
                user.setUserID(rs.getInt("userID"));
                transaksi.setTransaksiID(rs.getInt("transaksiID"));
                transaksi.setStatus(StatusPembayaran.valueOf(rs.getString("payment_confirmation")));
                transaksi.setTicket(ActiveTicket.valueOf(rs.getString("active_ticket")));
                transaksi.setPrice(rs.getInt("price_transaction"));
                transaksi.setName(rs.getString("name"));
                transaksi.setUser(user);
                transaksi.setFlight(flight);
                transaksi.setNik(rs.getInt("nik"));
                transaksi.setAge(Usia.valueOf(rs.getString("age")));
                transaksi.setDate_transaksi(rs.getDate("date_transaksi").toLocalDate());
                transactions.add(transaksi);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return transactions;
}

    public List<Transaksi> getTransactionsPayLater() {
        List<Transaksi> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaksi WHERE payment_confirmation = 'PAYLATER'"; 
        
       try (Connection conn = dbHandler.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                User user = new User();
                Flight flight = new Flight();
                flight.setFlightID(rs.getInt("flightID"));
                user.setUserID(rs.getInt("userID"));
                transaksi.setTransaksiID(rs.getInt("transaksiID"));
                transaksi.setStatus(StatusPembayaran.valueOf(rs.getString("payment_confirmation")));
                transaksi.setTicket(ActiveTicket.valueOf(rs.getString("active_ticket")));
                transaksi.setPrice(rs.getInt("price_transaction"));
                transaksi.setName(rs.getString("name"));
                transaksi.setUser(user);
                transaksi.setFlight(flight);
                transaksi.setNik(rs.getInt("nik"));
                transaksi.setAge(Usia.valueOf(rs.getString("age")));
                transaksi.setDate_transaksi(rs.getDate("date_transaksi").toLocalDate());
                transactions.add(transaksi);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return transactions;
}
}