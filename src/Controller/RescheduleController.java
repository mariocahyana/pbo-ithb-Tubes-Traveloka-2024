package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RescheduleController {
    private DatabaseHandler dbHandler;

    public RescheduleController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean requestReschedule(String oldTransaksiID, String newTransaksiID, String reason) {
        String query = "INSERT INTO reschedule_request (old_transaksiID, new_transaksiID, reason, status) VALUES (?, ?, ?, 'PENDING')";
        
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, oldTransaksiID);
            ps.setString(2, newTransaksiID);
            ps.setString(3, reason);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
