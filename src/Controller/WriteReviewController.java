package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import Model.Model_class.User;

public class WriteReviewController {
    private DatabaseHandler dbHandler;

    public WriteReviewController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean saveReview(String review) {
        User user = LoginController.getInstance().getLoggedInUser();
        if (user == null) {
            System.out.println("Tidak ada user yang login.");
            return false;
        }

        String query = "INSERT INTO review (userID, customer_review, date_review) VALUES (?, ?, ?)";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, user.getUserID());
            ps.setString(2, review);
            ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
