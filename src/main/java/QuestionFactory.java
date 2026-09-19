
// CLASS QuestionFactory creates the anonymous QuestionSuper subclasses, which represent one question.
// Assigns the passed-in array values to those anon objects and assigns the object to a listOfQuestionsSER.
// Also adds the profsRadioButtonSelections (the correct answers) to a masterListOfProfsCorrectAnswers, and
// sends all of that to the InsertlistOfQuestionsSERtoDB class where it is serialized and added to the 
// BUILDER_EXAMS_LISTS_17 table.

package main.java;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// Important class
public class QuestionFactory implements Serializable {

	dbUtility dBaseUtility;
	int numberOfRows;
	int rowcount;
	Integer selectedExamIndex;
	Integer selectedIndex;
	String[] questionArray;
	ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER_Disp;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	InsertlistOfQuestionsSERtoDB insertlistOfQuestionsSERtoDB;
	ArrayList<QuestionSuper> listOfQuestionsSER;
	QuestionSuper questionSub;// This is a reference to an anonymous subclass of QuestionSuper
	ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs;

	public void setQuestionArray(String[] questionArray, Integer selectedIndex, String selectedValueQ,
			Integer selectedExamIndex, ArrayList<Boolean> profsRadioButtonSelections, String previouslySelectedCommand)
			throws IOException, ClassNotFoundException, SQLException {
		System.out.println(selectedExamIndex + "Top of QuestionFactory in setQuestionArray() method");
		var getListFromDB = new GetListFromDB();
		listOfQuestionsSER = getListFromDB.getRowData(selectedExamIndex);
		this.questionArray = questionArray;
		this.selectedIndex = selectedIndex;
		this.selectedExamIndex = selectedExamIndex;

		breakFromSetObjectsLoop: for (int i = 0; i < 10; i++) {

			if (selectedExamIndex == i) {
				questionSub = new QuestionSuper() {
				};
				questionSub.setSelectedIndex(selectedIndex);
				questionSub.setSelectedValue(selectedValueQ);
				questionSub.setSelectedValue(questionArray[0]);
				questionSub.setTitle(questionArray[1]);
				questionSub.setQuestion(questionArray[2]);
				questionSub.setAnswer1(questionArray[3]);
				questionSub.setCorrectAnswer1(questionArray[4]);
				questionSub.setAnswer2(questionArray[5]);
				questionSub.setCorrectAnswer2(questionArray[6]);
				questionSub.setAnswer3(questionArray[7]);
				questionSub.setCorrectAnswer3(questionArray[8]);
				questionSub.setAnswer4(questionArray[9]);
				questionSub.setCorrectAnswer4(questionArray[10]);
				questionSub.setAnswer5(questionArray[11]);
				questionSub.setCorrectAnswer5(questionArray[12]);
				questionSub.setExplanation(questionArray[13]);
				questionSub.setCreated(questionArray[14]);
				questionSub.setCreator(questionArray[15]);
				questionSub.setPrimaryKey(Integer.parseInt(questionArray[16]));
				questionSub.setExamNumber(Integer.parseInt(questionArray[17]));
				questionSub.setQuestionNumber(selectedIndex + 1);
				questionSub.setIndexOfQuestionInList(Integer.parseInt(questionArray[18]));
				questionSub.setQuestionTopic(questionArray[19]);
				questionSub.setProfsCorrectAnswers(profsRadioButtonSelections);
				questionSub.setIsExamStarted(false);
				questionSub.setSelectedCommand(previouslySelectedCommand);
				
				// What is setSizeLerListr? And where is it used?
				// It throws a nullpointer
				// Shouldn't I pull this listOfQuestionsSER up from the display?
				// Move it down a few lines
				//questionSub.setSizeLerListr(listOfQuestionsSER.size());
				
				dBaseUtility = new dbUtility();
				numberOfRows = dBaseUtility.getRowCount();
				System.out.println(numberOfRows + "numberOfRows oue87594");
				break breakFromSetObjectsLoop;
			}
		}
		var displayExamsAndQuestions = new DisplayExamsAndQuestions();
		try {
			System.out.println("xpoe809458nv4");
			outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
			System.out.println("[pe9t0459");
			masterListOfQuestionsSER = outerMasterListOfMastersSER_Disp.get(selectedExamIndex);
			System.out.println("coirt9");
			
			listOfQuestionsSER = masterListOfQuestionsSER.get(0);
			System.out.println("docicorti");
		} catch (ClassNotFoundException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		
		if (listOfQuestionsSER == null) {
			listOfQuestionsSER = new ArrayList<QuestionSuper>();
			System.out.println("ere53reer");
		} else {
			questionSub.setSizeLerListr(listOfQuestionsSER.size());
		}
			
		listOfQuestionsSER.set(selectedIndex, questionSub);
		questionSub = listOfQuestionsSER.get(selectedIndex);

		System.out.println("6-23 wrsgd");
		
		OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
		outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
		System.out.println(outerMasterCorrectAnswersProfs + "6-23 4A");
		masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs.get(selectedExamIndex);
		System.out.println("oeuc98794e094");

		if (masterListOfProfsCorrectAnswers != null) {
			System.out.println("oe758y8");
			System.out.println(masterListOfProfsCorrectAnswers + " soruc8e57");
			ArrayList<Boolean> trueFalseList = masterListOfProfsCorrectAnswers.get(0);
			System.out.println(trueFalseList + " trueFalseList kjditogvsx");
			masterListOfProfsCorrectAnswers.set(selectedIndex, profsRadioButtonSelections);// changed 0 to selectedIndex
			ArrayList<Boolean> trueFalseList2 = masterListOfProfsCorrectAnswers.get(selectedIndex);
			System.out.println(trueFalseList2 + " trueFalseList iodjfie");
			System.out.println(masterListOfProfsCorrectAnswers + "this should run. 23xx4D");
		} else {
			JOptionPane.showMessageDialog(null,
					"Houston??? masterListOfProfsCorrectAnswers should never be null zxzxzx549");
			System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers 4DDF");
		}
		System.out.println("2A cv654df");
		System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers jsbch462e4");

		if (masterListOfProfsCorrectAnswers != null) {
			System.out.println("3A");
			System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers uffkf89");
			outerMasterCorrectAnswersProfs.set(selectedExamIndex, masterListOfProfsCorrectAnswers);
		}
		System.out.println("5A");
		try {
			rowcount = outerMasterCorrectAnswers.getRowCountOuterAnswers();
			System.out.println(rowcount + " rowcount from OUTER_MASTER_ANSWERS_TABLE_1 sxjdywnbsjdf");
			if (rowcount > 1) {
				JOptionPane.showMessageDialog(null,
						"Ooops! row count  should never be greater than 1 zc324 In Factory");
			}
		} catch (ClassNotFoundException | IOException | SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("UPDATE 4539");
		outerMasterCorrectAnswers.setUpdateOuterMasterAnswers(outerMasterCorrectAnswersProfs);
		System.out.println("6A");
		outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
		System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs sxjdywnbsjdf");
		outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
		System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs xvdfwe34");
		System.out.println(listOfQuestionsSER + "listOfQuestionsSER hfhfhfh645");
		listOfQuestionsSER.set(selectedIndex, questionSub);
		questionSub = listOfQuestionsSER.get(0);
		
		listOfQuestionsSER.set(0, questionSub);
		System.out.println(listOfQuestionsSER + "listOfQuestionsSER igjty72");
		var insertlistOfQuestionsSERtoDB = new InsertlistOfQuestionsSERtoDB();
		insertlistOfQuestionsSERtoDB.updateRows(selectedExamIndex, listOfQuestionsSER);
		System.out.println(selectedExamIndex + "Bottom of QuestionFactory in setQuestionArray() method");
	}
}
