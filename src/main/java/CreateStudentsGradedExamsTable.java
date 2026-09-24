// Keep

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateStudentsGradedExamsTable {

	public void createFinalGradedTable() throws SQLException, ClassNotFoundException {
		String createTableSQLT = "CREATE TABLE Students_Graded_Exams_Table2 (" + "id Integer NOT NULL,"
				+ "STUDENTLASTNAME VARCHAR(100)," + "STUDENTFIRSTNAME VARCHAR(100),"
				+ "LISTOFQUESTIONS JAVA_OBJECT(1000000000) ," + "LISTOFGRADEDEXAMSLISTS JAVA_OBJECT(1000000000) " + ");";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(createTableSQLT)) {
			String myTableName = "Students_Graded_Exams_Table2";

			if (doesTableExist(conn, myTableName)) {
				System.out.println("Table " + myTableName + " already exists");
			} else {
				System.out.println("Table " + myTableName + " Does not exist, must create");
				stmt.executeUpdate();
				System.out.println("columns created successfully");
			}			
		}
	}

	public boolean doesTableExist(Connection conn, String tableName) throws SQLException {
		DatabaseMetaData dbm = conn.getMetaData();

		ResultSet tables = dbm.getTables(null, null, tableName, null);
		boolean tableExists = false;

		if (tables.next()) {
			tableExists = true;
		}
		tables.close();
		return tableExists;
	}

}
