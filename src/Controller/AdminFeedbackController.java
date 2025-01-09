package Controller;

import Model.Model_class.Feedback;
import Model.Model_class.Reply;
import Model.Model_class.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminFeedbackController {
    private DatabaseHandler dbHandler;

    public AdminFeedbackController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbackList = new ArrayList<>();
        String query = "SELECT f.reviewID, f.customer_review, f.date_review, u.userID, u.name " +
                       "FROM review f JOIN users u ON f.userID = u.userID";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String reviewID = rs.getString("reviewID");
                String customerReview = rs.getString("customer_review");
                Date dateReview = rs.getDate("date_review");

                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setNama(rs.getString("name"));

                feedbackList.add(new Feedback(reviewID, customerReview, user, dateReview));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbackList;
    }

    public boolean isReplyAlreadyExists(int reviewID, int userID) {
        String query = "SELECT COUNT(*) FROM reply WHERE reviewID = ? AND userID = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
    
            ps.setInt(1, reviewID);
            ps.setInt(2, userID);
            ResultSet rs = ps.executeQuery();
    
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public boolean replyToFeedback(Reply reply) {
        String query = "INSERT INTO reply (reviewID, userID, reply_admin) VALUES (?, ?, ?)";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reply.getReviewID());
            ps.setInt(2, reply.getUserID());
            ps.setString(3, reply.getReplyAdmin());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
