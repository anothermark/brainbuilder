
// Class GetProfListFromDB deserializes and returns listOfQuestionsSER 
// from the second TESTER_EXAMS_LIST_4 table (profsfinal table)

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

public class GetProfListFromDB {
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<QuestionSuper> deserializedObject;

	@SuppressWarnings("unchecked")
	public ArrayList<QuestionSuper> getRowData(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of GetProfListFromDB class' getRowData() method");

		String sqlRS = " SELECT id, EXAM_NUMBER,  STUDENTLASTNAME, LISTOFQUESTIONS, LISTOFGRADEDEXAMSLISTS FROM TESTER_EXAMS_LIST_4 ";

		try (Connection conn = DatabaseConfig.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			byte[] listOfQBytes = null;

			 while (rs.next()) {
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
						listOfQuestionsSER = deserializedObject;						
					}
				}
			}
		}
		System.out.println(listOfQuestionsSER);
		System.out.println("Bottom of GetProfListFromDB class' getRowData() method");
		return listOfQuestionsSER;
	}
}
