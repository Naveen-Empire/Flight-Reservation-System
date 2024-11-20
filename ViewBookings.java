package FlightReservation;

import java.sql.*;
public class ViewBookings {
    public static void showBookings(){
        try(Connection con = DBconnection.connect()){
            String query = "SELECT * FROM reservations";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("Booking Details...");
            while(rs.next()){
                System.out.println("Reservation ID: " + rs.getInt("reservation_id") +
                                   ", User: " + rs.getString("user_name") +
                                   ", Flight ID: " + rs.getInt("flight_id") +
                                   ", Seats Booked: " + rs.getInt("seats_booked") +
                                   ", Booking Date: " + rs.getDate("booking_date"));
            }
         }
       catch(Exception e){
        e.printStackTrace();
       }
    }
}
