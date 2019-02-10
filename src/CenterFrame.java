import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;



import javax.swing.border.MatteBorder;
import java.util.*;

public class CenterFrame extends JPanel{
	
	private Center1 center1 = new Center1();
	public static Center2 center2 = new Center2();
	private Center3 center3 = new Center3();
	private Center4 center4 = new Center4();
	private Center5 center5 = new Center5();
	
	private final int c_x_location = 4;  //CenterFrame�гε��� x��ǥ�� ����
	
	public CenterFrame() {
		setLayout(null);
		
		center1.setLocation(c_x_location,20 );
		add(center1);
		
		center2.setLocation(-2, 45);
		add(center2);
		
		center3.setLocation(c_x_location,630);
		add(center3);
		
		center4.setLocation(c_x_location, 700);
		add(center4);
		
		center5.setLocation(300, 700);
		add(center5);
		
		setSize(650, 800);
		setBackground(MainFrame.colors[0]);
	}
}

/*�ؽ�Ʈ�ʵ� �׺��� ��Ÿ���� JLabel�� ���Ե� �г�*/
class Center1 extends JPanel{
	private JLabel[] L_item= new JLabel[10];
	
	public Center1() {
		setLayout(new GridLayout(1, 10, 0,0));
		
		L_item[0]=new JLabel(" �а�");
		L_item[1]=new JLabel(" �й�");
		L_item[2]=new JLabel(" �̸�");
		L_item[3]=new JLabel(" �߰�");
		L_item[4]=new JLabel(" �⸻");
		L_item[5]=new JLabel(" �⼮");
		L_item[6]=new JLabel(" ����1");
		L_item[7]=new JLabel(" ����2");
		L_item[8]=new JLabel(" ����");
		L_item[9]=new JLabel(" ����");
		
		for(int j=0; j<L_item.length; j++) {
			L_item[j].setOpaque(true);
			L_item[j].setFont(new Font("����ü", Font.BOLD, 15));
			L_item[j].setBorder(new MatteBorder(0,0,1,1,Color.LIGHT_GRAY));
			L_item[j].setBackground(MainFrame.colors[5]);
			L_item[j].setForeground(Color.black);
			add(L_item[j]);
		}
		L_item[8].setBackground(Color.decode("#6CC9F4"));
		L_item[9].setBackground(Color.decode("#6CC9F4"));
		
		setSize(630,30);
		setBackground(Color.decode("#fbe7e8"));
	}
}

/*�ؽ�Ʈ�ʵ� �г�*/
class Center2 extends JPanel{
	private ArrayList sort=new ArrayList<Double>();
	private JScrollPane SP_scroll=new JScrollPane();
	private JPanel P_panel=new JPanel();
	public static boolean drawg = false; //����, ������ �ο��Ǿ�� ������Ʈ�� �׷����� �׷������� �Ǵ��ϴ� ����
	public static JTextField[][] TF_content=new JTextField[40][10];//�ִ� 40����� ����, �߰� ����
	
	public Center2() {
		
		P_panel.setLayout(null);
		
		for(int i=0; i<TF_content.length; i++) {
			for(int j=0; j<TF_content[i].length; j++) {
				TF_content[i][j]=new JTextField();
				TF_content[i][j].setFont(new Font("����ü", Font.BOLD, 12));
				TF_content[i][j].setSize(63, 30);
				TF_content[i][j].setLocation(63*j, 30*i);
				TF_content[i][j].setBackground(MainFrame.colors[0]);
				P_panel.add(TF_content[i][j]);
			}
			
			totalPrint totalPrint=new totalPrint(); //����Ű�� ������ ����,������ ������ ActioinListener����
			TF_content[i][7].addActionListener(totalPrint);
		}
		//�ؽ�Ʈ�ʵ忡 �ش��ϴ� �гο� ��ũ���� ����
		SP_scroll = new JScrollPane(P_panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_scroll.setPreferredSize(new Dimension(650, 560));
		P_panel.setPreferredSize(new Dimension(650, 1250));

		add(SP_scroll);
		setSize(660,560);
		setVisible(true);
		this.setOpaque(false);
	}
	
}

/* ����2 �κ��� �ؽ�Ʈ�ʵ忡�� ����Ű�� ������ ����,������ ���,��µǴ� ActionListener.
 * Center3�� �ְ���,������,��� ���� ����ؼ� ����ϴ� �̺�Ʈ�� ���ԵǾ��ִ�.*/
class totalPrint implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		JTextField[][] TF_content=Center2.TF_content;
		Center2.drawg = true;
		
