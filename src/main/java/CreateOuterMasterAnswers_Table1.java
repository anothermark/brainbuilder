// Keep

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateOuterMasterAnswers_Table1 {
	public void createTable() throws SQLException {
		String createTableSQL = "CREATE TABLE OUTER_MASTER_ANSWERS_TABLE_1 (" + "id Integer NOT NULL,"
				+ "OUTER_MASTER_ANSWERS JAVA_OBJECT(10000) " + ");";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
			String myTableName = "OUTER_MASTER_ANSWERS_TABLE_1";

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
