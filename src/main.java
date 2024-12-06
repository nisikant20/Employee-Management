public class main {
    public static void main(String[] args) {
        Conn conn = new Conn();
        if (conn.isConnected()) {
            System.out.println("Connected to the database successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}