// CLASS UpdateProfsFinalList updates the second  (?)TESTER_EXAMS_LIST_4 table with 
// the latest listOfQuestionsSER list
// Another important class

package main.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateProfsFinalList implements AutoCloseable {
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfQuestionsSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER_Disp;

	// Why am I using this?
	public void close() {
		System.out.println("Closing UpdateProfsFinalList");
	}

	public void upDateProfsListInTester(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws ClassNotFoundException, IOException, SQLException {
		System.out.println("Top of upDateProfsListInTester in the UpdateProfsFinalList class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER sllslsl645etrg");

		System.out.println("oxei5948594n");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE TESTER_EXAMS_LIST_4  SET id = ?, EXAM_NUMBER =?, STUDENTLASTNAME = ?, LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
			TheUpdateLoop: for (int i = 0; i < 10; i++) {
				if (i == (selectedExamIndex + 1)) {
					stmt.setInt(1, selectedExamIndex + 1);
					stmt.setInt(2, selectedExamIndex);
					stmt.setString(3, null);
					stmt.setObject(4, serializedObjectBytes);
					stmt.setObject(5, null);
					stmt.setInt(6, selectedExamIndex + 1);
					int rowsEffected2 = stmt.executeUpdate();

					System.out.println(rowsEffected2 + " rowsEffected2 in upDateProfsListInTester()");
					GetProfListFromDB getProfListFromDB = new GetProfListFromDB();
					listOfQuestionsSER = getProfListFromDB.getRowData(selectedExamIndex);
					System.out.println("Bottom of UpdateProfsFinalList upDateProfsListInTester() method");
					break TheUpdateLoop;
				}
			}
		}

		upDateFINAL_GRADED_EXAMS_2(selectedExamIndex, listOfQuestionsSER);
		System.out.println("690rge5v986e509ym");
	}

	public void upDateFINAL_GRADED_EXAMS_2(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws ClassNotFoundException, IOException, SQLException {
		System.out.println("Top of upDateFINAL_GRADED_EXAMS_2() in the UpdateProfsFinalList class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER prints ____ hsgdterw");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE FINAL_GRADED_EXAMS_2  SET id = ?, EXAM_NUMBER =?, STUDENTLASTNAME = ?, LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			TheUpdateLoop: for (int i = 0; i < 10; i++) {
				if (i == (selectedExamIndex + 1)) {
					stmt.setInt(1, selectedExamIndex + 1);
					stmt.setInt(2, selectedExamIndex);
					stmt.setString(3, null);
					stmt.setObject(4, serializedObjectBytes);
					stmt.setObject(5, null);
					stmt.setInt(6, selectedExamIndex + 1);
					int rowsEffected2 = stmt.executeUpdate();

					System.out.println(rowsEffected2 + " rowsEffected2 in upDateFINAL_GRADED_EXAMS_2()");
					System.out.println(listOfQuestionsSER
							+ " listOfQuestionsSER PRINTS what? this one is in upDateFINAL_GRADED_EXAMS_2() 5-2 "
							+ " to check if that deleted question is null");
					break TheUpdateLoop;
				}
			}
		}

		upDateStudents_Graded_Exams_Table2(selectedExamIndex, listOfQuestionsSER);
	}

	public void upDateStudents_Graded_Exams_Table2(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER) throws ClassNotFoundException, IOException, SQLException {
		System.out.println("Top of Students_Graded_Exams_Table2() in the UpdateProfsFinalList class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER prints an object hgaczdsew");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE Students_Graded_Exams_Table2  SET id = ?, STUDENTLASTNAME =?, STUDENTFIRSTNAME = ?, LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			TheUpdateLoop: for (int i = 0; i < 10; i++) {
				if (i == (selectedExamIndex + 1)) {
					stmt.setInt(1, selectedExamIndex + 1);
					stmt.setInt(2, selectedExamIndex);
					stmt.setString(3, null);
					stmt.setObject(4, serializedObjectBytes);
					stmt.setObject(5, null);
					stmt.setInt(6, selectedExamIndex + 1);
					int rowsEffected2 = stmt.executeUpdate();
					System.out.println(rowsEffected2 + " rowsEffected2 in Students_Graded_Exams_Table2()");
					System.out.println(listOfQuestionsSER + " listOfQuestionsSER PRINTS what? ocr9tw039d");
					System.out.println("Bottom of Students_Graded_Exams_Table2() in the UpdateProfsFinalList class");
					break TheUpdateLoop;
				}
				

				System.out.println("oxei5948594n");

				DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
				displayExamsAndQuestions.getTheBigGradedOuter(selectedExamIndex, listOfQuestionsSER);

				InsertInitialOuterNestedPlaceholders insertInitialOuterNestedPlaceholders = new InsertInitialOuterNestedPlaceholders();
				outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders.getOuterMasterListOfMastersSER();
				System.out.println(
						outerMasterListOfMastersSER.size() + " outerMasterListOfMastersSER.size() mkei589u9ue0rut");
				System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER htdof65");
				// Using this for testing purposes
				innerMasterListOfQuestionsSER = outerMasterListOfMastersSER.get(0);
				System.out.println(innerMasterListOfQuestionsSER.size() + " innerMasterListOfQuestionsSER.size()");
				System.out.println(innerMasterListOfQuestionsSER + " innerMasterListOfQuestionsSER");
			}
		}
	}
}
