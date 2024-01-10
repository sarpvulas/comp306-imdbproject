package doa;

public class MainClass {

    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();

        String apiKey = "0c0d8834d28ec6f64ce489b1b5c823c9";

        // Check if the user is currently using the app
        if (dbConnection.isAppInUse(apiKey)) {
            System.out.println("The app is currently in use by another user. Please try again later.");
            return;
        }

        // TODO: Add your MovieDB API requests or other application logic here
        System.out.println("Executing MovieDB API requests or other application logic...");

        // Release the connection when the user is done using the app
        dbConnection.releaseConnection(apiKey);
    }
}