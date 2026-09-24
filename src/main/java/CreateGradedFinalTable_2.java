// Keep

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TUE THIS CLASS SPEAKS FOR ITSELF, WHERE AFTER CLICKING 'BEGIN EXAM' STUDENT EXAM IS
// INSERTED/UPDATED IN THIS TABLE, ONE ROW PER STUDENT WHICH HOLDS MASTERLIST OF LISTS OF EXAMS? I think.
public class CreateGradedFinalTable_2 {

	public void createFinalGradedTable() throws SQLException, ClassNotFoundException {

		String createTableSQLT = "CREATE TABLE FINAL_GRADED_EXAMS_2 (" + "id Integer NOT NULL,"
				+ "EXAM_NUMBER Integer NOT NULL," + "STUDENTLASTNAME VARCHAR(100),"
				+ "LISTOFQUESTIONS JAVA_OBJECT(1000000000) ," + "LISTOFGRADEDEXAMSLISTS JAVA_OBJECT(1000000000) " + ");";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(createTableSQLT)) {
			String myTableName = "FINAL_GRADED_EXAMS_2";

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
