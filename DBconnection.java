package FlightReservation;

import java.sql.*;
public class DBconnection {
    public static Connection connect(){
       Connection con = null;
       try{
        String url ="jdbc:mysql://localhost:3306/flightreservation";
        String username = "root";
        String password = "8870224700@naveen";
        con = DriverManager.getConnection(url, username, password);
        System.out.println("Connected Successfully");
       }
       catch(Exception e){
        System.out.println("Not Connected");
        e.printStackTrace();
       }
       return con;
    }
}
