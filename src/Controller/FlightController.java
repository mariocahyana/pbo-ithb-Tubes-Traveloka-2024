package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Model_class.Flight;
import Model.Model_enum.FlightClass;
import Model.Model_class.Airport;
import Model.Model_class.Airplane;

public class FlightController {
    private DatabaseHandler dbHandler;

    public FlightController() {
        dbHandler = new DatabaseHandler();
        dbHandler.connect();
    }

    // PRINT data flight
    public List<Flight> getFlightData() {
        List<Flight> flights = new ArrayList<>();
        String query = "SELECT * FROM flight";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Airplane airplane = new Airplane();
                Airport origin = new Airport();
                Airport destination = new Airport();
                Flight flight = new Flight();

                airplane.setAirplaneID(rs.getInt("airplaneID"));

                origin.setAirportID(rs.getInt("origin"));
                destination.setAirportID(rs.getInt("destination"));

                flight.setFlightID(rs.getInt("flightID"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setAirplane(airplane);
                flight.setAirplaneName(rs.getString("airplane_name"));
                flight.setCountTicket(rs.getInt("count_ticket"));
                flight.setOrigin(origin);
                flight.setDestination(destination);
                flight.setOriginCity(rs.getString("origin_city"));
                flight.setDestinationCity(rs.getString("destination_city"));
                flight.setDepartureTime(rs.getString("depature_time"));
                flight.setArrivalTime(rs.getString("arrival_time"));
                String flightClassString = rs.getString("flight_class");
                if (flightClassString != null) {
                    flight.setFlightClass(FlightClass.valueOf(flightClassString));
                }
                flight.setPrice(rs.getInt("price_flight"));

                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    // SEARCH data flight
    public Flight getFlight(int flightID) {
        Airplane airplane = new Airplane();
        Airport origin = new Airport();
        Airport destination = new Airport();
        Flight flight = new Flight();
        String query = "SELECT * FROM flight WHERE flightID='" + flightID + "'";

        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                do {
                    airplane.setAirplaneID(rs.getInt("airplaneID"));

                    origin.setAirportID(rs.getInt("origin"));
                    destination.setAirportID(rs.getInt("destination"));

                    flight.setFlightName(rs.getString("flight_name"));
                    flight.setAirplane(airplane);
                    flight.setAirplaneName(rs.getString("airplane_name"));
                    flight.setOrigin(origin);
                    flight.setOriginCity(rs.getString("origin_city"));
                    flight.setDestination(destination);
                    flight.setDestinationCity(rs.getString("destination_city"));
                    flight.setCountTicket(rs.getInt("count_ticket"));
                    flight.setDepartureTime(rs.getString("depature_time"));
                    flight.setArrivalTime(rs.getString("arrival_time"));
                    flight.setFlightClass(FlightClass.valueOf(rs.getString("flight_class").toUpperCase()));
                    flight.setPrice(rs.getInt("price_flight"));
                } while (rs.next());
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }

    // DELETE data flight
    public boolean deleteData(String flightName) {
        String query = "DELETE FROM flight WHERE flight_name=?";
        try {
            Connection conn = dbHandler.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, flightName);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // CEK ENUM flight class
    public FlightClass getFlightClass(String ticket) {
        if (ticket.equalsIgnoreCase("ECONOMY")) {
            return FlightClass.ECONOMY;
        } else if (ticket.equalsIgnoreCase("BUSINESS")){
            return FlightClass.BUSINESS;
        } else {
            return FlightClass.FIRSTCLASS;
        }
    }

    // ADD new data Flight
    public String add(Flight flight) {
        String originIDQuery = "SELECT airportID FROM airport WHERE city = ?";
        String destinationIDQuery = "SELECT airportID FROM airport WHERE city = ?";
        String airplaneIDQuery = "SELECT airplaneID FROM airplane WHERE airplane_name = ?";
        String insertQuery = "INSERT INTO flight (flight_name, airplaneID, airplane_name, origin, destination, count_ticket, origin_city, destination_city, depature_time, arrival_time, flight_class, price_flight) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = dbHandler.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(originIDQuery);
            ps.setString(1, flight.getOriginCity());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return "CITYOFORIGIN_NOT_FOUND";
            }

            int origin = rs.getInt("airportID");
            
            ps = conn.prepareStatement(destinationIDQuery);
            ps.setString(1, flight.getDestinationCity());
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                return "CITYOFDESTINATION_NOT_FOUND";
            }
            int destination = rs.getInt("airportID");

            ps = conn.prepareStatement(airplaneIDQuery);
            ps.setString(1, flight.getAirplaneName());
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                return "AIRPLANE_NOT_FOUND";
            }
            int airplaneID = rs.getInt("airplaneID");

            ps = conn.prepareStatement(insertQuery);
            ps.setString(1, flight.getFlightName());
            ps.setInt(2, airplaneID);
            ps.setString(3, flight.getAirplaneName());
            ps.setInt(4, origin);
            ps.setInt(5, destination);
            ps.setInt(6, flight.getCountTicket());
            ps.setString(7, flight.getOriginCity());
            ps.setString(8, flight.getDestinationCity());
            ps.setString(9, flight.getDepartureTime());
            ps.setString(10, flight.getArrivalTime());
            ps.setString(11, flight.getFlightClass().toString());
            ps.setDouble(12, flight.getPrice());

            ps.executeUpdate();
            return "SUCCESS";

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("flight_name")) {
                return "NAME_EXISTS";
            }
            e.printStackTrace();
            return "ERROR";
        }
    }

    // UPDATE new data flight
    public boolean updateData(Flight flight) {
        String originIDQuery = "SELECT airportID FROM airport WHERE city = ?";
        String destinationIDQuery = "SELECT airportID FROM airport WHERE city = ?";
        String airplaneIDQuery = "SELECT airplaneID FROM airplane WHERE airplane_name = ?";
        String query = "UPDATE flight SET flight_name=?, airplaneID=?, airplane_name=?, origin=?, destination=?, count_ticket=?, origin_city=?, destination_city=?, depature_time=?, arrival_time=?, flight_class=?, price_flight=? WHERE flightID=?";
        Connection conn = dbHandler.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(originIDQuery);
            ps.setString(1, flight.getOriginCity());
            rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }

            int origin = rs.getInt("airportID");
            
            ps = conn.prepareStatement(destinationIDQuery);
            ps.setString(1, flight.getDestinationCity());
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            int destination = rs.getInt("airportID");

            ps = conn.prepareStatement(airplaneIDQuery);
            ps.setString(1, flight.getAirplaneName());
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                return false;
            }
            int airplaneID = rs.getInt("airplaneID");

            ps = conn.prepareStatement(query);
            ps.setString(1, flight.getFlightName());
            ps.setInt(2, airplaneID);
            ps.setString(3, flight.getAirplaneName());
            ps.setInt(4, origin);
            ps.setInt(5, destination);
            ps.setInt(6, flight.getCountTicket());
            ps.setString(7, flight.getOriginCity());
            ps.setString(8, flight.getDestinationCity());
            ps.setString(9, flight.getDepartureTime());
            ps.setString(10, flight.getArrivalTime());
            ps.setString(11, flight.getFlightClass().toString());
            ps.setDouble(12, flight.getPrice());
            ps.setInt(13, flight.getFlightID());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
