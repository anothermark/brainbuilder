
// Class LastUtilityInBuilder recreates a deleted exam which isn't actually
// removed when deleted but set back to null which can then be populated again. Important distinction.

package main.java;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LastUtilityInBuilder {
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;

	// SHOULD BE USED ONLY FOR UPDATING THE FIRST 4 TABLES
	public void recreateDeletedExam(Integer selectedExamIndex, Integer numInitQuestionsRecreate) {
		System.out.println(selectedExamIndex + "  " + numInitQuestionsRecreate + "");
		if (listOfQuestionsSER == null) {
			listOfQuestionsSER = new ArrayList<QuestionSuper>();
			for (int i = 0; i < numInitQuestionsRecreate; i++) {
				listOfQuestionsSER.add(null);
			}
			var insertlistOfQuestionsSERtoDB = new InsertlistOfQuestionsSERtoDB();
			try {
				insertlistOfQuestionsSERtoDB.updateRows(selectedExamIndex, listOfQuestionsSER);
				System.out.println(listOfQuestionsSER + " listOfQuestionsSER koe89485");
			} catch (ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
