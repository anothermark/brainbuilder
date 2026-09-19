// Class OuterMasterCorrectAnswers has methods that set and get 
// a triple-nested outerMasterProfsCorrectAnswers that holds each 
// exam's masterListOfProfsCorrectAnswers. NO. Refactored 7-3 - This is the object retrieved
// and set whenever one creates, updates or deletes a question. And,
// over in Tester this is what is retrieved when exams are evaluated
// whether immediately or once at the end of the exam. 

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
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OuterMasterCorrectAnswers {
	Integer rowcount;
	static Integer count;	
	ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers;
	ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs;

	public void insertPlaceholders() throws ClassNotFoundException, SQLException, IOException {
		outerMasterCorrectAnswersProfs = getOuterMasterCorrectAns();
		System.out.println("jdi473d95fm");
		if (outerMasterCorrectAnswersProfs == null) {
			outerMasterCorrectAnswersProfs = new ArrayList<ArrayList<ArrayList<Boolean>>>();			
			for (int i = 0; i < 10; i++) {
				System.out.println("2-A");
				outerMasterCorrectAnswersProfs.add(new ArrayList<ArrayList<Boolean>>());
				System.out.println("2-B");
				System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs fkiu6730fr");
			}			

			for (int k = 0; k < 10; k++) {
				System.out.println("2-C");
				// you are only adding one per innermaster when it should be 10
				for (int j = 0; j < 10; j++) {
					outerMasterCorrectAnswersProfs.get(j).add(new ArrayList<Boolean>());
				}
				System.out.println("2-Dxxx");
				System.out.println(outerMasterCorrectAnswersProfs.get(k));
			}

		}
		rowcount = getRowCountOuterAnswers();
		System.out.println(rowcount + " rowcount from OUTER_MASTER_ANSWERS_TABLE_1 669t96");
		if (rowcount < 1) {
			insertOuterMasterAnswers(outerMasterCorrectAnswersProfs);
			outerMasterCorrectAnswersProfs = getOuterMasterCorrectAns();
			System.out.println(outerMasterCorrectAnswersProfs.size() + " outerMasterCorrectAnswersProfs.size() 9tjoo8");
		} else if((rowcount > 0) && (rowcount < 2)) {
			setUpdateOuterMasterAnswers(outerMasterCorrectAnswersProfs);
			outerMasterCorrectAnswersProfs = getOuterMasterCorrectAns();
			System.out.println(outerMasterCorrectAnswersProfs.size() + " outerMasterCorrectAnswersProfs.size() jsdhjsy345365gd");
		} else {
			JOptionPane.showMessageDialog(null, "Houston, in the insertPlaceholders(), there should never be more "
					+ "than one row.");
		}		
	}

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<ArrayList<Boolean>>> getOuterMasterCorrectAns()
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of getOuterMasterCorrectAns() of class OuterMasterCorrectAnswers");

		String sqlRS = " SELECT OUTER_MASTER_ANSWERS FROM OUTER_MASTER_ANSWERS_TABLE_1 WHERE id = 1";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {			
			System.out.println("pdmf87");
			while (rs.next()) {
				System.out.println("jjgvkjg");				
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("OUTER_MASTER_ANSWERS");

				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						outerMasterCorrectAnswersProfs = (ArrayList<ArrayList<ArrayList<Boolean>>>) ois.readObject();
						System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs hd53r");
					} catch (EOFException ef) {
						System.out.println("EOFException in getOuterMasterCorrectAns() method");
					}
				}
			}
		}
		System.out.println("Bottom of getOuterMasterCorrectAns() of class OuterMasterCorrectAnswers");
		return outerMasterCorrectAnswersProfs;
	}

	public void insertOuterMasterAnswers(ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs)
			throws IOException, SQLException {

		try (ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
				ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD)) {
			oosMCD.writeObject(outerMasterCorrectAnswersProfs);
			byte[] serializedObjectBytesMCD = baosMCD.toByteArray();			

			String insertDisplaySQL = "INSERT INTO OUTER_MASTER_ANSWERS_TABLE_1 (id, OUTER_MASTER_ANSWERS) VALUES(?, ?)";

			try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					PreparedStatement stmt2 = conn2.prepareStatement(insertDisplaySQL)) {

				stmt2.setInt(1, 1); // Always set it in the first row only				
				stmt2.setObject(2, serializedObjectBytesMCD);				
				int rowsEffected49 = stmt2.executeUpdate();
				System.out.println(rowsEffected49 + " rowsEffected49 masterOfCreatedExamsDisplayed dhr528hjey");
			}
		}
	}

	public void setUpdateOuterMasterAnswers(ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs)
			throws SQLException, IOException, ClassNotFoundException {
		
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(outerMasterCorrectAnswersProfs);
			byte[] serializedObjectBytes = baos.toByteArray();			

			String updateSQL = "UPDATE OUTER_MASTER_ANSWERS_TABLE_1 SET id = ?, OUTER_MASTER_ANSWERS = ? WHERE id = ?";
			
			try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

				stmt2.setInt(1, 1);
				stmt2.setObject(2, serializedObjectBytes);
				stmt2.setInt(3, 1);
				
				int rowsEffected245as = stmt2.executeUpdate();
				System.out.println(rowsEffected245as + " Number of rowsEffected245as");
				System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs nghg6749");
				System.out.println(outerMasterCorrectAnswersProfs.get(0) + " outerMasterCorrectAnswersProfs.get(0)");				
			}
		}
	}

	public int getRowCountOuterAnswers() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getRowCountOuterAnswers() in OuterMasterCorrectAnswers class");		

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM OUTER_MASTER_ANSWERS_TABLE_1")) {

			while (rs.next()) {
				count = rs.getInt(1);
				System.out.println("This OUTER_MASTER_ANSWERS_TABLE_1 table contains " + count + " rows ydudnwha3475");
			}
			System.out.println("Bottom of getRowCountOuterAnswers() in OuterMasterCorrectAnswers class");
			return count;
		}
	}

	public void deleteRows(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {

		String sqlDeleteRowsR2 = " DELETE FROM OUTER_MASTER_ANSWERS_TABLE_1 "; // make it like the others and delete all
																				// rows

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR2)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println(affectedRows + " Number of affectedRows deleted");
		}
		System.out.println("Bottom of deleteRows() from OUTER_MASTER_ANSWERS_TABLE_1 in dbUtility class");
	}

	// When the exam is deleted, the list of booleans must be set to null
	public void examWasDeleted(Integer selectedExamIndex) throws ClassNotFoundException, SQLException, IOException {
		outerMasterCorrectAnswersProfs = getOuterMasterCorrectAns();
		System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs koem9c8rc0mce");
		masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs.get(selectedExamIndex);
		System.out.println(masterListOfProfsCorrectAnswers.size() + " masterListOfProfsCorrectAnswers kieuce75");
		ArrayList<Boolean> theAnswers;
		// Say what? This also is the incorrect size because what if there are three exams?
		for (int i = 0; i < masterListOfProfsCorrectAnswers.size(); i++) {
			System.out.println("Can you hear me now? mkc ikejr845");
			theAnswers = masterListOfProfsCorrectAnswers.get(i);
			System.out.println(theAnswers + " theAnswers dju369f");
			theAnswers = null;
			masterListOfProfsCorrectAnswers.set(i, theAnswers);
		}
		System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers o3i98495vim");
		outerMasterCorrectAnswersProfs.set(selectedExamIndex, masterListOfProfsCorrectAnswers);
		System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs ixn8wq0");
		// Now stick it back in the table.
		setUpdateOuterMasterAnswers(outerMasterCorrectAnswersProfs);
		System.out.println(outerMasterCorrectAnswersProfs = getOuterMasterCorrectAns());
	}
}
