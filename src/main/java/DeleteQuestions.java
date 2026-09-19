
// Class DeleteQuestions does just that in a deleteQuestion() method, and
// then it must serialize that listOfQuestionsSER and save it to the first BUILDER_EXAMS_LISTS_17 table, 
// whereupon that change propagates down the line 

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

public class DeleteQuestions {

	Integer selectedExamIndex;
	Integer selectedQIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;
	ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers;
	ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs;

	public void deleteQuestion(Integer selectedExamIndex, Integer selectedIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER) throws IOException, SQLException, ClassNotFoundException {

		System.out.println("Top of deleteQuestion() of DeleteQuestions class");
		SelectIndNull: if (selectedIndex == null) {
			// This might be redundant because I replicate it in the call
			JOptionPane.showMessageDialog(null, "You must select a question before you can delete it.");
			break SelectIndNull;
		} else {
			OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
			outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
			masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs.get(selectedExamIndex);
			masterListOfProfsCorrectAnswers.set(selectedIndex, null);
			System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers jshqgvaxp98");
			outerMasterCorrectAnswersProfs.set(selectedExamIndex, masterListOfProfsCorrectAnswers);
			System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers cbdgrt4532");
			System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs idmejrt72593t6");
			// And now just delete the question and all the other questions will have the
			// updated masterListOfProfsCorrectAnswers.
			for (int i = 0; i < listOfQuestionsSER.size(); i++) {
				if (selectedIndex == i) {
					listOfQuestionsSER.set(selectedIndex, null);

				}
			}

			saveListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER);
			// Don't forget to save the outerMasterCorrectAnswersProfs to the new db
			outerMasterCorrectAnswers.setUpdateOuterMasterAnswers(outerMasterCorrectAnswersProfs);
			outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
			System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs sjdftqksxlf");
		}
		System.out.println("Bottom of deleteQuestion() of DeleteQuestions class");
	}

	public void saveListOfQuestionsSER(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws IOException, SQLException, ClassNotFoundException {

		System.out.println("Top of saveListOfQuestionsSER() of DeleteQuestions class");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE BUILDER_EXAMS_LISTS_17 SET id = ?, EXAM_NUMBER = ?, "
				+ "LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ? WHERE id = ?";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

			stmt2.setInt(1, selectedExamIndex + 1);
			stmt2.setInt(2, selectedExamIndex + 1);
			stmt2.setObject(3, serializedObjectBytes);
			stmt2.setObject(4, null);
			stmt2.setInt(5, selectedExamIndex + 1);
			int rowsEffected2W = stmt2.executeUpdate();
			System.out.println(rowsEffected2W + " Number of rowsEffected2W");

			listOfQuestionsSER = getUpdatedListOfQuestionsSER(selectedExamIndex);
		}
		// THIS IS IMPORTANT AS IT UPDATES DOWN THE LINE
		try (var updateProfsFinalList = new UpdateProfsFinalList()) {
			updateProfsFinalList.upDateProfsListInTester(selectedExamIndex, listOfQuestionsSER);
			updateProfsFinalList.upDateFINAL_GRADED_EXAMS_2(selectedExamIndex, listOfQuestionsSER);
			updateProfsFinalList.upDateStudents_Graded_Exams_Table2(selectedExamIndex, listOfQuestionsSER);
		}
		System.out.println("Bottom of saveListOfQuestionsSER() of DeleteQuestions class");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QuestionSuper> getUpdatedListOfQuestionsSER(Integer selectedExamIndex)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of getUpdatedListOfQuestionsSER() of DeleteQuestions class");

		String sqlRS = " SELECT id, EXAM_NUMBER, LISTOFQUESTIONS, LISTOFEXAMLISTS FROM BUILDER_EXAMS_LISTS_17 ";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			byte[] listOfQBytes = null;
			PrintedRowSoBreak: while (rs.next()) {
				Integer id = rs.getInt("id");
				if (id == selectedExamIndex + 1) {
					int EXAM_NUMBER = rs.getInt("EXAM_NUMBER"); // No need for this, actually
					System.out.println(EXAM_NUMBER + " EXAM_NUMBER printed");// Or this
					listOfQBytes = rs.getBytes("LISTOFQUESTIONS");

					if (listOfQBytes != null) {
						try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfQBytes);
								ObjectInputStream ois = new ObjectInputStream(bais)) {
							deserializedObject = (ArrayList<QuestionSuper>) ois.readObject();
						} catch (EOFException ef) {
							System.out.println("EOFException in getUpdatedListOfQuestionsSER() method");
						}
						listOfQuestionsSER = deserializedObject;
						System.out.println("Bottom of getUpdatedListOfQuestionsSER() of DeleteQuestions class");
						break PrintedRowSoBreak;
					}
				}
			}
		}
		return listOfQuestionsSER;
	}
}
