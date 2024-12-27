package Controller;

import Model.Model_class.User;
import Model.Model_enum.StatusUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    private DatabaseHandler dbHandler;

    public LoginController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setNama(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setNoTelp(rs.getString("phone_num"));
                user.setBalance(rs.getString("balance"));
                user.setStatus(StatusUser.valueOf(rs.getString("role").toUpperCase()));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
