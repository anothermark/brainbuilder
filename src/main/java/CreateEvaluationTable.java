
// Creates the CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 for 
// both the grade immediately and grade once schemes. 

package main.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateEvaluationTable {
	public void createTable() throws SQLException, ClassNotFoundException {
		String createTableSQL = "CREATE TABLE CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 (" + "id Integer NOT NULL,"
				+ "INNER_MASTER_EVALUATED JAVA_OBJECT(10000) " + ");";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
			String myTableName = "CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1";

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
