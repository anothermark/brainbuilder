
// Licenses: 
// Copyright: MKS (2025 - 2026)

package main.java;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;

public class BrainBuilder3 extends JDialog {

	private static final long serialVersionUID = -7590264413402486671L;
	
	// Define a unique key for EACH pop-up message
    private static final String PREF_KEY_EXAM_INTRO = "show_exam_intro_message";
    private static final String PREF_KEY_WELCOME = "show_welcome_message"; 
    private static final String PREF_KEY_DELETE_WARNING = "show_delete_warning";

    private static final Preferences prefs = Preferences.userNodeForPackage(BrainBuilder3.class);

	String listOfQuestionsStr;
	String anonStr;
	String numExamsDisplayed;
	
	String numOfQuestions;
	
	String selectedValue;
	String selectedExamNumber;
	String previouslySelectedCommand;
	String selectedValueQ;
	String selectedIndexExamString;
	String finalStr;
	String finalStrX = "";

	String exam1 = "";
	String exam2;
	String exam3;
	String exam4;
	String exam5;
	String exam6;
	String exam7;
	String exam8;
	String exam9;
	String exam10;

	ExamLabelsJlist1 examLabelsJlist1;
	QuestionSuper questionSuper;

	ButtonGroup builderGroup;

	StyledDocument doc;

	ArrayList<QuestionSuper> listOfQuestionsSER;
	ArrayList<String> allTableNames;
	ArrayList<ArrayList<Boolean>> masterListOfProfsCorrectAnswers;
	ArrayList<ArrayList<QuestionSuper>> masterOfCreatedExamsDisplayed;
	public static ArrayList<ArrayList<QuestionSuper>> builderMasterListOfLists;
	ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfLists;
	ArrayList<ArrayList<QuestionSuper>> innerMasterListOfMastersSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER_5th;
	ArrayList<ArrayList<ArrayList<Boolean>>> outerMasterCorrectAnswersProfs;
	ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSER_Disp;

	Integer displayRowCountEQ;
	Integer countingRows;
	Integer selectedExamIndex;
	Integer selectedIndex;
	Integer numInitQuestionsRecreate;
	Integer rowCountExamLabsInt;
	Integer selectedIndexExamNumber;
	Integer studentPrimaryKey;
	Integer questionNumberFromObject;
	Integer questionNumber;
	Integer examNumber;
	Integer initialNumberOfQuestions;
	Integer numberOfAdditionalQuestions;

	int numberOfRows;
	int additionalQuestions;

	JTextPane textPanePreview;
	private JTextField textFieldExamTitle;
	JList<String> list_1;
	private JTextField textFieldCreatedTime;
	private JTextField textFieldUpdatedTime;
	JTextArea textAreaExplain;
	JRadioButton jRadioButton;
	JButton button;

	String prevQTitle;
	String prevQTopic;
	String prevQuestion;
	String prevAns1;
	String prevAns2;
	String prevAns3;
	String prevAns4;
	String prevAns5;
	String prevExplanation;
	String prevCorrectAns1;
	String prevCorrectAns2;
	String prevCorrectAns3;
	String prevCorrectAns4;
	String prevCorrectAns5;

	private JTextField textFieldCreator;
	private JTextField textFieldQuestionNumber;
	private JTextField textFieldExamNumber;
	private JTextField textFieldTopic;
	private JTextField textFieldRowCount;
	private JTextField textFieldProfNumRows;
	private JTextField textFieldGradedNumRows;
	private JTextField textFieldStudentNumberRows;
	private JTextField textFieldOuterRowCount;
	private JTextField txtAdministratorOnlyBelow;
	private JTextField textFieldDisplayEQCount;
	private JTextField textFieldExamLabelsCount;
	private JTextField textFieldRowcountDisplayTable;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application
	 * 
	 * @throws IOException
	 * @throws SQLException
	 * @throws BackingStoreException
	 * @throws ClassNotFoundException 
	 */

