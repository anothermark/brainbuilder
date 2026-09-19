
// Class InsertInitialOuterNestedPlaceholders is important and the final resting place of a student's graded exams;
// ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER
// serialized and saved. It consists of three ArrayLists which are nested within each other. An outer, an inner, and
// the listOfQuestionsSERs as the inner-most list. This loads the initial placeholders.

// loadInitialOuterNested() - If necessary adds innerMasterListOfLists to outerMasterListOfMastersSER object, 
// and INSERTS outerMasterListOfMastersSER into STUDENTS_OUTERNESTED_TABLE4. 
// getOuterMasterListOfMastersSER()	- returns outerMasterListofMastersSER object from STUDENTS_OUTERNESTED_TABLE4.
// serialAdditionalListOfQuestionsSER()	- adds additional questions and UPDATES STUDENTS_OUTERNESTED_TABLE4, the first row, always.
// setUpdateOuterMasterListofMastersSER() - also serializes and UPDATES STUDENTS_OUTERNESTED_TABLE4 with the triple-nested
// outer object, although it appears to duplicate serialAdditionalListOfQuestionsSER(); am not sure where it is called 
// from. Must track that down. 

package main.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

public class InsertInitialOuterNestedPlaceholders {
	Integer numberOfRows;
	String selectedValue;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfLists;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<Boolean>> gradeOnceListOfListsSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListofMastersSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> deserializedOuterMasterObject;

