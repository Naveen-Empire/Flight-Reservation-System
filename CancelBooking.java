package FlightReservation;
import java.sql.*;
import java.util.Scanner;

public class CancelBooking {
    public static void cancelTicket(Scanner scanner) {
        try (Connection conn = DBconnection.connect()) {
            System.out.print("Enter Reservation ID: ");
            int reservationId = scanner.nextInt();

            // Retrieve reservation details
            String query = "SELECT * FROM reservations WHERE reservation_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int flightId = rs.getInt("flight_id");
                int seats = rs.getInt("seats_booked");

                // Delete reservation
                String deleteQuery = "DELETE FROM reservations WHERE reservation_id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, reservationId);
                deleteStmt.executeUpdate();

                // Update available seats
                String updateSeats = "UPDATE flights SET available_seats = available_seats + ? WHERE flight_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSeats);
                updateStmt.setInt(1, seats);
                updateStmt.setInt(2, flightId);
                updateStmt.executeUpdate();

                System.out.println("Booking cancelled successfully!");
            } else {
                System.out.println("Reservation not found.");
            }
        } catch (Exception e) {
            System.out.println("Error canceling booking: " + e.getMessage());
        }
    }
}
