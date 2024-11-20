package FlightReservation;

import java.sql.*;
public class ViewFlights {
    public static void showAvailableFlights() {
        try (Connection con = DBconnection.connect()) {

        String query = "SELECT * FROM flights";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        System.out.println("Available Flights....");
        while (rs.next()) {
            System.out.println("Flight ID: " + rs.getInt("flight_id") +
                                   ", Name: " + rs.getString("flight_name") +
                                   ", Source: " + rs.getString("source") +
                                   ", Destination: " + rs.getString("destination") +
                                   ", Departure: " + rs.getString("departure_time") +
                                   ", Arrival: " + rs.getString("arrival_time") +
                                   ", Available Seats: " + rs.getInt("available_seats"));
            
        }
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
}  