		int amount=0;
		double averageScore=0;
		double highestScore=0;
		double lowestScore=0;
		double standardDeviation=0;
		double medianValue=0;
		ArrayList<Double> sort = new ArrayList<>();
				
		for(int i=0; i<TF_content.length; i++) { //������ ����Ͽ� TF_content[i][8]�� ����ϴ� �ݺ���
			if (TF_content[i][7].getText().trim().length() > 0) {
				TF_content[i][8].setText(Double.toString(Math.round((
						Double.parseDouble(TF_content[i][3].getText())*0.3
						+Double.parseDouble(TF_content[i][4].getText())*0.35
						+Double.parseDouble(TF_content[i][5].getText())*0.1
						+Double.parseDouble(TF_content[i][6].getText())*0.12
						+Double.parseDouble(TF_content[i][7].getText())*0.13)*100)/100.0)
						);
				} //���� ���� : �߰�30%, �⸻ 35%, �⼮10%, ����1 12%, ����2 13% 
			else
				break;
			}

		
		Lecture.calGrade(LeftFrame.selectedItemName);//������ �ο��ϰ� TF_content[i][9]�� ����ϴ� �޼ҵ� ȣ��
				
				
		highestScore=Double.parseDouble(TF_content[0][8].getText());
		lowestScore=Double.parseDouble(TF_content[0][8].getText());
		
		//Center3�гο��� ���Ǵ� ������ ����ϴ� �ݺ�����
		for(int i=0; i<TF_content.length; i++) { //�ְ�, ����, ���
			if (TF_content[i][8].getText().trim().length() > 0) {
						
				if(highestScore<Double.parseDouble(TF_content[i][8].getText())) 
					highestScore=Double.parseDouble(TF_content[i][8].getText());
						
						
				if(lowestScore>Double.parseDouble(TF_content[i][8].getText())) 
					lowestScore=Double.parseDouble(TF_content[i][8].getText());
							
						

				sort.add(i, Double.parseDouble(TF_content[i][8].getText()));
						//�߾Ӱ�
						
				averageScore=averageScore+Double.parseDouble(TF_content[i][8].getText());
				amount++;
			}
			else
				break;
		}
		averageScore=Math.round((averageScore/amount)*100)/100.0;
		for(int i=0; i<TF_content.length; i++) { //�л�
			if (TF_content[i][8].getText().trim().length() > 0) 
				standardDeviation=standardDeviation+(Double.parseDouble(TF_content[i][8].getText())-averageScore)*(Double.parseDouble(TF_content[i][8].getText())-averageScore);
			else break;
			}
		standardDeviation=Math.round((Math.sqrt(standardDeviation/amount))*100)/100.0;
		
		Collections.sort(sort); //�������� ����
		medianValue=Math.round(getMedian(sort)*100)/100.0;
		Center3.L_lowestScore.setText("������ : " +lowestScore);
		Center3.L_highestScore.setText("�ְ��� : " +highestScore);
		Center3.L_averageScore.setText("��� : " +averageScore);
		Center3.L_standardDeviation.setText("ǥ������ : "+standardDeviation);
		Center3.L_medianValue.setText("�߾Ӱ� : "+medianValue);
				