	public static void main(String[] args) throws SQLException, IOException, BackingStoreException, ClassNotFoundException {
		DatabaseConfig.initializeDatabaseIfMissing();
		try {
			BrainBuilder3 dialog = new BrainBuilder3();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocation(150, 15);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// This restores my dialog message from "Don't show this message again" check
		// box.
		// Perhaps create a button to give the user a choice
		
		//Preferences.userNodeForPackage(BrainBuilder3.class).remove(PREF_KEY);
		//if (!prefs.getBoolean(PREF_KEY, true)) {
			//return;
		//}
		
		// (Optional) Remove line below in production if you don't want to reset keys every launch
        // prefs.remove(PREF_KEY_EXAM_INTRO); 
		GetListFromDB getListFromDB = new GetListFromDB();
		System.out.println(getListFromDB.getRowData(1) + "testing the new connection helper should return serlist");

		 // ==========================================
        // POP-UP 1: Exam Structure Intro
        // ==========================================
        // Only show if the user HAS NOT checked "Don't show this message again" (defaults to true)
        if (prefs.getBoolean(PREF_KEY_EXAM_INTRO, true)) {
            JCheckBox checkBoxExam = new JCheckBox("Don't show this message again"); 
            Object[] messageExam = { 
                "To create an exam structure you must first select an exam sequentially (ie. Exam 1, then Exam 2) \n" 
                + "from the 'All Available Exams' list, and then click the Create An Exam button. \n" 
                + "After this exam structure has been built, to populate with data, retrieve or update a question, \n" 
                + "you must first select the exam from the 'All Available Exams' list, and then select \n" 
                + "a question from the 'Individual Exams With Questions' list.", 
                checkBoxExam 
            };  


            int resultExam = JOptionPane.showConfirmDialog(null, messageExam, "Intro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);  

            if (resultExam == JOptionPane.OK_OPTION && checkBoxExam.isSelected()) { 
                prefs.putBoolean(PREF_KEY_EXAM_INTRO, false); 
                System.out.println("User checked don't show Exam Intro again"); 
            }
	}
	}

	/**
	 * Create the dialog
	 * 
	 * @throws BadLocationException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BrainBuilder3() throws BadLocationException, ClassNotFoundException, IOException, SQLException {
		setBounds(100, 100, 1199, 935);
		getContentPane().setLayout(null);
		Container contentPaneContainer = getContentPane();

		ActionListener selectionSaver = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				previouslySelectedCommand = e.getActionCommand();
				System.out.println(" Saved selection: " + previouslySelectedCommand);
			}
		};
		builderGroup = new ButtonGroup();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"The Brain-Builder Dashboard", TitledBorder.RIGHT, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panel.setBounds(34, 18, 1128, 58);
		getContentPane().add(panel);
		panel.setLayout(null);

		var lblCreator = new JLabel("**Creator:");
		lblCreator.setBounds(915, 32, 77, 17);
		lblCreator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCreator);

		textFieldCreator = new JTextField();
		textFieldCreator.setBounds(995, 30, 126, 23);
		textFieldCreator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(textFieldCreator);
		textFieldCreator.setColumns(10);

		var frame = new JFrame("FAQ Window");
		frame.setBounds(30, 40, 30, 40);
		var btnFAQ = new JButton("User FAQ");
		btnFAQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var faqTextPane = new JTextPane();
				faqTextPane.setCaretPosition(0);
				var customFont = new Font("Tahoma", Font.PLAIN, 14);
				faqTextPane.setFont(customFont);
				faqTextPane.setPreferredSize(new Dimension(450, 600));
				faqTextPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

				JList JListFAQ = new JList();
				JListFAQ.setBounds(10, 5, 224, 398);
				JListFAQ.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				JListFAQ.setModel(new AbstractListModel() {

					String[] values = new String[] { "THE BRAIN BUILDER FAQ - THE BIG PICTURE",
							"Create an Exam and Number of Questions", "Add Additional Questions to the Exam Structure",
							"Customize Exam Labels/Captions", "Delete an Exam", "Re-create a Deleted Exam",
							"Displaying Number of Exams and (empty) Questions",
							"Populate Created Exam Questions with Data",
							"Which Questions have been Populated with Data?", "Updating Questions and Answers",
							"Delete a Question", "Changing the Builder FAQ Text", "Something here",
							"Which User-Defined Tables are in the Database?", "For the Administrator, Only" };

					public int getSize() {
						return values.length;
					}

					public Object getElementAt(int index) {
						return values[index];
					}
				});

				JListFAQ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				JListFAQ.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting()) {
							if (JListFAQ.getSelectedIndex() != -1) {
								selectedValue = (String) JListFAQ.getSelectedValue();
								selectedExamIndex = JListFAQ.getSelectedIndex();
								FAQText faqText = new FAQText();

								if (selectedExamIndex == 0) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 1) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 2) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 3) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 4) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 5) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 6) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 7) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 8) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 9) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 10) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 11) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 12) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 13) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 14) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								} else if (selectedExamIndex == 15) {
									faqTextPane.setText(faqText.getFAQTextString(selectedExamIndex));
								}
							}
						}
					}
				});

				JListFAQ.setFont(new Font("Tahoma", Font.PLAIN, 14));

				JScrollPane jScrollPaneFAQ = new JScrollPane(faqTextPane);
				jScrollPaneFAQ.getViewport().setViewPosition(new Point(0, 0)); // does not work
				jScrollPaneFAQ.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				jScrollPaneFAQ.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				jScrollPaneFAQ.setBounds(30, 40, 60, 90);
				JSplitPane splitPaneFAQ = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
				splitPaneFAQ.setTopComponent(JListFAQ);
				splitPaneFAQ.setBottomComponent(jScrollPaneFAQ);

				JOptionPane.showConfirmDialog(frame, splitPaneFAQ, "FAQ  (Frequently Asked Questions, and Answers)",
						JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnFAQ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFAQ.setBounds(4, 11, 135, 21);
		panel.add(btnFAQ);

		JButton btnCreateAnExam = new JButton("Create An Exam");
		btnCreateAnExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// WHY DID I DO THIS 8-8?
				var displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}

				ExamExists: if (true) {
					var insertInitialOuterNestedPlaceholders = new InsertInitialOuterNestedPlaceholders();

					try {
						outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
								.getOuterMasterListOfMastersSER();
						System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER dhwy603w");

						if (outerMasterListOfMastersSER != null && selectedExamIndex != null
								&& outerMasterListOfMastersSER.get(selectedExamIndex) != null) {
							JOptionPane.showMessageDialog(null,
									"You cannot create an exam that has already been created");
							break ExamExists;
						}

						if (outerMasterListOfMastersSER == null) {
							outerMasterListOfMastersSER = new ArrayList<ArrayList<ArrayList<QuestionSuper>>>();
							for (int i = 0; i < 10; i++) {
								// Now add four ArrayList<ArrayList<QuestionSuper>> masterListOfQuestionsSER;
								outerMasterListOfMastersSER.add(null);
							}
							insertInitialOuterNestedPlaceholders.insertInitialOuter(outerMasterListOfMastersSER);

							// Test it
							outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
									.getOuterMasterListOfMastersSER();
							System.out.println(outerMasterListOfMastersSER
									+ " outerMasterListOfMastersSER AFTER INITIAL ADDED pox4td9405");

							var builderDBTesterUtility = new BuilderDBTesterUtility();
							try {
								// Should always be 1
								countingRows = builderDBTesterUtility.getRowCountOuterNested();
								textFieldOuterRowCount.setText(countingRows.toString());
							} catch (ClassNotFoundException | IOException | SQLException e1) {
								e1.printStackTrace();
							}

							try {
								System.out.println(outerMasterListOfMastersSER + " kl dfdfji");
								if (countingRows < 1) {

									insertInitialOuterNestedPlaceholders
											.insertInitialOuter(outerMasterListOfMastersSER);
								}
								System.out.println("kfgprrpy");
								outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
										.getOuterMasterListOfMastersSER();
								System.out.println(
										outerMasterListOfMastersSER + " outerMasterListOfMastersSER ri948t94cmt");

								System.out.println(outerMasterListOfMastersSER.size()
										+ " outerMasterListOfMastersSER.size() k,potci945");
							} catch (ClassNotFoundException | IOException | SQLException e1) {
								e1.printStackTrace();
							}
						}

					} catch (ClassNotFoundException | IOException | SQLException e1) {
						e1.printStackTrace();
					}

					System.out.println("dcocr8t490864");

					NoSequence: IsNull: BreakOnlyOneExam: if (selectedExamIndex == null) {
						// Always show this message
						System.out.println("selectedExamIndex is null yrhfr6472");
						JOptionPane.showMessageDialog(null, "Remember, you must first select an exam. ");
						break IsNull;

					} else {

						if (selectedExamIndex != null) {
							for (int i = 0; i < 10; i++) {
								System.out.println("[pctio0569");
								System.out.println(outerMasterListOfMastersSER + " cori955mb50");

								System.out
										.println(outerMasterListOfMastersSER.get(selectedExamIndex) + " dkcjtiurty334");
								System.out.println(outerMasterListOfMastersSER.get(selectedExamIndex) == null);
								if (outerMasterListOfMastersSER.get(selectedExamIndex) != null)

								{
									JOptionPane.showMessageDialog(null, "You may only create an exam once  xxx. \n"
											+ "Apparently this exam already exists.");
									break BreakOnlyOneExam;
								}
							}
						}
						System.out.println("kjdoirtrxtu");

						NullEmptyString: if (outerMasterListOfMastersSER != null
								&& outerMasterListOfMastersSER.get(selectedExamIndex) == null) {
							numOfQuestions = JOptionPane
									.showInputDialog("Enter the number of questions for this exam - Max 10");
							if ("".equals(numOfQuestions) || numOfQuestions == null) {
								JOptionPane.showMessageDialog(null, "You must enter a number (Max 10) corresponding to"
										+ " the number of questions you desire");
								break NullEmptyString;

							} else {
								initialNumberOfQuestions = Integer.parseInt(numOfQuestions);

								var loadInitialMasterList = new LoadInitialMasterList();
								try {

									listOfQuestionsSER = loadInitialMasterList.loadMasterList(selectedExamIndex,
											initialNumberOfQuestions, selectedValue, listOfQuestionsSER, 0);
								} catch (SQLException | IOException | ClassNotFoundException e1) {
									e1.printStackTrace();
								}
								var dbaseUtility = new dbUtility();
								try {
									try {
										System.out
												.println(dbaseUtility.rowCountEQDisplay() + " rowCountEQDisplay xczxasz ");
									} catch (ClassNotFoundException e1) {										
									    JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
									    e1.printStackTrace(); 
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								var dbUtility = new dbUtility();
								try {
									Integer countingRows = dbUtility.getRowCount();
									textFieldRowCount.setText(countingRows.toString());
								} catch (ClassNotFoundException | IOException | SQLException e1) {
									e1.printStackTrace();
								}
								var builderDBTesterUtility = new BuilderDBTesterUtility();
								try {
									Integer countingRows = builderDBTesterUtility.getRowCount();
									textFieldProfNumRows.setText(countingRows.toString());
									Integer countingGradedRows = builderDBTesterUtility.getGradedRowCount();
									textFieldGradedNumRows.setText(countingGradedRows.toString());
									textFieldStudentNumberRows.setText(
											builderDBTesterUtility.getRowCountStudentsFinalGraded().toString());
									textFieldOuterRowCount
											.setText(builderDBTesterUtility.getRowCountOuterNested().toString());
								} catch (ClassNotFoundException | IOException | SQLException e1) {
									e1.printStackTrace();
								}

								// #####################################
								// And here is where you set the initial outer 5th to 10 masters
								// with no null serlists within. Just the masters.
								// 8-7-26 THIS IS A MAJOR CHANGE. YOU SET THE 5TH OUTER EXAM MASTERS
								// HERE WITH MASTERS PLACEHOLDERS, NOT ABOVE WHEN LOADING THE
								// FIRST 4 TABLES. MUST KEEP THAT STUFF SEPARATE.

								// But test the outerMasterListOfMastersSER for null to set the
								// 10 masters only once. After that the only thing to touch it
								// are deleting exams, re-creating exams, and when the student
								// inserts a final graded serlist exam into the masters exams,
								// and deletes those from Tester.

								try {
									outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
											.getOuterMasterListOfMastersSER();
									// Now create the exam structure for 5th outer
									masterListOfQuestionsSER = new ArrayList<ArrayList<QuestionSuper>>();
									outerMasterListOfMastersSER.set(selectedExamIndex, masterListOfQuestionsSER);
									insertInitialOuterNestedPlaceholders.setUpdateOuterMasterListofMastersSER(
											selectedExamIndex, masterListOfQuestionsSER, outerMasterListOfMastersSER,
											numberOfAdditionalQuestions);
									// Test it
									outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
											.getOuterMasterListOfMastersSER();
									System.out.println(
											outerMasterListOfMastersSER + " outerMasterListOfMastersSER 0r5690956mb5");

								} catch (ClassNotFoundException | IOException | SQLException e1) {
									e1.printStackTrace();
								}

								// re DisplayExamsAndQuestions
								// #####################################################

								// Display stuff goes here because need number of questions
								try {
									outerMasterListOfMastersSER_Disp = displayExamsAndQuestions
											.getOuterExamsDisplayed();
									System.out.println(
											outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp "
													+ "from display before create vdfx45za");

								} catch (Exception ex) {

								}
								dbUtility dataBaseUtility = new dbUtility();
								int displayRowCount = 0;
								try {

									try {
										displayRowCount = dataBaseUtility.rowCountEQDisplay();
									} catch (ClassNotFoundException e1) {
										JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
									    e1.printStackTrace();
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}

								if (outerMasterListOfMastersSER_Disp == null) {
									outerMasterListOfMastersSER_Disp = new ArrayList<ArrayList<ArrayList<QuestionSuper>>>();
									
									for (int i = 0; i < 10; i++) {
										outerMasterListOfMastersSER_Disp.add(null);
									}
									
									

									try {
										System.out.println(outerMasterListOfMastersSER_Disp + " pot09t04t9");
										// Set the initial 10 nulls in the display outer table
										try {
											displayExamsAndQuestions
													.insertOuterDisplayTable(outerMasterListOfMastersSER_Disp);
										} catch (ClassNotFoundException e1) {
											JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
										    e1.printStackTrace();
										}

									} catch (SQLException | IOException e1) {
										// e1.printStackTrace();
									}
								}

								// Now that the initial 10 nulls are set you can update one
								// of them with an exam by calling setExam

								try {
									displayExamsAndQuestions.setExam(selectedExamIndex, listOfQuestionsSER);
								} catch (ClassNotFoundException | SQLException | IOException e1) {
									e1.printStackTrace();
								}

								System.out.println(
										outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSERTnull peu9457");
								System.out.println(outerMasterListOfMastersSER_Disp == null);
								System.out.println(outerMasterListOfMastersSER_Disp.size()
										+ " outerMasterListOfMastersSERTnull.size() doiexu83457");

								try {
									// Now see if it was properly added
									outerMasterListOfMastersSER_Disp = displayExamsAndQuestions
											.getOuterExamsDisplayed();
								} catch (ClassNotFoundException | SQLException | IOException e1) {
									e1.printStackTrace();
								}
								// This should print 10 nulls from display
								System.out.println(outerMasterListOfMastersSER_Disp
										+ " outerMasterListOfMastersSER_Disp " + "from display AFTER create vFKFUT75");
								System.out.println(outerMasterListOfMastersSER_Disp.size()
										+ " outerMasterListOfMastersSER_Disp.size() hy64");

								JOptionPane.showMessageDialog(null, selectedValue
										+ " Structure was created successfully. \n"
										+ "Next, click 'Define Questions for This Exam' and follow the instructions to populate your exam.");

								OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
								try {
									outerMasterCorrectAnswers.insertPlaceholders();
								} catch (ClassNotFoundException | SQLException | IOException e1) {
									e1.printStackTrace();

								}
							}
						}
					}
				}
			}
		});
		btnCreateAnExam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateAnExam.setBounds(4, 33, 135, 21);
		panel.add(btnCreateAnExam);

		JButton btnPopulateExam = new JButton("Define Questions ");
		btnPopulateExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Once the exam structure is created, to define, retrieve or update a question \n"
								+ "you  must first select the exam from the 'All Available Exams' list, \n"
								+ "then select a question from the 'Individual Exams With Questions' list, \n"
								+ "and fill in the applicable fields.\n\n "
								+ "If you wish to add additional questions to this exam you must select it, "
								+ "then \n click 'Add Additional Questions' to this Exam");
			}
		});
		btnPopulateExam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPopulateExam.setBounds(142, 11, 150, 21);
		panel.add(btnPopulateExam);

		JButton btnNewButton_5 = new JButton("Add Additional Questions ");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				initialNumberOfQuestions = 0;
				Integer numberOfAdditionalQuestions = 0;
				String numOfQuestions;

				
				DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
					System.out.println(outerMasterListOfMastersSER_Disp.size() + " kpoei904586956");
					System.out.println(outerMasterListOfMastersSER_Disp + " oex090495");
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}

				Limit10: OuterIsNull: QLimit10: if (selectedExamIndex == null) {
					JOptionPane.showMessageDialog(null, "You must first select an exam");
				} else {

					if (outerMasterListOfMastersSER_Disp == null) {
						JOptionPane.showMessageDialog(null,
								"Before you can add additional questions \n\n you must" + "first create the exam.");
						break OuterIsNull;

					} else if (outerMasterListOfMastersSER.size() < selectedExamIndex + 1) {
						JOptionPane.showMessageDialog(null, "This exam has not been created. \n"
								+ "Before you can add additional questions \n" + "you must first create the exam.");
					} else {
						System.out.println("poe894854nb");
						listOfQuestionsSER = outerMasterListOfMastersSER_Disp.get(selectedExamIndex).get(0);
						System.out.println(listOfQuestionsSER.size() + " dcprpt99");
						System.out.println(listOfQuestionsSER + " dspicto0c9650");

						if (listOfQuestionsSER.size() >= 10) {
							JOptionPane.showMessageDialog(null,
									"You may not create more than 10 questions \nin an exam, for now.");
							break QLimit10;
						}
					}

					numOfQuestions = JOptionPane
							.showInputDialog("Enter the number of additonal questions for this exam");
					if ("".equals(numOfQuestions)) {
						JOptionPane.showMessageDialog(null, "You must add a number greater than 0 \n" + "and less than "
								+ (11 - listOfQuestionsSER.size()));
					} else {
						if (numOfQuestions != null) {
							numberOfAdditionalQuestions = Integer.parseInt(numOfQuestions);
						}
						var loadInitialMasterList = new LoadInitialMasterList();
						try {
							listOfQuestionsSER = loadInitialMasterList.loadMasterList(selectedExamIndex,
									initialNumberOfQuestions, selectedValue, listOfQuestionsSER,
									numberOfAdditionalQuestions);
							System.out.println(
									listOfQuestionsSER + " listOfQuestionsSER in add additional q gkro849gnir");
							System.out.println("hchgy465 in additionalquestions button");
						} catch (SQLException | IOException | ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Additional question[s] have been added");
					}
				}

			}
		});

		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_5.setBounds(295, 11, 200, 21);
		panel.add(btnNewButton_5);

		JButton btnNumberExQs = new JButton("Number of Created Exams/Questions");
		btnNumberExQs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Testing
				var displayExamsAndQuestionsT2 = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER_Disp = displayExamsAndQuestionsT2.getOuterExamsDisplayed();
					System.out
							.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp opc5u9c485");
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}

				// TESTING TESTING
				ArrayList<ArrayList<ArrayList<QuestionSuper>>> outerMasterListOfMastersSERTnull;
				outerMasterListOfMastersSERTnull = new ArrayList<ArrayList<ArrayList<QuestionSuper>>>();
				for (int i = 0; i < 10; i++) {
					outerMasterListOfMastersSERTnull.add(null);
				}
				System.out.println(outerMasterListOfMastersSERTnull + " outerMasterListOfMastersSERTnull peu9457");
				System.out.println(outerMasterListOfMastersSERTnull == null);
				System.out.println(outerMasterListOfMastersSERTnull.size()
						+ " outerMasterListOfMastersSERTnull.size() doiexu83457");

					// Not testing
				DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();

				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}

				OuterIsNull: if (outerMasterListOfMastersSER == null) {
					JOptionPane.showMessageDialog(null, "No exams have been created");
					break OuterIsNull;
				} else {
					// Size should always be 10 which changes all below
					System.out.println(outerMasterListOfMastersSER.size()
							+ " outerMasterListOfMastersSER.size() should always be 10xdkfi-70rj");
					System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER xdkfi-;pofklflfg");
					
					int count = 0;
					String ExQStr = "";
					Integer sizeSerList;

					for (int j = 0; j < outerMasterListOfMastersSER.size(); j++) {
						if (outerMasterListOfMastersSER.get(j) != null) {
							System.out.println("1AXZ");
							masterOfCreatedExamsDisplayed = outerMasterListOfMastersSER.get(j);
							
							System.out.println(masterOfCreatedExamsDisplayed.size()
									+ " masterOfCreatedExamsDisplayed.size() hfnvf65 SHOULD ALWAYS JUST BE 1");
							System.out.println(masterOfCreatedExamsDisplayed + " masterOfCreatedExamsDisplayed");

							if (masterOfCreatedExamsDisplayed == null || masterOfCreatedExamsDisplayed.get(0) == null
									|| masterOfCreatedExamsDisplayed.size() == 0) {
								JOptionPane.showMessageDialog(null,
										"An exam might have been created and then deleted. \n"
												+ "To see which exam has been deleted, or which have not and contain \n "
												+ "questions, select the exam and click 'Questions in Selected Exam'");
							} else {
								listOfQuestionsSER = masterOfCreatedExamsDisplayed.get(0);
								System.out.println(listOfQuestionsSER + " listOfQuestionsSER kopdi98c4w");
								System.out.println(listOfQuestionsSER.size() + " listOfQuestionsSER.size() kopd8c4w");
								sizeSerList = listOfQuestionsSER.size();
								ExQStr += "Exam " + (j + 1) + " has " + sizeSerList + " available question[s]\n";
								count++;
								System.out.println(listOfQuestionsSER + " listOfQuestionsSER djhet56");
							}
						}
					}

					textPanePreview.setText("NUMBER OF EXAM STRUCTURES CREATED and " + "QUESTIONS\n\n"
							+ "Number of Exams Created: \n\n" + ExQStr);

				}
			}
		});
		btnNumberExQs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNumberExQs.setBounds(498, 34, 275, 21);
		panel.add(btnNumberExQs);

		JButton btnNewButton_13 = new JButton("Delete An Exam");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Deleting an exam means setting the master index to null, but
				// not actually removing it so as to maintain consistent indexes.
				

				
				Integer countingGradedRows = 0;

				var displayExamsAndQuestions = new DisplayExamsAndQuestions();

				try {
					outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp "
						+ "from DISPLAY before delete bn996xxx4jg");

				NoSpecExamsDelete: NoExamsToDelete: if (selectedExamIndex == null) {
					JOptionPane.showMessageDialog(null, "To delete an exam structure you must first select an exam \n "
							+ "from the 'All Available Exams' list.");

					
				} else if (outerMasterListOfMastersSER_Disp == null) {
					JOptionPane.showMessageDialog(null, "There are no exams to delete.");
					break NoExamsToDelete;
					
				} else {
					System.out.println("oct94-k crie9853");
					var dbUtility = new dbUtility();
					var builderDBTesterUtility = new BuilderDBTesterUtility();
					try {
						System.out.println("kori94834638");

						dbUtility.updateExamToNull1stTbl(selectedExamIndex);
						Integer countingRows = dbUtility.getRowCount();
						textFieldRowCount.setText(countingRows.toString());

						dbUtility.updateExamToNull2ndTbl(selectedExamIndex);
						Integer countingRows2 = builderDBTesterUtility.getRowCount();
						textFieldProfNumRows.setText(countingRows2.toString());
						System.out.println(",xoeir9347");
					} catch (ClassNotFoundException | IOException | SQLException e1) {
						e1.printStackTrace();
					}
					try {
						System.out.println("xejw46");

						dbUtility.updateExamToNull3rdTbl(selectedExamIndex);
						countingGradedRows = builderDBTesterUtility.getGradedRowCount();
						textFieldGradedNumRows.setText(countingGradedRows.toString());
					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					
					try {
						System.out.println("jomdeur94854");

						dbUtility.updateExamToNull4thTbl(selectedExamIndex);
						textFieldStudentNumberRows
								.setText(builderDBTesterUtility.getRowCountStudentsFinalGraded().toString());
					} catch (IOException | SQLException | ClassNotFoundException e1) {
						e1.printStackTrace();
					}


					try {
						outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
						System.out.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp "
								+ "from DISPLAY before delete bn996xxx4jg");
						outerMasterListOfMastersSER_Disp.set(selectedExamIndex, null);
						System.out.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp "
								+ "from DISPLAY with an empty null added kddiot04c85");

						
						displayExamsAndQuestions.setUpdateOuterDisplay(outerMasterListOfMastersSER_Disp);
						outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
						// Now test it
						System.out.println(
								outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp koemopi4o54ti");
						System.out.println("Here oipipcmetoir864");

					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}

					
					var insertInitialOuterNestedPlaceholders = new InsertInitialOuterNestedPlaceholders();
					try {
						outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
								.getOuterMasterListOfMastersSER();
						System.out.println(
								outerMasterListOfMastersSER + " outerMasterListOfMastersSER before delete exam ubj896");
						outerMasterListOfMastersSER.set(selectedExamIndex, null);

						// I don't even use 3 of these arguments but only selectedExamIndex and
						// outerMasterListOfMastersSER
						// so might have to remove masterListOfQuestionsSER and
						// numberOfAdditionalQuestions
						insertInitialOuterNestedPlaceholders.setUpdateOuterMasterListofMastersSER(selectedExamIndex,
								masterListOfQuestionsSER, outerMasterListOfMastersSER, numberOfAdditionalQuestions);
						

						// Test it
						outerMasterListOfMastersSER = insertInitialOuterNestedPlaceholders
								.getOuterMasterListOfMastersSER();
						System.out.println(outerMasterListOfMastersSER
								+ " outerMasterListOfMastersSER AFTER delete exam kxoei9045");
						outerMasterListOfMastersSER.set(selectedExamIndex, null);

					} catch (ClassNotFoundException | IOException | SQLException e1) {
						e1.printStackTrace();
					}

					

					JOptionPane.showMessageDialog(null, "The exam has been deleted, (supposedly)");
					System.out.println(listOfQuestionsSER + " listOfQuestionsSER bcvre45 ");
					System.out.println(
							outerMasterListOfMastersSER + " outerMasterListOfMastersSER after delete exam 132dsf");

					try {
						outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
						System.out.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER "
								+ "from DISPLAY after delete bnch6yyyx4jg");
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
					// Let's see if any of these boolean lists were deleted
					var outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
					try {
						outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
						System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs kjiwhr45");
						outerMasterCorrectAnswers.examWasDeleted(selectedExamIndex);
						outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
						System.out.println(
								outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs moe8t50w4u54cm");
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
					System.out.println(outerMasterCorrectAnswersProfs + " outerMasterCorrectAnswersProfs kfoe85945409");

				}

			}
		});
		btnNewButton_13.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_13.setBounds(142, 33, 150, 21);
		panel.add(btnNewButton_13);

		JButton btnNewButton_9 = new JButton("Remove Empty Q");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Formerly the Re-Create an Exam button, now discontinued.
				// Just create an exam again as before.
				JOptionPane.showMessageDialog(null, "Disconnected");
				// DANGER DANGER. DO YOU KNOW WHAT YOU ARE DOING?
				/*
				DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					System.out.println(selectedExamIndex + " selectedExamIndex joepciu509464");
					displayExamsAndQuestions.deleteEmptyQuestion(selectedIndex, selectedExamIndex);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				*/
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_9.setBounds(295, 32, 200, 23);
		panel.add(btnNewButton_9);

		JButton btnExamLabels = new JButton("Exam Labels");
		btnExamLabels.setBounds(774, 33, 117, 21);
		panel.add(btnExamLabels);
		btnExamLabels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var loadInitExamLabsJList = new LoadInitExamLabsJList();
				JPanel setExLabelsPanel = new JPanel();
				setExLabelsPanel.setLayout(new GridLayout(10, 3, 10, 10));

				setExLabelsPanel.add(new JLabel("Exam 1 ----> "));
				JTextField jTextField1 = new JTextField(20);
				jTextField1.setText(examLabelsJlist1.getExam1Str());
				setExLabelsPanel.add(jTextField1);

				setExLabelsPanel.add(new JLabel("Exam 2 ----> " + ""));
				JTextField jTextField2 = new JTextField(20);
				jTextField2.setText(examLabelsJlist1.getExam2Str());
				setExLabelsPanel.add(jTextField2);

				setExLabelsPanel.add(new JLabel("Exam 3 ----> "));
				JTextField jTextField3 = new JTextField(20);
				jTextField3.setText(examLabelsJlist1.getExam3Str());
				setExLabelsPanel.add(jTextField3);

				setExLabelsPanel.add(new JLabel("Exam 4 ----> "));
				JTextField jTextField4 = new JTextField(20);
				jTextField4.setText(examLabelsJlist1.getExam4Str());
				setExLabelsPanel.add(jTextField4);

				setExLabelsPanel.add(new JLabel("Exam 5 ----> "));
				JTextField jTextField5 = new JTextField(20);
				jTextField5.setText(examLabelsJlist1.getExam5Str());
				setExLabelsPanel.add(jTextField5);

				setExLabelsPanel.add(new JLabel("Exam 6 ----> "));
				JTextField jTextField6 = new JTextField(20);
				jTextField6.setText(examLabelsJlist1.getExam6Str());
				setExLabelsPanel.add(jTextField6);

				setExLabelsPanel.add(new JLabel("Exam 7 ----> "));
				JTextField jTextField7 = new JTextField(20);
				jTextField7.setText(examLabelsJlist1.getExam7Str());
				setExLabelsPanel.add(jTextField7);

				setExLabelsPanel.add(new JLabel("Exam 8 ----> "));
				JTextField jTextField8 = new JTextField(20);
				jTextField8.setText(examLabelsJlist1.getExam8Str());
				setExLabelsPanel.add(jTextField8);

				setExLabelsPanel.add(new JLabel("Exam 9 ----> "));
				JTextField jTextField9 = new JTextField(20);
				jTextField9.setText(examLabelsJlist1.getExam9Str());
				setExLabelsPanel.add(jTextField9);

				setExLabelsPanel.add(new JLabel("Exam 10 ---> "));
				JTextField jTextField10 = new JTextField(20);
				jTextField10.setText(examLabelsJlist1.getExam10Str());
				setExLabelsPanel.add(jTextField10);

				getContentPane().add(setExLabelsPanel);
				setVisible(true);

				int applyOption = JOptionPane.showConfirmDialog(null, setExLabelsPanel,
						"Make your changes and click 'Yes' to apply, or 'No' to exit", JOptionPane.YES_NO_OPTION);

				String exam1LabelStr = "";
				String exam2LabelStr = "";
				String exam3LabelStr = "";
				String exam4LabelStr = "";
				String exam5LabelStr = "";
				String exam6LabelStr = "";
				String exam7LabelStr = "";
				String exam8LabelStr = "";
				String exam9LabelStr = "";
				String exam10LabelStr = "";

				if (applyOption == 0) {
					exam1LabelStr = jTextField1.getText();
					exam2LabelStr = jTextField2.getText();
					exam3LabelStr = jTextField3.getText();
					exam4LabelStr = jTextField4.getText();
					exam5LabelStr = jTextField5.getText();
					exam6LabelStr = jTextField6.getText();
					exam7LabelStr = jTextField7.getText();
					exam8LabelStr = jTextField8.getText();
					exam9LabelStr = jTextField9.getText();
					exam10LabelStr = jTextField10.getText();

					try {
						examLabelsJlist1.setExam1Str(exam1LabelStr);
						jTextField1.setText(examLabelsJlist1.getExam1Str());

						examLabelsJlist1.setExam2Str(exam2LabelStr);
						jTextField2.setText(examLabelsJlist1.getExam2Str());

						examLabelsJlist1.setExam3Str(exam3LabelStr);
						jTextField3.setText(examLabelsJlist1.getExam3Str());

						examLabelsJlist1.setExam4Str(exam4LabelStr);
						jTextField4.setText(examLabelsJlist1.getExam4Str());

						examLabelsJlist1.setExam5Str(exam5LabelStr);
						jTextField5.setText(examLabelsJlist1.getExam5Str());

						examLabelsJlist1.setExam6Str(exam6LabelStr);
						jTextField6.setText(examLabelsJlist1.getExam6Str());

						examLabelsJlist1.setExam7Str(exam7LabelStr);
						jTextField7.setText(examLabelsJlist1.getExam7Str());

						examLabelsJlist1.setExam8Str(exam8LabelStr);
						jTextField8.setText(examLabelsJlist1.getExam8Str());

						examLabelsJlist1.setExam9Str(exam9LabelStr);
						jTextField9.setText(examLabelsJlist1.getExam9Str());

						examLabelsJlist1.setExam10Str(exam10LabelStr);
						jTextField10.setText(examLabelsJlist1.getExam10Str());

					} catch (IOException | SQLException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
					Integer counter = null;
					try {
						counter = loadInitExamLabsJList.rowCountExamsLab();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textFieldExamLabelsCount.setText(counter.toString());
					exam1 = examLabelsJlist1.getExam1Str();
					String[] exams = new String[] { exam1 };
					try {
						JOptionPane.showMessageDialog(null,
								"To see your changes to the exam labels you must restart the \n"
										+ "application until I figure out how to code this properly\n");
						System.exit(0);
						// Okay, so this simply runs main() again after System.exit(0);?
						try {
							BrainBuilder3.main(exams);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
					} catch (BackingStoreException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnExamLabels.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnQinExams = new JButton("Questions In Selected Exam");
		btnQinExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER jduryu");
				if (selectedExamIndex == null) {
					JOptionPane.showMessageDialog(null, "You must first select an exam");
				} else if (outerMasterListOfMastersSER == null) {
					System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER jgj67");
					JOptionPane.showMessageDialog(null, "No exams have been created");
				} else if (outerMasterListOfMastersSER.size() < selectedExamIndex + 1) {
					System.out.println(
							outerMasterListOfMastersSER.size() + " outerMasterListOfMastersSER.size() yeyeye6464532");
					JOptionPane.showMessageDialog(null, "Exam as not been created");
				} else {
					var showQuestionsPerExam = new ShowQuestionsPerExam();

					// ###########################################

					
					try {
						anonStr = showQuestionsPerExam.showExamQuestionsFromOuter(selectedExamIndex,
								outerMasterListOfMastersSER);
						textPanePreview.setText(anonStr);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
					System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER kkkkhhh2");
				}
			}
		});
		btnQinExams.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnQinExams.setBounds(498, 11, 275, 21);
		panel.add(btnQinExams);
		
		JButton resetTipsButton = new JButton("Reset Tips");
		// Add the action listener using a lambda expression (Replaces actionPerformed)
		resetTipsButton.addActionListener(e -> {
		    try {
		        // Clear the exact preference key you created
		        prefs.remove(PREF_KEY_EXAM_INTRO);
		        
		        // Save the changes to disk immediately
		        prefs.flush(); 
		        
		        JOptionPane.showMessageDialog(this, 
		            "Informational messages have been reset and will display again.", 
		            "Success", 
		            JOptionPane.INFORMATION_MESSAGE);
		            
		    } catch (BackingStoreException ex) {
		        ex.printStackTrace();
		    }
		});
		resetTipsButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetTipsButton.setBounds(775, 11, 117, 20);
		panel.add(resetTipsButton);

		JScrollPane scrollQuestion = new JScrollPane();
		scrollQuestion.setBounds(763, 105, 400, 50);
		getContentPane().add(scrollQuestion);

		JTextArea textAreaQuestion = new JTextArea();
		DefaultCaret caretQues = (DefaultCaret) textAreaQuestion.getCaret();
		caretQues.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaQuestion.setCaretPosition(0);

		textAreaQuestion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaQuestion.setMargin(new Insets(3, 3, 3, 3));
		scrollQuestion.setViewportView(textAreaQuestion);
		textAreaQuestion.setLineWrap(true);
		textAreaQuestion.setWrapStyleWord(true);

		JLabel lblNewLabel = new JLabel("Question:  ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(634, 115, 118, 31);
		getContentPane().add(lblNewLabel);

		JLabel lblAnswerA = new JLabel("Answer A:  ");
		lblAnswerA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerA.setBounds(674, 167, 78, 25);
		getContentPane().add(lblAnswerA);

		JLabel lblAnswerB = new JLabel("Correct A:  ");
		lblAnswerB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerB.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerB.setBounds(690, 210, 65, 25);
		getContentPane().add(lblAnswerB);

		JLabel lblAnswerC = new JLabel("Answer B:  ");
		lblAnswerC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerC.setBounds(634, 246, 118, 31);
		getContentPane().add(lblAnswerC);

		JLabel lblAnswerD = new JLabel("Correct B:  ");
		lblAnswerD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerD.setBounds(690, 295, 65, 25);
		getContentPane().add(lblAnswerD);

		JLabel lblAnswerE = new JLabel("Answer C:  ");
		lblAnswerE.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerE.setBounds(634, 330, 118, 31);
		getContentPane().add(lblAnswerE);

		textFieldExamTitle = new JTextField();
		textFieldExamTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldExamTitle.setBounds(762, 76, 166, 23);
		textFieldExamTitle.setMargin(new Insets(3, 3, 3, 3));
		getContentPane().add(textFieldExamTitle);
		textFieldExamTitle.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("[required]**Exam Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(602, 77, 150, 20);
		getContentPane().add(lblNewLabel_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(34, 100, 250, 130);
		getContentPane().add(scrollPane_2);

		JScrollPane scrollAnsA = new JScrollPane();
		scrollAnsA.setBounds(763, 160, 400, 38);
		scrollAnsA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollAnsA);

		JTextArea textAreaAnsA = new JTextArea();
		DefaultCaret caretAns1 = (DefaultCaret) textAreaAnsA.getCaret();
		caretAns1.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaAnsA.setCaretPosition(0);
		textAreaAnsA.setLocation(766, 0);
		textAreaAnsA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaAnsA.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		scrollAnsA.setViewportView(textAreaAnsA);

		textAreaAnsA.setLineWrap(true);
		textAreaAnsA.setWrapStyleWord(true);

		JScrollPane scrollCorrectA = new JScrollPane();
		scrollCorrectA.setBounds(763, 203, 400, 38);
		scrollCorrectA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollCorrectA);

		JTextArea textAreaCorrectA = new JTextArea();
		DefaultCaret caretCorAns1 = (DefaultCaret) textAreaCorrectA.getCaret();
		caretCorAns1.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaCorrectA.setCaretPosition(0);

		textAreaCorrectA.setLocation(766, 0);
		textAreaCorrectA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaCorrectA.setMargin(new Insets(3, 3, 3, 3));
		textAreaCorrectA.setWrapStyleWord(true);
		textAreaCorrectA.setLineWrap(true);
		scrollCorrectA.setViewportView(textAreaCorrectA);

		JScrollPane scrollAnsB = new JScrollPane();
		scrollAnsB.setBounds(763, 246, 400, 38);
		scrollAnsB.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollAnsB);

		JTextArea textAreaAnsB = new JTextArea();
		DefaultCaret caretAns2 = (DefaultCaret) textAreaAnsB.getCaret();
		caretAns2.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaAnsB.setCaretPosition(0);

		textAreaAnsB.setLocation(766, 0);
		textAreaAnsB.setWrapStyleWord(true);
		textAreaAnsB.setLineWrap(true);
		textAreaAnsB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaAnsB.setMargin(new Insets(3, 3, 3, 3));
		scrollAnsB.setViewportView(textAreaAnsB);

		JScrollPane scrollCorrectB = new JScrollPane();
		scrollCorrectB.setBounds(763, 288, 400, 38);
		scrollCorrectB.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollCorrectB);

		JTextArea textAreaCorrectB = new JTextArea();
		DefaultCaret caretCorAns2 = (DefaultCaret) textAreaCorrectB.getCaret();
		caretCorAns2.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaCorrectB.setCaretPosition(0);

		textAreaCorrectB.setLocation(766, 0);
		textAreaCorrectB.setWrapStyleWord(true);
		textAreaCorrectB.setLineWrap(true);
		textAreaCorrectB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaCorrectB.setMargin(new Insets(3, 3, 3, 3));
		scrollCorrectB.setViewportView(textAreaCorrectB);

		JScrollPane scrollAnsC = new JScrollPane();
		scrollAnsC.setBounds(763, 330, 400, 38);
		scrollAnsC.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollAnsC);

		JTextArea textAreaAnsC = new JTextArea();
		DefaultCaret caretAns3 = (DefaultCaret) textAreaAnsC.getCaret();
		caretAns3.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaAnsC.setCaretPosition(0);

		textAreaAnsC.setLocation(766, 0);
		scrollAnsC.setViewportView(textAreaAnsC);
		textAreaAnsC.setWrapStyleWord(true);
		textAreaAnsC.setLineWrap(true);
		textAreaAnsC.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollCorrectC = new JScrollPane();
		scrollCorrectC.setBounds(763, 372, 400, 38);
		scrollCorrectC.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollCorrectC);

		JTextArea textAreaCorrectC = new JTextArea();
		DefaultCaret caretCorAns3 = (DefaultCaret) textAreaCorrectC.getCaret();
		caretCorAns3.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaCorrectC.setCaretPosition(0);

		textAreaCorrectC.setLocation(766, 0);
		scrollCorrectC.setViewportView(textAreaCorrectC);
		textAreaCorrectC.setWrapStyleWord(true);
		textAreaCorrectC.setLineWrap(true);
		textAreaCorrectC.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollAnsD = new JScrollPane();
		scrollAnsD.setBounds(763, 415, 400, 38);
		scrollAnsD.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollAnsD);

		JTextArea textAreaAnsD = new JTextArea();
		DefaultCaret caretAns4 = (DefaultCaret) textAreaAnsD.getCaret();
		caretAns4.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaAnsD.setCaretPosition(0);

		textAreaAnsD.setLocation(766, 0);
		scrollAnsD.setViewportView(textAreaAnsD);
		textAreaAnsD.setWrapStyleWord(true);
		textAreaAnsD.setLineWrap(true);
		textAreaAnsD.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollCorrectD = new JScrollPane();
		scrollCorrectD.setBounds(763, 457, 400, 38);
		scrollCorrectD.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollCorrectD);

