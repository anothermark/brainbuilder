
// Class InsertInitialGradedExPlaceholders INSERTS listOfQuestionsSER into FINAL_GRADED_EXAMS_2 table
// using loadInitialFinalGraded() while the student is taking the exam. 
// serialAdditionalListOfQuestionsSER() UPDATES FINAL_GRADED_EXAMS_2 when additional questions are added.
// Chained insertInitialStudentsFinalGraded.setStudentsFinalGradedExams is called at the bottom
// of both methods. (Is this still valid on 7-25-26?)

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

public class InsertInitialGradedExPlaceholders {
	ArrayList<QuestionSuper> listOfQuestionsSER;

	public void loadInitialFinalGraded(Integer selectedExamIndex, Integer initialNumberOfQuestions,
			String selectedValue, ArrayList<QuestionSuper> listOfQuestionsSER) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of loadInitialFinalGraded() method in class loadInitialFinalGraded");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER fhfhf98hfh");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String insertSQL = "INSERT INTO FINAL_GRADED_EXAMS_2 (id, EXAM_NUMBER, STUDENTLASTNAME, "
				+ "LISTOFQUESTIONS, LISTOFGRADEDEXAMSLISTS ) VALUES(?, ?, ?, ?, ?)";

		try (Connection conn2 = DatabaseConfig.getConnection();
				PreparedStatement stmt2 = conn2.prepareStatement(insertSQL)) {

			if (initialNumberOfQuestions <= 10) {
				stmt2.setInt(1, selectedExamIndex + 1);
				stmt2.setInt(2, selectedExamIndex);
				stmt2.setString(3, "finally student last name");
				stmt2.setObject(4, serializedObjectBytes);
				stmt2.setObject(5, null); // The old useless masterlist?
				int rowsEffected3 = stmt2.executeUpdate();
				System.out.println(rowsEffected3 + " number of rowsEffected3 in loadInitialFinalGraded()");
			} else {
				JOptionPane.showMessageDialog(null, "Exceeding number of questions permitted (10)");
			}
		}

		try {
			var insertInitialStudentsFinalGraded = new InsertInitialStudentsFinalGraded();
			insertInitialStudentsFinalGraded.setStudentsFinalGradedExams(selectedExamIndex, initialNumberOfQuestions,
					selectedValue, listOfQuestionsSER);
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Bottom of loadInitialFinalGraded() method in class loadInitialFinalGraded");
	}

	public void serialAdditionalListOfQuestionsSER(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER, Integer numberOfAdditionalQuestions)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println(
				"Top of serialAdditionalListOfQuestionsSER() method in the InsertInitialGradedExPlaceholders class");

		System.out.println(listOfQuestionsSER + " listOfQuestionsSER cvfrsu");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE FINAL_GRADED_EXAMS_2 SET id = ?, EXAM_NUMBER = ?, "
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ? WHERE id = ?";

		try (Connection conn2 = DatabaseConfig.getConnection();
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

			stmt2.setInt(1, selectedExamIndex + 1);
			stmt2.setInt(2, selectedExamIndex + 1);
			stmt2.setObject(3, serializedObjectBytes);
			stmt2.setObject(4, null);
			stmt2.setInt(5, selectedExamIndex + 1);
			int rowsEffected2 = stmt2.executeUpdate();
			System.out.println(rowsEffected2 + " Number of rowsEffected2");
		}

		var builderDBTesterUtility = new BuilderDBTesterUtility();
		int count = builderDBTesterUtility.getRowCountFinalGraded();
		var insertInitialStudentsFinalGraded = new InsertInitialStudentsFinalGraded();
		// This can't be right. The outermaster 5th doesn't get updated
		insertInitialStudentsFinalGraded.setUpdateOuterMasterListofMastersSER(selectedExamIndex, listOfQuestionsSER,
				numberOfAdditionalQuestions);
		System.out.println("This FINAL_GRADED_EXAMS_2 table contains " + count + " rows iyiyi231yutturyry ");
		System.out.println(
				"Bottom of serialAdditionalListOfQuestionsSER() method in the InsertInitialGradedExPlaceholders class");
	}
}
