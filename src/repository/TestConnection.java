package repository;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL driver.");
            e.printStackTrace();
        }
    }
}
