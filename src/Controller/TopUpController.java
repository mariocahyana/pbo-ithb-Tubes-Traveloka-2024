package Controller;

import Model.Model_class.User;
import java.sql.*;
import java.util.*;

public class TopUpController {
    private DatabaseHandler dbHandler;

    public TopUpController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean createTopUpRequest(double amount) {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return false;
        }

        String query = "INSERT INTO topup_requests (userID, amount) VALUES (?, ?)";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, user.getUserID());
            ps.setDouble(2, amount);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public double getBalance() {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return 0.0;
        }

        String query = "SELECT balance FROM users WHERE userID = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, user.getUserID());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Map<String, Object>> getPendingTopUpRequests() {
        List<Map<String, Object>> reqList = new ArrayList<>();
        String query = "SELECT * FROM topup_requests WHERE status = 'PENDING' ORDER BY request_date ASC";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> reqTopup = new HashMap<>();
                reqTopup.put("requestID", rs.getInt("requestID"));
                reqTopup.put("userID", rs.getInt("userID"));
                reqTopup.put("amount", rs.getDouble("amount"));
                reqTopup.put("status", rs.getString("status"));
                reqTopup.put("request_date", rs.getTimestamp("request_date"));
                reqList.add(reqTopup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reqList;
    }

    public boolean approveTopUpRequest(int requestID) {
        String updateRequestQuery = "UPDATE topup_requests SET status = 'APPROVED' WHERE requestID = ?";
        String updateBalanceQuery = "UPDATE users u JOIN topup_requests t ON u.userID = t.userID " +
                                    "SET u.balance = u.balance + t.amount WHERE t.requestID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps1 = con.prepareStatement(updateRequestQuery);
             PreparedStatement ps2 = con.prepareStatement(updateBalanceQuery)) {

            ps1.setInt(1, requestID);
            ps2.setInt(1, requestID);

            if (ps1.executeUpdate() > 0) {
                return ps2.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rejectTopUpRequest(int requestID) {
        String query = "UPDATE topup_requests SET status = 'REJECTED' WHERE requestID = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, requestID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
