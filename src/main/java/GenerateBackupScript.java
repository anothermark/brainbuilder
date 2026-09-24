package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class GenerateBackupScript {
    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        // We use a direct raw connection here to bypass the helper's INIT trigger
        String rawUrl = "jdbc:h2:" + userHome + "/.brainapps/test;AUTO_SERVER=TRUE";
        
        try (Connection conn = DriverManager.getConnection(rawUrl, "sa", "");
             Statement stmt = conn.createStatement()) {
            
            System.out.println("Bypassing init trigger and connecting straight to your 1,000 KB database...");
            
            // This command extracts your tables/data and writes them to a file in your project
            stmt.execute("SCRIPT TO 'src/backup.sql'");
            
            System.out.println("SUCCESS! backup.sql has been created inside your src folder.");
            System.out.println("Please right-click your project root in Eclipse and hit 'Refresh'.");
            
        } catch (SQLException e) {
            System.err.println("Error generating script: " + e.getMessage());
            e.printStackTrace();
        }
    }
}