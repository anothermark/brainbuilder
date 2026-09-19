
// Class GetListFromDB retrieves data from rows, especially listOfQuestionsSER, then prints data from objects
// in Builder

package main.java;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetListFromDB {
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;

	@SuppressWarnings("unchecked")
	public ArrayList<QuestionSuper> getRowData(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of GetListFromDB getRowData() method");
		
		String sqlRS = " SELECT id, EXAM_NUMBER, LISTOFQUESTIONS, LISTOFEXAMLISTS FROM BUILDER_EXAMS_LISTS_17 ";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			byte[] listOfQBytes = null;

			OutOfFirstIf: while (rs.next()) {
				int id = rs.getInt("id");
				if (id == selectedExamIndex + 1) { 
					listOfQBytes = rs.getBytes("LISTOFQUESTIONS");
					if (listOfQBytes != null) {
						try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfQBytes);
								ObjectInputStream ois = new ObjectInputStream(bais)) {
							deserializedObject = (ArrayList<QuestionSuper>) ois.readObject();
						} catch (EOFException ef) {
							System.out.println("EOFException in getRowData() method");
						}
						this.listOfQuestionsSER = deserializedObject;
					}
					break OutOfFirstIf;
				}
			}
		}
		System.out.println("Bottom of GetListFromDB getRowData() method");
		return listOfQuestionsSER;
	}
}
