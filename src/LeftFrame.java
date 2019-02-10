import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class LeftFrame extends JPanel{
	public Left1 left1 = new Left1();
	public Left2 left2 = new Left2();
	public Left3 left3 = new Left3();
	
	public static String selectedItemName;// 다른 클래스에서 선택된 파일 이름을 불러오기위해 선언한 문자열
	
	private final int l_x_location = 20;  // LeftFrame패널들의 x좌표의 기준
	
	
	public LeftFrame() {
		setLayout(null);

		left1.setLocation(l_x_location, 20);
		add(left1);
		left2.setLocation(l_x_location,130);
		add(left2);
		left3.setLocation(l_x_location, 600);
		add(left3);
		
		setSize(280,800);
		setBackground(MainFrame.colors[0]);
		setVisible(true);
	}
}

/*과목을 선택하는 콤보박스가 있는 패널*/
class Left1 extends JPanel{ 
	private JLabel L_selectLecture=new JLabel("과목 선택");
	public static String [] selectSubArray = {"JAVAB","컴퓨터구조","소프트웨어분석설계"};
	static JComboBox<String> CB_subCombo=new JComboBox<String>(selectSubArray);
	
	public Left1() { 
		
		add(L_selectLecture);
		add(CB_subCombo);
		
		SelectSubAL myAct = new SelectSubAL();
		CB_subCombo.addActionListener(myAct);
		
		this.setBackground(MainFrame.colors[1]);
		this.setSize(250, 90);
		setVisible(true);
	}
}

/*콤보박스에서 과목을 선택될때의 ActionListener*/
class SelectSubAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//콤보박스의 과목 아이템이 선택될 때마다 파이그래프의 수치를 초기화
		Lecture.ratioA=0;
		Lecture.ratioB=0;
		Lecture.ratioC=0;
		Right2.sumratio = 0.0;
		Right2.math = 0.0;
		Right2.computer = 0.0;
		Right2.dep = 0.0;
		
		//이벤트가 발생한 콤보박스 알아내기
		JComboBox<String> actionPerformedCB = (JComboBox<String>)e.getSource();
		
		//선택된 아이템의 이름을 문자열로 타입캐스트. 파일이름과 동일하게 둔다
		LeftFrame.selectedItemName = (String)actionPerformedCB.getSelectedItem();

		
		//콤보박스가 선택될때 텍스트필드를 비워둔다. (이전에 선택된 과목과 같이 출력되지 않도록)
		for(int i=0;i<Center2.TF_content.length;i++) {
			for(int j=0;j<Center2.TF_content[i].length;j++) {
				Center2.TF_content[i][j].setText(null);
			}
		}
		
		//선택된 과목에 대한 수강생 정보를 텍스트필드에 보여지게 하는 메소드 호출
		Lecture.showInfo(LeftFrame.selectedItemName);
		
		
	}
	
}

/*과목을 추가하고 , 학점 기준과 사용방법을 보여주는 패널*/
class Left2 extends JPanel{ //왼2
	private JLabel L_addLecture=new JLabel("과목추가 : ");
	public JTextField TF_addLecture=new JTextField(8);
	public JButton B_addLecture=new JButton("확인");
	public static JTextArea TA_showStandard = new JTextArea();
	public static String standardGrade = " [학점 기준] "
			+"\n\n<총점 비율>"+" \n"
			+ "각 항목 만점 : 100점 \n"
			+ "중간 : 30%   기말 : 35%\n"
			+ "출석 : 10%\n"
			+ "과제1:12%   과제2:13%\n\n\n"
			+ "<학점 비율> \n"
			+ "A : 30% \n"
			+ "B : 35% \n"
			+ "C : 35% \n";
	public Left2() {
		setLayout(null);
		
		L_addLecture.setLocation(10, 10);
		L_addLecture.setSize(80, 20);
		
		TF_addLecture.setLocation(90, 10);
		TF_addLecture.setSize(70, 20);
		
		B_addLecture.setSize(65, 20);
		B_addLecture.setLocation(170, 10);
		
		AddLecture AddLec = new AddLecture();
		B_addLecture.addActionListener(AddLec);
		
		TA_showStandard.setSize(230,380);
		TA_showStandard.setLocation(10, 50);
		TA_showStandard.setText(standardGrade);
		TA_showStandard.setBackground(Color.decode("#fbe7e8"));
		
		add(L_addLecture);
		add(TF_addLecture);
		add(B_addLecture);
		add(TA_showStandard);
			
		setBackground(MainFrame.colors[1]);
		setSize(250,450);
	}
	/*과목추가 버튼을 누르면 과목을 추가하는 ActionListener*/
	public class AddLecture implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Left1.CB_subCombo.addItem(TF_addLecture.getText());
			
			try {
				
				FileWriter fileWriter = new FileWriter(TF_addLecture.getText()+".txt");
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
	}
}

/*텍스트필드를 숨겨주고 학점기준/사용방법을 알려줄 수 있는 버튼이 있는 패널*/
class Left3 extends JPanel{ //왼3
	private JButton B_hideField=new JButton("수정필드 숨김 / 보이기");
	private JButton B_showStandard=new JButton("학점기준 / 사용방법");
	
	private static boolean showStandard = true;
	private static boolean hideField = true;
	
	public Left3() {
		setLayout(null);
		
		B_hideField.setLocation(40,50);
		B_hideField.setSize(170, 30);
		B_showStandard.setLocation(40,100);
		B_showStandard.setSize(170, 30);
		B_hideField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				hideField = !hideField;
				CenterFrame.center2.setVisible(hideField);
				
			}
			
		});
		
		B_showStandard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showStandard = !showStandard;
				
				if(Left3.showStandard==true) {
					Left2.TA_showStandard.setText(Left2.standardGrade);
					}
				else Left2.TA_showStandard.setText("[성적 처리 프로그램] \n"
						+ "\n<사용방법> \n"
						+ "1. 위의 ComboBox에서 과목을 선택. \n"
						+ "2. 학생정보 및 점수를 수정/추가 \n"
						+ "3. 과제2 부분에서 Enter키 입력 \n"
						+ "4. 학과별 학점 비율 CheckBox를 체크 \n\n"
						+ "*불러오기 버튼:텍스트 파일에서 수정, 저장 \n"
						+ "                          후 파일을 불러옴 \n"
						+ "*저장하기 버튼:TextField에서 수정, 추가한 \n"
						+ "                          내용을 텍스트 파일에 저장"
						//+ "\n\n#ComboBox에서 다른과목을 선택하려면 \n"
						//+ "CheckBox를 모두 해제하는것을 권장.#  \n\n\n\n"
						+ "\n\n\n완성일 : 2018.12.02."
						+"\n20160816 정보통계학과 최정민"
						+ "\n20160781 수학과 한정원 \n"
						); 
			}
			
		});

		add(B_hideField);
		add(B_showStandard);

		setSize(250, 180);
		setBackground(MainFrame.colors[3]);
		
	}
}
