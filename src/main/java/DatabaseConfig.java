package main.java; // Keep your actual package name here

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    private static final String USER_HOME = System.getProperty("user.home");
    
    // 1. The clean URL used for all normal queries (stops the constant wiping)
    private static final String URL = "jdbc:h2:" + USER_HOME + "/.brainapps/test;AUTO_SERVER=TRUE";

 // 1. Update the normal getConnection() call to pass "sa" and ""
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(URL, "sa", "");
    }

    // 2. Update the initialization fallback block to pass "sa" and "" as well
    public static void initializeDatabaseIfMissing() {
        File dbFile = new File(USER_HOME + "/.brainapps/test.mv.db");
        
        if (!dbFile.exists()) {
            System.out.println("First-time setup detected. Creating database from backup.sql...");
            
            String initURL = "jdbc:h2:" + USER_HOME + "/.brainapps/test"
                + ";INIT=CREATE TABLE IF NOT EXISTS SCHEMACHECK(ID INT)\\;RUNSCRIPT FROM 'classpath:backup.sql'\\;DROP TABLE IF EXISTS SCHEMACHECK"
                + ";AUTO_SERVER=TRUE";
                
            // ADD "sa", "" HERE:
            try (Connection conn = DriverManager.getConnection(initURL, "sa", "")) {
                System.out.println("Database successfully initialized for first-time use.");
            } catch (SQLException e) {
                System.out.println("Critical error initializing fresh database: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Database file already exists. Skipping backup script to protect data!");
        }
    }
}