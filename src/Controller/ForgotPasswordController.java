package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPasswordController {
    private DatabaseHandler dbHandler;

    public ForgotPasswordController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean requestPasswordReset(String email, String newPassword) {
        String findUserQuery = "SELECT userID FROM users WHERE email = ?";
        String insertResetRequestQuery = "INSERT INTO password_reset (userID, newPassword) VALUES (?, ?)";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement findUser = con.prepareStatement(findUserQuery);
             PreparedStatement insertResetRequest = con.prepareStatement(insertResetRequestQuery)) {

            findUser.setString(1, email);
            ResultSet rs = findUser.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");

                insertResetRequest.setInt(1, userID);
                insertResetRequest.setString(2, newPassword);
                return insertResetRequest.executeUpdate() > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
