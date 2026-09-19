package main.java;

public class FAQText {

	String faqFinalString;
	String overview;
	String faqString;
	String faqString2;
	String faqString3;
	String faqString4;
	String faqString5;
	String faqString6;
	String faqString7;
	String faqString8;
	String faqString9;
	String faqString10;
	String faqString11;
	String faqString12;
	String faqString13;
	String faqString14;
	String faqString15;
	String faqString16;
	String faqString17;
	String faqString18;
	String faqString19;
	String faqString20;

	public String getFAQTextString(Integer selectedExamIndex) {

		faqString = "USER FAQ FOR THE BRAIN-BUILDER \n";

		if (selectedExamIndex == 0) {
			overview = "THE BRAIN BUILDER FAQ - THE BIG PICTURE\n\n"
					+ "This stand-alone BrainBuilder (Builder) application works in conjunction with the BrainTester (Tester) app. "
					+ "The Builder creates exam questions and answers in essentially multiple-choice format, which can also be used for "
					+ "true/false questions. A student takes these exams in the Tester. The Tester is not merely a test-taking program you might find in "
					+ "a university environment, but much more - an enhanced study and learning tool, something that is intended to be used "
					+ "repeatedly to better learn a particular subject and improve one's knowledge base in that field. \n\nRepetition "
					+ "is the key. Unlike a regular multiple-choice testing program which simply evaluates a student's knowledge of a particular "
					+ "subject and returns a grade, this program also previews, in real time if desired, each graded question and ideally should provide a wealth of feedback "
					+ "information explaining why a particular answer is wrong or right. \n\n"
					+ "The inner workings of these programs can be detailed, and somewhat complex. "
					+ "For additional information regarding classes and methods, and other Java-centric "
					+ "topics, etc., please study the READ_ME_BUILDER file";

			return overview;
		} else if (selectedExamIndex == 1) {
			faqString2 = "CREATE AN EXAM (STRUCTURE) AND SPECIFY \nTHE NUMBER OF QUESTIONS IT WILL CONTAIN\n\n";
			faqString3 = "Before you populate your questions with data (title, topic, question, answers, "
					+ "correct answers, detailed explanations, etc.) you must first create an exam, "
					+ "also called an exam structure. An exam structure initially contains questions without "
					+ "the data. When the Builder is first executed, the 'All Available Exams' section "
					+ "lists ten (10) exams (Exam 1, Exam 2 etc.). You may customize this list of labels/captions as "
					+ "explained below.\n\n";
			faqString4 = "Always create your exams sequentially (Exam 1, then Exam 2, etc.). Select Exam 1 "
					+ "and you will be prompted for the number of questions you wish to include in "
					+ "this exam, currently limited to a  maximum of ten (10). To display the number of exam "
					+ "structures and questions created click 'Number of Created Exams/Questions'.";

			return faqString2 + faqString3 + faqString4;
		} else if (selectedExamIndex == 2) {
			faqString5 = "ADD ADDITONAL QUESTIONS TO THE EXAM (STRUCTURE)\n\n"
					+ "To add additional questions to the exam structure, select the exam from the 'All Available Exams' JList "
					+ "and click 'Add Additional Questions to this Exam' and follow the prompt. \n\n"

					+ "IMPORTANT: When you test your exams in Tester it is essential that you test the same exam. "
					+ "For instance, if Exam 1 has one question and you test it in the Tester, and then "
					+ "you return to the Builder and add an additional question to that Exam 1, and switching "
					+ "over to the Tester you take that same Exam 1 again it will crash the program and you will "
					+ "get a faulty average score, among other bad things. \n\n"
					+ "Why? Because during the review process Java averages the scores (stored in each object) for "
					+ "the latest two exams. But you are essentially comparing apples to oranges, a "
					+ "listOfQuestionsSER list containing one QuestionSuper object, with another containing "
					+ "two, and you can't average null. Accordingly, if you add additional question[s] to an "
					+ "exam you must delete the oldest taken-exam over in Tester before you take the exam again because "
					+ "you only want to compare oranges. The inverse is also true. If you create an exam with "
					+ "two questions and test it, don't delete one question and test the same exam again.";
			return faqString5;
		} else if (selectedExamIndex == 3) {			
			faqString6 = "CUSTOMIZE THE LIST OF EXAM LABELS/CAPTIONS IN THE 'All Available Exams' SECTION \n\n"
					+ "When the Builder app is first loaded it displays default settings of list exams such "
					+ "as Exam 1, Exam 2, Exam 3, etc., in the 'All Available Exams' section. To customize these "
					+ "exam titles, for instance, change a caption to 'Exam 1 - Trinity', click 'Exam Labels' "
					+ "near the top-right of your screen where you will be presented with an OptionPane "
					+ "holding ten labels with corresponding TextField rows.\n\n";

			faqString7 = "Edit your changes to one or more of these text fields and select 'Yes' to apply those changes. "
					+ "Unfortunately, after your changes have been made you will be informed that to see those changes "
					+ "take effect the app must close (which it will automatically), requiring you to re-load the Builder.\n";
			return faqString6 + faqString7;
		} else if (selectedExamIndex == 4) {
			faqString8 = "DELETE AN EXAM\r\n\n" + "Select the exam from the list and click 'Delete an Exam'. "
					+ "Clicking 'Delete An Exam' does not remove the exam row from database tables, but rather, sets their values to "
					+ "the null placeholder status quo. If the table rows were removed entirely other exams would be impacted and indexes "
					+ "would shift accordingly, which would be unworkable. This way you may re-create, "
					+ "say Exam 1, in the same Exam 1 'slot', so to speak.\n\n"

					+ "CAUTION! In the event you need to remove exam rows completely from your tables (which is best left to your "
					+ "Java experts) and set the row count to 0, use the buttons in the 5th column (beginning with DeleteOrigRows) "
					+ "under the section entitled 'Administrator Only Below This (yellow) Line'. Be careful using these "
					+ "options as the changes are permanent. Remember to select an exam first. Sometimes you "
					+ "just have to keep clicking to get all table row counts down to 0, the row count being displayed to the "
					+ "left in the 4th column. The 3rd column buttons also display the adjoining row count. "
					+ "It is recommended that you first read the section 'DELETING AN EXAM STRUCTURE (Removing it from tables)' in "
					+ "the READ_ME_BUILDER file. ";
			return faqString8;
		} else if (selectedExamIndex == 5) {
			faqString9 = "RE-CREATE A DELETED EXAM\n\n" + "If you 'Delete an Exam' (returning it to a null status) "
					+ "and wish to re-create that exam in the same 'slot', say, 'Exam 1', click 'Re-create Deleted Exam' "
					+ "where you will be prompted for the number of questions (currently max 10) you wish to include. Then, "
					+ "populate and create your individual questions with data as explained below.";
			return faqString9;
		} else if (selectedExamIndex == 6) {
			faqString10 = "DISPLAYING THE NUMBER OF EXAMS AND (empty) QUESTIONS\n\n"
					+ "For the time being only ten (10) exams can be created. Sometimes it can be confusing "
					+ "keeping track of which exams have been created, and the number of (empty) questions "
					+ "available to be populated in each exam. "
					+ "Clicking 'Number of Created Exams/Questions' helps in this regard as it will display this "
					+ "information in the preview pane.\n\n";
			return faqString10;
		} else if (selectedExamIndex == 7) {
			faqString11 = "POPULATE CREATED EXAM QUESTIONS WITH DATA\n\n"
					+ "This is where most of the work takes place. Here we explain how to populate each "
					+ "question and answer field (and more) with data after the exam structure has been "
					+ "created and the number of questions specified. It creates the final version of "
					+ "those questions and answers, and saves the exams to the appropriate database table[s], "
					+ "which the student will retrieve when beginning the exam in the BrainTester app (Tester). "
					+ "Each exam in this current version is limited, for now, to ten (10) questions. \n\n"
					+ "Once a question is created initially, or added later, that question must still be used in the final exam. If "
					+ "you delete it, at some point it must be re-created in that 'slot' or at that list index. See the "
					+ "section DELETE AN INDIVIDUAL QUESTION for further information."

					+ "WARNING!! Never type your questions and answers directly into the data fields initially. Always type Q&As into "
					+ "a word processor or similar program, then copy and paste that information into the appropriate fields. "
					+ "The beta Builder app is temperamental and all of your hard work may be lost. Also, database redundancy is "
					+ "essential, and due to the significant amount of data involved, backing up everything (the database) on "
					+ "a separate hard drive will greatly help prevent catastrophic loss and much work rebuilding the exams. \n\n"

					+ "As always, first, select your created exam, then select a question from the 'Individual Exams With "
					+ "Questions' pane. Click 'Preview Answers' or 'Preview Correct Answers' to see a more user-friendly "
					+ "summary of what you have entered. "
					+ "Fill in all fields to the extent possible, such as original questions, possible answers, correct answers, "
					+ "and detailed explanations if desired. \n\n"

					+ "Furthermore, REMEMBER to select one radio button to indicate which answer is "
					+ "the correct answer. The radio buttons as designed, for now, are grouped in such a way that only one "
					+ "answer can be selected. If nothing is selected all answers are evaluated as false, a fail. "

					+ "At such time as the instructor/creator is finished creating the exam and populating all "
					+ "questions he/she must remember to take it for a test run over in the Brain Tester app to verify "
					+ "it functions as anticipated. If any questions are empty/null a warning will appear and prohibit "
					+ "the exam from being taken until that mistake is rectified.\n\n"

					+ "At a later time, after the exam has been created and you wish to see your work, simply "
					+ "select your exam, then select your question and the data will appear. For a more comprehensive "
					+ "explanatiion of how this works - classes, methods, and more - please see the READ_ME_BUILDER file.\n\n";
			return faqString11;
		} else if (selectedExamIndex == 8) {
			faqString12 = "WHICH QUESTIONS HAVE DATA, AND WHICH ARE EMPTY/NULL?\n\n"
					+ "To determine which questions have been populated with data, or whether a particular "
					+ "question is 'empty' of data, first select the exam in the left-hand list pane "
					+ "and click 'Questions In Selected Exam'. "
					+ "A list should appear in the preview pane with each question's title if the question "
					+ "has any data, or the word 'empty' if the question has not been populated with data. "
					+ "Needless to say, providing your exams with titles is important, and required. ";
			return faqString12;
		} else if (selectedExamIndex == 9) {
			faqString13 = "UPDATING QUESTION AND ANSWER FIELDS AFTER CREATION \n\n"
					+ "After you have created (populated with data) your questions you will want to review them and possibly make changes and "
					+ "update them. First, select an exam from the left-most 'All Available Exams' list which will display "
					+ "each exam's questions in the 'Individual Exams with Questions' panel to the right. Select a question and "
					+ "the question's data will be displayed. Click 'Preview Answers' or 'Preview Correct Answers' to display "
					+ "that information in a more-readable format in the large preview pane. Then simply copy your changes "
					+ "from your text editor and paste that in the appropriate field (or insert changes directly into the field) "
					+ "and click 'Update Question'.\n\n";
			return faqString13;
		} else if (selectedExamIndex == 10) {
			faqString14 = "DELETE AN INDIVIDUAL QUESTION\n\n"
					+ "As always, first, select your exam which will display the questions in that exam, and select "
					+ "the question you wish to delete. Then, after serious, hand-wringing reflection - because deletion "
					+ "is permanent and irreversible and might require a laborious rebuild, hold your breath and "
					+ "click 'Delete Question'. Now exhale. \n\n"
					+ "As a preliminary matter, deleting a question merely sets it to null thereby preserving the index order "
					+ "of all remaining questions, which is very important. Consequently, at some point you must re-create that "
					+ "deleted question in order to make the exam work because an empty/null question throws a nullpointer exception "
					+ "in the Tester when the student takes the exam; which is another reason to always take your exam "
					+ "for a test run over in the Tester app. \n\n"

					+ "Remember to always retain that deleted information in your text editor in the event you feel the need "
					+ "to re-populate that question, or another, with same. ";
			return faqString14;
		} else if (selectedExamIndex == 11) {
			faqString15 = "CHANGING THE BUILDER FAQ TEXT\n\n"
					+ "No doubt you will wish to modify text in the FAQ, but that requires changing "
					+ "information directly in a Java file. The FAQ text is contained in the FAQText.java file "
					+ "where String values are assigned to String reference variables, some of which are concatenated, and "
					+ "returned to the method call in the 'User FAQ' actionPerformed() method. If you "
					+ "don't know what this means it's probably best if you ask your Java expert to make "
					+ "those changes for you. The process is rather simple and self-explanatory. For "
					+ "details see the READ_ME_BUILDER file ";
			return faqString15;
		} else if (selectedExamIndex == 12) {
			faqString16 = "Something here ";
			return faqString16;
		} else if (selectedExamIndex == 13) {
			faqString17 = "WHICH BUILDER/TESTER TABLES EXIST IN THE DATABASE?\n\n"
					+ "Click 'Show Tables' at the bottom-center of your screen. "
					+ "Near the bottom of the previewed list will be the custom-created "
					+ "tables for these programs beginning with BUILDER_EXAMS_LISTS_17.";
			return faqString17;		
		} else if (selectedExamIndex == 14) {
			faqString18 = "ADMINISTRATOR ONLY \n\n"
					+ "Certain tasks are best left to your Java experts. Information with respect to "
					+ "other core topics can be found in the READ_ME_BUILDER file. These other "
					+ "topics include, but are not limited to: a) UML charts, b) program structure, classes and "
					+ "methods, c) creating/deleting database tables, d) the Swing GUI, e) classes that load initial "
					+ "(null) placeholders, f) method flow, g) utility classes, h) the java-technical details with respect "
					+ "to all topics listed in this FAQ, and much more." + " ";
			return faqString18;
		}
		return faqFinalString;
	}
}
