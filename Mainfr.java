package FlightReservation;

import java.util.Scanner;

public class Mainfr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Use a single Scanner instance
        int choice;

        do {
            try {
                System.out.println("\nWelcome to Flight Reservation System!");
                System.out.println("1. View Available Flights");
                System.out.println("2. Book a Flight");
                System.out.println("3. Cancel Booking");
                System.out.println("4. View Booking Details");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                // Validate user input
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); // Clear invalid input
                    continue;
                }

                choice = sc.nextInt(); // Read user input
                sc.nextLine(); // Consume the newline

                switch (choice) {
                    case 1:
                        ViewFlights.showAvailableFlights(); // Call view flights
                        break;
                    case 2:
                        BookFlight.bookTicket(sc); // Pass Scanner instance
                        break;
                    case 3:
                        CancelBooking.cancelTicket(sc); // Pass Scanner instance
                        break;
                    case 4:
                        ViewBookings.showBookings(); // Call view bookings
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you for using the Flight Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine(); // Clear scanner buffer to prevent infinite loops
            }
        } while (true); // Exit only on choice 5
    }
}
