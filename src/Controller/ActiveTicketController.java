package Controller;

import Model.Model_class.Active_Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ActiveTicketController {
    private DatabaseHandler dbHandler;
    private List <Active_Ticket> ticket;
    public ActiveTicketController(int id){
        dbHandler = new DatabaseHandler();
        InitActiveController(id);
    }

    public List<Active_Ticket> InitActiveController(int id){
        String query = "SELECT \r\n" + //
                        "    transaksi.name, \r\n" + //
                        "    transaksi.nik, \r\n" + //
                        "    transaksi.age, \r\n" + //
                        "    ori.city AS origin_city, \r\n" + //
                        "    des.city AS destination_city\r\n" + //
                        "FROM \r\n" + //
                        "    transaksi \r\n" + //
                        "INNER JOIN \r\n" + //
                        "    flight ON flight.flightID = transaksi.flightID\r\n" + //
                        "INNER JOIN \r\n" + //
                        "    airport ori ON ori.airportID = flight.origin\r\n" + //
                        "INNER JOIN \r\n" + //
                        "    airport des ON des.airportID = flight.destination\r\n" + //
                        "WHERE \r\n" + //
                        "    transaksi.userID = ?;\r\n" + //
                        "";
        try (Connection con = dbHandler.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ticket = new ArrayList<>();
            while (rs.next()) {
                String name = (rs.getString("name"));
                String nik = (rs.getString("nik"));
                String age =  (rs.getString("age"));
                String origin = (rs.getString("origin_city"));
                String destination = (rs.getString("destination_city"));                
                Active_Ticket oneTicket = new Active_Ticket(name, nik, age, origin, destination);
                ticket.add(oneTicket);
                System.out.println(name );
            }
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
}