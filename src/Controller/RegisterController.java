package Controller;

import Model.Model_class.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {
    private DatabaseHandler dbHandler;

    public RegisterController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public String register(User user) {
        String query = "INSERT INTO users (name, password, email, phone_num, balance, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, user.getNama());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getNoTelp());
            ps.setDouble(5, 0.0);
            ps.setString(6, user.getStatus().toString());

            ps.executeUpdate();
            return "SUCCESS";
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("email")) {
                return "EMAIL_EXISTS";
            } else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("phone_num")) {
                return "PHONE_EXISTS";
            }
            e.printStackTrace();
            return "ERROR";
        }
    }
}
