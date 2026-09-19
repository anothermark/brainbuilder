
// DbTableUtility class shows which tables exist in the database, and
// is used to delete those not used anymore, and other things ...
// BUT CHECK THAT THIS WORKS BECAUSE THERE WAS A COMPILER ERROR I
// THINK I FIXED BUT IT MIGHT HAVE MESSED THINGS UP 5-9

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbTableUtility {
	ArrayList<String> allTableNames;

	public ArrayList<String> displayAllTables() throws SQLException {

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")) {
			DatabaseMetaData metaData = conn.getMetaData();			
			String[] types = { "TABLE" };
			allTableNames = new ArrayList<>();
			try (ResultSet rs = metaData.getTables(null, null, null, types)) {
				while (rs.next()) {
					allTableNames.add(rs.getString("TABLE_NAME"));
				}
				for (int i = 0; i < allTableNames.size(); i++) {
					System.out.println(allTableNames.get(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allTableNames;
	}

	public void deleteRemoveTable() throws SQLException {
		 String sqlDrop = "DROP TABLE IF EXISTS STUDENTS_OUTERNESTED_TABLE4"; 

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(sqlDrop);// Does this work?	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
