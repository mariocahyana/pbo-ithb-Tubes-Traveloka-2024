package Controller;

import Model.Model_class.User;
import Model.Model_enum.StatusUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
    private static LoginController instance;
    private User loggedInUser;
    private DatabaseHandler dbHandler;

    private LoginController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public User login(String email, String password) {
        if (loggedInUser != null) {
            return loggedInUser;
        }

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection con = dbHandler.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loggedInUser = new User();
                loggedInUser.setUserID(rs.getInt("userID"));
                loggedInUser.setNama(rs.getString("name"));
                loggedInUser.setPassword(rs.getString("password"));
                loggedInUser.setEmail(rs.getString("email"));
                loggedInUser.setNoTelp(rs.getString("phone_num"));
                loggedInUser.setBalance(rs.getDouble("balance"));
                loggedInUser.setStatus(StatusUser.valueOf(rs.getString("role").toUpperCase()));
                return loggedInUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}
