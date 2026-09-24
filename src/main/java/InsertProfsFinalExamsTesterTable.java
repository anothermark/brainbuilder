
// Major update 8-6. Refactored all the code setting initials etc. in display table.
// So, at the bottom of setOriginalProfExams() send the listOfQuestionsSER which should hold
// the number of questions as nulls over to DisplayExamsAndQuestions class and pull up the 
// display outer over there and first time through there should be 10 nulls so as per
// which selectedExamIndex create a master and insert (add) that listOfQuestionsSER at
// index 0, always, because there will always only be one listOfQuestionsSER per 
// master (exam). Should I create a new method in DisplayExamsAndQuestions? Maybe to keep it
// simple?


// Class InsertProfsFinalExamsTesterTable inserts the prof's final exam/questions 
// into the second table TESTER_EXAMS_LIST_4, from where the student retrieves it
// when beginning the exam.

// setOriginalProfExams() INSERTS listOfQuestionsSER in the TESTER_EXAMS_LIST_4 table, and also calls 
// 'chained' insertInitialGradedExPlaceholders.loadInitialFinalGraded() at bottom of method.
// serialAdditionalListOfQuestionsSER() UPDATES the TESTER_EXAMS_LIST_4 table when adding additional questions.

package main.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class InsertProfsFinalExamsTesterTable {
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;
	Integer count;

	public void setOriginalProfExams(Integer selectedExamIndex, Integer initialNumberOfQuestions, String selectedValue,
			ArrayList<QuestionSuper> listOfQuestionsSER) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of setOriginalProfExams() in the builders InsertProfsFinalExamsTesterTable class");

		if (initialNumberOfQuestions <= 10) {
			String insertSQL = "INSERT INTO TESTER_EXAMS_LIST_4 (id, EXAM_NUMBER, "
					+ "STUDENTLASTNAME, LISTOFQUESTIONS, LISTOFGRADEDEXAMSLISTS ) VALUES(?, ?, ?, ?, ?)";

			try (Connection conn = DatabaseConfig.getConnection();
					PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

				for (int k = 0; k < 10; k++) {
					if (k == selectedExamIndex) {
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ObjectOutputStream oos = new ObjectOutputStream(baos);
						oos.writeObject(listOfQuestionsSER);
						byte[] serializedObjectBytes = baos.toByteArray();
						oos.close();

						stmt.setInt(1, selectedExamIndex + 1); // this maps to the selectedExamIndex from first JList
						stmt.setInt(2, selectedExamIndex);
						stmt.setString(3, "Placeholder LAST NAME SINCE UPDATED");
						stmt.setObject(4, serializedObjectBytes);
						stmt.setObject(5, null);
						int rowsEffected2 = stmt.executeUpdate();
						System.out.println(rowsEffected2 + " rowsEffected2 TESTER_EXAMS_LIST_4 table");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Exceeding number of questions permitted (10)");
		}
		var insertInitialGradedExPlaceholders = new InsertInitialGradedExPlaceholders();
		try {
			insertInitialGradedExPlaceholders.loadInitialFinalGraded(selectedExamIndex, initialNumberOfQuestions,
					selectedValue, listOfQuestionsSER);
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
		// It think I need to call my new method setInitialExams(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
		// over in displayExams 

		
		//  8-6 BUT WHY CALL IT HERE? BECAUSE NOW YOU HAVE THE SERLIST
		
		// THIS IS IMPORTANT AS IT PRIMES OR LOADS THE DISPLAY OUTER INITIALLY
		
		// NOPE 8-6 DON'T NEED THIS. CAN DO IT IN THE CREATE EXAM SECTION AT BOTTOM
		//DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
		//outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
		// now set or insert the serlist into the master of the display table's outer.
		//displayExamsAndQuestions.setInitialExams(selectedExamIndex, listOfQuestionsSER);
		
		
		
		System.out.println("Bottom of setOriginalProfExams() in the builders InsertProfsFinalExamsTesterTable class");
	}

	public void serialAdditionalListOfQuestionsSER(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER, Integer numberOfAdditionalQuestions)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println(
				"Top of serialAdditionalListOfQuestionsSER() method in the InsertProfsFinalExamsTesterTable class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER lkkjiuk");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE TESTER_EXAMS_LIST_4 SET id = ?, EXAM_NUMBER = ?, "
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ? WHERE id = ?";

		try(Connection conn2 = DatabaseConfig.getConnection();
		PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)){

		stmt2.setInt(1, selectedExamIndex + 1);
		stmt2.setInt(2, selectedExamIndex + 1);
		stmt2.setObject(3, serializedObjectBytes);
		stmt2.setObject(4, null);
		stmt2.setInt(5, selectedExamIndex + 1);
		int rowsEffected2 = stmt2.executeUpdate();
		System.out.println(rowsEffected2 + " Number of rowsEffected2");
		}
		var builderDBTesterUtility = new BuilderDBTesterUtility();
		int count = builderDBTesterUtility.getRowCount();
		System.out.println(
				"This TESTER_EXAMS_LIST_4 table contains " + count + " rows on FRIDAY 4-24-26 FHNCV which is good");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER sfwrfcv465");			
		var insertInitialGradedExPlaceholders = new InsertInitialGradedExPlaceholders();
		insertInitialGradedExPlaceholders.serialAdditionalListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER,
				numberOfAdditionalQuestions);			
		System.out.println(
				"Bottom of serialAdditionalListOfQuestionsSER() method in the InsertProfsFinalExamsTesterTable class");
	}
}