		sort.clear();

	}
	
	public double getMedian(ArrayList<Double> sort) {
	    if (sort.size() == 0) return Double.NaN; // �� �迭�� ���� ��ȯ(NaN�� ���ڰ� �ƴ϶�� ��)
	    int mid = sort.size() / 2;            // ��� ������ ���ݰ� ���ϱ�

	    if (sort.size() % 2 == 1)  // ��� ������ Ȧ����
	      return (double) sort.get(mid); // Ȧ�� ������ �迭������ �߰� ��Ҹ� �״�� ��ȯ
	    
	    else 
	      return (sort.get(mid-1) + sort.get(mid)) / 2.0; // ¦�� �� ��Ҵ�, �߰� �� ���� ��� ��ȯ
	  }
}

 class Center3 extends JPanel{  
	public static JLabel L_highestScore=new JLabel("�ְ��� : 0");
	public static JLabel L_lowestScore=new JLabel("������ : 0");
	public static JLabel L_medianValue=new JLabel("�߾Ӱ� : 0");
	public static JLabel L_averageScore=new JLabel("��� : 0");
	public static JLabel L_standardDeviation=new JLabel("ǥ������ : 0");
	
	public Center3() {
		add(L_highestScore);
		add(L_lowestScore);
		add(L_medianValue);
		add(L_averageScore);
		add(L_standardDeviation);
		setSize(630, 50);
		setBackground(MainFrame.colors[3]);
	}
}

class Center4 extends JPanel{
	private JLabel L_fontSize=new JLabel("�۾�ũ��: ");
	private JSlider slider=new JSlider(JSlider.HORIZONTAL, 100, 200, 150);
	
	public Center4() {
		setLayout(null);
		L_fontSize.setLocation(10, 10);
		L_fontSize.setSize(60, 20);

		slider.setLocation(70, 10);
		slider.setSize(200,50);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(25);
		slider.setBackground(MainFrame.colors[1]);
		
		slider.addChangeListener(new ChangeListener() {//�ؽ�Ʈ�ʵ�� Center3�� �۾�ũ�⸦ �ٲٴ� ������

			@Override
			public void stateChanged(ChangeEvent e) {
				double event = slider.getValue();
				int newTextSize = (int)(event*0.1);
				Center3.L_lowestScore.setFont(new Font("����ü", Font.BOLD, newTextSize));
				Center3.L_highestScore.setFont(new Font("����ü", Font.BOLD, newTextSize));
				Center3.L_averageScore.setFont(new Font("����ü", Font.BOLD, newTextSize));
				Center3.L_standardDeviation.setFont(new Font("����ü", Font.BOLD, newTextSize));
				Center3.L_medianValue.setFont(new Font("����ü", Font.BOLD, newTextSize));
				
				for(int i=0; i<Center2.TF_content.length; i++) {
					for(int j=0; j<Center2.TF_content[i].length; j++) {
						Center2.TF_content[i][j].setFont(new Font("����ü", Font.BOLD, newTextSize));
					}
				}
			}
		});

		add(L_fontSize);
		add(slider);	
		setSize(280, 80);
		setBackground(MainFrame.colors[1]);
	}
}

/*������ �ҷ�����, �����ϴ� ��ư�� �ִ� �г�*/
class Center5 extends JPanel{
	private JButton B_loading = new JButton("�ҷ�����");
	private JButton B_saving = new JButton("�����ϱ�");
	
	public Center5() {
		
		B_saving.addActionListener(new ActionListener() { //�����ϱ� ��ư ������
			@Override
			public void actionPerformed(ActionEvent e) {
				//�ؽ�Ʈ�ʵ��� ������ �����ϴ� �޼ҵ�
				Lecture.saveFile(LeftFrame.selectedItemName );
			}
			
		});
		
		B_loading.addActionListener(new ActionListener() { //�ҷ����� ��ư ������
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ư�� ������ ������ �ؽ�Ʈ�ʵ� ��������� ������ �ʱ�ȭ(������ �ҷ��ͼ� �ؽ�Ʈ�ʵ忡 �ҷ��;� �ϹǷ�)
				for(int i=0;i<Center2.TF_content.length;i++) {
					for(int j=0;j<Center2.TF_content[i].length;j++) {
						Center2.TF_content[i][j].setText(null);
					}
				}
				Lecture.showInfo(LeftFrame.selectedItemName);//�ش������� �ҷ����� ��������� �ؽ�Ʈ�ʵ忡 ������ ������.
				
			}
			
		});
			
		
		add(B_loading);
		add(B_saving);
		setSize(340, 80);
		setBackground(MainFrame.colors[1]);
	}
}
