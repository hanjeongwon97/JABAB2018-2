import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class LeftFrame extends JPanel{
	public Left1 left1 = new Left1();
	public Left2 left2 = new Left2();
	public Left3 left3 = new Left3();
	
	public static String selectedItemName;// �ٸ� Ŭ�������� ���õ� ���� �̸��� �ҷ��������� ������ ���ڿ�
	
	private final int l_x_location = 20;  // LeftFrame�гε��� x��ǥ�� ����
	
	
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

/*������ �����ϴ� �޺��ڽ��� �ִ� �г�*/
class Left1 extends JPanel{ 
	private JLabel L_selectLecture=new JLabel("���� ����");
	public static String [] selectSubArray = {"JAVAB","��ǻ�ͱ���","����Ʈ����м�����"};
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

/*�޺��ڽ����� ������ ���õɶ��� ActionListener*/
class SelectSubAL implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//�޺��ڽ��� ���� �������� ���õ� ������ ���̱׷����� ��ġ�� �ʱ�ȭ
		Lecture.ratioA=0;
		Lecture.ratioB=0;
		Lecture.ratioC=0;
		Right2.sumratio = 0.0;
		Right2.math = 0.0;
		Right2.computer = 0.0;
		Right2.dep = 0.0;
		
		//�̺�Ʈ�� �߻��� �޺��ڽ� �˾Ƴ���
		JComboBox<String> actionPerformedCB = (JComboBox<String>)e.getSource();
		
		//���õ� �������� �̸��� ���ڿ��� Ÿ��ĳ��Ʈ. �����̸��� �����ϰ� �д�
		LeftFrame.selectedItemName = (String)actionPerformedCB.getSelectedItem();

		
		//�޺��ڽ��� ���õɶ� �ؽ�Ʈ�ʵ带 ����д�. (������ ���õ� ����� ���� ��µ��� �ʵ���)
		for(int i=0;i<Center2.TF_content.length;i++) {
			for(int j=0;j<Center2.TF_content[i].length;j++) {
				Center2.TF_content[i][j].setText(null);
			}
		}
		
		//���õ� ���� ���� ������ ������ �ؽ�Ʈ�ʵ忡 �������� �ϴ� �޼ҵ� ȣ��
		Lecture.showInfo(LeftFrame.selectedItemName);
		
		
	}
	
}

/*������ �߰��ϰ� , ���� ���ذ� ������� �����ִ� �г�*/
class Left2 extends JPanel{ //��2
	private JLabel L_addLecture=new JLabel("�����߰� : ");
	public JTextField TF_addLecture=new JTextField(8);
	public JButton B_addLecture=new JButton("Ȯ��");
	public static JTextArea TA_showStandard = new JTextArea();
	public static String standardGrade = " [���� ����] "
			+"\n\n<���� ����>"+" \n"
			+ "�� �׸� ���� : 100�� \n"
			+ "�߰� : 30%   �⸻ : 35%\n"
			+ "�⼮ : 10%\n"
			+ "����1:12%   ����2:13%\n\n\n"
			+ "<���� ����> \n"
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
	/*�����߰� ��ư�� ������ ������ �߰��ϴ� ActionListener*/
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

/*�ؽ�Ʈ�ʵ带 �����ְ� ��������/������� �˷��� �� �ִ� ��ư�� �ִ� �г�*/
class Left3 extends JPanel{ //��3
	private JButton B_hideField=new JButton("�����ʵ� ���� / ���̱�");
	private JButton B_showStandard=new JButton("�������� / �����");
	
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
				else Left2.TA_showStandard.setText("[���� ó�� ���α׷�] \n"
						+ "\n<�����> \n"
						+ "1. ���� ComboBox���� ������ ����. \n"
						+ "2. �л����� �� ������ ����/�߰� \n"
						+ "3. ����2 �κп��� EnterŰ �Է� \n"
						+ "4. �а��� ���� ���� CheckBox�� üũ \n\n"
						+ "*�ҷ����� ��ư:�ؽ�Ʈ ���Ͽ��� ����, ���� \n"
						+ "                          �� ������ �ҷ��� \n"
						+ "*�����ϱ� ��ư:TextField���� ����, �߰��� \n"
						+ "                          ������ �ؽ�Ʈ ���Ͽ� ����"
						//+ "\n\n#ComboBox���� �ٸ������� �����Ϸ��� \n"
						//+ "CheckBox�� ��� �����ϴ°��� ����.#  \n\n\n\n"
						+ "\n\n\n�ϼ��� : 2018.12.02."
						+"\n20160816 ��������а� ������"
						+ "\n20160781 ���а� ������ \n"
						); 
			}
			
		});

		add(B_hideField);
		add(B_showStandard);

		setSize(250, 180);
		setBackground(MainFrame.colors[3]);
		
	}
}
