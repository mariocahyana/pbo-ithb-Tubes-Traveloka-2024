package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminRescheduleController {
    private DatabaseHandler dbHandler;

    public AdminRescheduleController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public List<String[]> getPendingRescheduleRequests() {
        List<String[]> pendingRequests = new ArrayList<>();
        String query = "SELECT requestID, old_transaksiID, new_transaksiID, reason, request_date FROM reschedule_request WHERE status = 'PENDING'";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] request = new String[]{
                    String.valueOf(rs.getInt("requestID")),
                    rs.getString("old_transaksiID"),
                    rs.getString("new_transaksiID"),
                    rs.getString("reason"),
                    rs.getString("request_date")
                };
                pendingRequests.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pendingRequests;
    }

    public boolean approveReschedule(int requestID, String oldTransaksiID, String newTransaksiID) {
        String updateOldTransQuery = "UPDATE transaksi SET active_ticket = 'NONACTIVE' WHERE transaksiID = ?";
        String updateNewTransQuery = "UPDATE transaksi SET active_ticket = 'ACTIVE' WHERE transaksiID = ?";
        String updateStatusQuery = "UPDATE reschedule_request SET status = 'APPROVED', process_date = NOW() WHERE requestID = ?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement psOld = con.prepareStatement(updateOldTransQuery);
             PreparedStatement psNew = con.prepareStatement(updateNewTransQuery);
             PreparedStatement psStatus = con.prepareStatement(updateStatusQuery)) {

            psOld.setString(1, oldTransaksiID);
            psOld.executeUpdate();

            psNew.setString(1, newTransaksiID);
            psNew.executeUpdate();

            psStatus.setInt(1, requestID);
            return psStatus.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean rejectReschedule(int requestID) {
        String query = "UPDATE reschedule_request SET status = 'REJECTED', process_date = NOW() WHERE requestID = ?";
        
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, requestID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
