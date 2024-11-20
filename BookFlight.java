package FlightReservation;
import java.sql.*;
import java.util.Scanner;

public class BookFlight {
    public static void bookTicket(Scanner scanner) {
        try (Connection conn = DBconnection.connect()) {
            System.out.print("Enter Flight ID: ");
            int flightId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            System.out.print("Enter Your Name: ");
            String userName = scanner.nextLine();

            System.out.print("Enter Number of Seats to Book: ");
            int seats = scanner.nextInt();

            // Check available seats
            String checkSeatsQuery = "SELECT available_seats FROM flights WHERE flight_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSeatsQuery);
            checkStmt.setInt(1, flightId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("available_seats") >= seats) {
                // Update available seats
                String updateSeatsQuery = "UPDATE flights SET available_seats = available_seats - ? WHERE flight_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSeatsQuery);
                updateStmt.setInt(1, seats);
                updateStmt.setInt(2, flightId);
                updateStmt.executeUpdate();

                // Insert reservation
                String bookQuery = "INSERT INTO reservations (user_name, flight_id, seats_booked, booking_date) VALUES (?, ?, ?, NOW())";
                PreparedStatement bookStmt = conn.prepareStatement(bookQuery);
                bookStmt.setString(1, userName);
                bookStmt.setInt(2, flightId);
                bookStmt.setInt(3, seats);
                bookStmt.executeUpdate();

                System.out.println("Flight ticket booked successfully!");
            } else {
                System.out.println("Insufficient seats available.");
            }
        } catch (Exception e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        }
    }
}
