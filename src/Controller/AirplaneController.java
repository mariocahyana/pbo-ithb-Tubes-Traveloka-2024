package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Airline;
import Model.Model_class.Airplane;

public class AirplaneController {
    private DatabaseHandler dbHandler;

    public AirplaneController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    // ADD new data airline
    public String add(Airplane airplane) {
        String airlineIDQuery = "SELECT airlineID FROM airline WHERE airline_name = ?";
        String insertQuery = "INSERT INTO airplane (airlineID, airline_name, airplane_name, seat) VALUES (?, ?, ?, ?)";
        Connection conn = dbHandler.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(airlineIDQuery);
            ps.setString(1, airplane.getAirlineName());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return "AIRLINE_NOT_FOUND";
            }

            int airlineID = rs.getInt("airlineID");

            ps = conn.prepareStatement(insertQuery);
            ps.setInt(1, airlineID);
            ps.setString(2, airplane.getAirlineName());
            ps.setString(3, airplane.getAirplaneName());
            ps.setInt(4, airplane.getSeat());

            ps.executeUpdate();
            return "SUCCESS";

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("airplane_name")) {
                return "NAME_EXISTS";
            }
            e.printStackTrace();
            return "ERROR";
        }
    }

    // PRINT data airplane
    public List<Airplane> getAirplaneData() {
        List<Airplane> airplanes = new ArrayList<>();
        String query = "SELECT * FROM airplane";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Airplane airplane = new Airplane();
                Airline airline = new Airline();

                airline.setAirlineID(rs.getInt("airlineID"));

                airplane.setAirplaneID(rs.getInt("airplaneID"));
                airplane.setAirlineName(rs.getString("airline_name"));
                airplane.setAirplaneName(rs.getString("airplane_name"));
                airplane.setAirline(airline);
                airplane.setSeat(rs.getInt("seat"));

                airplanes.add(airplane);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airplanes;
    }

    // SEARCH data airplane
    public Airplane getAirplane(int airplaneID) {
        Airplane airplane = new Airplane();
        Airline airline = new Airline();
        String query = "SELECT * FROM airplane WHERE airplaneID='" + airplaneID + "'";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    airline.setAirlineID(rs.getInt("airlineID"));

                    airplane.setAirplaneID(rs.getInt("airplaneID"));
                    airplane.setAirplaneName(rs.getString("airplane_name"));
                    airplane.setAirlineName(rs.getString("airline_name"));
                    airplane.setAirline(airline);
                    airplane.setSeat(rs.getInt("seat"));
                } while (rs.next());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airplane;
    }

    // DELETE data airplane
    public boolean deleteData(String airplaneName) {
        String query = "DELETE FROM airplane WHERE airplane_name=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, airplaneName);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE new data airplane
    public boolean updateData(Airplane airplane) {
        String airlineIDQuery = "SELECT airlineID FROM airline WHERE airline_name = ?";
        String query = "UPDATE airplane SET airlineID=?, airline_name=?, airplane_name=?, seat=? WHERE airplaneID=?";
        Connection conn = dbHandler.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(airlineIDQuery);
            ps.setString(1, airplane.getAirlineName());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }

            int airlineID = rs.getInt("airlineID");

            ps = conn.prepareStatement(query);
            ps.setInt(1, airlineID);
            ps.setString(2, airplane.getAirlineName());
            ps.setString(3, airplane.getAirplaneName());
            ps.setInt(4, airplane.getSeat());
            ps.setInt(5, airplane.getAirplaneID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
