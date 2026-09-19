// Keep !!!!

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Outer master table, a master of inner masters
public class CreateStudentsOuterNestedTable {

	public void createOuterNestedTable() throws SQLException {
		// This table uses the outernestedJlist to contain the other master lists,
		// like a master list of master lists
		String createTableSQLT = "CREATE TABLE STUDENTS_OUTERNESTED_TABLE4 (" + "id Integer NOT NULL,"
				+ "STUDENTLASTNAME VARCHAR(100)," + "STUDENTFIRSTNAME VARCHAR(100),"
				+ "OUTERNESTEDMASTERS JAVA_OBJECT(1000000000) ," + "LISTOFGRADEDEXAMSLISTS JAVA_OBJECT(1000000000) " + ");";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(createTableSQLT)) {
			String myTableName = "STUDENTS_OUTERNESTED_TABLE4";

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
