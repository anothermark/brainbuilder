// Class BuilderDBTesterUtility primarily deletes table rows and retrieves row counts

package main.java;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuilderDBTesterUtility implements Serializable {
	
	private static final long serialVersionUID = -8576544114542593882L;
	int count;

	public Integer getRowCount() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getRowCount in BuilderDBTesterUtility class");		
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM TESTER_EXAMS_LIST_4")) {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the count kduir867");
				count = rs.getInt(1);
				System.out.println("This TESTER_EXAMS_LIST_4 table contains " + count + " rows");
			}
		}
		System.out.println("Bottom of getRowCount in BuilderDBTesterUtility class");
		return count;
	}

	public Integer getRowCountFinalGraded() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getRowCountFinalGraded in BuilderDBTesterUtility class");	
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM FINAL_GRADED_EXAMS_2")) {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the count");
				count = rs.getInt(1);
				System.out.println("This FINAL_GRADED_EXAMS_2 table contains " + count + " rows");
			}
		}
		System.out.println("Bottom of getRowCountFinalGraded in BuilderDBTesterUtility class");
		return count;
	}

	public void deleteRows(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of deleteRows() in BuilderDBTesterUtility class");
		// Deletes all rows at once
		String sqlDeleteRowsR2 = " DELETE FROM TESTER_EXAMS_LIST_4 "; 
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR2)) {
			int affectedRows2T = stmt.executeUpdate();
			System.out.println(affectedRows2T + " Number of affectedRows2T deleted");
		}
		System.out.println("Bottom of deleteRows() in BuilderDBTesterUtility class");
		/*
		 * // This was the old code that deletes one row at a time. Integer idNumberInt
		 * = selectedExamIndex + 1; String idNumberStr = idNumberInt.toString(); String
		 * sqlDeleteRowsR1 = " DELETE FROM TESTER_EXAMS_LIST_4 WHERE id = ";
		 * idFoundLabel: for (int i = 1; i < 10; i++) { if ((idNumberInt) == i) {
		 * sqlDeleteRowsR1 = sqlDeleteRowsR1 + idNumberStr; break idFoundLabel; }
		 */
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR2)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println(affectedRows + " Number of effectedRows deleted");
		}
		System.out.println("Bottom of deleteRows() in BuilderDBTesterUtility class");
	}

	public Integer getGradedRowCount() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getGradedRowCount() in BuilderDBTesterUtility class");
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM FINAL_GRADED_EXAMS_2")) {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the count");
				count = rs.getInt(1);
				System.out.println("This FINAL_GRADED_EXAMS_2 table contains " + count + " rows");
			}
		}
		System.out.println("Bottom of getGradedRowCount() in BuilderDBTesterUtility class");
		return count;
	}

	public void deleteGradedRows(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of deleteGradedRows() in BuilderDBTesterUtility class");
		// Deletes all rows at once
		String sqlDeleteRowsR3 = " DELETE FROM FINAL_GRADED_EXAMS_2 "; 
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR3)) {
			int affectedRows3T = stmt.executeUpdate();
			System.out.println(affectedRows3T + " Number of affectedRows3T deleted");
		}
		// Isn't this redundant?
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR3)) {
			int affectedRowsR3 = stmt.executeUpdate();
			System.out.println(affectedRowsR3 + " Number of effectedRowsR3 deleted");
		}
		System.out.println("Bottom of deleteGradedRows() in BuilderDBTesterUtility class");
		/*
		 * Integer idNumberInt = selectedExamIndex + 1; String idNumberStr =
		 * idNumberInt.toString(); String sqlDeleteRowsR1 =
		 * " DELETE FROM FINAL_GRADED_EXAMS_2 WHERE id = "; idFoundLabel: for (int i =
		 * 1; i < 10; i++) { if ((idNumberInt) == i) { sqlDeleteRowsR1 = sqlDeleteRowsR1
		 * + idNumberStr; break idFoundLabel; } }
		 * 
		 * try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa",
		 * ""); PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR1)) { int
		 * affectedRows = stmt.executeUpdate(); System.out.println(affectedRows +
		 * " Number of rows deleted"); } System.out.
		 * println("Bottom of deleteGradedRows() in BuilderDBTesterUtility class");
		 */
	}
	
	public Integer getRowCountStudentsFinalGraded() throws IOException, SQLException, ClassNotFoundException {
		System.out.println(" Top of getRowCountStudentsFinalGraded() in BuilderDBTesterUtitility class");	
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM Students_Graded_Exams_Table2")) {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the count");
				count = rs.getInt(1);
				System.out.println("This Students_Graded_Exams_Table2 table contains " + count + " rows cvxcsdwe");
			}
		}
		System.out.println("Bottom of getRowCountStudentsFinalGraded() in BuilderDBTesterUtitility class");
		return count;
	}

	public void deleteStudentsFinalRows(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of deleteStudentsFinalRows() in  BuilderDBTesterUtitility class");
		// Deletes all rows at once
		String sqlDeleteRowsR4 = " DELETE FROM Students_Graded_Exams_Table2 "; 
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR4)) {
			int affectedRows3T = stmt.executeUpdate();
			System.out.println(affectedRows3T + " Number of affectedRows3T deleted");
		}
		// Redundant from previous code
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR4)) {

			int affectedRowsR4 = stmt.executeUpdate();
			System.out.println(affectedRowsR4 + " Number of effectedRowsR4 deleted");
		}
		System.out.println("Bottom of deleteStudentsFinalRows() in  BuilderDBTesterUtitility class");
		/*
		 * 
		 * Integer idNumberInt = selectedExamIndex + 1; String idNumberStr =
		 * idNumberInt.toString(); String sqlDeleteRowsR1 =
		 * " DELETE FROM Students_Graded_Exams_Table2 WHERE id = "; idFoundLabel: for
		 * (int i = 1; i < 10; i++) { if ((idNumberInt) == i) { sqlDeleteRowsR1 =
		 * sqlDeleteRowsR1 + idNumberStr; break idFoundLabel; } }
		 * 
		 * try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa",
		 * ""); PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR1)) {
		 * 
		 * int affectedRows = stmt.executeUpdate(); System.out.println(affectedRows +
		 * " Number of rows deleted"); }
		 */
	}

	public void deleteOuterNestedRows(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {
		
		String sqlDeleteRowsR1 = " DELETE FROM STUDENTS_OUTERNESTED_TABLE4 WHERE id = 1";	
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR1)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println(affectedRows + " Number of rows deleted in STUDENTS_OUTERNESTED_TABLE4 is ___");
		}

	}

	// Again, where is sqlRSCount used? It says it is not
	public Integer getRowCountOuterNested() throws IOException, SQLException, ClassNotFoundException {
		System.out.println(" Top of getRowCountOuterNested() from BuilderDBTesterUtitility class");	
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM STUDENTS_OUTERNESTED_TABLE4")) {
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the count");
				count = rs.getInt(1);
				System.out.println("This STUDENTS_OUTERNESTED_TABLE4 table contains " + count + " rows");
			}
			System.out.println(
					"This STUDENTS_OUTERNESTED_TABLE4 table contains " + count + " rows - after the while loop");
		}
		System.out.println(" Bottom of getRowCountOuterNested() from BuilderDBTesterUtitility class");
		return count;
	}
}