	public void insertInitialOuter(ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListofMastersSER)
			throws IOException, SQLException, ClassNotFoundException {
		this.outerMasterListOfMastersSER = outerMasterListofMastersSER;
		System.out.println("kpoe5i94d58");
		System.out.println(outerMasterListofMastersSER + " cpori4bvt9v");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(outerMasterListOfMastersSER);
		byte[] serializedOuterNestedBytes = baos.toByteArray();
		baos.close();

		String insertSQL = "INSERT INTO STUDENTS_OUTERNESTED_TABLE4 (id, STUDENTLASTNAME, STUDENTFIRSTNAME, "
				+ "OUTERNESTEDMASTERS, LISTOFGRADEDEXAMSLISTS ) VALUES(?, ?, ?, ?, ?)";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(insertSQL)) {
			System.out.println(outerMasterListofMastersSER + " md dklfcoeiteo");
			stmt2.setInt(1, 1);
			stmt2.setString(2, "Outernested student last name");
			stmt2.setString(3, "Outernested student first name");
			stmt2.setObject(4, serializedOuterNestedBytes);
			stmt2.setObject(5, gradeOnceListOfListsSER); // NO, I decided to stuff the ...
			int rowsEffected4 = stmt2.executeUpdate();

			System.out.println(rowsEffected4 + " number of rowsEffected4 in final outer nested placeholders oeir9458");
		}
		outerMasterListOfMastersSER = getOuterMasterListOfMastersSER();
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER jirtoir8695");
	}

	// After major refactoring 8-6 I might not be using this method any more.
	// Create a new method that strictly inserts the 5th outer with the 10
	// masters???
	public void loadInitialOuterNested(Integer selectedExamIndex, Integer initialNumberOfQuestions,
			String selectedValue, ArrayList<QuestionSuper> listOfQuestionsSER,
			ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of loadInitialOuterNested() method of InsertInitialOuterNestedPlaceholders class");
		this.selectedValue = selectedValue;
		innerMasterListOfLists = masterListOfQuestionsSER;
		System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER ssssss47f654ttttt ");
		System.out.println(innerMasterListOfLists.size() + " innerMasterListOfLists.size() zzz465zpzpzp");
		System.out.println(innerMasterListOfLists + " innerMasterListOfLists " + "prints what? NBN77BNB");

		outerMasterListOfMastersSER = getOuterMasterListOfMastersSER();
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER koct7i8457");

		var builderDBTesterUtility = new BuilderDBTesterUtility();
		try {

			numberOfRows = builderDBTesterUtility.getRowCountOuterNested();
			System.out.println(numberOfRows + " numberOfRows ldoeit9485");
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}

		if (outerMasterListOfMastersSER == null && numberOfRows < 1) {
			// Add this ten times, one per exam.
			for (int i = 0; i < 10; i++) {
				outerMasterListOfMastersSER.add(new ArrayList<ArrayList<QuestionSuper>>());
			}

			
			System.out.println("7-3-959gtjdiu");
			
			System.out.println("ko74dn87");

			try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(outerMasterListOfMastersSER);
				byte[] serializedOuterNestedBytes = baos.toByteArray();

				String insertSQL = "INSERT INTO STUDENTS_OUTERNESTED_TABLE4 (id, STUDENTLASTNAME, STUDENTFIRSTNAME, "
						+ "OUTERNESTEDMASTERS, LISTOFGRADEDEXAMSLISTS ) VALUES(?, ?, ?, ?, ?)";

				try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
						PreparedStatement stmt2 = conn2.prepareStatement(insertSQL)) {

					stmt2.setInt(1, 1);
					stmt2.setString(2, "Outernested student last name");
					stmt2.setString(3, "Outernested student first name");
					stmt2.setObject(4, serializedOuterNestedBytes);
					stmt2.setObject(5, gradeOnceListOfListsSER); // NO, I decided to stuff the ...
					int rowsEffected3 = stmt2.executeUpdate();
					System.out.println(
							rowsEffected3 + " number of rowsEffected3 in final outer nested placeholders xaxavvvxax");
					JOptionPane.showMessageDialog(null, selectedValue + " Structure was created successfully. \n"
							+ "Next, click 'Define Questions for This Exam' and follow the instructions to populate your exam.");
					outerMasterListOfMastersSER = getOuterMasterListOfMastersSER();
					System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER 9586958mt49f4");
					try {
						numberOfRows = builderDBTesterUtility.getRowCountOuterNested();
						System.out.println(numberOfRows + " numberOfRows jdiuei75445");
					} catch (ClassNotFoundException | IOException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}			
		}
		

		System.out.println("Bottom of loadInitialOuterNested() method of InsertInitialOuterNestedPlaceholders class");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<ArrayList<QuestionSuper>>> getOuterMasterListOfMastersSER()
			throws ClassNotFoundException, IOException, SQLException {

		String sqlRS = " SELECT id, STUDENTLASTNAME,  STUDENTFIRSTNAME, OUTERNESTEDMASTERS, LISTOFGRADEDEXAMSLISTS FROM STUDENTS_OUTERNESTED_TABLE4 ";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rSet = stmt.executeQuery()) {
			byte[] listOfOuterMasterBytes = null;

			if (rSet.next()) {
				System.out.println("did I get in htere pr594584");
				listOfOuterMasterBytes = rSet.getBytes("OUTERNESTEDMASTERS");
				if (listOfOuterMasterBytes != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfOuterMasterBytes);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						deserializedOuterMasterObject = (ArrayList<ArrayList<ArrayList<QuestionSuper>>>) ois
								.readObject();
						System.out.println(deserializedOuterMasterObject + " deserializedOuterMasterObject");
						outerMasterListofMastersSER = deserializedOuterMasterObject;
						System.out.println(outerMasterListofMastersSER + "outerMasterListofMastersSER98 poecpoer8");
						
					}
				}
			}
		}
		return outerMasterListofMastersSER;
	}

	public void serialAdditionalListOfQuestionsSER(Integer selectedExamIndex,
			ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER)
			throws IOException, SQLException, ClassNotFoundException {
		System.out.println(
				"Top of serialAdditionalListOfQuestionsSER() method in the InsertInitialOuterNestedPlaceholders class");
		System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER before finally serialized wtwtwt");
		outerMasterListOfMastersSER = getOuterMasterListOfMastersSER();
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER is fresh from the db");
		if (outerMasterListOfMastersSER == null) {
			outerMasterListOfMastersSER = new ArrayList<ArrayList<ArrayList<QuestionSuper>>>();
			outerMasterListOfMastersSER.add(masterListOfQuestionsSER);
			System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER 08t94vn4"); //
		} else {
			outerMasterListOfMastersSER.set(selectedExamIndex, masterListOfQuestionsSER);
			System.out.println(outerMasterListOfMastersSER + " 2)R) outerMasterListOfMastersSER fr875");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(outerMasterListOfMastersSER);
			byte[] serializedObjectBytes = baos.toByteArray();
			oos.close();

			String updateSQL = "UPDATE STUDENTS_OUTERNESTED_TABLE4 SET id = ?, STUDENTLASTNAME = ?, STUDENTFIRSTNAME = ?, "
					+ "OUTERNESTEDMASTERS = ?, LISTOFGRADEDEXAMSLISTS = ? WHERE id = ?";

			try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

				stmt2.setInt(1, 1);
				stmt2.setObject(2, "Last Name");
				stmt2.setObject(3, "First Name");
				stmt2.setObject(4, serializedObjectBytes);
				stmt2.setObject(5, null);
				stmt2.setInt(6, 1);
				int rowsEffected2 = stmt2.executeUpdate();
				System.out.println(rowsEffected2 + " Number of rowsEffected2 ghghj");
			}
			var builderDBTesterUtility = new BuilderDBTesterUtility();
			int count = builderDBTesterUtility.getRowCountOuterNested();
			System.out.println("This STUDENTS_OUTERNESTED_TABLE4 table contains " + count + " rows zrz34rzrzr ");
			outerMasterListofMastersSER = getOuterMasterListOfMastersSER();
			System.out.println(outerMasterListofMastersSER + " outerMasterListofMastersSER after adding questions");
			System.out.println(
					"Bottom of serialAdditionalListOfQuestionsSER() method in the InsertInitialOuterNestedPlaceholders class");
		}
	}

	public void setUpdateOuterMasterListofMastersSER(Integer selectedExamIndex,
			ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER,
			ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER,
			Integer numberOfAdditionalQuestions) throws IOException, SQLException, ClassNotFoundException {

		System.out.println(
				"Top of setUpdateOuterMasterListofMastersSER() method in the InsertInitialOuterNestedPlaceholders class XDSE34");
		System.out.println(outerMasterListOfMastersSER + " po,exurx45u4");
		

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(outerMasterListOfMastersSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE STUDENTS_OUTERNESTED_TABLE4 SET id = ?, STUDENTLASTNAME = ?, STUDENTFIRSTNAME = ?, "
				+ "OUTERNESTEDMASTERS = ?, LISTOFGRADEDEXAMSLISTS = ? WHERE id = ?";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {
			stmt2.setInt(1, 1);
			stmt2.setObject(2, "Last Name");
			stmt2.setObject(3, "First Name");
			stmt2.setObject(4, serializedObjectBytes);
			stmt2.setObject(5, null);
			stmt2.setInt(6, 1);
			int rowsEffected2 = stmt2.executeUpdate();
			System.out.println(rowsEffected2 + " Number of rowsEffected2 azazszsdc");
		}

		if ((numberOfAdditionalQuestions != null) && (numberOfAdditionalQuestions > 0)) {
			JOptionPane.showMessageDialog(null, " Additional question[s] were created successfully. \n"
					+ "Next, click 'Define Questions for This Exam' and follow the instructions to populate your exam.");
		}

		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER tdgersferdf");

		System.out.println(
				"Bottom of setUpdateOuterMasterListofMastersSER() method in the InsertInitialOuterNestedPlaceholders class");
	}
}
