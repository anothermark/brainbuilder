
// This is a confusing mess
// InsertInitialStudentsFinalGraded has two (2) methods.
// setStudentsFinalGradedExams() INSERTS a listOfQuestionsSER into Students_Graded_Exams_Table2.
// At the bottom of the method insertInitialOuterNestedPlaceholders.loadInitialOuterNested() sends
// a masterListOfQuestionsSER and listOfQuestionsSER to the InsertInitialOuterNestedPlaceholders class,
// where graded exams eventually reside in the STUDENTS_OUTERNESTED_TABLE4. 


package main.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertInitialStudentsFinalGraded {
	Integer studentsPrimaryKey;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	InsertInitialOuterNestedPlaceholders insertInitialOuterNestedPlaceholders;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;

	// Careful - This is actually an Insert, not a set or Update. Rename?

	// Remember 8-6 you don't actually use these for anything because this is where
	// the
	// student's graded exam serlist goes eventually and that is then
	// set in the 5th outer in Tester after exam is finished.
	public void setStudentsFinalGradedExams(Integer selectedExamIndex, Integer initialNumberOfQuestions,
			String selectedValue, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {
		System.out
				.println("Top of setStudentsFinalGradedExams() method of the builders InsertInitialStudentsFinalGraded "
						+ " class where each student's master list of graded exams are stored");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER DFDSDFD");

		

		// 8-6 No need for this now because the outer 5th doesn't get null serlist
		// placeholders
		var insertInitialOuterNestedPlaceholders = new InsertInitialOuterNestedPlaceholders();
		outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders.getOuterMasterListOfMastersSER();
		
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER DFDSDFD");

		// No need for this now. The road stops after inserting in the 4th table below
		if (masterListOfQuestionsSER == null) {// This becomes the innernestedmaster in the final table

			// But now 8-6 no need for this masterListOfQuestionsSER
			masterListOfQuestionsSER = new ArrayList<ArrayList<QuestionSuper>>();
			System.out.println(listOfQuestionsSER + " listOfQuestionsSER czcz28734czc");

			System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER pup87upupu");
		} else {
			// Eat it?
		}

		String insertSQL = "INSERT INTO Students_Graded_Exams_Table2 (id, STUDENTLASTNAME, STUDENTFIRSTNAME, "
				+ "LISTOFQUESTIONS, LISTOFGRADEDEXAMSLISTS ) VALUES(?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

			BreakerBreaker: for (int k = 0; k < 10; k++) {
				if (k == selectedExamIndex + 1) {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					oos.writeObject(listOfQuestionsSER);
					byte[] serializedObjectBytes = baos.toByteArray();
					oos.close();

					stmt.setInt(1, selectedExamIndex + 1); // This maps to the selectedExamIndex from first JList
					stmt.setString(2, "Student LAST NAME - variable?");
					stmt.setString(3, "Students FIRST NAME - variable?");
					stmt.setObject(4, serializedObjectBytes);
					stmt.setObject(5, null);
					int rowsInitialEffectedStudentsFinal = stmt.executeUpdate();
					System.out.println(rowsInitialEffectedStudentsFinal + " rowsInitialEffectedStudentsFinal  "
							+ "Original tester Students_Graded_Exams_Table2 table");
					break BreakerBreaker;
				}
			}
		}

		System.out.println(
				"Bottom of setStudentsFinalGradedExams() method of the builders InsertInitialStudentsFinalGraded "
						+ " class where each student's master list of graded exams are stored");
	}

	// Bad method name and confusing because it updates the
	// Students_Graded_Exams_Table2
	// and not the 5th outer
	public void setUpdateOuterMasterListofMastersSER(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER, Integer numberOfAdditionalQuestions)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println(
				"Top of setUpdateOuterMasterListofMastersSER() method in the InsertInitialStudentsFinalGraded class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER djy46dg");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE Students_Graded_Exams_Table2 SET id = ?, STUDENTLASTNAME = ?, STUDENTFIRSTNAME = ?,"
				+ "LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ? WHERE id = ?";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {
			stmt2.setInt(1, selectedExamIndex + 1);
			stmt2.setObject(2, "Last Name");
			stmt2.setObject(3, "First Name");
			stmt2.setObject(4, serializedObjectBytes);
			stmt2.setObject(5, null);
			stmt2.setInt(6, selectedExamIndex + 1);
			int rowsEffected2 = stmt2.executeUpdate();
			System.out.println(rowsEffected2 + " Number of rowsEffected2 nvhftr");
		}
		var builderDBTesterUtility = new BuilderDBTesterUtility();
		int count = builderDBTesterUtility.getRowCountStudentsFinalGraded();

		System.out.println(
				"Bottom of setUpdateOuterMasterListofMastersSER() method in the InsertInitialStudentsFinalGraded class");
	}
}
