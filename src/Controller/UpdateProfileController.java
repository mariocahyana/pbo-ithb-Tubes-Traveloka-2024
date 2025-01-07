package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Model_class.User;

public class UpdateProfileController {
    private DatabaseHandler dbHandler;

    public UpdateProfileController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean updateProfile(String name, String email, String phone) {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return false;
        }

        String query = "UPDATE users SET name = ?, email = ?, phone_num = ? WHERE userID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setInt(4, user.getUserID());

            int updatedData = ps.executeUpdate();
            if (updatedData > 0) {
                user.setNama(name);
                user.setEmail(email);
                user.setNoTelp(phone);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
