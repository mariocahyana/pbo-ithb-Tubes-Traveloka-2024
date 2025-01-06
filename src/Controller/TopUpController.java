package Controller;

import Model.Model_class.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TopUpController {
    private DatabaseHandler dbHandler;

    public TopUpController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean topUpBalance(double amount) {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return false;
        }

        String query = "UPDATE users SET balance = balance + ? WHERE userID = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDouble(1, amount);
            ps.setInt(2, user.getUserID());
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
}
