
// This QuestionSuper is in the BrainBuilder3 class, not the Tester. And, it's a 
// constant work in progress.
// CLASS: QuestionSuper - Central to the Builder and Tester apps, this super or parent class contains 
//over seventy (70) getter and setter methods (it's just easier to lump all methods in one class) 
// (some of which aren't being used and need to be removed). 
//Too numerous to list, these methods set and get such things as title, topic, question, possible answers, 
//correct answers, time of creation, test taken, pass/fail messages, percent grades, previouslySelectedCommand,
//masterListOfProfsCorrectAnswers, and much more.

package main.java;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

// Work in progress, always
public class QuestionSuper implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Integer sizeOfSerList;
	Integer numberOfPass;
	Integer initialNumberOfQuestions; // Need getter/setter for this? Yes
	Integer additionalQuestions; // Need getter/setter for this? Yes
	Integer totalNumberOfQuestions = 0;
	Integer primaryKeyDB;// Not sure I'm using this. I think this is also the exam number that should
						// always match up?
	Integer questionNumber; // important also for navigation back and forth
	Integer indexOfQuestionInList; // which index in the list is the question associated with?
	Integer examNumberPK; // Say what?? setSelectedIndex(Integer selectedIndex) below might provide this
	Integer selectedIndex;
	Integer indexOfSelected; // Seems important but it doesn't belong to any method
	double percentGrade;
	
	Date dateOfExam; // Might not need this as I use String version instead
	
	Boolean isQGraded = false; // Note, I'm using this variable in three (3) methods below	
	Boolean isExamStarted; // Used in Begin and Previous buttons
	Boolean isTheExamFinished = false;
	boolean noSelectionMade;
	
	String dateOfExamStr;
	String creator;
	String studentLastName;
	String studentFirstName;
	String examTitle = "";
	String examTopic = "topic";
	String question;
	String answer1;
	String answer2;
	String answer3;
	String answer4;
	String answer5 = "";

	String correctAnswer1;
	String correctAnswer2;
	String correctAnswer3;
	String correctAnswer4;
	String correctAnswer5;
	String explanation;

	String created;// It should reference when a question was created, otherwise, created what?
	String lastUpdate;	
	String previouslySelectedCommand;
	String selectedValue;
	String passFail = "FAIL"; // CHANGED from pass to fail on 6-28
	String passFailMessage = "FAIL"; // CHANGED from pass to fail on 6-28	
	String questionTopic; // possible duplicate	
	String timeStart;
	String timeEnd;
	String timeDuration;
	
	ArrayList<Boolean> profCorrectAnswers;
	ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers;
	
	// Where is this used?
	public void setSizeLerListr(Integer sizeOfSerList) {
		this.sizeOfSerList = sizeOfSerList;
	}
	public Integer getSizeLerListr() {
		return sizeOfSerList;
	}	
	
	public void setCreator(String creator) {
		this.creator = creator;
	}	

	public String getCreator() {
		return creator;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	// I might not need this if I'm using the String version, which I am
	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public Date getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExamStr(String dateOfExamStr) {
		this.dateOfExamStr = dateOfExamStr;
	}

	public String getDateOfExamStr() {
		System.out.println(dateOfExamStr + " dateOfExamStr from inside QuestionSuper getter");
		return dateOfExamStr;
	}
	
	public void setQuestionGraded(Boolean isQGraded) {// I might not need this in the Begin Exam button. Use only isExamFinished
		this.isQGraded = isQGraded;
	}

	public Boolean getQuestionGraded() {
		return isQGraded;
	}

		// don't use this one? This is causing confusion
	public Boolean isQuestionGraded() {
		return isQGraded;
	}

	// might not need
	public void setPrimaryKey(Integer primaryKeyDB) {
		this.primaryKeyDB = primaryKeyDB;
	}

	public Integer getPrimaryKey() {
		return primaryKeyDB;
	}

	// This is used to set isExamStarted from the default false to true when
	// Begin Exam is clicked. Use that in conjunction with isfinished in the Previous button
	// toi restrict backwards movement
	public void setIsExamStarted(Boolean isExamStarted) {
		this.isExamStarted = isExamStarted;
	}

	public Boolean getIsExamStarted() {
		return isExamStarted;
	}

	public void setTitle(String title) {
		examTitle = title;
	}

	public String getTitle() {
		return examTitle;
	}

	public void setTopic(String examTopic) {
		this.examTopic = examTopic;
	}

		// I'm using the other one, getQuestionTopic
	public String getTopic() {
		return examTopic;
	}

	// Possible duplicate. Use getTopic() instead.? or not. built with this one.  Double-check
	public void setQuestionTopic(String questionTopic) {
		this.questionTopic = questionTopic;
	}

	public String getQuestionTopic() {
		return questionTopic;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setCorrectAnswer1(String correctAnswer1) {
		this.correctAnswer1 = correctAnswer1;
	}

	public String getCorrectAnswer1() {
		return correctAnswer1;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setCorrectAnswer2(String correctAnswer2) {
		this.correctAnswer2 = correctAnswer2;
	}

	public String getCorrectAnswer2() {
		return correctAnswer2;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setCorrectAnswer3(String correctAnswer3) {
		this.correctAnswer3 = correctAnswer3;
	}

	public String getCorrectAnswer3() {
		return correctAnswer3;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setCorrectAnswer4(String correctAnswer4) {
		this.correctAnswer4 = correctAnswer4;
	}

	public String getCorrectAnswer4() {
		return correctAnswer4;
	}

	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}

	public String getAnswer5() {
		return answer5;
	}

	public void setCorrectAnswer5(String correctAnswer5) {
		this.correctAnswer5 = correctAnswer5;
	}

	public String getCorrectAnswer5() {
		return correctAnswer5;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getExplanation() {
		return explanation;
	}

	// Is this the date created? Double-check
	public void setCreated(String created) {
		this.created = created;
	}

	public String getCreated() {
		return created;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getlastUpdate() {
		return lastUpdate;
	}

	public void setProfsCorrectAnswers(ArrayList<Boolean> profCorrectAnswers) {
		this.profCorrectAnswers = profCorrectAnswers;
	}

	public ArrayList<Boolean> getProfsCorrectAnswers() {
		return profCorrectAnswers;
	}

	public void setMasterProfsCorrectAns(ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers) {
		this.masterListOfProfsCorrectAnswers = masterListOfProfsCorrectAnswers;
	}

	public ArrayList<ArrayList<Boolean>> getMasterProfsCorrectAns() {
		return masterListOfProfsCorrectAnswers;
	}

	// Re previouslySelectedCommand ///////////////////////////////
	public void setSelectedCommand(String selectedCommand) {
		previouslySelectedCommand = selectedCommand;
	}

	public String getSelectedCommand() {
		return previouslySelectedCommand;
	}

	// But will this lock me in? Can't I just use the serlist index plus 1?
	// VERY IMPORTANT FOR PURPOSES OF NAVIGATION IN TESTER
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	public Integer getQuestionNumber() {
		return questionNumber;
	}

	// Where is this used? Double-check
	public void setIndexOfQuestionInList(Integer indexOfQuestionInList) {
		this.indexOfQuestionInList = indexOfQuestionInList;
	}

	public Integer getIndexOfQuestionInList() {
		return indexOfQuestionInList;
	}

	// Double-check
	// Do I use this?????????????????????????
	public void setExamNumber(Integer examNumberPK) {
		this.examNumberPK = examNumberPK;
	}

	public Integer getExamNumber() {
		return examNumberPK;
	}

	// Do I need this? Double-check
	public void setSelectedIndex(Integer selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public Integer getSelectedIndex() {
		return selectedIndex;
	}

	// Double-check its use
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setPassFail(String passFail) {
		this.passFail = passFail;
	}

	public String getPassFail() {
		return passFail;
	}

	// I don't think I'm using this one. using setPassFail() instead, but
	// double-check
	public void setPassFailMessage(String passFailMessage) {
		this.passFailMessage = passFailMessage;
	}

	public String getPassFailMessage() {
		return passFailMessage;
	}

	public void setPercentGrade(double percentGrade) {
		this.percentGrade = percentGrade;
	}

	public double getPercentGrade() {
		return percentGrade;
	}
	
	// Warning, this is set only one time in the first anon question object!!!
	public void setNumberOfPass(Integer numberOfPass) {
		this.numberOfPass = numberOfPass;
	}	
	public Integer getNumberOfPass() {
		return numberOfPass;
	}
	
	public void setIsExamFinished(Boolean isTheExamFinished) {
		this.isTheExamFinished = isTheExamFinished;
	}
	public Boolean getIsExamFinished() {
		return isTheExamFinished;
	}
	
	public void setInitialNumberOfQuestions(Integer initialNumberOfQuestions) {
		this.initialNumberOfQuestions = initialNumberOfQuestions;
		setTotalNumberOfQuestions(initialNumberOfQuestions);
	}	
	public Integer getInitialNumberOfQuestions() {
		return initialNumberOfQuestions;
	}
	
	public void setAdditonalNumberOfQuestions(Integer additionalQuestions) {
		this.additionalQuestions = additionalQuestions;
		setTotalNumberOfQuestions(additionalQuestions);
	}	
	public Integer getAdditonalNumberOfQuestions() {
		return additionalQuestions;
	}	
	
	public void setTotalNumberOfQuestions(Integer totalNumberOfQuestions) {
		//
		this.totalNumberOfQuestions += totalNumberOfQuestions;
	}
	// If I want to use the first anon object to get the total number of questions, 
	//  I need to pull it up from the deSerialize method that pulls it out of the 17 table
	// in LoadInitialMasterList class, or maybe use a utility? No utility I can find does this
	public Integer getTotalNumberOfQuestions() {
		
		// Print totalnumber from LoadInitialMasterList class and deSerializeListOfQuestionsSER() from 17 table
		//LoadInitialMasterList loadInitialMasterList = new LoadInitialMasterList();
		//loadInitialMasterList.deSerializeListOfQuestionsSER(selectedExamIndex);
		
		return totalNumberOfQuestions;
	}
	
	public void setNoSelectionMade(boolean noSelectionMade) {
		this.noSelectionMade = noSelectionMade;
	}
	public boolean getNoSelectionMade() {
		return noSelectionMade;
	}
	
	public void setTimeExamStarts(String timeStart) {
		this.timeStart = timeStart;
	}
	
	public String getTimeExamStarts() {
		return timeStart;
	}
	
	public void setTimeExamEnds(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	public String getTimeExamEnds() {
		return timeEnd;
	}
	
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	
	public String getTimeDuration() {
		return timeDuration;
	}	
}
