import java.sql.*;

public class Conn {

    Connection c;
    Statement s;



    public Conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employemanagementsystem","root", "Nisi@700");

            s = c.createStatement();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public boolean isConnected() {
        try {
            return c != null && !c.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    }

