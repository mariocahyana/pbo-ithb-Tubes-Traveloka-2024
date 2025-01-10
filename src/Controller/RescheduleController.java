package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RescheduleController {
    private DatabaseHandler dbHandler;

    public RescheduleController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public void createTransaction(int transaksiIdAwal, int transaksiIdBaru) {
        String query = "INSERT INTO reschedule_request (transaksi_id_awal, transaksi_id_baru) VALUES (?, ?)";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, transaksiIdAwal);
            stmt.setInt(2, transaksiIdBaru);
            
            stmt.executeUpdate();
            System.out.println("Transaction created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read All Transactions
    public List<String> getAllTransactions() {
        List<String> transactions = new ArrayList<>();
        String query = "SELECT * FROM reschedule_request";
        try (Connection conn = dbHandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String transaction = "Transaksi ID Awal: " + rs.getInt("transaksi_id_awal") +
                                   ", Transaksi ID Baru: " + rs.getInt("transaksi_id_baru");
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Get Transaction by ID
    public String getTransactionById(int id) {
        String query = "SELECT * FROM reschedule_request WHERE transaksi_id_awal = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "Transaksi ID Awal: " + rs.getInt("transaksi_id_awal") +
                       ", Transaksi ID Baru: " + rs.getInt("transaksi_id_baru");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update Transaction
    public void updateTransaction(int oldId, int newId) {
        String query = "UPDATE reschedule_request SET transaksi_id_baru = ? WHERE transaksi_id_awal = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, newId);
            stmt.setInt(2, oldId);

            stmt.executeUpdate();
            System.out.println("Transaction updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Transaction
    public void deleteTransaction(int id) {
        String query = "DELETE FROM reschedule_request WHERE transaksi_id_awal = ?";
        try (Connection conn = dbHandler.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
            System.out.println("Transaction deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}