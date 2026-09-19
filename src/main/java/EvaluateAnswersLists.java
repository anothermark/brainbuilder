
// Class EvaluateAnswersLists inserts, updates, deletes and counts
// rows in the CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1

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

public class EvaluateAnswersLists {
	static Integer count;
	ArrayList<ArrayList<Boolean>> innerMasterEvaluated;

	@SuppressWarnings("unchecked")
	public ArrayList<ArrayList<Boolean>> getEvaluatedList() throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of getEvaluatedList() of class EvaluateAnswersLists");

		String sqlRS = " SELECT INNER_MASTER_EVALUATED FROM CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 WHERE id = 1";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlRS);
				ResultSet rs = stmt.executeQuery()) {
			System.out.println(" 8de8rkg");
			while (rs.next()) {
				System.out.println("is the RS the problem? two rows in there? jjgvkjg");
				byte[] serializedObjectBytesMCDisplay = rs.getBytes("INNER_MASTER_EVALUATED");

				if (serializedObjectBytesMCDisplay != null) {
					try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedObjectBytesMCDisplay);
							ObjectInputStream ois = new ObjectInputStream(bais)) {
						innerMasterEvaluated = (ArrayList<ArrayList<Boolean>>) ois.readObject();
						System.out.println(innerMasterEvaluated + " innerMasterEvaluated 98cf");
					} catch (EOFException ef) {
						System.out.println("EOFException in getEvaluatedList() method");
					}
				}
			}
		}
		System.out.println("Bottom of getEvaluatedList() of class EvaluateAnswersLists");
		return innerMasterEvaluated;
	}

	public void insertOuterMasterAnswers(ArrayList<ArrayList<Boolean>> innerMasterEvaluated)
			throws IOException, SQLException {
		System.out.println("Top of insertOuterMasterAnswers() of class EvaluateAnswersLists");

		try (ByteArrayOutputStream baosMCD = new ByteArrayOutputStream();
				ObjectOutputStream oosMCD = new ObjectOutputStream(baosMCD)) {
			oosMCD.writeObject(innerMasterEvaluated);
			byte[] serializedObjectBytesMCD = baosMCD.toByteArray();

			String insertDisplaySQL = "INSERT INTO CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 (id, "
					+ "INNER_MASTER_EVALUATED) VALUES(?, ?)";

			try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					PreparedStatement stmt2 = conn2.prepareStatement(insertDisplaySQL)) {

				stmt2.setInt(1, 1); // Always set it in the first row only
				stmt2.setObject(2, serializedObjectBytesMCD);
				int rowsEffected49awr = stmt2.executeUpdate();
				System.out.println(rowsEffected49awr + " rowsEffected49awr re innerMasterEvaluated 2wa");
				System.out.println("Bottom of insertOuterMasterAnswers() of class EvaluateAnswersLists");
			}
		}
	}

	public void setUpdateOuterMasterAnswers(ArrayList<ArrayList<Boolean>> innerMasterEvaluated)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Top of setUpdateOuterMasterAnswers() of class EvaluateAnswersLists");

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(innerMasterEvaluated);
			byte[] serializedObjectBytes = baos.toByteArray();

			String updateSQL = "UPDATE CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 SET id = ?, INNER_MASTER_EVALUATED = ? WHERE id = ?";

			try (Connection conn2 = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
					PreparedStatement stmt2 = conn2.prepareStatement(updateSQL)) {

				stmt2.setInt(1, 1);
				stmt2.setObject(2, serializedObjectBytes);
				stmt2.setInt(3, 1);

				int rowsEffected2459sm = stmt2.executeUpdate();
				System.out.println(rowsEffected2459sm + " Number of rowsEffected2459sm");
				System.out.println(innerMasterEvaluated + " innerMasterEvaluated nghgksiu6749");
			}
		}
		System.out.println("Bottom of setUpdateOuterMasterAnswers() of class EvaluateAnswersLists");
	}

	public int getRowCountEvaluated() throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of getRowCountEvaluated() of class EvaluateAnswersLists");

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery(" SELECT COUNT(*) AS rowcount FROM CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1")) {
			while (rs.next()) {
				count = rs.getInt(1);
				System.out.println(
						"This CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 table contains " + count + " rows 7sjdu35");
			}
			System.out.println("Bottom of getRowCountEvaluated() of class EvaluateAnswersLists");
			return count;
		}
	}

	public void deleteRowsEval(Integer selectedExamIndex) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("Top of deleteRowsEval() of class EvaluateAnswersLists");
		String sqlDeleteRowsR2 = " DELETE FROM CREATE_EVAL_ONCE_AND_IMMEDIATE_TABLE_1 ";

		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
				PreparedStatement stmt = conn.prepareStatement(sqlDeleteRowsR2)) {
			int affectedRows12345x = stmt.executeUpdate();
			System.out.println(affectedRows12345x + " Number of affectedRows12345x deleted");
			System.out.println(getRowCountEvaluated() + " Rows after deletion");
		}
		System.out.println("Bottom of deleteRowsEval() of class EvaluateAnswersLists");
	}
}
