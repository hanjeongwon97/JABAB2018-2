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
	
	private final int c_x_location = 4;  //CenterFrame패널들의 x좌표의 기준
	
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

/*텍스트필드 항복을 나타내는 JLabel이 포함된 패널*/
class Center1 extends JPanel{
	private JLabel[] L_item= new JLabel[10];
	
	public Center1() {
		setLayout(new GridLayout(1, 10, 0,0));
		
		L_item[0]=new JLabel(" 학과");
		L_item[1]=new JLabel(" 학번");
		L_item[2]=new JLabel(" 이름");
		L_item[3]=new JLabel(" 중간");
		L_item[4]=new JLabel(" 기말");
		L_item[5]=new JLabel(" 출석");
		L_item[6]=new JLabel(" 과제1");
		L_item[7]=new JLabel(" 과제2");
		L_item[8]=new JLabel(" 총점");
		L_item[9]=new JLabel(" 학점");
		
		for(int j=0; j<L_item.length; j++) {
			L_item[j].setOpaque(true);
			L_item[j].setFont(new Font("굴림체", Font.BOLD, 15));
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

/*텍스트필드 패널*/
class Center2 extends JPanel{
	private ArrayList sort=new ArrayList<Double>();
	private JScrollPane SP_scroll=new JScrollPane();
	private JPanel P_panel=new JPanel();
	public static boolean drawg = false; //총점, 학점이 부여되어야 파이차트와 그래프가 그려지도록 판단하는 변수
	public static JTextField[][] TF_content=new JTextField[40][10];//최대 40명까지 수정, 추가 가능
	
	public Center2() {
		
		P_panel.setLayout(null);
		
		for(int i=0; i<TF_content.length; i++) {
			for(int j=0; j<TF_content[i].length; j++) {
				TF_content[i][j]=new JTextField();
				TF_content[i][j].setFont(new Font("굴림체", Font.BOLD, 12));
				TF_content[i][j].setSize(63, 30);
				TF_content[i][j].setLocation(63*j, 30*i);
				TF_content[i][j].setBackground(MainFrame.colors[0]);
				P_panel.add(TF_content[i][j]);
			}
			
			totalPrint totalPrint=new totalPrint(); //엔터키를 누르면 총점,학점이 나오는 ActioinListener부착
			TF_content[i][7].addActionListener(totalPrint);
		}
		//텍스트필드에 해당하는 패널에 스크롤팬 부착
		SP_scroll = new JScrollPane(P_panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SP_scroll.setPreferredSize(new Dimension(650, 560));
		P_panel.setPreferredSize(new Dimension(650, 1250));

		add(SP_scroll);
		setSize(660,560);
		setVisible(true);
		this.setOpaque(false);
	}
	
}

/* 과제2 부분의 텍스트필드에서 엔터키를 누르면 총점,학점이 계산,출력되는 ActionListener.
 * Center3의 최고점,최저점,평균 등을 계산해서 출력하는 이벤트도 포함되어있다.*/
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
				
		for(int i=0; i<TF_content.length; i++) { //총점을 계산하여 TF_content[i][8]에 출력하는 반복문
			if (TF_content[i][7].getText().trim().length() > 0) {
				TF_content[i][8].setText(Double.toString(Math.round((
						Double.parseDouble(TF_content[i][3].getText())*0.3
						+Double.parseDouble(TF_content[i][4].getText())*0.35
						+Double.parseDouble(TF_content[i][5].getText())*0.1
						+Double.parseDouble(TF_content[i][6].getText())*0.12
						+Double.parseDouble(TF_content[i][7].getText())*0.13)*100)/100.0)
						);
				} //총점 비율 : 중간30%, 기말 35%, 출석10%, 과제1 12%, 과제2 13% 
			else
				break;
			}

		
		Lecture.calGrade(LeftFrame.selectedItemName);//학점을 부여하고 TF_content[i][9]에 출력하는 메소드 호출
				
				
		highestScore=Double.parseDouble(TF_content[0][8].getText());
		lowestScore=Double.parseDouble(TF_content[0][8].getText());
		
		//Center3패널에서 사용되는 내용을 계산하는 반복문들
		for(int i=0; i<TF_content.length; i++) { //최고, 최저, 평균
			if (TF_content[i][8].getText().trim().length() > 0) {
						
				if(highestScore<Double.parseDouble(TF_content[i][8].getText())) 
					highestScore=Double.parseDouble(TF_content[i][8].getText());
						
						
				if(lowestScore>Double.parseDouble(TF_content[i][8].getText())) 
					lowestScore=Double.parseDouble(TF_content[i][8].getText());
							
						

				sort.add(i, Double.parseDouble(TF_content[i][8].getText()));
						//중앙값
						
				averageScore=averageScore+Double.parseDouble(TF_content[i][8].getText());
				amount++;
			}
			else
				break;
		}
		averageScore=Math.round((averageScore/amount)*100)/100.0;
		for(int i=0; i<TF_content.length; i++) { //분산
			if (TF_content[i][8].getText().trim().length() > 0) 
				standardDeviation=standardDeviation+(Double.parseDouble(TF_content[i][8].getText())-averageScore)*(Double.parseDouble(TF_content[i][8].getText())-averageScore);
			else break;
			}
		standardDeviation=Math.round((Math.sqrt(standardDeviation/amount))*100)/100.0;
		
		Collections.sort(sort); //오름차순 정렬
		medianValue=Math.round(getMedian(sort)*100)/100.0;
		Center3.L_lowestScore.setText("최저점 : " +lowestScore);
		Center3.L_highestScore.setText("최고점 : " +highestScore);
		Center3.L_averageScore.setText("평균 : " +averageScore);
		Center3.L_standardDeviation.setText("표준편차 : "+standardDeviation);
		Center3.L_medianValue.setText("중앙값 : "+medianValue);
				
		sort.clear();

	}
	
	public double getMedian(ArrayList<Double> sort) {
	    if (sort.size() == 0) return Double.NaN; // 빈 배열은 에러 반환(NaN은 숫자가 아니라는 뜻)
	    int mid = sort.size() / 2;            // 요소 개수의 절반값 구하기

	    if (sort.size() % 2 == 1)  // 요소 개수가 홀수면
	      return (double) sort.get(mid); // 홀수 개수인 배열에서는 중간 요소를 그대로 반환
	    
	    else 
	      return (sort.get(mid-1) + sort.get(mid)) / 2.0; // 짝수 개 요소는, 중간 두 수의 평균 반환
	  }
}

 class Center3 extends JPanel{  
	public static JLabel L_highestScore=new JLabel("최고점 : 0");
	public static JLabel L_lowestScore=new JLabel("최저점 : 0");
	public static JLabel L_medianValue=new JLabel("중앙값 : 0");
	public static JLabel L_averageScore=new JLabel("평균 : 0");
	public static JLabel L_standardDeviation=new JLabel("표준편차 : 0");
	
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
	private JLabel L_fontSize=new JLabel("글씨크기: ");
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
		
		slider.addChangeListener(new ChangeListener() {//텍스트필드와 Center3의 글씨크기를 바꾸는 리스너

			@Override
			public void stateChanged(ChangeEvent e) {
				double event = slider.getValue();
				int newTextSize = (int)(event*0.1);
				Center3.L_lowestScore.setFont(new Font("굴림체", Font.BOLD, newTextSize));
				Center3.L_highestScore.setFont(new Font("굴림체", Font.BOLD, newTextSize));
				Center3.L_averageScore.setFont(new Font("굴림체", Font.BOLD, newTextSize));
				Center3.L_standardDeviation.setFont(new Font("굴림체", Font.BOLD, newTextSize));
				Center3.L_medianValue.setFont(new Font("굴림체", Font.BOLD, newTextSize));
				
				for(int i=0; i<Center2.TF_content.length; i++) {
					for(int j=0; j<Center2.TF_content[i].length; j++) {
						Center2.TF_content[i][j].setFont(new Font("굴림체", Font.BOLD, newTextSize));
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

/*파일을 불러오고, 저장하는 버튼이 있는 패널*/
class Center5 extends JPanel{
	private JButton B_loading = new JButton("불러오기");
	private JButton B_saving = new JButton("저장하기");
	
	public Center5() {
		
		B_saving.addActionListener(new ActionListener() { //저장하기 버튼 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				//텍스트필드의 내용을 저장하는 메소드
				Lecture.saveFile(LeftFrame.selectedItemName );
			}
			
		});
		
		B_loading.addActionListener(new ActionListener() { //불러오기 버튼 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				//버튼을 누르면 기존의 텍스트필드 수강생명단 내용을 초기화(파일을 불러와서 텍스트필드에 불러와야 하므로)
				for(int i=0;i<Center2.TF_content.length;i++) {
					for(int j=0;j<Center2.TF_content[i].length;j++) {
						Center2.TF_content[i][j].setText(null);
					}
				}
				Lecture.showInfo(LeftFrame.selectedItemName);//해당파일을 불러오고 수강생명단 텍스트필드에 정보가 보여짐.
				
			}
			
		});
			
		
		add(B_loading);
		add(B_saving);
		setSize(340, 80);
		setBackground(MainFrame.colors[1]);
	}
}
