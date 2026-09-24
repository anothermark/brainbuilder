// Class dbUtility (should be DBUtility) gets row counts and deletes rows

package main.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class dbUtility implements Serializable {

	int count;
	Integer selectedExamIndex;
	Integer rowCountEQDisplayInt;
	ArrayList<ArrayList<QuestionSuper>> deserializedMasterDisplayObject;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;
	PreparedStatement stmt;

	public int getRowCount() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getRowCount() in dbUtility class");
		
		try (Connection conn = DatabaseConfig.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM BUILDER_EXAMS_LISTS_17")) {

			while (rs.next()) {
				count = rs.getInt(1); // I don't think I even use this.
				System.out.println(
						"This original BUILDER_EXAMS_LISTS_17 table contains " + count + " rows ldkjtiru68567");
			}
			System.out.println("Bottom of getRowCount() in dbUtility class");
			return count;
		}
	}

	public void updateExamToNull1stTbl(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of updateExamToNull1stTbl() method of the dbUtility class ");
		String updateSQL = "UPDATE BUILDER_EXAMS_LISTS_17  SET id = ?, EXAM_NUMBER =?, "
				+ "LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			stmt.setInt(1, selectedExamIndex + 1);
			stmt.setInt(2, 0);
			stmt.setObject(3, null); // set this to null
			stmt.setObject(4, null);
			stmt.setInt(5, selectedExamIndex + 1);
			int rowsEffected2 = stmt.executeUpdate();
			System.out.println(rowsEffected2 + " rowsEffected2 from delete/update() BUILDER_EXAMS_LISTS_17 ");
		}
		System.out.println("Bottom of updateExamToNull1stTbl() method of the dbUtility class ");
	}

	public void updateExamToNull2ndTbl(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of updateExamToNull2ndTbl() method of the dbUtility class ");
		String updateSQL = "UPDATE TESTER_EXAMS_LIST_4  SET id = ?, EXAM_NUMBER =?, STUDENTLASTNAME = ?,"
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
			stmt.setInt(1, selectedExamIndex + 1);
			stmt.setInt(2, 0);
			stmt.setObject(3, null);
			stmt.setObject(4, null); // SerList is set to null
			stmt.setObject(5, null); // not used
			stmt.setInt(6, selectedExamIndex + 1);
			int rowsEffected33 = stmt.executeUpdate();			
			System.out.println(rowsEffected33 + " rowsEffected33 from delete/update() TESTER_EXAMS_LIST_4 is __ 4-27");
		}
		System.out.println("Bottom of updateExamToNull2ndTbl() method of the dbUtility class ");
	}

	// For the third FINAL_GRADED_EXAMS_2 table
	public void updateExamToNull3rdTbl(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of updateExamToNull3rdTbl() method of the dbUtility class ");

		String updateSQL = "UPDATE FINAL_GRADED_EXAMS_2  SET id = ?, EXAM_NUMBER =?, STUDENTLASTNAME = ?,"
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			stmt.setInt(1, selectedExamIndex + 1);
			stmt.setInt(2, 0);
			stmt.setObject(3, null);
			stmt.setObject(4, null); // SerList is set to null
			stmt.setObject(5, null); // not used
			stmt.setInt(6, selectedExamIndex + 1);
			int rowsEffected34 = stmt.executeUpdate();
			
			System.out.println(rowsEffected34 + " rowsEffected34 from delete/update() FINAL_GRADED_EXAMS_2 is __ 4-27");
		}
		System.out.println("Bottom of updateExamToNull3rdTbl() method of the dbUtility class ");
	}

	// Fourth Students_Graded_Exams_Table2
	public void updateExamToNull4thTbl(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of updateExamToNull4thTbl() method of the dbUtility class ");

		String updateSQL = "UPDATE Students_Graded_Exams_Table2  SET id = ?, STUDENTLASTNAME = ?, STUDENTFIRSTNAME = ? ,"
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			stmt.setInt(1, selectedExamIndex + 1);
			stmt.setObject(2, null);
			stmt.setObject(3, null);
			stmt.setObject(4, null); // SerList is set to null
			stmt.setObject(5, null); // not used
			stmt.setInt(6, selectedExamIndex + 1);
			int rowsEffected35 = stmt.executeUpdate();

			// But how to verify that it has been set to null?
			System.out.println(
					rowsEffected35 + " rowsEffected35 from delete/update() Students_Graded_Exams_Table2 is __ 4-27");
		}
		System.out.println("Bottom of updateExamToNull4thTbl() method of the dbUtility class ");
	}

	public void deleteRows(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {

		String sqlDeleteRowsR2 = " DELETE FROM BUILDER_EXAMS_LISTS_17 "; // make it like the others and delete all rows

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR2)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println(affectedRows + " Number of affectedRows deleted");
		}
		System.out.println("Bottom of deleteRows() in dbUtility class");
	}

	// EQDisplay stands for exams and questions, the number of which are displayed
	// in the preview pane
	public void deleteEQDisplayRow() throws SQLException, ClassNotFoundException {
		System.out.println("Top of deleteEQDisplayRow() in dbUtility class");
		String sqlDeleteRow1 = " DELETE FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1 WHERE id = 1";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRow1)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println(affectedRows + " Number of affectedRows deleted");
		}
		System.out.println("Bottom of deleteEQDisplayRow() in dbUtility class");
	}

	public Integer rowCountEQDisplay() throws SQLException, ClassNotFoundException {
		System.out.println("Top of rowCountEQDisplay() in dbUtility class");

		try (Connection conn = DatabaseConfig.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1")) {
			rs.next();
			rowCountEQDisplayInt = rs.getInt(1);
			System.out.println(
					"This DISPLAY_EXAMS_QUESTIONS_TABLE_1 table contains " + rowCountEQDisplayInt + " rows muihotjf");
		}

		System.out.println("Bottom of rowCountEQDisplay() in dbUtility class");
		return rowCountEQDisplayInt;
	}

	// From the DeleteMasterDisplayRow button
	@SuppressWarnings("unchecked")
	public void deleteExamRowsMasterDisplayed(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {

		System.out.println("Top of deleteExamRowsMasterDisplayed() class in dbUtility class");
		// FIRST, GRAB IT FROM THE DB
		String sqlRS = " SELECT id, EXAM_NUMBER, LISTOFQUESTIONS, LISTOFEXAMLISTS FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1 ";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			byte[] listOfAQdisplayBytes = null;

			OutOfFirstIf: while (rs.next()) {
				int id = rs.getInt("id");
				if (id == 1) {
					listOfAQdisplayBytes = rs.getBytes("LISTOFEXAMLISTS");
					if (listOfAQdisplayBytes != null) {
						try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfAQdisplayBytes);
								ObjectInputStream ois = new ObjectInputStream(bais)) {
							deserializedMasterDisplayObject = (ArrayList<ArrayList<QuestionSuper>>) ois.readObject();
						} catch (EOFException ef) {
							System.out.println("EOFException in deleteExamRowsMasterDisplayed() method");
						}
						masterOfCreatedExamsDisplayed = deserializedMasterDisplayObject;
						if (masterOfCreatedExamsDisplayed.size() > 0) {
							masterOfCreatedExamsDisplayed.removeLast(); 
						} else {
							JOptionPane.showMessageDialog(null, "No exams to delete");
						}

						ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
						ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
						oosMCD.writeObject(masterOfCreatedExamsDisplayed);
						byte[] serializedObjectBytesMCD = baosMCD.toByteArray();
						oosMCD.close();

						String displayEQSQL = "UPDATE DISPLAY_EXAMS_QUESTIONS_TABLE_1  SET id = ?, EXAM_NUMBER =?, LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ?  WHERE id=?";

						Connection conn2 = DatabaseConfig.getConnection();
						PreparedStatement stmt2 = conn2.prepareStatement(displayEQSQL);
						stmt2.setInt(1, 1);
						stmt2.setInt(2, 424242);
						stmt2.setObject(3, null);
						stmt2.setObject(4, serializedObjectBytesMCD);
						stmt2.setInt(5, 1);
						int rowsEffected3 = stmt2.executeUpdate(); // it's not updating
						System.out.println(rowsEffected3 + " rowsEffected3");
						conn2.close();
					}
					break OutOfFirstIf;
				}
			}
		}
		System.out.println("Bottom of deleteExamRowsMasterDisplayed() class in dbUtility class");
	}

}
