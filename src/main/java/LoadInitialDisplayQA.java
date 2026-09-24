
// DANGER, DANGER. NEEDS LOTS OF WORK - still dirty

// 5-28 the LoadInitialDisplay button disconnects this.
// BUT SINCE I REFACTORED THIS IT MIGHT NOT BE NECESSARY,
// SO TRACK IT DOWN ANDN FIND OUT HOW IT IS USED
// Class LoadInitialDisplayQA is used when displaying the current number of
// exams and questions ("Number of Created Exams/Questions" button) in the main preview pane. 
// It creates an ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed, serializes and saves it 
// to DISPLAY_EXAMS_QUESTIONS_TABLE_1.
// Contains three (3) methods currently

package main.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadInitialDisplayQA {
	Integer rowsEffected4;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;

	public void loadInitMasterCreatedExamsDisplayed() throws SQLException, IOException, ClassNotFoundException {
		if (masterOfCreatedExamsDisplayed == null) {
			masterOfCreatedExamsDisplayed = new ArrayList<ArrayList<QuestionSuper>>();
		} else {
			System.out.println(
					(masterOfCreatedExamsDisplayed.size() + " masterOfCreatedExamsDisplayed.size() 8989898989898989"));
		}

		ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
		ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
		oosMCD.writeObject(masterOfCreatedExamsDisplayed);
		byte[] serializedObjectBytesMCD = baosMCD.toByteArray();
		oosMCD.close();

		String insertDisplaySQL = "INSERT INTO DISPLAY_EXAMS_QUESTIONS_TABLE_1 (id, EXAM_NUMBER, "
				+ "LISTOFQUESTIONS, LISTOFEXAMLISTS ) VALUES(?, ?, ?, ?)";

		try (Connection conn2 = DatabaseConfig.getConnection();
				PreparedStatement stmt2 = conn2.prepareStatement(insertDisplaySQL)) {

			stmt2.setInt(1, 1);
			stmt2.setInt(2, 424242);
			stmt2.setObject(3, null);
			stmt2.setObject(4, serializedObjectBytesMCD);
			rowsEffected4 = stmt2.executeUpdate();

		}

		System.out.println(rowsEffected4 + " rowsEffected4 SERlist RowsEffected ");
		System.out.println("Bottom of loadInitMasterCreatedExamsDisplayed() of the LoadInitialDisplayQA class");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<QuestionSuper>> getInitMasterCreatedExamsDisplayed()
			throws SQLException, IOException, ClassNotFoundException {

		String sqlRS = " SELECT id, LISTOFEXAMLISTS FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1 ";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {

			// byte[] serializedObjectBytesMCD = null;

			// Do I need to break out of this?
			while (rs.next()) {
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("LISTOFEXAMLISTS");
				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						masterOfCreatedExamsDisplayed = (ArrayList<ArrayList<QuestionSuper>>) ois.readObject();
					} catch (EOFException ef) {
						System.out.println("EOFException in getInitMasterCreatedExamsDisplayed() method");
					}
				}
			}
		}
		return masterOfCreatedExamsDisplayed;
	}

	public void loadMasterListOfQuestionsSER(ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {

		if (masterListOfQuestionsSER == null) {
			masterListOfQuestionsSER = new ArrayList<ArrayList<QuestionSuper>>();
			System.out.println(
					(masterListOfQuestionsSER == null) + " Is masterOfCreatedExamsDisplayed null? 6565656565656565656");
			System.out.println((masterListOfQuestionsSER.size() + " masterOfCreatedExamsDisplayed.size() 77777777777"));
		} else {
			System.out.println(
					(masterListOfQuestionsSER.size() + " masterOfCreatedExamsDisplayed.size() 8989898989898989"));
		}

		ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
		ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
		oosMCD.writeObject(masterListOfQuestionsSER);
		byte[] serializedMasterBytesMCD = baosMCD.toByteArray();
		oosMCD.close();

		String insertDisplaySQL = "INSERT INTO DISPLAY_EXAMS_QUESTIONS_TABLE_1 (id, EXAM_NUMBER, "
				+ "LISTOFQUESTIONS, LISTOFEXAMLISTS ) VALUES(?, ?, ?, ?)";

		try (Connection conn2 = DatabaseConfig.getConnection();
				PreparedStatement stmt2 = conn2.prepareStatement(insertDisplaySQL)) {

			stmt2.setInt(1, 1);
			stmt2.setInt(2, 424242);
			stmt2.setObject(3, null); //
			stmt2.setObject(4, serializedMasterBytesMCD);
			rowsEffected4 = stmt2.executeUpdate();
		}
		System.out.println(rowsEffected4 + " rowsEffected4");
		System.out.println("Bottom of loadMasterListOfQuestionsSER of LoadInitialDisplayQA class");
	}
}
