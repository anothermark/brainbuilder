
// Class InsertlistOfQuestionsSERtoDB is where the QuestionSuper is serialized and set/updated 
// in the first BUILDER_EXAMS_LISTS_17 table. Called at the bottom of the QuestionFactory's setQuestionArray() method.
// It's called lastly in the factory and is important

package main.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertlistOfQuestionsSERtoDB implements Serializable {
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;

	public ArrayList<QuestionSuper> updateRows(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of updateRows() method in the InsertlistOfQuestionsSERtoDB class");
		System.out.println(listOfQuestionsSER + " xkoexirsz");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE BUILDER_EXAMS_LISTS_17  SET id = ?, EXAM_NUMBER =?, LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ?  WHERE id=?";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
			stmt.setInt(1, selectedExamIndex + 1); 
			stmt.setInt(2, 424242);
			stmt.setObject(3, serializedObjectBytes);
			stmt.setObject(4, null);
			stmt.setInt(5, selectedExamIndex + 1);
			int rowsEffected2 = stmt.executeUpdate();
			System.out.println(rowsEffected2 + " rowsEffected2 perr9504");
		}
		var getListFromDB = new GetListFromDB();
		
				
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER sl757lsl645etp");
		
		System.out.println("oxei5948594n");

		// Is this trywithresources necessary? I don't think so.
		try(var updateProfsFinalList = new UpdateProfsFinalList()) {		
			updateProfsFinalList.upDateProfsListInTester(selectedExamIndex, listOfQuestionsSER);
		} catch (Exception e) {}
		
		System.out.println("Bottom of updateRows() method in the InsertlistOfQuestionsSERtoDB class");
		return listOfQuestionsSER;
	}
}
