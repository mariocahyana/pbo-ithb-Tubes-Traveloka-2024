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
        String query = "SELECT r.reviewID, u.name AS customer_name, r.customer_review, rp.reply_admin " +
                "FROM review r " +
                "LEFT JOIN reply rp ON r.reviewID = rp.reviewID " +
                "JOIN users u ON r.userID = u.userID";

        try (Connection con = dbHandler.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                String customerReview = rs.getString("customer_review");
                String adminReply = rs.getString("reply_admin");

                StringBuilder reviewDetails = new StringBuilder();
                reviewDetails.append(customerName).append(": ").append(customerReview);

                if (adminReply != null && !adminReply.isEmpty()) {
                    reviewDetails.append("\n  Balasan Admin: ").append(adminReply);
                } else {
                    reviewDetails.append("\n  (Belum ada balasan dari admin)");
                }
                reviews.add(reviewDetails.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
}