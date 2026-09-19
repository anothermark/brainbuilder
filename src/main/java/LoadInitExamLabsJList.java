
// Class LoadInitExamLabsJList modifies the exam labels JList.
// This class has five (5) methods, 1) loads the initial loadJListExamLabs, 
// 2) setExamLabelsJlist1(), and 3) getExamLabelsJlist1(), 4) rowCountExamsLab() 
// and 5) deleteExamsLablsRows()

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

public class LoadInitExamLabsJList {
	ExamLabelsJlist1 examLabelsJlist1;
	ExamLabelsJlist1 deserializedObject;
	Object stuckObject;

	public void loadJListExamLabs() throws SQLException, IOException {
		ExamLabelsJlist1 examLabelsJlist1 = new ExamLabelsJlist1();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(examLabelsJlist1);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String insertSQL = "INSERT INTO EXAMS_JLIST_LABELS (id, EXAMLABELSJLIST1 ) VALUES(?, ?)";

		try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt2 = conn2.prepareStatement(insertSQL)) {

			stmt2.setInt(1, 1);
			stmt2.setObject(2, serializedObjectBytes);
			int rowsEffected2 = stmt2.executeUpdate();
			System.out.println(rowsEffected2 + " Number of rowsEffected2 ");
		}
	}

	public ExamLabelsJlist1 setExamLabelsJlist1(ExamLabelsJlist1 examLabelsJlist1) throws IOException, SQLException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(examLabelsJlist1);
		byte[] serializedObjectBytes = baos.toByteArray();
		oos.close();

		String updateSQL = "UPDATE EXAMS_JLIST_LABELS  SET id = ?, EXAMLABELSJLIST1 =?  WHERE id=?";

		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = conn.prepareStatement(updateSQL)){

		stmt.setInt(1, 1);
		stmt.setObject(2, serializedObjectBytes);
		stmt.setInt(3, 1);
		int rowsEffected2 = stmt.executeUpdate();
		System.out.println(rowsEffected2 + " rowsEffected2 updating EXAMLABELSJLIST1");		
		return examLabelsJlist1;
		}
	}

	public ExamLabelsJlist1 getExamLabelsJlist1() throws IOException, SQLException, ClassNotFoundException {

		String sqlRS = " SELECT id, EXAMLABELSJLIST1 FROM EXAMS_JLIST_LABELS ";

		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = conn.prepareStatement(sqlRS);
		ResultSet rs = stmt.executeQuery()){
		
		byte[] listOfEXLABBytes = null;

		while (rs.next()) {
			listOfEXLABBytes = rs.getBytes("EXAMLABELSJLIST1");

			if (listOfEXLABBytes != null) {
				try (ByteArrayInputStream bais = new ByteArrayInputStream(listOfEXLABBytes);
						ObjectInputStream ois = new ObjectInputStream(bais)) {
					examLabelsJlist1 = (ExamLabelsJlist1) ois.readObject();
				} catch (EOFException ef) {
					System.out.println("EOFException in getExamLabelsJlist1() method");
				}
			}
		}		
		return examLabelsJlist1;
		}
	}

	public Integer rowCountExamsLab() throws SQLException {

		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(" SELECT COUNT(*) AS rowcount FROM EXAMS_JLIST_LABELS")){
		
		rs.next();
		System.out.println(rs.getInt(1) + " What rs.getInt(1) returns, ie the rowCountExamsLab()");
		Integer rowCountExamLabsInt = rs.getInt(1);
		System.out.println("This EXAMS_JLIST_LABELS table contains " + rowCountExamLabsInt + " rows");
		return rowCountExamLabsInt;
		}		
	}

	// Method to delete that one row ?? Yes
	public void deleteExamsLablsRows() throws SQLException {
		String sqlDeleteRow1 = " DELETE FROM EXAMS_JLIST_LABELS WHERE id = 1";

		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = conn.prepareStatement(sqlDeleteRow1)){		
		int affectedRows = stmt.executeUpdate();
		System.out.println(affectedRows + " Number of affectedRows deleted");
		}
	}
}
