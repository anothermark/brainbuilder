
// Important class
// Class DisplayExamsAndQuestions retrieves masterOfCreatedExamsDisplayed list from DISPLAY_EXAMS_QUESTIONS_TABLE_1
// and previews the number of exams created, and number of created questions per exam in the main preview pane, the business
// logic of the actual display being in the Builder under the 'Number of Created Exams/Questions' button, which relies 
// a lot on masterOfCreatedExamsDisplayed.size()

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

public class DisplayExamsAndQuestions {
	Integer selectedExamIndex;
	Integer numberOfAdditionalQuestions;
	ArrayList<String> examsAndNullQuestions;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfMastersSER;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;
	ArrayList<ArrayList<QuestionSuper>> deserializedMasterDisplayObject;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;

	public ArrayList<ArrayList<ArrayList<QuestionSuper>>> getTheBigGradedOuter(Integer selectedExamIndex,
			ArrayList<QuestionSuper> listOfQuestionsSER) throws ClassNotFoundException, SQLException, IOException {

		System.out.println("Top of getTheBigGradedOuter() in DisplayExamsAndQuestions class");
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER [PEXIR0494");
		try {
			outerMasterListOfMastersSER = getOuterExamsDisplayed();
			System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER q4q4q4q");
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		masterOfCreatedExamsDisplayed = outerMasterListOfMastersSER.get(selectedExamIndex);

		System.out.println(listOfQuestionsSER + " listOfQuestionsSER for7396itore");

		masterOfCreatedExamsDisplayed.set(0, listOfQuestionsSER);// try index of 3

		outerMasterListOfMastersSER.set(selectedExamIndex, masterOfCreatedExamsDisplayed);
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER 9r8fj");

		if (outerMasterListOfMastersSER == null) {
			insertOuterDisplayTable(outerMasterListOfMastersSER);
			System.out.println("por90");
		} else {
			setUpdateOuterDisplay(outerMasterListOfMastersSER);
			System.out.println("8738dhry7473hdh");
		}
		System.out.println("Did i get here? djfy36rh");
		outerMasterListOfMastersSER = getOuterExamsDisplayed();
		masterOfCreatedExamsDisplayed = outerMasterListOfMastersSER.get(selectedExamIndex);
		listOfQuestionsSER = masterOfCreatedExamsDisplayed.get(0);
		System.out.println(listOfQuestionsSER + "listOfQuestionsSER orit594586");

		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER mcnh99gag");

		System.out.println("Bottom of getTheBigGradedOuter() in DisplayExamsAndQuestions class");
		return outerMasterListOfMastersSER;
	}

	// But what calls this?? It is called from method setOriginalProfExams() in the
	// class InsertProfsFinalExamsTesterTable - NO, NOT ANYMORE. iT IS CALLED NEAR
	// THE BOTTOM
	// OF THE Create an Exam section after the 10 nulls are set in the display outer
	public void setExam(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws ClassNotFoundException, SQLException, IOException {
		System.out.println("Top of setInitialExams() method in dispayExams class");
		outerMasterListOfMastersSER = getOuterExamsDisplayed();

		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER kexi85d4857");
		if (outerMasterListOfMastersSER.get(selectedExamIndex) == null) {
			masterListOfQuestionsSER = new ArrayList<ArrayList<QuestionSuper>>();
			masterListOfQuestionsSER.add(listOfQuestionsSER);
			System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER ie594584");
			outerMasterListOfMastersSER.set(selectedExamIndex, masterListOfQuestionsSER);
			setUpdateOuterDisplay(outerMasterListOfMastersSER);
			outerMasterListOfMastersSER = getOuterExamsDisplayed();
			System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER por8tc958654");
		} else { // This might be wrong but should simply say if not null that
			// it already exists and can't create another.

			JOptionPane.showMessageDialog(null, "This exam already exists in the Display table ");

		}

	}

	public void addAdditionalNullQuestions() {
		// What is this?

	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<ArrayList<QuestionSuper>>> getOuterExamsDisplayed()
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of getOuterExamsDisplayed() in DisplayExamsAndQuestions class");

		String sqlRS = " SELECT LISTOFEXAMLISTS FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1 WHERE id = 1";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				System.out.println("jjgvkjg");
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("LISTOFEXAMLISTS");

				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						outerMasterListOfMastersSER = (ArrayList<ArrayList<ArrayList<QuestionSuper>>>) ois.readObject();
						System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER oe8594fnue4tu");
					} catch (EOFException ef) {
						System.out.println("EOFException in getOuterExamsDisplayed() method");
					}
				}
			}
		}
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER gyeyeyey4565");
		System.out.println("Bottom of getOuterExamsDisplayed() in DisplayExamsAndQuestions class");
		return outerMasterListOfMastersSER;
	}

	public void insertOuterDisplayTable(ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER)
			throws IOException, SQLException {
		System.out.println("Top of insertOuterDisplayTable() in DisplayExamsAndQuestions class");

		ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
		ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD);
		oosMCD.writeObject(outerMasterListOfMastersSER);
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
		System.out.println("Bottom of insertOuterDisplayTable() in DisplayExamsAndQuestions class");
	}

	public void setUpdateOuterDisplay(ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of setUpdateOuterDisplay() in DisplayExamsAndQuestions class");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(outerMasterListOfMastersSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE DISPLAY_EXAMS_QUESTIONS_TABLE_1 SET id = ?, EXAM_NUMBER = ?, LISTOFQUESTIONS = ?, "
				+ "LISTOFEXAMLISTS = ? WHERE id = ?";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

			stmt2.setInt(1, 1);
			stmt2.setObject(2, 0);
			stmt2.setObject(3, null);
			stmt2.setObject(4, serializedObjectBytes); // this is where the outerMasterListOfMastersSER should be set
			stmt2.setInt(5, 1);
			int rowsEffected245 = stmt2.executeUpdate();
			System.out.println(rowsEffected245 + " Number of rowsEffected245");
			System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER");
		}
		System.out.println("Bottom of setUpdateOuterDisplay() in DisplayExamsAndQuestions class");
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<QuestionSuper>> getInitMasterCreatedExamsDisplayed()
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of getInitMasterCreatedExamsDisplayed() in DisplayExamsAndQuestions class");

		String sqlRS = " SELECT LISTOFEXAMLISTS FROM DISPLAY_EXAMS_QUESTIONS_TABLE_1 WHERE id = 1";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("LISTOFEXAMLISTS");

				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						masterOfCreatedExamsDisplayed = (ArrayList<ArrayList<QuestionSuper>>) ois.readObject();
					} catch (EOFException ef) {
						System.out.println("EOFException in getInitMasterCreatedExamsDisplayed() method");
					}
				}
			}
		}
		System.out.println("Bottom of getInitMasterCreatedExamsDisplayed() in DisplayExamsAndQuestions class");
		return masterOfCreatedExamsDisplayed;
	}

	public void setUpdateAdditionalQuestions(Integer selectedExamIndex, ArrayList<QuestionSuper> listOfQuestionsSER)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of setUpdateAdditionalQuestions() in DisplayExamsAndQuestions class");

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(outerMasterListOfMastersSER);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE DISPLAY_EXAMS_QUESTIONS_TABLE_1 SET id = ?, EXAM_NUMBER = ?, LISTOFQUESTIONS = ?, "
				+ "LISTOFEXAMLISTS = ? WHERE id = ?";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

			stmt2.setInt(1, 1);
			stmt2.setObject(2, 0);
			stmt2.setObject(3, null);
			stmt2.setObject(4, serializedObjectBytes); // this is where the outerMasterListOfMastersSER should be set
			stmt2.setInt(5, 1);
			int rowsEffected245 = stmt2.executeUpdate();
			System.out.println(rowsEffected245 + " Number of rowsEffected245");
		}
		System.out.println("Bottom of setUpdateAdditionalQuestions() in DisplayExamsAndQuestions class");
	}

	public void deleteEmptyQuestion(Integer selectedIndex, Integer selectedExamIndex)
			throws ClassNotFoundException, SQLException, IOException {
		outerMasterListOfMastersSER = getOuterExamsDisplayed();
		System.out.println(outerMasterListOfMastersSER + " pxeiuoetuvrjg");

		masterListOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex);
		masterListOfQuestionsSER = masterListOfQuestionsSER;
		listOfQuestionsSER = masterListOfQuestionsSER.get(0);
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER before remove poeut0948603496gmn");
		listOfQuestionsSER.remove(null);
		System.out.println(listOfQuestionsSER + " listOfQuestionsSER after remove ;dkfogfo9");
		masterListOfQuestionsSER.set(0, listOfQuestionsSER);
		System.out.println(masterListOfQuestionsSER + " podpfurogturio");
		System.out.println(outerMasterListOfMastersSER + " ocu85464");
		outerMasterListOfMastersSER.set(selectedExamIndex, masterListOfQuestionsSER);

		System.out.println(outerMasterListOfMastersSER + " cei9865");
		setUpdateOuterDisplay(outerMasterListOfMastersSER);

		outerMasterListOfMastersSER = getOuterExamsDisplayed();
		System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER ds;citprotcr");

		// Now send the new listOfQuestionsSER to the first 4 tables
		LoadInitialMasterList loadInitialMasterList = new LoadInitialMasterList();
		loadInitialMasterList.serialAdditionalListOfQuestionsSER(selectedExamIndex, listOfQuestionsSER,
				numberOfAdditionalQuestions);

	}

}
