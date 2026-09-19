// Keep
//Class CreateExamLabelsJListTable creates a table to hold one object, ExamLabelsJlist1,
// which is used, among other things, to hold state of the String labels for the First JList, 
// along with getters and setters

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateExamLabelsJListTable {
	public void createTable() throws SQLException {
		String createTableSQL = "CREATE TABLE EXAMS_JLIST_LABELS (" + "id Integer NOT NULL," // Always 1
				+ "EXAMLABELSJLIST1  Object " + ");";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
			String myTableName = "EXAMS_JLIST_LABELS";

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
