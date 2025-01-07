package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Model_class.User;


public class ChangePasswordController {
    private DatabaseHandler dbHandler;

    public ChangePasswordController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return false;
        }

        String queryCheck = "SELECT password FROM users WHERE userID = ?";
        String queryUpdate = "UPDATE users SET password = ? WHERE userID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement passCheck = con.prepareStatement(queryCheck);
             PreparedStatement passUpdate = con.prepareStatement(queryUpdate)) {

            passCheck.setInt(1, user.getUserID());
            ResultSet rs = passCheck.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (!storedPassword.equals(oldPassword)) {
                    return false;
                }
            } else {
                return false;
            }

            passUpdate.setString(1, newPassword);
            passUpdate.setInt(2, user.getUserID());
            return passUpdate.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
