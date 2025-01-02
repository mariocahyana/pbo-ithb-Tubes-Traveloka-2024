package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Airport;

public class AirportController {
    private DatabaseHandler dbHandler;

    public AirportController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    // PRINT data airport
    public List<Airport> getAirportData() {
        List<Airport> airports = new ArrayList<>();
        String query = "SELECT * FROM airport";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Airport airport = new Airport();
                airport.setAirportID(rs.getInt("airportID"));
                airport.setCity(rs.getString("city"));
                airports.add(airport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airports;
    }

    // ADD new data airport
    public String add(Airport airport) {
        String query = "INSERT INTO airport (city) VALUES (?)";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, airport.getCity());
            ps.executeUpdate();
            return "SUCCESS";
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("city")) {
                return "NAME_EXISTS";
            }
            e.printStackTrace();
            return "ERROR";
        }
    }

    // SEARCH data airport
    public Airport getAirport(int airportID) {
        Airport airport = new Airport();
        String query = "SELECT * FROM airport WHERE airportID='" + airportID + "'";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    airport.setAirportID(rs.getInt("airportID"));
                    airport.setCity(rs.getString("city"));
                } while (rs.next());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airport;
    }

    // UPDATE new data airport
    public boolean updateData(Airport airport) {
        String query = "UPDATE airport SET city=? WHERE airportID=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, airport.getCity());
            stmt.setInt(2, airport.getAirportID());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE data airport
    public boolean deleteData(String airportName) {
        String query = "DELETE FROM airport WHERE city=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, airportName);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
