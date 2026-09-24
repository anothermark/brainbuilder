
// Class LoadInitialMasterList - Important
// INSERTS initial questions and UPDATES additional questions in BUILDER_EXAMS_LISTS_17 table.

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

public class LoadInitialMasterList {

	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	Integer numberOfRows;
	Integer initialNumberOfQuestions = 0;
	Integer selectedExamIndex;
	ArrayList<QuestionSuper> deserializedObject;
	Integer numberOfAdditionalQuestions = null;

	public ArrayList<QuestionSuper> loadMasterList(Integer selectedExamIndex, Integer initialNumberOfQuestions,
			String selectedValue, ArrayList<QuestionSuper> listOfQuestionsSER, Integer numberOfAdditionalQuestions)
			throws SQLException, IOException, ClassNotFoundException {

		try {
			listOfQuestionsSER = deSerializeListOfQuestionsSER(selectedExamIndex);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		if (initialNumberOfQuestions > 0) {
			if (listOfQuestionsSER == null) {
				listOfQuestionsSER = new ArrayList<QuestionSuper>();

				for (int i = 0; i < initialNumberOfQuestions; i++) {
					listOfQuestionsSER.add(null);
				}

				var insertProfsFinalExamsTesterTable = new InsertProfsFinalExamsTesterTable();
				insertProfsFinalExamsTesterTable.setOriginalProfExams(selectedExamIndex, initialNumberOfQuestions,
						selectedValue, listOfQuestionsSER);
			} else if (listOfQuestionsSER != null) {
				if (listOfQuestionsSER.size() < initialNumberOfQuestions) {
					for (int i = listOfQuestionsSER.size(); i < initialNumberOfQuestions; i++) {
						listOfQuestionsSER.add(null);
					}
				}
				System.out.println(listOfQuestionsSER + " listOfQuestionsSER dhaco68kg");
				var insertProfsFinalExamsTesterTable = new InsertProfsFinalExamsTesterTable();
				insertProfsFinalExamsTesterTable.setOriginalProfExams(selectedExamIndex, initialNumberOfQuestions,
						selectedValue, listOfQuestionsSER);
			}
			serializeListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER, initialNumberOfQuestions);
			listOfQuestionsSER = deSerializeListOfQuestionsSER(selectedExamIndex);
			return listOfQuestionsSER;
		} else if (numberOfAdditionalQuestions > 0) {
// ######################################################################################################
			// 8-6 maybe pull up the serlist from the display instead!!!!!!!!!!!!!!!!!!!!!!!!!11
			
			// This way I'm not overriding created questions in the serlist
			DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
			outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
			masterListOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex);
			listOfQuestionsSER = masterListOfQuestionsSER.get(0);
						
			//listOfQuestionsSER = deSerializeListOfQuestionsSER(selectedExamIndex);
			if (listOfQuestionsSER != null) {
				for (int j = 0; j < numberOfAdditionalQuestions; j++) {
					// Because I'm adding it should not override 
					// any created questions already in there, I hope.
					listOfQuestionsSER.add(null);
				}				
			}	
			
			masterListOfQuestionsSER.set(0, listOfQuestionsSER); // Always at index 0
			outerMasterListOfMastersSER.set(selectedExamIndex, masterListOfQuestionsSER);
			displayExamsAndQuestions.setUpdateOuterDisplay(outerMasterListOfMastersSER);
			// Now set the new serlist with additional null questions back in display table.
			serialAdditionalListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER, numberOfAdditionalQuestions);
			
			//listOfQuestionsSER = deSerializeListOfQuestionsSER(selectedExamIndex);				
			
		}
		return listOfQuestionsSER;
	}

	public void serializeListOfQuestionsSER(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER,
			Integer initialNumberOfQuestions) throws IOException, SQLException, ClassNotFoundException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String insertSQL = "INSERT INTO BUILDER_EXAMS_LISTS_17 (id, EXAM_NUMBER, "
				+ "LISTOFQUESTIONS, LISTOFEXAMLISTS ) VALUES(?, ?, ?, ?)";

		try (Connection conn2 = DatabaseConfig.getConnection();
				PreparedStatement stmt2 = conn2.prepareStatement(insertSQL)) {
			if (initialNumberOfQuestions > 0 && initialNumberOfQuestions <= 10) {
				stmt2.setInt(1, selectedExamIndex + 1);
				stmt2.setInt(2, selectedExamIndex);
				stmt2.setObject(3, serializedObjectBytes);
				stmt2.setObject(4, null);
				int rowsEffected2 = stmt2.executeUpdate();
				System.out.println(rowsEffected2 + " Number of rowsEffected2 xvsfersdq re table 17");
			} else {
				System.out.println(
						"The initial number of questions must be greater than 0" + " and less than 11 (or 1-10");
			}
		}
	}

	public void serialAdditionalListOfQuestionsSER(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER, Integer numberOfAdditionalQuestions)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of serialAdditionalListOfQuestionsSER() method in the LoadInitialMasterList class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER fsfsfd");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(listOfQuestionsSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE BUILDER_EXAMS_LISTS_17 SET id = ?, EXAM_NUMBER = ?, "
				+ "LISTOFQUESTIONS = ?, LISTOFEXAMLISTS = ? WHERE id = ?";

		try(Connection conn2 = DatabaseConfig.getConnection();
		PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)){

		stmt2.setInt(1, selectedExamIndex + 1);
		stmt2.setInt(2, selectedExamIndex + 1);
		stmt2.setObject(3, serializedObjectBytes);
		stmt2.setObject(4, null);
		stmt2.setInt(5, selectedExamIndex + 1);
		int rowsEffected2 = stmt2.executeUpdate();
		System.out.println(rowsEffected2 + " Number of rowsEffected2");
		}	
		
		var insertProfsFinalExamsTesterTable = new InsertProfsFinalExamsTesterTable();
		try {
			insertProfsFinalExamsTesterTable.serialAdditionalListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER,
					numberOfAdditionalQuestions);
		} catch (ClassNotFoundException | IOException | SQLException e) {			
			e.printStackTrace();
		}
		System.out.println("Bottom of serialAdditionalListOfQuestionsSER() method in the LoadInitialMasterList class");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<QuestionSuper> deSerializeListOfQuestionsSER(Integer selectedExamIndex)
			throws IOException, SQLException, ClassNotFoundException {

		String sqlRS = " SELECT id, EXAM_NUMBER, LISTOFQUESTIONS, LISTOFEXAMLISTS FROM BUILDER_EXAMS_LISTS_17 ";

		try(Connection conn = DatabaseConfig.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sqlRS);
		ResultSet rs = stmt.executeQuery()){
		
		byte[] listOfQBytes = null;

		PrintedRowSoBreak: while (rs.next()) {
			Integer id = rs.getInt("id");
			if (id == selectedExamIndex + 1) {
				listOfQBytes = rs.getBytes("LISTOFQUESTIONS");
				if (listOfQBytes != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfQBytes);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						deserializedObject = (ArrayList<QuestionSuper>) ois.readObject();
					} catch (EOFException ef) {
						System.out.println("EOFException in deSerializeListOfQuestionsSER() method");
					}
					listOfQuestionsSER = deserializedObject;
					break PrintedRowSoBreak;
				}
			}
		}
		return listOfQuestionsSER;
		}
	}
}
