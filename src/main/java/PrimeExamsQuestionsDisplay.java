
// Important class

// Not certain if these comments are correct after so much refactoring. But keep just in case.

// First, exam is created with placeholders and travels down the chain of tables as explained above.
// Then, primeExamsQuestionsDisplay.loadSerListPlaceholders is called at the bottom of 
// the setStudentsFinalGradedExams() method of InsertInitialStudentsFinalGraded class (4th table).
// loadSerListPlaceholders() returns a masterOfCreatedExamsDisplayed object from 
// displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();

// This masterOfCreatedExamsDisplayed object is set with a listOfQuestionsSER,  
// then, if the table's row count is 0 it INSERTS the masterOfCreatedExamsDisplayed
// into the DISPLAY_EXAMS_QUESTIONS_TABLE_1 by calling insertMasterPlaceDisplayTable() in this 
// PrimeExamsQuestionsDisplay class. And if not 0 (meaning it already exists 
// in the 1st and only row) it calls updateMasterPlaceDisplayTable() in the same class 
// and UPDATES that existing object in Row 1.

package main.java;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrimeExamsQuestionsDisplay {

	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;

	// First, load up the ten serlist placeholders in the
	// masterOfCreatedExamsDisplayed	
	public void loadSerListPlaceholders(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)	
			throws ClassNotFoundException, SQLException, IOException {
		System.out.println("Top of loadSerListPlaceholders() method of class PrimeExamsQuestionsDisplay");
		var displayExamsAndQuestions = new DisplayExamsAndQuestions();
		masterOfCreatedExamsDisplayed = displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();

		if (masterOfCreatedExamsDisplayed == null) {
			masterOfCreatedExamsDisplayed = new ArrayList<ArrayList<QuestionSuper>>();
			for (int i = 0; i < 10; i++) {
				masterOfCreatedExamsDisplayed.add(null);
			}
		}

		// Get the rowcount, then if rowcount is 0 call the insert method, but if
		// rowcount is 1 and !> 1 use the update method
		var dBaseUtility = new dbUtility();
		Integer rowCount = dBaseUtility.rowCountEQDisplay();
		if (rowCount == 0) {
			// Call the new method that inserts that first row
			masterOfCreatedExamsDisplayed.set(selectedExamIndex, listOfQuestionsSER);
			insertMasterPlaceDisplayTable(masterOfCreatedExamsDisplayed);
		} else {
			// Call the new method that updates that first row (always only use that one row
			// to hold the masterOfCreatedExamsDisplayed)
			masterOfCreatedExamsDisplayed.set(selectedExamIndex, listOfQuestionsSER);
			updateMasterPlaceDisplayTable(masterOfCreatedExamsDisplayed);
		}
		masterOfCreatedExamsDisplayed = displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();
		System.out.println(masterOfCreatedExamsDisplayed + " masterOfCreatedExamsDisplayed dhbcpi0786");
		System.out.println("Bottom of loadSerListPlaceholders() method of class PrimeExamsQuestionsDisplay");
	}

	public void insertMasterPlaceDisplayTable(ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed)
			throws IOException, SQLException {
		System.out.println("Top of insertMasterPlaceDisplayTable() method of class PrimeExamsQuestionsDisplay");

		ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
		ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
		oosMCD.writeObject(masterOfCreatedExamsDisplayed);
		byte[] serializedObjectBytesMCD = baosMCD.toByteArray();
		oosMCD.close();

		String insertDisplaySQL = "INSERT INTO DISPLAY_EXAMS_QUESTIONS_TABLE_1 (id, EXAM_NUMBER, "
				+ "LISTOFQUESTIONS, LISTOFEXAMLISTS ) VALUES(?, ?, ?, ?)";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(insertDisplaySQL)) {

			stmt2.setInt(1, 1); // Always set it in the first row only
			stmt2.setInt(2, 424242);
			stmt2.setObject(3, null);
			stmt2.setObject(4, serializedObjectBytesMCD);
			int rowsEffected49 = stmt2.executeUpdate();

			System.out.println(rowsEffected49 + " rowsEffected49 masterOfCreatedExamsDisplayed cv77dlo");
			
		}
		System.out.println("Bottom of insertMasterPlaceDisplayTable() method of class PrimeExamsQuestionsDisplay");
	}

		// It's called above in loadSerListPlaceholders()
	public void updateMasterPlaceDisplayTable(ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed)
			throws IOException, SQLException {
		System.out.println("Top of updateMasterPlaceDisplayTable() method of class PrimeExamsQuestionsDisplay");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(masterOfCreatedExamsDisplayed);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE DISPLAY_EXAMS_QUESTIONS_TABLE_1 SET id = ?, EXAM_NUMBER = ?, LISTOFQUESTIONS = ?, "
				+ "LISTOFEXAMLISTS = ? WHERE id = ?";

		try(Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)){

		stmt2.setInt(1, 1);
		stmt2.setObject(2, 0);
		stmt2.setObject(3, null);
		stmt2.setObject(4, serializedObjectBytes); // this is where the masterOfCreatedExamsDisplayed should be set
		stmt2.setInt(5, 1);

		int rowsEffected245 = stmt2.executeUpdate();
		System.out.println(rowsEffected245 + " Number of rowsEffected245");
		}
		System.out.println("Bottom of updateMasterPlaceDisplayTable() method of class PrimeExamsQuestionsDisplay");
	}

		
	// This is called in upDateProfsListInTester() of the UpdateProfsFinalList class which has
	// three methods that update tables 2, 3 and 4. I'm thinking all those calls are grouped
	// together someplace. 
	public void setSerialMasterOfCreatedExamsDisplayed(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER) throws SQLException, ClassNotFoundException, IOException {
		System.out.println("Top of setSerialMasterOfCreatedExamsDisplayed() method of class PrimeExamsQuestionsDisplay");		
		var displayExamsAndQuestions = new DisplayExamsAndQuestions();
		masterOfCreatedExamsDisplayed = displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();
		masterOfCreatedExamsDisplayed.set(selectedExamIndex, listOfQuestionsSER);

		ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
		ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
		oosMCD.writeObject(masterOfCreatedExamsDisplayed);
		byte[] serializedObjectBytesMCD = baosMCD.toByteArray();
		oosMCD.close();

		String displayEQSQL = "UPDATE DISPLAY_EXAMS_QUESTIONS_TABLE_1  SET id = ?, EXAM_NUMBER =?, LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ?  WHERE id=?";

		try(Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt2 = conn2.prepareStatement(displayEQSQL)){
			
		stmt2.setInt(1, 1);
		stmt2.setInt(2, 424242);
		stmt2.setObject(3, null);
		stmt2.setObject(4, serializedObjectBytesMCD);
		stmt2.setInt(5, 1);
		int rowsEffected3xz = stmt2.executeUpdate();
		System.out.println(rowsEffected3xz + " update rowsEffected3xz");
		}
	
		// Pull master up and check
		masterOfCreatedExamsDisplayed = displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();
		System.out.println(masterOfCreatedExamsDisplayed + " masterOfCreatedExamsDisplayed cvdferyuhj");
		listOfQuestionsSER = masterOfCreatedExamsDisplayed.get(selectedExamIndex);
		for (int k = 0; k < listOfQuestionsSER.size(); k++) {
			if (listOfQuestionsSER.get(k) != null) {
				System.out.println(listOfQuestionsSER.get(k).getTitle() + " title?,fhzsaerjfu");
			}
		}
		System.out.println("Bottom of setSerialMasterOfCreatedExamsDisplayed() method of class PrimeExamsQuestionsDisplay");
	}
}
