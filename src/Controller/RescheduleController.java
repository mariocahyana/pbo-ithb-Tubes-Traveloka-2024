package Controller;

import Model.Model_class.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RescheduleController {
    private DatabaseHandler dbHandler;

    public RescheduleController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    public boolean requestReschedule(String oldTransaksiID, String newFlightID, String reason) {
        User loggedInUser = LoginController.getInstance().getLoggedInUser();

        if (loggedInUser == null) {
            System.out.println("User belum login.");
            return false;
        }

        String query = "INSERT INTO reschedule_request (userID, old_transaksiID, new_flightID, reason, status) VALUES (?, ?, ?, ?, 'PENDING')";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, loggedInUser.getUserID());
            ps.setInt(2, Integer.parseInt(oldTransaksiID));
            ps.setInt(3, Integer.parseInt(newFlightID));
            ps.setString(4, reason);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