		JTextArea textAreaCorrectD = new JTextArea();
		DefaultCaret caretCorAns4 = (DefaultCaret) textAreaCorrectD.getCaret();
		caretCorAns4.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaCorrectD.setCaretPosition(0);

		textAreaCorrectD.setLocation(766, 0);
		scrollCorrectD.setViewportView(textAreaCorrectD);
		textAreaCorrectD.setWrapStyleWord(true);
		textAreaCorrectD.setLineWrap(true);
		textAreaCorrectD.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JScrollPane scrollAnsE = new JScrollPane();
		scrollAnsE.setBounds(763, 499, 400, 38);
		scrollAnsE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollAnsE);

		JTextArea textAreaAnsE = new JTextArea();
		DefaultCaret caretAns5 = (DefaultCaret) textAreaAnsE.getCaret();
		caretAns5.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaAnsE.setCaretPosition(0);

		textAreaAnsE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaAnsE.setLocation(766, 0);
		textAreaAnsE.setWrapStyleWord(true);
		textAreaAnsE.setLineWrap(true);
		scrollAnsE.setViewportView(textAreaAnsE);

		JScrollPane scrollCorrectE = new JScrollPane();
		scrollCorrectE.setBounds(763, 541, 400, 38);
		scrollCorrectE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollCorrectE);

		JTextArea textAreaCorrectE = new JTextArea();
		textAreaCorrectE.setWrapStyleWord(true);
		textAreaCorrectE.setLineWrap(true);
		DefaultCaret caretCorAns5 = (DefaultCaret) textAreaCorrectE.getCaret();
		caretCorAns5.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaCorrectE.setCaretPosition(0);

		textAreaCorrectE.setLocation(766, 0);
		textAreaCorrectE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollCorrectE.setViewportView(textAreaCorrectE);

		JLabel lblAnsE = new JLabel("Answer E: ");
		lblAnsE.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAnsE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnsE.setBounds(674, 502, 78, 20);
		getContentPane().add(lblAnsE);

		JLabel lblCorrectE = new JLabel("Correct E: ");
		lblCorrectE.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrectE.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCorrectE.setBounds(690, 548, 65, 25);
		getContentPane().add(lblCorrectE);

		JScrollPane scrollExplain = new JScrollPane();
		scrollExplain.setBounds(763, 582, 400, 90);
		getContentPane().add(scrollExplain);

		JTextArea textAreaExplain = new JTextArea();
		DefaultCaret caretExplanation = (DefaultCaret) textAreaExplain.getCaret();
		caretExplanation.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
		textAreaExplain.setCaretPosition(0);

		textAreaExplain.setLocation(766, 0);
		textAreaExplain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollExplain.setViewportView(textAreaExplain);
		textAreaExplain.setWrapStyleWord(true);
		textAreaExplain.setLineWrap(true);

		JRadioButton rdbtnCorrectA = new JRadioButton("");
		rdbtnCorrectA.setActionCommand("Option1");
		rdbtnCorrectA.setBounds(660, 212, 25, 21);
		rdbtnCorrectA.addActionListener(selectionSaver);
		getContentPane().add(rdbtnCorrectA);

		JRadioButton rdbtnCorrectB = new JRadioButton("");
		rdbtnCorrectB.setActionCommand("Option2");
		rdbtnCorrectB.setBounds(660, 297, 25, 21);
		rdbtnCorrectB.addActionListener(selectionSaver);
		getContentPane().add(rdbtnCorrectB);

		JRadioButton rdbtnCorrectC = new JRadioButton("");
		rdbtnCorrectC.setActionCommand("Option3");
		rdbtnCorrectC.setBounds(660, 381, 25, 21);
		rdbtnCorrectC.addActionListener(selectionSaver);
		getContentPane().add(rdbtnCorrectC);

		JRadioButton rdbtnCorrectD = new JRadioButton("");
		rdbtnCorrectD.setActionCommand("Option4");
		rdbtnCorrectD.setBounds(660, 466, 25, 21);
		rdbtnCorrectD.addActionListener(selectionSaver);
		getContentPane().add(rdbtnCorrectD);

		JRadioButton rdbtnCorrectE = new JRadioButton("");
		rdbtnCorrectE.setActionCommand("Option5");
		rdbtnCorrectE.setBounds(662, 550, 21, 21);
		rdbtnCorrectE.addActionListener(selectionSaver);
		getContentPane().add(rdbtnCorrectE);

		builderGroup.add(rdbtnCorrectA);
		builderGroup.add(rdbtnCorrectB);
		builderGroup.add(rdbtnCorrectC);
		builderGroup.add(rdbtnCorrectD);
		builderGroup.add(rdbtnCorrectE);

		// The first JList re Exam numbers, ie. Exam 1
		JList<String> list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list.setBounds(10, 5, 224, 398);
		list.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		var loadInitExamLabsJList = new LoadInitExamLabsJList();
		try {
			rowCountExamLabsInt = loadInitExamLabsJList.rowCountExamsLab();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (rowCountExamLabsInt == null || rowCountExamLabsInt < 1) {
			try {
				loadInitExamLabsJList.loadJListExamLabs();
				examLabelsJlist1 = loadInitExamLabsJList.getExamLabelsJlist1();
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
			exam1 = examLabelsJlist1.getExam1Str();
			exam2 = examLabelsJlist1.getExam2Str();
			exam3 = examLabelsJlist1.getExam3Str();
			exam4 = examLabelsJlist1.getExam4Str();
			exam5 = examLabelsJlist1.getExam5Str();
			exam6 = examLabelsJlist1.getExam6Str();
			exam7 = examLabelsJlist1.getExam7Str();
			exam8 = examLabelsJlist1.getExam8Str();
			exam9 = examLabelsJlist1.getExam9Str();
			exam10 = examLabelsJlist1.getExam10Str();
		} else {
			examLabelsJlist1 = loadInitExamLabsJList.getExamLabelsJlist1();
			exam1 = examLabelsJlist1.getExam1Str();
			exam2 = examLabelsJlist1.getExam2Str();
			exam3 = examLabelsJlist1.getExam3Str();
			exam4 = examLabelsJlist1.getExam4Str();
			exam5 = examLabelsJlist1.getExam5Str();
			exam6 = examLabelsJlist1.getExam6Str();
			exam7 = examLabelsJlist1.getExam7Str();
			exam8 = examLabelsJlist1.getExam8Str();
			exam9 = examLabelsJlist1.getExam9Str();
			exam10 = examLabelsJlist1.getExam10Str();
		}
		DefaultListModel DLM = new DefaultListModel();
		DLM.addElement(exam1);
		DLM.addElement(exam2);
		DLM.addElement(exam3);
		DLM.addElement(exam4);
		DLM.addElement(exam5);
		DLM.addElement(exam6);
		DLM.addElement(exam7);
		DLM.addElement(exam8);
		DLM.addElement(exam9);
		DLM.addElement(exam10);
		list.setModel(DLM);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				DefaultListModel DLM2 = new DefaultListModel();
				if (!e.getValueIsAdjusting()) {
					if (list.getSelectedIndex() != -1) {
						selectedValue = (String) list.getSelectedValue();
						selectedExamIndex = list.getSelectedIndex();
						selectedIndexExamString = selectedExamIndex.toString();
						selectedExamNumber = selectedValue;
						selectedIndexExamNumber = (int) list.getSelectedIndex(); // should map to primary key
						int examNumber = selectedExamIndex + 1;
						Integer selectedExamIndexInteger = examNumber;
						textFieldExamNumber.setText(selectedExamIndexInteger.toString());
						builderGroup.clearSelection();

						for (int i = 1; i < 10; i++) {
							if (selectedExamIndex == (i - 1)) {
								DLM2.addElement("Exam " + i + " Question 1");
								DLM2.addElement("Exam " + i + " Question 2");
								DLM2.addElement("Exam " + i + " Question 3");
								DLM2.addElement("Exam " + i + " Question 4");
								DLM2.addElement("Exam " + i + " Question 5");
								DLM2.addElement("Exam " + i + " Question 6");
								DLM2.addElement("Exam " + i + " Question 7");
								DLM2.addElement("Exam " + i + " Question 8");
								DLM2.addElement("Exam " + i + " Question 9");
								DLM2.addElement("Exam " + i + " Question 10");
								list_1.setModel(DLM2);

								textFieldExamTitle.setText("");
								textAreaQuestion.setText("");
								textAreaAnsA.setText("");
								textAreaCorrectA.setText("");
								textAreaAnsB.setText("");
								textAreaCorrectB.setText("");
								textAreaAnsC.setText("");
								textAreaCorrectC.setText("");
								textAreaAnsD.setText("");
								textAreaCorrectD.setText("");
								textAreaExplain.setText("");
								textFieldCreator.setText("");
								textFieldTopic.setText("");
								textPanePreview.setText("");
								break;
							}
						}
					}
				}
			}
		});
		scrollPane_2.setViewportView(list);

		JLabel lblCorrectC = new JLabel("Correct C:  ");
		lblCorrectC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorrectC.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrectC.setBounds(690, 379, 65, 25);
		getContentPane().add(lblCorrectC);

		JLabel lblAnswerD_1 = new JLabel("Answer D:  ");
		lblAnswerD_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswerD_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnswerD_1.setBounds(634, 419, 118, 31);
		getContentPane().add(lblAnswerD_1);

		JLabel lblCorrectD = new JLabel("Correct D:  ");
		lblCorrectD.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorrectD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCorrectD.setBounds(690, 464, 65, 25);
		getContentPane().add(lblCorrectD);

		JLabel lblNewLabel_2 = new JLabel("Explanation:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(663, 608, 89, 23);
		getContentPane().add(lblNewLabel_2);

		textPanePreview = new JTextPane();
		textPanePreview.setCaretPosition(0);
		textPanePreview.setFont(new Font("Tahoma", Font.BOLD, 14));
		textPanePreview.setBounds(10, 5, 224, 398);
		textPanePreview.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane_11 = new JScrollPane(textPanePreview);
		scrollPane_11.setBounds(34, 250, 545, 330);
		getContentPane().add(scrollPane_11);
		scrollPane_11.setViewportView(textPanePreview);

		JButton btnCreateQuestion = new JButton("Create Exam Question");
		btnCreateQuestion.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		btnCreateQuestion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				QuestionFactory questionFactory = new QuestionFactory();

				MustUpdate: if (selectedIndex == null) {
					JOptionPane.showMessageDialog(null, "After selecting an exam remember to select " + "a question.",
							null, JOptionPane.INFORMATION_MESSAGE);
				} else {

					if ("".equals(textFieldExamTitle.getText())) {
						JOptionPane.showMessageDialog(null, "A question's TITLE is required", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
						try {
							outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
							System.out.println(
									outerMasterListOfMastersSER + " outerMasterListOfMastersSER 8fj4iicmfirut");

						} catch (ClassNotFoundException | SQLException | IOException e1) {
							e1.printStackTrace();
						}
						innerMasterListOfMastersSER = outerMasterListOfMastersSER.get(selectedExamIndex);

						System.out.println(innerMasterListOfMastersSER + " innerMasterListOfMastersSER nnb76");
						listOfQuestionsSER = innerMasterListOfMastersSER.get(0);
						System.out.println("23B");

						if (listOfQuestionsSER.get(selectedIndex) != null) {
							JOptionPane.showMessageDialog(null,
									"To update a question, please use the " + "'Update Question' button.\n"
											+ "Use 'Create Exam Question' when creating a question for the first "
											+ "time.",
									null, JOptionPane.INFORMATION_MESSAGE);
							System.out.println("23D");
							break MustUpdate;

						} else {
							String[] questionArray = new String[20]; // must increase to 21 for 5th answer
							questionArray[0] = selectedValueQ;
							questionArray[1] = textFieldExamTitle.getText();
							questionArray[2] = textAreaQuestion.getText();
							questionArray[3] = textAreaAnsA.getText();
							questionArray[4] = textAreaCorrectA.getText();
							questionArray[5] = textAreaAnsB.getText();
							questionArray[6] = textAreaCorrectB.getText();
							questionArray[7] = textAreaAnsC.getText();
							questionArray[8] = textAreaCorrectC.getText();
							questionArray[9] = textAreaAnsD.getText();
							questionArray[10] = textAreaCorrectD.getText();
							questionArray[11] = textAreaAnsE.getText();
							questionArray[12] = textAreaCorrectE.getText();
							questionArray[13] = textAreaExplain.getText();
							textFieldCreatedTime.setText(LocalTime.now().toString());
							textFieldUpdatedTime.setText("");
							questionArray[14] = textFieldCreatedTime.getText();
							questionArray[15] = textFieldCreator.getText();
							questionArray[16] = "2222";
							questionArray[17] = textFieldExamNumber.getText();
							questionArray[18] = textFieldQuestionNumber.getText();
							questionArray[19] = textFieldTopic.getText();

							Integer selected = 0;
							Integer indexOfSelected = 0; // Nonsense. It's used extensively below
							ArrayList<Boolean> profsRadioButtonSelections = new ArrayList<Boolean>();
							for (int i = 0; i < 5; i++) {
								profsRadioButtonSelections.add(false);
							}

							if (rdbtnCorrectA.isSelected()) {
								selected++;
								profsRadioButtonSelections.set(0, true);
								indexOfSelected = 0;
							} else {
								profsRadioButtonSelections.set(0, false);
							}

							if (rdbtnCorrectB.isSelected()) {
								selected++;
								profsRadioButtonSelections.set(1, true);
								indexOfSelected = 1;
							} else {
								profsRadioButtonSelections.set(1, false);
							}

							if (rdbtnCorrectC.isSelected()) {
								selected++;
								profsRadioButtonSelections.set(2, true);
								indexOfSelected = 2;
							} else {
								profsRadioButtonSelections.set(2, false);
							}
							if (rdbtnCorrectD.isSelected()) {
								selected++;
								profsRadioButtonSelections.set(3, true);
								indexOfSelected = 3;
							} else {
								profsRadioButtonSelections.set(3, false);
							}
							if (rdbtnCorrectE.isSelected()) {
								selected++;
								profsRadioButtonSelections.set(4, true);
								indexOfSelected = 4;
							} else {
								profsRadioButtonSelections.set(4, false);
							}
							// Make certain an answer was selected.
							int count = 0;
							for (int i = 0; i < 5; i++) {
								if (profsRadioButtonSelections.get(i) == false) {
									count++;
								}
							}
							if (count == 5) {
								JOptionPane.showMessageDialog(null, "Remember to select the correct answer", null,
										JOptionPane.INFORMATION_MESSAGE);
							} else {

								if ((textFieldCreator.getText() == null) || (textFieldExamTitle.getText() == null)) {
									JOptionPane.showMessageDialog(null, "Required.", null,
											JOptionPane.INFORMATION_MESSAGE);
								} else {
									try {
										questionFactory.setQuestionArray(questionArray, selectedIndex, selectedValueQ,
												selectedExamIndex, profsRadioButtonSelections,
												previouslySelectedCommand);
									} catch (IOException e1) {
										e1.printStackTrace();
									} catch (ClassNotFoundException e1) {
										e1.printStackTrace();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									prevQTitle = textFieldExamTitle.getText();
									prevQTopic = textFieldTopic.getText();
									prevQuestion = textAreaQuestion.getText();
									prevAns1 = textAreaAnsA.getText();
									prevCorrectAns1 = textAreaCorrectA.getText();
									prevAns2 = textAreaAnsB.getText();
									prevCorrectAns2 = textAreaCorrectB.getText();
									prevAns3 = textAreaAnsC.getText();
									prevCorrectAns3 = textAreaCorrectC.getText();
									prevAns4 = textAreaAnsD.getText();
									prevCorrectAns4 = textAreaCorrectD.getText();
									prevAns5 = textAreaAnsE.getText();
									prevCorrectAns5 = textAreaCorrectE.getText();
									prevExplanation = textAreaExplain.getText();

									// Another if-else for the TrueFalse option
									if (prevAns1.equals("True") || prevAns2.equals("False")) {
										System.out.println("ko8549");
										textPanePreview.setText("Title: " + prevQTitle + "\nTopic: " + prevQTopic
												+ "\n\n" + "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1
												+ "\n\n" + "" + prevCorrectAns1 + "\n" + "\nB) " + prevAns2 + "\n\n"
												+ "" + prevCorrectAns2 + "\n\n" + "Explanation: " + prevExplanation
												+ "\n");
										// Need to add caret code etc. here?
									} else {
										textPanePreview.setText("Title: " + prevQTitle + "\n" + "Topic: " + prevQTopic
												+ "\n\nQuestion: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n"
												+ "" + prevCorrectAns1 + "\n\n" + "B) " + prevAns2 + "\n\n" + ""
												+ prevCorrectAns2 + "\n\n" + "C) " + prevAns3 + "\n\n" + ""
												+ prevCorrectAns3 + "\n\n" + "D) " + prevAns4 + "\n\n" + ""
												+ prevCorrectAns4 + "\n\n" + "E) " + prevAns5 + "\n\n" + ""
												+ prevCorrectAns5 + "\n\n" + prevExplanation);
									}

									textFieldExamTitle.setText("");
									textAreaQuestion.setText("");
									textAreaAnsA.setText("");
									textAreaCorrectA.setText("");
									textAreaAnsB.setText("");
									textAreaCorrectB.setText("");
									textAreaAnsC.setText("");
									textAreaCorrectC.setText("");
									textAreaAnsD.setText("");
									textAreaCorrectD.setText("");
									textAreaAnsE.setText("");
									textAreaCorrectE.setText("");
									textAreaExplain.setText("");
									textFieldCreatedTime.setText("");
									textFieldUpdatedTime.setText("");
									textFieldCreator.setText(textFieldCreator.getText());
									textFieldExamNumber.setText("");
									textFieldQuestionNumber.setText("");
									textFieldTopic.setText("");
									builderGroup.clearSelection();
								}
							}
						}
					}
				}
			}
		});
		btnCreateQuestion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreateQuestion.setBounds(769, 674, 178, 31);
		getContentPane().add(btnCreateQuestion);

		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(327, 100, 250, 130);
		getContentPane().add(scrollPane_10);

		list_1 = new JList(); // This is the second JList re Questions
		list_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		list_1.setBounds(10, 5, 224, 398);
		list_1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_1.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listOfQuestionsSERIsFull: if (!e.getValueIsAdjusting()) {
					if (list_1.getSelectedIndex() != -1) {
						textPanePreview.setText("");
						selectedIndex = list_1.getSelectedIndex();
						questionNumber = selectedIndex + 1;
						String selectedValue = (String) list_1.getSelectedValue();// don't believe it
						var loadInitialMasterList = new LoadInitialMasterList();
						try {
							listOfQuestionsSER = loadInitialMasterList.deSerializeListOfQuestionsSER(selectedExamIndex);
						} catch (ClassNotFoundException | IOException | SQLException e1) {
							e1.printStackTrace();
						}
						if (listOfQuestionsSER == null) {
							JOptionPane.showMessageDialog(null, "This exam has not been created");
							break listOfQuestionsSERIsFull;
						}
						builderGroup.clearSelection();
						if ((selectedIndex + 1) > listOfQuestionsSER.size()) {
							JOptionPane.showMessageDialog(null,
									"This question has not been created. \n"
											+ "Click 'Add Additional Questions to this Exam' to add \n"
											+ "an additonal question");
						} else {
							for (int i = 0; i < 10; i++) {
								if (selectedExamIndex == i) {
									for (int k = 0; k < 10; k++) {
										if (selectedIndex == k && listOfQuestionsSER.get(selectedIndex) == null) {
											textFieldExamTitle.setText("");
											textAreaQuestion.setText("");
											textAreaAnsA.setText("");
											textAreaCorrectA.setText("");
											textAreaAnsB.setText("");
											textAreaCorrectB.setText("");
											textAreaAnsC.setText("");
											textAreaCorrectC.setText("");
											textAreaAnsD.setText("");
											textAreaCorrectD.setText("");
											textAreaAnsE.setText("");
											textAreaCorrectE.setText("");
											textAreaExplain.setText("Explain further");
											// Have creator's name appear each time from first question anon object
											if (listOfQuestionsSER.get(0) != null) {
												textFieldCreator.setText(listOfQuestionsSER.get(0).getCreator());
											} else {
												textFieldCreator.setText("");
											}
											textFieldExamNumber.setText(((Integer) (i + 1)).toString());
											textFieldQuestionNumber.setText(((Integer) (questionNumber)).toString());
											textFieldTopic.setText("");
										}
									}
								}
							}

							if ((selectedIndex < 10) && listOfQuestionsSER.get(selectedIndex) != null) {
								for (int ib = 0; ib < 10; ib++) {
									if (selectedExamIndex == ib) {
										for (int kb = 0; kb < 10; kb++) {
											Firstlabel: if ((selectedExamIndex == ib) && (selectedIndex == kb)
													&& (listOfQuestionsSER.get(kb) != null)) {
												textFieldExamTitle.setText(listOfQuestionsSER.get(kb).getTitle());
												textAreaQuestion.setText(listOfQuestionsSER.get(kb).getQuestion());
												textAreaAnsA.setText(listOfQuestionsSER.get(kb).getAnswer1());
												textAreaCorrectA
														.setText(listOfQuestionsSER.get(kb).getCorrectAnswer1());
												textAreaAnsB.setText(listOfQuestionsSER.get(kb).getAnswer2());
												textAreaCorrectB
														.setText(listOfQuestionsSER.get(kb).getCorrectAnswer2());
												textAreaAnsC.setText(listOfQuestionsSER.get(kb).getAnswer3());
												textAreaCorrectC
														.setText(listOfQuestionsSER.get(kb).getCorrectAnswer3());
												textAreaAnsD.setText(listOfQuestionsSER.get(kb).getAnswer4());
												textAreaCorrectD
														.setText(listOfQuestionsSER.get(kb).getCorrectAnswer4());
												textAreaAnsE.setText(listOfQuestionsSER.get(kb).getAnswer5());
												textAreaCorrectE
														.setText(listOfQuestionsSER.get(kb).getCorrectAnswer5());
												textAreaExplain.setText(listOfQuestionsSER.get(kb).getExplanation());
												textFieldCreatedTime.setText(listOfQuestionsSER.get(kb).getCreated());
												textFieldUpdatedTime
														.setText(listOfQuestionsSER.get(kb).getlastUpdate());
												textFieldCreator.setText(listOfQuestionsSER.get(kb).getCreator());
												textFieldExamNumber
														.setText(listOfQuestionsSER.get(kb).getExamNumber().toString());
												questionNumberFromObject = listOfQuestionsSER.get(kb)
														.getQuestionNumber();
												textFieldQuestionNumber.setText(questionNumberFromObject.toString());
												textFieldTopic.setText(listOfQuestionsSER.get(kb).getQuestionTopic());

												previouslySelectedCommand = listOfQuestionsSER.get(kb)
														.getSelectedCommand();
												System.out.println(
														previouslySelectedCommand + " previouslySelectedCommand jfh65");
												restoreSelection();
												break Firstlabel;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		});
		scrollPane_10.setViewportView(list_1);
		JButton btnPreviewQ = new JButton("Preview Correct Answers");
		btnPreviewQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedIndex == null) {
					JOptionPane.showMessageDialog(null, "You must select a question to preview the correct answers.",
							null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					var displayExamsAndQuestions = new DisplayExamsAndQuestions();
					try {
						outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
					} catch (ClassNotFoundException | SQLException | IOException e1) {
					}
					System.out.println(outerMasterListOfMastersSER + "outerMasterListOfMastersSER jdhyet56");
					System.out.println(
							outerMasterListOfMastersSER.size() + " outerMasterListOfMastersSER.size() mbncj76");
					innerMasterListOfMastersSER = outerMasterListOfMastersSER.get(selectedExamIndex);
					System.out.println(innerMasterListOfMastersSER + " innerMasterListOfMastersSER asdf234");
					System.out
							.println(innerMasterListOfMastersSER.size() + " innerMasterListOfMastersSER.size() plkm98");

					listOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex).get(0);

					System.out.println(listOfQuestionsSER + "listOfQuestionsSER adx234");

					QuestionIsNull: if (listOfQuestionsSER.get(selectedIndex) == null) {
						JOptionPane.showMessageDialog(null,
								"This question is empty. You must create the\n "
										+ "question first if you wish to preview it.",
								null, JOptionPane.INFORMATION_MESSAGE);
						break QuestionIsNull;
					} else {
						prevQTitle = listOfQuestionsSER.get(selectedIndex).getTitle();
						prevQTopic = listOfQuestionsSER.get(selectedIndex).getQuestionTopic();
						prevQuestion = listOfQuestionsSER.get(selectedIndex).getQuestion();
						prevAns1 = listOfQuestionsSER.get(selectedIndex).getAnswer1();
						prevCorrectAns1 = listOfQuestionsSER.get(selectedIndex).getCorrectAnswer1();

						prevAns2 = listOfQuestionsSER.get(selectedIndex).getAnswer2();
						prevCorrectAns2 = listOfQuestionsSER.get(selectedIndex).getCorrectAnswer2();
						prevAns3 = listOfQuestionsSER.get(selectedIndex).getAnswer3();
						prevCorrectAns3 = listOfQuestionsSER.get(selectedIndex).getCorrectAnswer3();
						prevAns4 = listOfQuestionsSER.get(selectedIndex).getAnswer4();
						prevCorrectAns4 = listOfQuestionsSER.get(selectedIndex).getCorrectAnswer4();
						prevAns5 = listOfQuestionsSER.get(selectedIndex).getAnswer5();
						prevCorrectAns5 = listOfQuestionsSER.get(selectedIndex).getCorrectAnswer5();
						prevExplanation = listOfQuestionsSER.get(selectedIndex).getExplanation();

						String titlePrev = "\n      " + prevQTitle;
						String topicPrev = prevQTopic;
						String questionPrev = "\n          " + prevQuestion + "\n\n";
						String ans1Prev = " A) " + prevAns1;
						String ans2Prev = "  B) " + prevAns2;
						String ans3Prev = "C)" + prevAns3;
						String ans4Prev = "D)    " + prevAns4;
						String ans5Prev = "E) " + prevAns5;

						// These aren't used now because I had to disconnect the radiobuttons and carets
						// below, for now ... maybe? Bit confused still
						int lengthToCorrectAnsA = (titlePrev.length()) + topicPrev.length() + questionPrev.length()
								+ ans1Prev.length();
						int lengthToCorrectAnsB = lengthToCorrectAnsA + prevCorrectAns1.length() + ans2Prev.length();
						int lengthToCorrectAnsC = lengthToCorrectAnsB + prevCorrectAns2.length() + ans3Prev.length();
						int lengthToCorrectAnsD = lengthToCorrectAnsC + prevCorrectAns3.length() + ans4Prev.length();
						int lengthToCorrectAnsE = lengthToCorrectAnsD + prevCorrectAns4.length() + ans5Prev.length();

						// Do an if-else to choose a True/False option
						if (prevAns1.equals("True") || prevAns2.equals("False")) {
							System.out.println("ko8549");
							textPanePreview
									.setText("Title: " + prevQTitle + "\nTopic: " + topicPrev + "\n" + "Question: "
											+ prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + "" + prevCorrectAns1
											+ "\n" + "\nB) " + prevAns2 + "\n\n" + "" + prevCorrectAns2 + "\n\n");

							// Need to add caret code etc. here
							JRadioButton radButtonA = new JRadioButton();
							radButtonA.setBackground(Color.white);
							radButtonA.setAlignmentY(.70F);
							radButtonA.setPreferredSize(new Dimension(15, 15));

							JRadioButton radButtonB = new JRadioButton();
							radButtonB.setBackground(Color.white);
							radButtonB.setAlignmentY(.70F);
							radButtonB.setPreferredSize(new Dimension(15, 15));

							textPanePreview.setCaretPosition(lengthToCorrectAnsA + 9);
							textPanePreview.insertComponent(radButtonA);

							textPanePreview.setCaretPosition((lengthToCorrectAnsB + 12));
							textPanePreview.insertComponent(radButtonB);
							textPanePreview.setCaretPosition(0);

							for (int i = 0; i < listOfQuestionsSER.size(); i++) {
								// How is this dead code? Because of the break?
								// Figure it out.
								if (previouslySelectedCommand.equals("Option1")) {
									radButtonA.setSelected(true);
								} else if (previouslySelectedCommand.equals("Option2")) {
									radButtonB.setSelected(true);
								}
								break;
							}
						} else {
							textPanePreview.setText("Title: " + prevQTitle + "\nTopic: " + topicPrev + "\n\n"
									+ "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + ""
									+ prevCorrectAns1 + "\n" + "\nB) " + prevAns2 + "\n\n" + "" + prevCorrectAns2 + "\n"
									+ "\nC) " + prevAns3 + "\n\n" + "" + prevCorrectAns3 + "\n" + "\nD) " + prevAns4
									+ "\n\n" + "" + prevCorrectAns4 + "\n" + "\nE) " + prevAns5 + "\n\n" + ""
									+ prevCorrectAns5 + "\n\n" + "Explanation: " + prevExplanation + "\n");

							JRadioButton radButtonA = new JRadioButton();
							radButtonA.setBackground(Color.white);
							radButtonA.setAlignmentY(.70F);
							radButtonA.setPreferredSize(new Dimension(15, 15));

							JRadioButton radButtonB = new JRadioButton();
							radButtonB.setBackground(Color.white);
							radButtonB.setAlignmentY(.70F);
							radButtonB.setPreferredSize(new Dimension(15, 15));

							JRadioButton radButtonC = new JRadioButton();
							radButtonC.setBackground(Color.white);
							radButtonC.setAlignmentY(.70F);
							radButtonC.setPreferredSize(new Dimension(15, 15));

							JRadioButton radButtonD = new JRadioButton();
							radButtonD.setBackground(Color.white);
							radButtonD.setAlignmentY(.70F);
							radButtonD.setPreferredSize(new Dimension(15, 15));

							JRadioButton radButtonE = new JRadioButton();
							radButtonE.setBackground(Color.white);
							radButtonE.setAlignmentY(.70F);
							radButtonE.setPreferredSize(new Dimension(15, 15));

							textPanePreview.setCaretPosition(lengthToCorrectAnsA + 10);
							textPanePreview.insertComponent(radButtonA);

							textPanePreview.setCaretPosition((lengthToCorrectAnsB + 13));
							textPanePreview.insertComponent(radButtonB);

							textPanePreview.setCaretPosition((lengthToCorrectAnsC + 19));
							textPanePreview.insertComponent(radButtonC);

							textPanePreview.setCaretPosition((lengthToCorrectAnsD + 21)); //
							textPanePreview.insertComponent(radButtonD);

							textPanePreview.setCaretPosition((lengthToCorrectAnsE + 26)); //
							textPanePreview.insertComponent(radButtonE);

							// move curly down to here
							textPanePreview.setCaretPosition(0);
							for (int i = 0; i < listOfQuestionsSER.size(); i++) {
								// How is this dead code? Because of the break?
								// Figure it out.
								if (previouslySelectedCommand.equals("Option1")) {
									radButtonA.setSelected(true);
								} else if (previouslySelectedCommand.equals("Option2")) {
									radButtonB.setSelected(true);
								} else if (previouslySelectedCommand.equals("Option3")) {
									radButtonC.setSelected(true);
								} else if (previouslySelectedCommand.equals("Option4")) {
									radButtonD.setSelected(true);
								} else if (previouslySelectedCommand.equals("Option5")) {
									radButtonE.setSelected(true);
								}
								break;
							}
						}
					}
				}
			}
		});

		btnPreviewQ.setHorizontalAlignment(SwingConstants.LEFT);
		btnPreviewQ.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPreviewQ.setBounds(376, 580, 203, 31);
		getContentPane().add(btnPreviewQ);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPanePreview.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClear.setBounds(34, 580, 86, 31);
		getContentPane().add(btnClear);

		JButton btnNewButton = new JButton("^ Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldExamTitle.setText("");
				textAreaQuestion.setText("");
				textAreaAnsA.setText("");
				textAreaCorrectA.setText("");
				textAreaAnsB.setText("");
				textAreaCorrectB.setText("");
				textAreaAnsC.setText("");
				textAreaCorrectC.setText("");
				textAreaAnsD.setText("");
				textAreaCorrectD.setText("");
				textAreaAnsE.setText("");
				textAreaCorrectE.setText("");

				textAreaExplain.setText("");
				textFieldCreatedTime.setText("");
				textFieldUpdatedTime.setText("");
				textFieldCreator.setText("");
				textFieldExamNumber.setText("");
				textFieldQuestionNumber.setText("");
				textFieldTopic.setText("");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(1077, 674, 85, 31);
		getContentPane().add(btnNewButton);

		JButton btnaPreviewAnswers = new JButton("Preview Answers");
		btnaPreviewAnswers.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (selectedIndex == null) {
					JOptionPane.showMessageDialog(null, "You must select a question to preview the possible answers.",
							null, JOptionPane.INFORMATION_MESSAGE);
				} else {
					var displayExamsAndQuestions = new DisplayExamsAndQuestions();
					try {
						outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// I don't think I'm even using this innerMasterListOfMastersSER
						innerMasterListOfMastersSER = outerMasterListOfMastersSER.get(selectedExamIndex);
					}
					listOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex).get(0);
					QuestionIsNull2: if (listOfQuestionsSER.get(selectedIndex) == null) {
						JOptionPane.showMessageDialog(null,
								"This question is empty. You must create the\n "
										+ "question first if you wish to preview it.",
								null, JOptionPane.INFORMATION_MESSAGE);
						break QuestionIsNull2;
					} else {
						prevQTitle = listOfQuestionsSER.get(selectedIndex).getTitle();
						prevQTopic = listOfQuestionsSER.get(selectedIndex).getQuestionTopic();
						prevQuestion = listOfQuestionsSER.get(selectedIndex).getQuestion();
						prevAns1 = listOfQuestionsSER.get(selectedIndex).getAnswer1();
						prevAns2 = listOfQuestionsSER.get(selectedIndex).getAnswer2();
						prevAns3 = listOfQuestionsSER.get(selectedIndex).getAnswer3();
						prevAns4 = listOfQuestionsSER.get(selectedIndex).getAnswer4();
						prevAns5 = listOfQuestionsSER.get(selectedIndex).getAnswer5();

						// if/else for trueFalse
						if (prevAns1.equals("True") || prevAns2.equals("False")) {
							System.out.println("ko8547779");
							textPanePreview.setText("Title: " + prevQTitle + " \nTopic: " + prevQTopic + "\n\n"
									+ "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + "B) "
									+ prevAns2 + "\n\n");
							textPanePreview.setCaretPosition(0);
						} else {
							textPanePreview.setText("Title: " + prevQTitle + " \nTopic: " + prevQTopic + "\n\n"
									+ "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + "B) "
									+ prevAns2 + "\n\n" + "C) " + prevAns3 + "\n\n" + "D) " + prevAns4 + "\n\n" + "E) "
									+ prevAns5 + "\n\n");
							textPanePreview.setCaretPosition(0);
						}
					}
				}
			}
		});
		btnaPreviewAnswers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnaPreviewAnswers.setBounds(214, 580, 153, 31);
		getContentPane().add(btnaPreviewAnswers);

		JButton btnNewButton_2 = new JButton(" Print");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					textPanePreview.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(130, 580, 75, 31);
		getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel_3 = new JLabel("All Available Exams");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(76, 76, 166, 20);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Individual Exams With Questions");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(327, 76, 250, 20);
		getContentPane().add(lblNewLabel_4);

		JButton btnUpdate = new JButton("Update Question");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
				try {
					outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers.getOuterMasterCorrectAns();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs.get(selectedExamIndex);
				System.out.println(masterListOfProfsCorrectAnswers + " masterListOfProfsCorrectAnswers xjshfl3027dj");
				// Test if title is empty
				if ("".equals(textFieldExamTitle.getText())) {
					JOptionPane.showMessageDialog(null, "A question's TITLE is required", null,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					// Test if question is null because you can't update a null.
					var displayExamsAndQuestions = new DisplayExamsAndQuestions();
					try {
						outerMasterListOfMastersSER = displayExamsAndQuestions.getOuterExamsDisplayed();

						// Some of these printlns have to go, but later. Handy for troubleshooting for
						// now
						System.out.println("odirecri90 indexoutofbounds 7-31");
						System.out.println(outerMasterListOfMastersSER + " outerMasterListOfMastersSER ldslkfko98");
						System.out.println(
								outerMasterListOfMastersSER.size() + " outerMasterListOfMastersSER.size() 74747yryry");

						System.out.println(outerMasterListOfMastersSER.get(selectedExamIndex));
						System.out.println("k dofoe76");
						System.out.println(outerMasterListOfMastersSER.get(selectedExamIndex));
						masterListOfQuestionsSER = outerMasterListOfMastersSER.get(selectedExamIndex);
						System.out.println();
						System.out.println("space A");

						System.out.println(masterListOfQuestionsSER + " masterListOfQuestionsSER orioe904");
						System.out.println(masterListOfQuestionsSER.size() + " masterListOfQuestionsSER.size() kxw743");

						listOfQuestionsSER = masterListOfQuestionsSER.get(0);
						System.out.println(listOfQuestionsSER.get(selectedIndex));
						System.out.println("kdxoe88e5945");

						System.out.println(listOfQuestionsSER.get(0).getTitle());
						System.out.println(listOfQuestionsSER.get(0).getTopic());
						System.out.println(listOfQuestionsSER.get(0).getAnswer1() + " answer1 iu5894d");
						System.out.println(listOfQuestionsSER.get(0).getAnswer2() + " answer2 j287483");

						System.out.println(listOfQuestionsSER + " listOfQuestionsSER ,lskoqiwp2324");
						System.out.println("0390v54");
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						e1.printStackTrace();
					}
					if (selectedIndex == null) {
						JOptionPane.showMessageDialog(null, "After selecting exam remember to select " + "a question",
								null, JOptionPane.INFORMATION_MESSAGE);
					} else if (listOfQuestionsSER.get(selectedIndex) == null) {
						JOptionPane.showMessageDialog(null,
								"There is no question to update. Create Exam Question instead.", null,
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						System.out.println("klxdiroet87");
						for (int i = 0; i < 10; i++) {
							System.out.println("koeir9e854");
							if (selectedIndex.equals(i)) {
								listOfQuestionsSER.get(i).setTitle(textFieldExamTitle.getText());
								listOfQuestionsSER.get(i).setTopic(textFieldTopic.getText());
								listOfQuestionsSER.get(i).setQuestion(textAreaQuestion.getText());
								listOfQuestionsSER.get(i).setAnswer1(textAreaAnsA.getText());
								listOfQuestionsSER.get(i).setCorrectAnswer1(textAreaCorrectA.getText());
								listOfQuestionsSER.get(i).setAnswer2(textAreaAnsB.getText());
								listOfQuestionsSER.get(i).setCorrectAnswer2(textAreaCorrectB.getText());
								listOfQuestionsSER.get(i).setAnswer3(textAreaAnsC.getText());
								listOfQuestionsSER.get(i).setCorrectAnswer3(textAreaCorrectC.getText());
								listOfQuestionsSER.get(i).setAnswer4(textAreaAnsD.getText());
								listOfQuestionsSER.get(i).setCorrectAnswer4(textAreaCorrectD.getText());
								listOfQuestionsSER.get(i).setAnswer5(textAreaAnsE.getText());
								listOfQuestionsSER.get(i).setCorrectAnswer5(textAreaCorrectE.getText());

								listOfQuestionsSER.get(i).setExplanation(textAreaExplain.getText());
								listOfQuestionsSER.get(i).setCreator(textFieldCreator.getText());
								listOfQuestionsSER.get(i).setIsExamStarted(false);
								listOfQuestionsSER.get(i).setQuestionNumber(questionNumberFromObject);
								listOfQuestionsSER.get(i).setQuestionTopic(textFieldTopic.getText());
								System.out.println("jircu59404");
								System.out.println(listOfQuestionsSER.get(i).getQuestionTopic());

								System.out.println(listOfQuestionsSER.get(selectedIndex).getTitle());
								System.out.println(listOfQuestionsSER.get(selectedIndex).getTopic());
								System.out.println("kdkd8756g");

								System.out.println(listOfQuestionsSER.get(0).getTitle());
								System.out.println(listOfQuestionsSER.get(0).getTopic());
								System.out.println(listOfQuestionsSER.get(0).getAnswer1() + " answer1 mdctrucr9t8");
								System.out.println(listOfQuestionsSER.get(0).getAnswer2() + " answer2 njsdxnucshs");

								listOfQuestionsSER.get(i).setSelectedCommand(previouslySelectedCommand);
								String selectedSelectedCommandAnon = listOfQuestionsSER.get(i).getSelectedCommand();
								System.out.println(previouslySelectedCommand + " previouslySelectedCommand jdezxas");
								System.out
										.println(selectedSelectedCommandAnon + " selectedSelectedCommandAnon jf87urt");
								if (previouslySelectedCommand == selectedSelectedCommandAnon) {
									Integer selected = 0;
									Integer indexOfSelected = 0; // Nonsense. It's used extensively below
									ArrayList<Boolean> profsRadioButtonSelections = new ArrayList<Boolean>();
									for (int j = 0; j < 5; j++) {
										profsRadioButtonSelections.add(false);
									}

									if (rdbtnCorrectA.isSelected()) {
										selected++;
										profsRadioButtonSelections.set(0, true);
										indexOfSelected = 0;
									} else {
										profsRadioButtonSelections.set(0, false);
									}

									if (rdbtnCorrectB.isSelected()) {
										selected++;
										profsRadioButtonSelections.set(1, true);
										indexOfSelected = 1;
									} else {
										profsRadioButtonSelections.set(1, false);
									}

									if (rdbtnCorrectC.isSelected()) {
										selected++;

										profsRadioButtonSelections.set(2, true);
										indexOfSelected = 2;
									} else {
										profsRadioButtonSelections.set(2, false);
									}
									if (rdbtnCorrectD.isSelected()) {
										selected++;
										profsRadioButtonSelections.set(3, true);
										indexOfSelected = 3;
									} else {
										profsRadioButtonSelections.set(3, false);
									}
									if (rdbtnCorrectE.isSelected()) {
										selected++;
										profsRadioButtonSelections.set(4, true);
										indexOfSelected = 4;
									} else {
										profsRadioButtonSelections.set(4, false);
									}

									try {
										outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers
												.getOuterMasterCorrectAns();
									} catch (ClassNotFoundException | SQLException | IOException e1) {
										e1.printStackTrace();
									}
									masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs
											.get(selectedExamIndex);
									System.out.println(outerMasterCorrectAnswersProfs + " 775tsthh");
									masterListOfProfsCorrectAnswers.set(selectedIndex, profsRadioButtonSelections);
									System.out.println(masterListOfProfsCorrectAnswers
											+ " masterListOfProfsCorrectAnswers qead347");
									outerMasterCorrectAnswersProfs.set(selectedExamIndex,
											masterListOfProfsCorrectAnswers);
									try {
										outerMasterCorrectAnswers
												.setUpdateOuterMasterAnswers(outerMasterCorrectAnswersProfs);
										outerMasterCorrectAnswersProfs = outerMasterCorrectAnswers
												.getOuterMasterCorrectAns();
										masterListOfProfsCorrectAnswers = outerMasterCorrectAnswersProfs
												.get(selectedExamIndex);
										System.out.println(masterListOfProfsCorrectAnswers
												+ " masterListOfProfsCorrectAnswers did it update? orkr95j");
									} catch (ClassNotFoundException | SQLException | IOException e1) {
										e1.printStackTrace();
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Houston, we have a problem. The previously selected \n"
													+ "button selections don't match and the \n"
													+ "exam might not be accurate. Notify the guy and fix it.",
											null, JOptionPane.INFORMATION_MESSAGE);
								}

								textFieldUpdatedTime.setText(LocalTime.now().toString());
								textFieldCreatedTime.setText("");
								prevQTitle = textFieldExamTitle.getText();
								prevQTopic = textFieldTopic.getText();
								prevQuestion = textAreaQuestion.getText();
								prevAns1 = textAreaAnsA.getText();
								prevCorrectAns1 = textAreaCorrectA.getText();
								prevAns2 = textAreaAnsB.getText();
								prevCorrectAns2 = textAreaCorrectB.getText();
								prevAns3 = textAreaAnsC.getText();
								prevCorrectAns3 = textAreaCorrectC.getText();
								prevAns4 = textAreaAnsD.getText();
								prevCorrectAns4 = textAreaCorrectD.getText();
								prevAns5 = textAreaAnsE.getText();
								prevCorrectAns5 = textAreaCorrectE.getText();

								prevExplanation = textAreaExplain.getText();

								if (prevAns1.equals("True") || prevAns2.equals("False")) {
									System.out.println("octiuor");
									textPanePreview.setText("Title: " + prevQTitle + "\nTopic: " + prevQTopic + "\n\n"
											+ "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + ""
											+ prevCorrectAns1 + "\n\n" + "B) " + prevAns2 + "\n\n" + ""
											+ prevCorrectAns2 + "\n\n" + prevExplanation);

									// 8-4-26 LEAVE ALL OF THE BELOW FOR NOW!!
									// textPanePreview.setCaretPosition(0);

									// + "Explanation: " + prevExplanation + "\n");
									// Need to add caret code etc. here
									/*
									 * JRadioButton radButtonA = new JRadioButton();
									 * radButtonA.setBackground(Color.white); radButtonA.setAlignmentY(.70F);
									 * radButtonA.setPreferredSize(new Dimension(15, 15));
									 * 
									 * JRadioButton radButtonB = new JRadioButton();
									 * radButtonB.setBackground(Color.white); radButtonB.setAlignmentY(.70F);
									 * radButtonB.setPreferredSize(new Dimension(15, 15));
									 * 
									 * textPanePreview.setCaretPosition(lengthToCorrectAnsA + 9);
									 * textPanePreview.insertComponent(radButtonA);
									 * 
									 * textPanePreview.setCaretPosition((lengthToCorrectAnsB + 12));
									 * textPanePreview.insertComponent(radButtonB);
									 * textPanePreview.setCaretPosition(0);
									 * 
									 * for (int i = 0; i < listOfQuestionsSER.size(); i++) { // How is this dead
									 * code? Because of the break? // Figure it out. if
									 * (previouslySelectedCommand.equals("Option1")) { radButtonA.setSelected(true);
									 * } else if (previouslySelectedCommand.equals("Option2")) {
									 * radButtonB.setSelected(true); } break; }
									 */
								} else {

									textPanePreview.setText("Title: " + prevQTitle + "\nTopic: " + prevQTopic + "\n\n"
											+ "Question: " + prevQuestion + "\n\n" + "A) " + prevAns1 + "\n\n" + ""
											+ prevCorrectAns1 + "\n\n" + "B) " + prevAns2 + "\n\n" + ""
											+ prevCorrectAns2 + "\n\n" + "C) " + prevAns3 + "\n\n" + ""
											+ prevCorrectAns3 + "\n\n" + "D) " + prevAns4 + "\n\n" + ""
											+ prevCorrectAns4 + "\n\n" + "E) " + prevAns5 + "\n\n" + ""
											+ prevCorrectAns5 + "\n\n" + prevExplanation);
									textPanePreview.setCaretPosition(0);
								}
								var updateListOfQuestionsSERtoDB = new InsertlistOfQuestionsSERtoDB();
								try {
									updateListOfQuestionsSERtoDB.updateRows(selectedExamIndex, listOfQuestionsSER);
								} catch (ClassNotFoundException | SQLException | IOException e1) {
									e1.printStackTrace();
								}
								textFieldExamTitle.setText("");
								textAreaQuestion.setText("");
								textAreaAnsA.setText("");
								textAreaCorrectA.setText("");
								textAreaAnsB.setText("");
								textAreaCorrectB.setText("");
								textAreaAnsC.setText("");
								textAreaCorrectC.setText("");
								textAreaAnsD.setText("");
								textAreaCorrectD.setText("");
								textAreaAnsE.setText("");
								textAreaCorrectE.setText("");
								textAreaExplain.setText("");
								textFieldCreatedTime.setText("");
								textFieldUpdatedTime.setText("");
								textFieldCreator.setText("");
								textFieldExamNumber.setText("");
								textFieldQuestionNumber.setText("");
								textFieldTopic.setText("");
								builderGroup.clearSelection();
								var insertlistOfQuestionsSERtoDB = new InsertlistOfQuestionsSERtoDB();
								try {
									insertlistOfQuestionsSERtoDB.updateRows(selectedExamIndex, listOfQuestionsSER);
								} catch (ClassNotFoundException | SQLException | IOException e1) {
									e1.printStackTrace();
								}
								textFieldUpdatedTime.setText(LocalTime.now().toString());
								textFieldCreatedTime.setText("");
								try {
									var updateProfsFinalList = new UpdateProfsFinalList();
									updateProfsFinalList.upDateProfsListInTester(selectedExamIndex, listOfQuestionsSER);
								} catch (ClassNotFoundException | IOException | SQLException e1) {
									e1.printStackTrace();
								}
								break;

							}
						}
					}
				}
			}
		});

		btnUpdate.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnUpdate.setAlignmentY(Component.TOP_ALIGNMENT);
		btnUpdate.setBounds(949, 674, 124, 31);
		getContentPane().add(btnUpdate);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textFieldCreatedTime = new JTextField();
		textFieldCreatedTime.setForeground(new Color(255, 0, 0));
		textFieldCreatedTime.setBounds(769, 710, 178, 31);
		getContentPane().add(textFieldCreatedTime);
		textFieldCreatedTime.setColumns(10);
		textFieldCreatedTime.setFont(getFont());

		JLabel lblNewLabel_5 = new JLabel("Created:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(689, 710, 70, 31);
		getContentPane().add(lblNewLabel_5);

		textFieldUpdatedTime = new JTextField();
		textFieldUpdatedTime.setForeground(Color.RED);
		textFieldUpdatedTime.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldUpdatedTime.setColumns(10);
		textFieldUpdatedTime.setBounds(949, 710, 118, 31);
		getContentPane().add(textFieldUpdatedTime);

		JLabel lblUpdated = new JLabel("<Updated");
		lblUpdated.setHorizontalAlignment(SwingConstants.LEFT);
		lblUpdated.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUpdated.setBounds(1073, 709, 89, 31);
		getContentPane().add(lblUpdated);

		JButton btnNewButton_3 = new JButton("Delete Question");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayExamsAndQuestions displayExamsAndQuestions = new DisplayExamsAndQuestions();
				try {
					outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
				listOfQuestionsSER = outerMasterListOfMastersSER_Disp.get(selectedExamIndex).get(0);
				if (listOfQuestionsSER.get(selectedIndex) == null) {
					JOptionPane.showMessageDialog(null, "There is no such question to delete");
				} else {

					System.out
							.println(outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp pexiurw35o");
					// 8-9But did you set it back into the display table??
					SelectIndNull: if (selectedIndex == null) {
						JOptionPane.showMessageDialog(null, "You must select a question before you can delete it.");
						break SelectIndNull;
					} else {
						var deleteQuestions = new DeleteQuestions();
						try {
							deleteQuestions.deleteQuestion(selectedExamIndex, selectedIndex, listOfQuestionsSER);
							builderGroup.clearSelection();
						} catch (ClassNotFoundException | IOException | SQLException e1) {
							e1.printStackTrace();
						}
						try {
							// No reason for this. Why return it if you are not using it?
							listOfQuestionsSER = deleteQuestions.getUpdatedListOfQuestionsSER(selectedExamIndex);
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							e1.printStackTrace();
						}
						// Test it

						try {
							outerMasterListOfMastersSER_Disp = displayExamsAndQuestions.getOuterExamsDisplayed();
							listOfQuestionsSER = outerMasterListOfMastersSER_Disp.get(selectedExamIndex).get(0);
							if (listOfQuestionsSER.get(selectedIndex) == null) {
								JOptionPane.showMessageDialog(null, "This question is deleted RD2");
							}

							System.out.println(
									outerMasterListOfMastersSER_Disp + " outerMasterListOfMastersSER_Disp pexiurw35o");
						} catch (ClassNotFoundException | SQLException | IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_3.setBounds(950, 748, 120, 31);
		getContentPane().add(btnNewButton_3);

		JButton btnCreateOrigTable = new JButton("CreateOrigTable");
		btnCreateOrigTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateOrigTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createBuilderExamsTable1 = new CreateBuilderExamsTable1();
				try {
					try {
						createBuilderExamsTable1.createTable();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace(); // Keep this for your own local logging
					    JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateOrigTable.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCreateOrigTable.setBounds(38, 634, 153, 21);
		getContentPane().add(btnCreateOrigTable);

		JLabel lblQuestionNumber = new JLabel("Q  #");
		lblQuestionNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuestionNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuestionNumber.setBounds(690, 690, 39, 13);
		getContentPane().add(lblQuestionNumber);

		textFieldQuestionNumber = new JTextField();
		textFieldQuestionNumber.setEditable(false);
		textFieldQuestionNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldQuestionNumber.setBounds(735, 687, 25, 19);
		getContentPane().add(textFieldQuestionNumber);
		textFieldQuestionNumber.setColumns(10);

		JLabel lblExamNumber = new JLabel("E #");
		lblExamNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExamNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExamNumber.setBounds(673, 665, 56, 20);
		getContentPane().add(lblExamNumber);

		textFieldExamNumber = new JTextField();
		textFieldExamNumber.setEditable(false);
		textFieldExamNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldExamNumber.setBounds(735, 665, 25, 19);
		getContentPane().add(textFieldExamNumber);
		textFieldExamNumber.setColumns(10);

		JLabel lblTopic = new JLabel("Topic:");
		lblTopic.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTopic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTopic.setBounds(943, 81, 45, 13);
		getContentPane().add(lblTopic);

		textFieldTopic = new JTextField();
		textFieldTopic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldTopic.setBounds(996, 76, 166, 23);
		textFieldTopic.setMargin(new Insets(3, 3, 3, 3));
		getContentPane().add(textFieldTopic);
		textFieldTopic.setColumns(10);

		textFieldRowCount = new JTextField();
		textFieldRowCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldRowCount.setBounds(444, 636, 25, 19);
		getContentPane().add(textFieldRowCount);
		textFieldRowCount.setColumns(10);

		JButton btnNumberOfRows = new JButton("Initial # Rows");
		btnNumberOfRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnNumberOfRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dataBaseUtility = new dbUtility();
				try {
					Integer countingRows = dataBaseUtility.getRowCount();
					textFieldRowCount.setText(countingRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNumberOfRows.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNumberOfRows.setBounds(335, 634, 106, 21);
		getContentPane().add(btnNumberOfRows);

		JButton btnDeleteRows = new JButton("DeleteOrigRows");
		btnDeleteRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dbUtility = new dbUtility();
				try {
					dbUtility.deleteRows(selectedExamIndex);
					Integer countingRows = dbUtility.getRowCount();
					textFieldRowCount.setText(countingRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteRows.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnDeleteRows.setBounds(472, 634, 142, 21);
		getContentPane().add(btnDeleteRows);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(1077, 748, 85, 31);
		getContentPane().add(cancelButton);

		JButton btnInitialOrigLoad = new JButton("InitialOrigLoad");
		btnInitialOrigLoad.setHorizontalAlignment(SwingConstants.LEFT);
		btnInitialOrigLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Because WindowBuilder is broke I'm using this button
				// to create CreateOuterMasterAnswers_Table1
				CreateOuterMasterAnswers_Table1 createOuterMasterAnswers_Table1 = new CreateOuterMasterAnswers_Table1();
				try {
					try {
						createOuterMasterAnswers_Table1.createTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				/*
				 * // LEAVE THIS var loadInitialMasterList = new LoadInitialMasterList(); try {
				 * loadInitialMasterList.loadMasterList(selectedExamIndex,
				 * initialNumberOfQuestions, selectedValue, listOfQuestionsSER, 0); } catch
				 * (SQLException | IOException | ClassNotFoundException e1) {
				 * e1.printStackTrace(); }
				 */
			}
		});
		btnInitialOrigLoad.setBounds(194, 634, 140, 21);
		getContentPane().add(btnInitialOrigLoad);

		JButton btnSetInitialProfsExam = new JButton("SetInitialProfsExam");
		btnSetInitialProfsExam.setHorizontalAlignment(SwingConstants.LEFT);
		btnSetInitialProfsExam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
				try {
					int rowcount = outerMasterCorrectAnswers.getRowCountOuterAnswers();
					System.out.println(rowcount + " rowcount from OUTER_MASTER_ANSWERS_TABLE_1");
				} catch (ClassNotFoundException | IOException | SQLException e1) {

					e1.printStackTrace();
				}

				// JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * Leave this var insertProfsFinalExamsTesterTable = new
				 * InsertProfsFinalExamsTesterTable(); try {
				 * insertProfsFinalExamsTesterTable.setOriginalProfExams(selectedExamIndex,
				 * initialNumberOfQuestions, selectedValue, listOfQuestionsSER); } catch
				 * (SQLException | IOException e1) { e1.printStackTrace(); } catch
				 * (ClassNotFoundException e1) { e1.printStackTrace(); }
				 */
			}
		});
		btnSetInitialProfsExam.setBounds(194, 654, 140, 21);
		getContentPane().add(btnSetInitialProfsExam);

		JButton btnNewButton_6 = new JButton("CreateProfFinishedTable");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createExTableOrigFinished = new CreateExTableOrigFinished();
				try {
					try {
						createExTableOrigFinished.createTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(38, 654, 153, 21);
		getContentPane().add(btnNewButton_6);

		JButton btnProfNumRows = new JButton("Profs # Rows");
		btnProfNumRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnProfNumRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					Integer countingRows = builderDBTesterUtility.getRowCount();
					textFieldProfNumRows.setText(countingRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnProfNumRows.setBounds(335, 654, 106, 21);
		getContentPane().add(btnProfNumRows);

		textFieldProfNumRows = new JTextField();
		textFieldProfNumRows.setBounds(444, 655, 25, 19);
		getContentPane().add(textFieldProfNumRows);
		textFieldProfNumRows.setColumns(10);

		JButton btnDeleteProfRows = new JButton("DeleteProfRows");
		btnDeleteProfRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteProfRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					builderDBTesterUtility.deleteRows(selectedExamIndex);
					Integer countingRows = builderDBTesterUtility.getRowCount();
					textFieldProfNumRows.setText(countingRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnDeleteProfRows.setBounds(472, 654, 142, 21);
		getContentPane().add(btnDeleteProfRows);

		JButton btnGradedListPrimkey = new JButton("CreateGradedTable");
		btnGradedListPrimkey.setHorizontalAlignment(SwingConstants.LEFT);
		btnGradedListPrimkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createGradedFinalTable_2 = new CreateGradedFinalTable_2();
				try {
					try {
						createGradedFinalTable_2.createFinalGradedTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGradedListPrimkey.setBounds(38, 674, 153, 21);
		getContentPane().add(btnGradedListPrimkey);

		JButton btnSetInitialGradedExams = new JButton("SetInitialGradedExams");
		btnSetInitialGradedExams.setHorizontalAlignment(SwingConstants.LEFT);
		btnSetInitialGradedExams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OuterMasterCorrectAnswers outerMasterCorrectAnswers = new OuterMasterCorrectAnswers();
				try {
					outerMasterCorrectAnswers.deleteRows(selectedExamIndex);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
				// JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * Leave this * var insertInitialGradedExPlaceholders = new
				 * InsertInitialGradedExPlaceholders(); try {
				 * insertInitialGradedExPlaceholders.loadInitialFinalGraded(selectedExamIndex,
				 * initialNumberOfQuestions, selectedValue, listOfQuestionsSER); } catch
				 * (SQLException | IOException e1) { e1.printStackTrace(); }
				 * 
				 */
			}
		});
		btnSetInitialGradedExams.setBounds(194, 673, 140, 21);
		getContentPane().add(btnSetInitialGradedExams);

		JButton btnGradedNumberOfRows = new JButton("Graded # Rows");
		btnGradedNumberOfRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnGradedNumberOfRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					Integer gradedRowcount = builderDBTesterUtility.getGradedRowCount();
					textFieldGradedNumRows.setText(gradedRowcount.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGradedNumberOfRows.setBounds(335, 673, 106, 21);
		getContentPane().add(btnGradedNumberOfRows);

		textFieldGradedNumRows = new JTextField();
		textFieldGradedNumRows.setBounds(444, 675, 25, 19);
		getContentPane().add(textFieldGradedNumRows);
		textFieldGradedNumRows.setColumns(10);

		JButton btnDeleteGradedRows = new JButton("DeleteGradedRows");
		btnDeleteGradedRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteGradedRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					builderDBTesterUtility.deleteGradedRows(selectedExamIndex);
					Integer countingGradedRows = builderDBTesterUtility.getGradedRowCount();
					textFieldGradedNumRows.setText(countingGradedRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteGradedRows.setBounds(472, 674, 142, 21);
		getContentPane().add(btnDeleteGradedRows);

		JButton btnCreateStudentsFinalTable = new JButton("CreateStudentsFinalTable");
		btnCreateStudentsFinalTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateStudentsFinalTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createStudentsGradedExamsTable = new CreateStudentsGradedExamsTable();
				try {
					try {
						createStudentsGradedExamsTable.createFinalGradedTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateStudentsFinalTable.setBounds(38, 694, 153, 21);
		getContentPane().add(btnCreateStudentsFinalTable);

		JButton btnNewButton_4 = new JButton("SetInitialFinalStudent");
		btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEvaluationTable createEvaluationTable = new CreateEvaluationTable();
				try {
					try {
						createEvaluationTable.createTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				// JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * Leave this var insertInitialStudentsFinalGraded = new
				 * InsertInitialStudentsFinalGraded(); try {
				 * insertInitialStudentsFinalGraded.setStudentsFinalGradedExams(
				 * selectedExamIndex, initialNumberOfQuestions, selectedValue,
				 * listOfQuestionsSER); } catch (ClassNotFoundException | SQLException |
				 * IOException e1) { e1.printStackTrace(); }
				 */
			}
		});
		btnNewButton_4.setBounds(194, 693, 140, 21);
		getContentPane().add(btnNewButton_4);

		JButton btnStudentNumberRows = new JButton("Student # Rows");
		btnStudentNumberRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnStudentNumberRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					Integer finalStudentRowcount = builderDBTesterUtility.getRowCountStudentsFinalGraded();
					textFieldStudentNumberRows.setText(finalStudentRowcount.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStudentNumberRows.setBounds(335, 693, 106, 21);
		getContentPane().add(btnStudentNumberRows);

		textFieldStudentNumberRows = new JTextField();
		textFieldStudentNumberRows.setBounds(444, 695, 25, 19);
		getContentPane().add(textFieldStudentNumberRows);
		textFieldStudentNumberRows.setColumns(10);

		JButton btnDeleteFinalStudentRows = new JButton("DeleteFinalStudentRows");
		btnDeleteFinalStudentRows.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteFinalStudentRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					builderDBTesterUtility.deleteStudentsFinalRows(selectedExamIndex);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
				try {
					textFieldStudentNumberRows
							.setText(builderDBTesterUtility.getRowCountStudentsFinalGraded().toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteFinalStudentRows.setBounds(472, 694, 142, 21);
		getContentPane().add(btnDeleteFinalStudentRows);

		JButton btnCreateOuterNestedTable = new JButton("CreateOuterNestedTble");
		btnCreateOuterNestedTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateOuterNestedTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createStudentsOuterNestedTable = new CreateStudentsOuterNestedTable();
				try {
					try {
						createStudentsOuterNestedTable.createOuterNestedTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateOuterNestedTable.setBounds(38, 715, 153, 21);
		getContentPane().add(btnCreateOuterNestedTable);

		JButton btnDeleteOuterNestedTable = new JButton("DeleteOuterMastRows");
		btnDeleteOuterNestedTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnDeleteOuterNestedTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					builderDBTesterUtility.deleteOuterNestedRows(selectedExamIndex);
					textFieldOuterRowCount.setText(builderDBTesterUtility.getRowCountOuterNested().toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteOuterNestedTable.setBounds(472, 715, 142, 21);
		getContentPane().add(btnDeleteOuterNestedTable);

		JButton btnRowCountOuter = new JButton("OuterMasterRows");
		btnRowCountOuter.setHorizontalAlignment(SwingConstants.LEFT);
		btnRowCountOuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var builderDBTesterUtility = new BuilderDBTesterUtility();
				try {
					Integer countingRows = builderDBTesterUtility.getRowCountOuterNested();
					textFieldOuterRowCount.setText(countingRows.toString());
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRowCountOuter.setBounds(335, 714, 106, 21);
		getContentPane().add(btnRowCountOuter);

		textFieldOuterRowCount = new JTextField();
		textFieldOuterRowCount.setBounds(444, 716, 25, 19);
		getContentPane().add(textFieldOuterRowCount);
		textFieldOuterRowCount.setColumns(10);

		JButton btnInitialOuterLoad = new JButton("InitialOuterLoad");
		btnInitialOuterLoad.setHorizontalAlignment(SwingConstants.LEFT);
		btnInitialOuterLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvaluateAnswersLists evaluateAnswersLists = new EvaluateAnswersLists();
				try {
					int rowCount = evaluateAnswersLists.getRowCountEvaluated();
					System.out.println(rowCount + " rowCountEval lofpogir96oy");
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}

				// JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * // Leave this var insertInitialOuterNestedPlaceholders = new
				 * InsertInitialOuterNestedPlaceholders(); try {
				 * insertInitialOuterNestedPlaceholders.loadInitialOuterNested(
				 * selectedExamIndex, initialNumberOfQuestions, selectedValue,
				 * listOfQuestionsSER, masterListOfQuestionsSER); } catch
				 * (ClassNotFoundException | SQLException | IOException e1) {
				 * e1.printStackTrace(); }
				 */
			}
		});
		btnInitialOuterLoad.setBounds(194, 714, 140, 21);
		getContentPane().add(btnInitialOuterLoad);

		txtAdministratorOnlyBelow = new JTextField();
		txtAdministratorOnlyBelow.setText("Administrator Only Below This Line");
		txtAdministratorOnlyBelow.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdministratorOnlyBelow.setBackground(new Color(255, 255, 0));
		txtAdministratorOnlyBelow.setBounds(34, 613, 587, 19);
		getContentPane().add(txtAdministratorOnlyBelow);
		txtAdministratorOnlyBelow.setColumns(10);

		JButton btnCrreateDisplayTable = new JButton("Create Display Table");
		btnCrreateDisplayTable.setHorizontalAlignment(SwingConstants.LEFT);
		btnCrreateDisplayTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createDisplayExamsQuestionsTable = new CreateDisplayExamsQuestionsTable();
				try {
					try {
						createDisplayExamsQuestionsTable.createTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCrreateDisplayTable.setBounds(38, 774, 153, 21);
		getContentPane().add(btnCrreateDisplayTable);

		JButton btnInitialDisplayLoad = new JButton("LoadInitialDisplay");
		btnInitialDisplayLoad.setHorizontalAlignment(SwingConstants.LEFT);

		// DANGER DANGER, DO I EVEN USE THIS BUTTON? SHOULD IT BE REMOVED?
		btnInitialDisplayLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * Leave this for now var loadInitialDisplayQA = new LoadInitialDisplayQA(); try
				 * { loadInitialDisplayQA.loadInitMasterCreatedExamsDisplayed(); } catch
				 * (SQLException | IOException e1) { e1.printStackTrace(); }
				 */
			}
		});
		btnInitialDisplayLoad.setBounds(194, 774, 140, 21);
		getContentPane().add(btnInitialDisplayLoad);

		JButton btnDeleteDisplayEQ = new JButton("DeleteDisplayEQ");
		btnDeleteDisplayEQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dbUtilityRef = new dbUtility();
				try {
					try {
						dbUtilityRef.deleteEQDisplayRow();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					
					try {
						displayRowCountEQ = dbUtilityRef.rowCountEQDisplay();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
					textFieldDisplayEQCount.setText(displayRowCountEQ.toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteDisplayEQ.setBounds(472, 774, 142, 21);
		getContentPane().add(btnDeleteDisplayEQ);

		textFieldDisplayEQCount = new JTextField();
		textFieldDisplayEQCount.setBounds(444, 775, 25, 19);
		getContentPane().add(textFieldDisplayEQCount);
		textFieldDisplayEQCount.setColumns(10);

		JButton btnNewButton_8 = new JButton("DeleteMasterDisplayRow");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dBaseUtility = new dbUtility();
				try {
					dBaseUtility.deleteExamRowsMasterDisplayed(selectedExamIndex);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
				try {
					try {
						textFieldRowcountDisplayTable.setText(dBaseUtility.rowCountEQDisplay().toString());
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_8.setBounds(813, 757, 136, 21);
		getContentPane().add(btnNewButton_8);

		JButton btnNewButton_10 = new JButton("Create JList Labels Table");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createExamLabelsJListTable = new CreateExamLabelsJListTable();
				try {
					try {
						createExamLabelsJListTable.createTable();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_10.setBounds(38, 734, 153, 21);
		getContentPane().add(btnNewButton_10);

		JButton btnNewButton_11 = new JButton("loadInitExLabels");
		btnNewButton_11.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EvaluateAnswersLists evaluateAnswersLists = new EvaluateAnswersLists();
				try {
					evaluateAnswersLists.deleteRowsEval(selectedExamIndex);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}

				// JOptionPane.showMessageDialog(null, "Disconnected");
				/*
				 * Leave this for now var loadInitExamLabsJList = new LoadInitExamLabsJList();
				 * try { loadInitExamLabsJList.loadJListExamLabs(); Integer counter = null; try
				 * { counter = loadInitExamLabsJList.rowCountExamsLab(); } catch (SQLException
				 * e1) { e1.printStackTrace(); }
				 * textFieldExamLabelsCount.setText(counter.toString()); } catch (SQLException |
				 * IOException e1) { e1.printStackTrace(); }
				 */
			}
		});
		btnNewButton_11.setBounds(194, 734, 140, 21);
		getContentPane().add(btnNewButton_11);

		textFieldExamLabelsCount = new JTextField();
		textFieldExamLabelsCount.setBounds(444, 735, 25, 19);
		getContentPane().add(textFieldExamLabelsCount);
		textFieldExamLabelsCount.setColumns(10);

		JButton btnDeleteExamLabRow = new JButton("DeleteTableRowEXLAB");
		btnDeleteExamLabRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var loadInitExamLabsJList = new LoadInitExamLabsJList();
				Integer counter = null;
				try {
					loadInitExamLabsJList.deleteExamsLablsRows();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					counter = loadInitExamLabsJList.rowCountExamsLab();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				textFieldExamLabelsCount.setText(counter.toString());
			}
		});
		btnDeleteExamLabRow.setBounds(472, 734, 142, 21);
		getContentPane().add(btnDeleteExamLabRow);

		JButton btnNewButton_12 = new JButton("NumRowsLbls");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var loadInitExamLabsJList = new LoadInitExamLabsJList();
				Integer counter = null;
				try {
					counter = loadInitExamLabsJList.rowCountExamsLab();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				textFieldExamLabelsCount.setText(counter.toString());
			}
		});
		btnNewButton_12.setBounds(333, 734, 106, 21);
		getContentPane().add(btnNewButton_12);

		textFieldRowcountDisplayTable = new JTextField();
		textFieldRowcountDisplayTable.setBounds(660, 717, 25, 19);
		getContentPane().add(textFieldRowcountDisplayTable);
		textFieldRowcountDisplayTable.setColumns(10);

		JButton btnTables = new JButton("ShowTables");
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				var dbTableUtility = new DbTableUtility();
				try {
					try {
						allTableNames = dbTableUtility.displayAllTables();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
					// Display allTableNames list in the preview pane
					// Loop through allTableNames and insert in String
					String latestTableNames = "Current Database Tables\n";
					for (int kk = 0; kk < allTableNames.size(); kk++) {
						latestTableNames += "\n" + allTableNames.get(kk);
					}
					textPanePreview.setText(latestTableNames);
					JOptionPane.showMessageDialog(null,
							"The tables created and used in the database are listed at the bottom\n"
									+ "of this preview, beginning with BUILDER_EXAMS_LISTS_17");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTables.setBounds(618, 757, 105, 21);
		getContentPane().add(btnTables);

		JButton btnDeleteTable = new JButton("DeleteTable");
		btnDeleteTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// DANGER DANGER!! USE THIS ONLY IF YOU KNOW WHAT YOU ARE DOING.
				/*
				 * DbTableUtility dbTableUtility = new DbTableUtility(); try {
				 * dbTableUtility.deleteRemoveTable(); } catch (SQLException e1) {
				 * e1.printStackTrace(); } JOptionPane.showMessageDialog(null,
				 * "Table deleted, let's hope");
				 */
			}
		});
		btnDeleteTable.setBounds(725, 757, 89, 21);
		getContentPane().add(btnDeleteTable);

		JButton btnNewButton_14 = new JButton("DisplayRowCount");
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var dataBUtility = new dbUtility();
				Integer displayRowCount = 0;
				try {
					try {
						displayRowCount = dataBUtility.rowCountEQDisplay();
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "A database error occurred. Please try again.");
					    e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				textFieldDisplayEQCount.setText(displayRowCount.toString());
			}
		});
		btnNewButton_14.setBounds(333, 774, 106, 21);
		getContentPane().add(btnNewButton_14);

		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 0));
		textField.setBounds(618, 613, 10, 140);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 0));
		textField_1.setBounds(626, 740, 322, 10);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("Create ComboLblTable");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var createComboLabelsTable = new CreateComboLabelsTable();
				try {
					try {
						createComboLabelsTable.createTable();
					} catch (ClassNotFoundException e1) {						
					    JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
					    e1.printStackTrace(); 
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(38, 753, 153, 21);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_7 = new JButton("DeleteComboLbls");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Use the one in Tester instead. Disconnected for now
				/*
				 * Leave this for now var loadInitComboLabelTester = new
				 * LoadInitComboLabelTester(); try { // Wrong method
				 * loadInitComboLabelTester.deleteExamsComboRows();
				 * 
				 * System.out.println(loadInitComboLabelTester.rowCountComboLabels() +
				 * " loadInitComboLabelTester.rowCountComboLabels() hdy98eohjip"); } catch
				 * (SQLException e1) { e1.printStackTrace(); }
				 */
			}
		});

		JTextArea jTextArea = new JTextArea();
		jTextArea.setRows(1);
		jTextArea.setColumns(20);
		jRadioButton = new JRadioButton("R-Two");
		button = new JButton("Reg Two");
		button.setSize(1, 80);

	} // End of constructor

	public void serializeListSER(ArrayList<QuestionSuper> ex1QuestionsSER) throws IOException {
		try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ex1QuestionsSER")))) {
			out.writeObject(ex1QuestionsSER);
		}
	}

	private void restoreSelection() {
		System.out.println("Top of restoreSelection() in Builder");
		if (previouslySelectedCommand != null) {
			for (int i = 0; i < 3; ++i) {
				if (selectedIndex == i) {
					previouslySelectedCommand = listOfQuestionsSER.get(selectedIndex).getSelectedCommand();
				}
			}
			Enumeration<AbstractButton> buttons = builderGroup.getElements();
			while (buttons.hasMoreElements()) {
				AbstractButton button = buttons.nextElement();
				if (previouslySelectedCommand.equals(button.getActionCommand())) {
					button.setSelected(true);
					break;
				} else {
					System.out.println("No previously selected to restore.");
				}
			}
		} else {
			System.out.println(previouslySelectedCommand + " previouslySelectedCommand is  ______ ");
		}
	}
}
