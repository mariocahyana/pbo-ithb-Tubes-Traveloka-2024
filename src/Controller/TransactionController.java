package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Transaksi;;

public class TransactionController {
    private DatabaseHandler dbHandler;

    public TransactionController(){
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    // Create Transaction
    public void createTransaction(Transaksi transaksi) {
        String query = "INSERT INTO transaksi (userID, price_transaction, flightID, nik, seat_number, name, " +
                       "date_transaksi, age, payment_confirmation, active_ticket) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transaksi.getUser().getUserID());
            stmt.setDouble(2, transaksi.getPrice());
            stmt.setInt(3, transaksi.getFlight().getFlightID());
            stmt.setInt(4, transaksi.getNik());
            stmt.setInt(5, Integer.parseInt(transaksi.getSeat()));
            stmt.setString(6, transaksi.getName());
            stmt.setDate(7, java.sql.Date.valueOf(transaksi.getDate_transaksi()));
            stmt.setString(8, transaksi.getAge().toString());
            stmt.setString(9, transaksi.getStatus().toString());
            stmt.setString(10, transaksi.getTicket().toString());
                
            stmt.executeUpdate();
            System.out.println("Transaction created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int createReturnedTransaction(Transaksi transaksi) {
        String query = "INSERT INTO transaksi (userID, price_transaction, flightID, nik, seat_number, name, " +
                       "date_transaksi, age, payment_confirmation, active_ticket) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transaksi.getUser().getUserID());
            stmt.setDouble(2, transaksi.getPrice());
            stmt.setInt(3, transaksi.getFlight().getFlightID());
            stmt.setInt(4, transaksi.getNik());
            stmt.setInt(5, Integer.parseInt(transaksi.getSeat()));
            stmt.setString(6, transaksi.getName());
            stmt.setDate(7, java.sql.Date.valueOf(transaksi.getDate_transaksi()));
            stmt.setString(8, transaksi.getAge().toString());
            stmt.setString(9, transaksi.getStatus().toString());
            stmt.setString(10, transaksi.getTicket().toString());
                
            stmt.executeUpdate();
            String query2 = "SELECT transaksiID FROM transaksi WHERE userID = ? AND active_ticket = ?";
            try (Connection conn2 = dbHandler.getConnection();
             PreparedStatement stmt2 = conn2.prepareStatement(query2)) {

            stmt2.setInt(1, transaksi.getUser().getUserID());
            stmt2.setString(2, transaksi.getStatus().toString());
            ResultSet rs = stmt2.executeQuery();

            while (rs.next()) {
                return(rs.getInt("transaksiID"));
            }
                System.out.println("Transaction created successfully!");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Read All Transactions
    public List<String> getAllTransactions() {
        List<String> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaksi";
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String transaction = "ID: " + rs.getInt("transaksiID") +
                                     ", UserID: " + rs.getInt("userID") +
                                     ", Price: " + rs.getInt("price_transaction") +
                                     ", FlightID: " + rs.getInt("flightID") +
                                     ", Name: " + rs.getString("name") +
                                     ", Seat Number: " + rs.getString("seat_number") +
                                     ", Date: " + rs.getDate("date_transaksi") +
                                     ", Age: " + rs.getString("age") +
                                     ", Payment: " + rs.getString("payment_confirmation") +
                                     ", Active: " + rs.getString("active_ticket");
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Update Transaction
    public void updateTransaction(int transaksiID, String seatNumber, String paymentConfirmation, String activeTicket) {
        String query = "UPDATE transaksi SET seat_number = ?, payment_confirmation = ?, active_ticket = ? WHERE transaksiID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, seatNumber);
            stmt.setString(2, paymentConfirmation);
            stmt.setString(3, activeTicket);
            stmt.setInt(4, transaksiID);

            stmt.executeUpdate();
            System.out.println("Transaction updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Transaction
    public void deleteTransaction(int transaksiID) {
        String query = "DELETE FROM transaksi WHERE transaksiID = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaksiID);

            stmt.executeUpdate();
            System.out.println("Transaction deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
}

