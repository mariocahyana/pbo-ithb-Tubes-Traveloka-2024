package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewReviewsController {
    private DatabaseHandler dbHandler;

    public ViewReviewsController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public List<String> getAllReviews() {
        List<String> reviews = new ArrayList<>();
        String query = "SELECT r.customer_review, u.name FROM review r JOIN users u ON r.userID = u.userID";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String customerName = rs.getString("name");
                String reviewText = rs.getString("customer_review");
                reviews.add(customerName + ": " + reviewText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
