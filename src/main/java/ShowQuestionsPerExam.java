
// Class ShowQuestionsPerExam accesses the outerMasterListOfMastersSER object from
// the DISPLAY_EXAMS_QUESTIONS_TABLE_1 and via selectedExamIndex displays all 
// questions in each selected exam. Displays title, null, or "empty" or something like that
// to show which questions are where, and those that are empty or null. Handy.
// But perhaps not necessarily accurate since I created the PrimeExamsQuestionsDisplay class

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

import javax.swing.JOptionPane;

public class ShowQuestionsPerExam {

	String titleStuff;
	String listOfQuestionsStr = "";
	Integer selectedExamIndex;
	QuestionSuper anonQuestion;

	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfMastersSER;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed; // this comes over from PrimeExamQuestion class
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;

	public String showExamQuestionsFromOuter(Integer selectedExamIndex, ArrayList<ArrayList<ArrayList<QuestionSuper>>> 
	outerMasterListOfMastersSER) throws ClassNotFoundException, SQLException, IOException {		
		
		masterListOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex);
		//if(masterListOfQuestionsSER == null) {
			//JOptionPane.showMessageDialog(null,
					//"No exam here 8-5 - deleted and null");
			//System.out.println("kpocio8t5r");
			
		//} else {
		// 8-5 but now it is null pointer thrown
		if(masterListOfQuestionsSER != null) {
		listOfQuestionsSER = masterListOfQuestionsSER.get(0);
		} 
		String anonStr = "Questions Populated in Exam " + (selectedExamIndex + 1) + "\n";		

		if (listOfQuestionsSER == null) {
			JOptionPane.showMessageDialog(null,
					"There are no populated questions to display");
		} else {
			for (int i = 0; i < listOfQuestionsSER.size(); i++) {
				if (listOfQuestionsSER.get(i) == null) {
					anonStr += "\n " + (i + 1) + ") " + "Empty";
				} else {
					anonStr += "\n " + (i + 1) + ") " + "Title: " + listOfQuestionsSER.get(i).getTitle() + ""
							+ "\n      Topic: " + listOfQuestionsSER.get(i).getQuestionTopic();
				}
			}
		
		}
	
		return anonStr;
		
	}		
	
	public String showExamQuestions(Integer selectedExamIndex)
			throws ClassNotFoundException, SQLException, IOException {

		var displayExamsAndQuestions = new DisplayExamsAndQuestions();
		masterOfCreatedExamsDisplayed = displayExamsAndQuestions.getInitMasterCreatedExamsDisplayed();

		listOfQuestionsSER = masterOfCreatedExamsDisplayed.get(selectedExamIndex);
		String anonStr = "Questions Populated in Exam " + (selectedExamIndex + 1) + "\n";
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER should be mapped to selectedExamIndex asqwkoipul");

		if (listOfQuestionsSER == null) {
			JOptionPane.showMessageDialog(null,
					"The selected exam has not been created and therefore " + "\nthere are no populated questions");
		} else {
			for (int i = 0; i < listOfQuestionsSER.size(); i++) {
				if (listOfQuestionsSER.get(i) == null) {
					anonStr += "\n " + (i + 1) + ") " + "Empty";
				} else {
					anonStr += "\n " + (i + 1) + ") " + listOfQuestionsSER.get(i).getTitle();
				}
			}
		}
		return anonStr;
	}

		// What calls this?
	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<QuestionSuper>> getMasterListOfQuestionsSER(Integer selectedExamIndex)
			throws SQLException, IOException, ClassNotFoundException {

		String sqlRS = " SELECT * FROM Students_Graded_Exams_Table2";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {			
			int count = 0;
	
			while (rs.next()) {				
				System.out.println("popongy945");
				count++;
				System.out.println(count + " count gddfer");			
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("LISTOFGRADEDEXAMSLISTS");

				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						masterListOfQuestionsSER = (ArrayList<ArrayList<QuestionSuper>>) ois.readObject();
					} catch (EOFException ef) {
						System.out.println("EOFException in getMasterListOfQuestionsSER() method");
					}
				}
			}
		}
		return masterListOfQuestionsSER;
	}

		// Called from bottom of upDateStudents_Graded_Exams_Table2() of the UpdateProfsFinalList class
	// which sets these in the Students_Graded_Exams_Table2, but that makes no sense. 
	public void setMasterListOfQuestionsSER(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {
		if (masterListOfQuestionsSER == null) {// this becomes the innernestedmaster in the final table
			masterListOfQuestionsSER = new ArrayList<ArrayList<QuestionSuper>>();
			System.out.println(listOfQuestionsSER + " listOfQuestionsSER vcfljou");
			masterListOfQuestionsSER.add(listOfQuestionsSER);
			System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER hajlf");
		} else {
			// Oops. Just eat it?
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		ByteArrayOutputStream baosMast = new ByteArrayOutputStream();
		ObjectOutputStream oosMast = new ObjectOutputStream(baosMast);
		oosMast.writeObject(masterListOfQuestionsSER);
		byte[] serializedObjectBytesMast = baosMast.toByteArray();
		oosMast.close();

		String updateSQL = "UPDATE Students_Graded_Exams_Table2  SET id = ?, STUDENTLASTNAME =?, STUDENTFIRSTNAME = ?, LISTOFQUESTIONS = ?, LISTOFGRADEDEXAMSLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

			TheUpdateLoop: for (int i = 0; i < 10; i++) {
				if (i == (selectedExamIndex + 1)) {
					stmt.setInt(1, selectedExamIndex + 1);
					stmt.setInt(2, selectedExamIndex);
					stmt.setString(3, null);
					stmt.setObject(4, serializedObjectBytes);
					stmt.setObject(5, serializedObjectBytesMast);
					stmt.setInt(6, selectedExamIndex + 1);

					int rowsEffected782 = stmt.executeUpdate();
					System.out.println(rowsEffected782 + " rowsEffected782 in setMasterListOfQuestionsSER()");
					System.out.println("Bottom of setMasterListOfQuestionsSER() in the ShowQuestionsPerExam class");
					break TheUpdateLoop;
				}
			}
		}
	}
}
