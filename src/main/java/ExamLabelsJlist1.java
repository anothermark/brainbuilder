
// Class ExamLabelsJlist1 provides getters and setters for each exam label in 
// the 'All Available Exams' Jlist. Used to allow creator to customize each exam
// label such as 'Exam 1 - Apologetics 101'. 

package main.java;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class ExamLabelsJlist1 implements Serializable {

	String exam1 = "Exam 1";
	String exam2 = "Exam 2";
	String exam3 = "Exam 3";
	String exam4 = "Exam 4";
	String exam5 = "Exam 5";
	String exam6 = "Exam 6";
	String exam7 = "Exam 7";
	String exam8 = "Exam 8";
	String exam9 = "Exam 9";
	String exam10 = "Exam 10";

	public void setExam1Str(String exam1) throws IOException, SQLException, ClassNotFoundException {
		this.exam1 = exam1;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam1Str() {
		return exam1;
	}

	public void setExam2Str(String exam2) throws IOException, SQLException, ClassNotFoundException {
		this.exam2 = exam2;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam2Str() {
		return exam2;
	}

	public void setExam3Str(String exam3) throws IOException, SQLException, ClassNotFoundException {
		this.exam3 = exam3;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam3Str() {
		return exam3;
	}

	public void setExam4Str(String exam4) throws IOException, SQLException, ClassNotFoundException {
		this.exam4 = exam4;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam4Str() {
		return exam4;
	}

	public void setExam5Str(String exam5) throws IOException, SQLException, ClassNotFoundException {
		this.exam5 = exam5;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam5Str() {
		return exam5;
	}

	public void setExam6Str(String exam6) throws IOException, SQLException, ClassNotFoundException {
		this.exam6 = exam6;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam6Str() {
		return exam6;
	}

	public void setExam7Str(String exam7) throws IOException, SQLException, ClassNotFoundException {
		this.exam7 = exam7;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam7Str() {
		return exam7;
	}

	public void setExam8Str(String exam8) throws IOException, SQLException, ClassNotFoundException {
		this.exam8 = exam8;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam8Str() {
		return exam8;
	}

	public void setExam9Str(String exam9) throws IOException, SQLException, ClassNotFoundException {
		this.exam9 = exam9;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam9Str() {
		return exam9;
	}

	public void setExam10Str(String exam10) throws IOException, SQLException, ClassNotFoundException {
		this.exam10 = exam10;
		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		loadInitExamLabsJList.setExamLabelsJlist1(this);
	}

	public String getExam10Str() {
		return exam10;
	}
}
