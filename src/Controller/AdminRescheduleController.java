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
        String query = "SELECT requestID, old_transaksiID, new_flightID, reason, request_date FROM reschedule_request WHERE status = 'PENDING'";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String[] request = new String[]{
                    String.valueOf(rs.getInt("requestID")),
                    rs.getString("old_transaksiID"),
                    rs.getString("new_flightID"),
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

    public boolean approveReschedule(int requestID, String oldTransaksiID, String newFlightID) {
        String updateFlightQuery = "UPDATE transaksi SET flightID = ? WHERE transaksiID = ?";
        String updateStatusQuery = "UPDATE reschedule_request SET status = 'APPROVED', process_date = NOW() WHERE requestID = ?";
    
        try (Connection con = dbHandler.getConnection();
             PreparedStatement psUpdateFlight = con.prepareStatement(updateFlightQuery);
             PreparedStatement psStatus = con.prepareStatement(updateStatusQuery)) {
    
            // Ubah flightID di transaksi lama
            psUpdateFlight.setInt(1, Integer.parseInt(newFlightID));
            psUpdateFlight.setInt(2, Integer.parseInt(oldTransaksiID));
            psUpdateFlight.executeUpdate();
    
            // Ubah status request jadi APPROVED
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
