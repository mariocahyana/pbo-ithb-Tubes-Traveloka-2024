package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Airline;

public class AirlineController {
    private DatabaseHandler dbHandler;

    public AirlineController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    // ADD new data airline
    public String add(Airline airline) {
        String query = "INSERT INTO airline (airline_name) VALUES (?)";
        try (Connection conn = dbHandler.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, airline.getName());

            ps.executeUpdate();
            return "SUCCESS";
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("name")) {
                return "NAME_EXISTS";
            }
            e.printStackTrace();
            return "ERROR";
        }
    }

    // PRINT data airline
    public List<Airline> getAirlineData() {
        List<Airline> airlines = new ArrayList<>();
        String query = "SELECT * FROM airline";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Airline airline = new Airline();
                airline.setAirlineID(rs.getInt("airlineID"));
                airline.setName(rs.getString("airline_name"));
                airlines.add(airline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airlines;
    }

    // SEARCH data airline
    public Airline getAirline(int airlineID) {
        Airline airline = new Airline();
        String query = "SELECT * FROM airline WHERE airlineID='" + airlineID + "'";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    airline.setAirlineID(rs.getInt("airlineID"));
                    airline.setName(rs.getString("airline_name"));
                } while (rs.next());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airline;
    }

    // UPDATE new data airline
    public boolean updateData(Airline airline) {
        String query = "UPDATE airline SET airline_name=? WHERE airlineID=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, airline.getName());
            stmt.setInt(2, airline.getAirlineID());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            dbHandler.disconnect();
        }
    }

    // DELETE data airline
    public boolean deleteData(String airlineName) {
        String query = "DELETE FROM airline WHERE airline_name=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, airlineName);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
