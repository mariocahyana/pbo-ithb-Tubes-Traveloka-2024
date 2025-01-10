package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminForgotPasswordController {
    private DatabaseHandler dbHandler;

    public AdminForgotPasswordController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

public List<String[]> getPendingRequest() {
        List<String[]> pendingRequests = new ArrayList<>();
        String query = "SELECT pr.resetID, u.name, u.email, pr.request_date " +
                       "FROM password_reset pr JOIN users u ON pr.userID = u.userID " +
                       "WHERE pr.status = 'PENDING'";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] request = {
                        String.valueOf(rs.getInt("resetID")),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("request_date").toString()
                };
                pendingRequests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingRequests;
    }


    public boolean approveRequest(int resetID) {
        String getRequestQuery = "SELECT userID, newPassword FROM password_reset WHERE resetID = ?";
        String updatePasswordQuery = "UPDATE users SET password = ? WHERE userID = ?";
        String updateRequestStatusQuery = "UPDATE password_reset SET status = 'APPROVED' WHERE resetID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement getRequest = con.prepareStatement(getRequestQuery);
             PreparedStatement updatePassword = con.prepareStatement(updatePasswordQuery);
             PreparedStatement updateRequestStatus = con.prepareStatement(updateRequestStatusQuery)) {

            getRequest.setInt(1, resetID);
            ResultSet rs = getRequest.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("userID");
                String newPassword = rs.getString("newPassword");

                updatePassword.setString(1, newPassword);
                updatePassword.setInt(2, userID);
                updatePassword.executeUpdate();

                updateRequestStatus.setInt(1, resetID);
                return updateRequestStatus.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rejectRequest(int resetID) {
        String query = "UPDATE password_reset SET status = 'REJECTED' WHERE resetID = ?";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, resetID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
