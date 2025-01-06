package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateProfileController {
    private DatabaseHandler dbHandler;

    public UpdateProfileController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean updateProfile(String name, String email, String phone) {
        int userId = LoginController.getInstance().getLoggedInUser().getUserID();
        String query = "UPDATE users SET name = ?, email = ?, phone_num = ? WHERE userID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, userId);

            int updatedData = ps.executeUpdate();
            if (updatedData > 0) {
                LoginController.getInstance().getLoggedInUser().setNama(name);
                LoginController.getInstance().getLoggedInUser().setEmail(email);
                LoginController.getInstance().getLoggedInUser().setNoTelp(phone);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
