import java.awt.*;	
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RightFrame extends JPanel{
	public static Color picolors[] = {Color.decode("#6199F4"),Color.decode("#61EFA0"),Color.decode("#CA88FB")};// 파이차트에만 사용될 색깔들
	private Right1 right1 = new Right1();
	private Right2 right2 = new Right2();
	private final int r_x_location = 10; // RightFrame패널들의 x좌표의 기준
	private MyDrawPichartPanel drawPichart = new MyDrawPichartPanel();
	private MyDrawGraphPanel drawgraph = new MyDrawGraphPanel();
	
	public RightFrame() {
		setLayout(null);
		right1.setLocation(r_x_location,20);
		add(right1);
		right2.setLocation(r_x_location, 130);
		add(right2);
		
		right2.add(drawPichart);
		right2.add(drawgraph);
		
		drawPichart.setLocation(0, 65);
		drawgraph.setLocation(0,400);
		
		setSize(270, 800);
		setBackground(MainFrame.colors[0]);
	}
	
	/*파이차트를 그리는 패널*/
	class MyDrawPichartPanel extends JPanel{
		public void paintComponent(Graphics g) {
			if(Right2.drawb==true) { // 학점이 있어야만 파이차트를 그릴 수 있기 때문에 조건문이 필요.
				
				super.paintComponent(g);
				g.setColor(MainFrame.colors[5]);//파이차트의 틀을 그려주는 원
				g.drawOval(40, 20, 160, 160);
				g.setColor(picolors[0]);
				g.fillArc(40, 20, 160, 160, 0, (int)(Right2.computer/Right2.sumratio*360));//컴퓨터학과 파이
				g.setColor(picolors[1]);
				g.fillArc(40,20,160,160,(int)(Right2.computer/Right2.sumratio*360),(int)(Right2.math/Right2.sumratio*360));//수학과 파이
				g.setColor(picolors[2]);
				g.fillArc(40, 20, 160, 160, (int)((Right2.math+Right2.computer)/Right2.sumratio*360), (int)(Right2.dep/Right2.sumratio*360));//정보통계학과 파이
			}
		}
		public MyDrawPichartPanel(){
			setLayout(null);
			setSize(240,200);
			setBackground(MainFrame.colors[3]);
		}
	}
	
	/*점수분포표를 그리는 패널*/
	class MyDrawGraphPanel extends JPanel{
		public void paintComponent(Graphics g) {
			int sarr[] = {0,0,0,0,0,0,0,0,0,0};// 구간별 학생수를 의미함 
			if(Center2.drawg==true) {//총점이 계산되어야 점수분포표를 그릴 수 있기 때문에 조건문이 필요.
				super.paintComponent(g);
				g.setColor(MainFrame.colors[2]);
				g.fillRect(20, 20, 200, 180); 
				
				for(int i=0;i<Lecture.sv.size();i++) {//점수 구간별로 학생수를 세는 반복문
					int t =(int)(Lecture.sv.get(i).total);
					switch(t/10) {
						case 0 : //t는 0~9
							sarr[0]++;
							break;
						case 1 : //t는 10~19
							sarr[1]++;
							break;
						case 2 : //20~29
							sarr[2]++;
							break;
						case 3 : //30~39
							sarr[3]++;
							break;
						case 4 : //40~49
							sarr[4]++;
							break;
						case 5 : //50~59
							sarr[5]++;
							break;
						case 6 : //60~69
							sarr[6]++;
							break;
						case 7 : //70~79
							sarr[7]++;
							break;
						case 8 : //80~89
							sarr[8]++;
							break;
						case 9 : //90~99
						case 10 ://100(만점)
							sarr[9]++;
							break;
					
					}
				}
				
				for (int i=0;i<sarr.length;i++) {
					g.setColor(MainFrame.colors[3]);
					g.fillRect(20+(i*20), 20, 20, (180/sarr.length)*(sarr.length-sarr[i]));
					//사각형은 왼쪽위 좌표를 기준으로 그려지기때문에 아래서부터 쌓이는것같은 점수분표표를 그리기 위해서
					//다음과 같이 그려주었다.현재프레임크기에 맞게 그래프의 색깔이 될 색상으로 사각형을 그려준다.(60행)
					//(현재프레임높이/구간수)를 하나의 학생수에 해당하는 직사각형의높이 이라고 생각한다.
					//(전체학생수-해당구간학생수)곱하여 프레임배경색상과 동일하게 그래프를 그려주면 아래서부터 쌓인것같은 그래프가 그려진다.
				}
			}
		}
		public MyDrawGraphPanel () {
			setLayout(null);
			setSize(240,200);
			setBackground(MainFrame.colors[3]);
		}
	}
}

/*총점을 기준으로 오름차순,내림차순으로 정렬할 수 있는 라디오버튼이 있는 패널*/
	class Right1 extends JPanel{ //오1
		private JLabel L_sort=new JLabel("정렬(총점 기준)");
		private ButtonGroup g=new ButtonGroup();
		private JRadioButton[] radio=new JRadioButton[2];
		private String[] radio_text= {"오름차순", "내림차순"};
		private Center2 Center2;
		private String[] temp=new String[10];
		
		public Right1() {
			setLayout(null);
			L_sort.setLocation(10, 10);
			L_sort.setSize(120, 20);
			
			for(int i=0; i<radio.length; i++) {
				radio[i]=new JRadioButton(radio_text[i]);
				radio[i].setSize(80, 20);
				radio[i].setBackground(MainFrame.colors[1]);
				g.add(radio[i]);
				add(radio[i]);
				
				radio[i].addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(e.getStateChange()==ItemEvent.DESELECTED)
							return;
						if(radio[0].isSelected()) { //오름차순 정렬을 하는 조건,반복문
							for(int i=0; i<Center2.TF_content.length-1; i++) {//현재행
									for(int j=i+1; j<Center2.TF_content.length; j++) {//다 돎 
										if (Center2.TF_content[j][8].getText().trim().length() > 0) {
											if (Double.parseDouble(Center2.TF_content[i][8].getText())>Double.parseDouble(Center2.TF_content[j][8].getText())) {
												for(int k=0; k<Center2.TF_content[i].length; k++) { //값 각각 저장
													temp[k]=Center2.TF_content[i][k].getText();
													Center2.TF_content[i][k].setText(Center2.TF_content[j][k].getText());
													Center2.TF_content[j][k].setText(temp[k]);
												}
											}
										}
										else break;
								}
								
								}
							}
							
	
						else if(radio[1].isSelected()) { //내림차순 정렬을 하는 조건,반복문
							for(int i=0; i<Center2.TF_content.length-1; i++) {//현재행
								for(int j=i+1; j<Center2.TF_content.length; j++) {//다 돎 
									if (Center2.TF_content[j][8].getText().trim().length() > 0) {
										if (Double.parseDouble(Center2.TF_content[i][8].getText())<Double.parseDouble(Center2.TF_content[j][8].getText())) {
											for(int k=0; k<Center2.TF_content[i].length; k++) { //값 각각 저장
												temp[k]=Center2.TF_content[i][k].getText();
												Center2.TF_content[i][k].setText(Center2.TF_content[j][k].getText());
												Center2.TF_content[j][k].setText(temp[k]);
											}
										}
									}
									else break;
								}
							
							}
						}							
					}
				});
			}
			radio[0].setLocation(15, 50);
			radio[1].setLocation(100, 50);
			setBackground(MainFrame.colors[1]);
			add(L_sort);
			setSize(240, 90);
			
		}
	}
	
	/*파이차트와 점수분포표 정보를 나타내는 패널
	 * 그래프부착은 RightFrame에서 한다.*/
	class Right2 extends JPanel{ //오2
		public static boolean drawb = false;
		
		//파이차트를 그리기 위한 각도((각 학과의 비율 /분모)*360)에서 각 학과의 비율에 해당되는 double형 변수
		static double computer=0.0;//컴퓨터학과
		static double math = 0.0;//수학과
		static double dep = 0.0;//정보통계학과
	
		static double sumratio = 0.0;//분모에 해당하는 변수
		
		private JLabel L_rate=new JLabel("학과별 학점 비율");  
		private JLabel L_graph=new JLabel("점수 분포표");   
		private JCheckBox CB_a=new JCheckBox("A");
		private JCheckBox CB_b=new JCheckBox("B");
		private JCheckBox CB_c=new JCheckBox("C");
		private JLabel L_rateInfo_com = new JLabel("컴퓨터학과");
		private JLabel L_rateInfo_math = new JLabel("수학과");
		private JLabel L_rateInfo_dep = new JLabel("정보통계학과");
		private JLabel L_graphInfo = new JLabel(" 0  10  20  30  40  50  60  70  80  90 ");

		public Right2(){
			setLayout(null);
			L_rate.setLocation(10, 10);
			L_rate.setSize(200, 20);
			L_graph.setLocation(10, 400);
			L_graph.setSize(200,20);
			CB_a.setLocation(10, 40);
			CB_a.setSize(50, 20);
			CB_a.setBackground(MainFrame.colors[3]);
			CB_b.setLocation(60, 40);
			CB_b.setSize(50, 20);
			CB_b.setBackground(MainFrame.colors[3]);
			CB_c.setLocation(110, 40);
			CB_c.setSize(50,20);
			CB_c.setBackground(MainFrame.colors[3]);
			
			L_rateInfo_com.setLocation(10,270);
			L_rateInfo_com.setSize(75,20);
			L_rateInfo_com.setOpaque(true);
			L_rateInfo_com.setBackground(RightFrame.picolors[0]);
			L_rateInfo_math.setLocation(90, 270);
			L_rateInfo_math.setSize(40, 20);
			L_rateInfo_math.setOpaque(true);
			L_rateInfo_math.setBackground(RightFrame.picolors[1]);
			L_rateInfo_dep.setLocation(135, 270);
			L_rateInfo_dep.setSize(80, 20);
			L_rateInfo_dep.setOpaque(true);
			L_rateInfo_dep.setBackground(RightFrame.picolors[2]);
			L_graphInfo.setLocation(15, 600);
			L_graphInfo.setSize(220, 20);
			L_graphInfo.setFont(new Font("Arial",Font.PLAIN,13));
			
			//A 학점에 해당하는 체크박스가 선택되면 실행되는 리스너 
			CB_a.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) { //체크박스 선택 상태가 변하면 호출
					if(math<0||computer<0||dep<0||sumratio<0) {//다른과목 선택하고 해제/선택할때 음수가나올수 있으므로 0으로 초기화되도록 설정
						math = 0.0;
						computer =0.0;
						dep = 0.0;
						sumratio=0.0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // 선택된 경우
						
						for(int i=0;i<Lecture.sv.size();i++) {//학생 크기만큼 반복하면서, 해당 학과와 학점이 일치하면 1씩 증가
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("A")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("A")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("A")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioA;
						
					}
					else {//선택 해제 경우
						for(int i=0;i<Lecture.sv.size();i++) {//해제되면 1씩 감소
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("A")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("A")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("A")) {
								dep = dep-1;
							}
							
						}
						
						sumratio = sumratio-Lecture.ratioA;
						
				
					}
					
				}
				
			});
			
			//B 학점에 해당하는 체크박스가 선택되면 실행되는 리스너 
			CB_b.addItemListener(new ItemListener() {
				
				
				@Override
				public void itemStateChanged(ItemEvent e) { //체크박스 선택 상태가 변하면 호출
					
					if(math<0||computer<0||dep<0||sumratio<0) {//다른과목 선택하고 해제/선택할때 음수가나올수 있으므로 0으로 초기화되도록 설정

						
						math = 0;
						computer =0;
						dep = 0;
						sumratio=0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // 선택된 경우
						
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("B")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("B")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("B")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioB;
						
					}
					else {//선택 해제 경우
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("B")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("B")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("B")) {
								dep = dep-1;
							}
							
						}
						
						sumratio = sumratio-Lecture.ratioB;
						
					}
					
				}

				
			});
			//C 학점에 해당하는 체크박스가 선택되면 실행되는 리스너 
			CB_c.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) { //체크박스 선택 상태가 변하면 호출
					if(math<0||computer<0||dep<0||sumratio<0) {//다른과목 선택하고 해제/선택할때 음수가나올수 있으므로 0으로 초기화되도록 설정

						
						math = 0;
						computer =0;
						dep = 0;
						sumratio=0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // 선택된 경우
						
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("C")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("C")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("C")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioC;
						
					}
					else {//선택 해제 경우
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("컴퓨터학과")&&Lecture.sv.get(i).grade.equals("C")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("수학과")&&Lecture.sv.get(i).grade.equals("C")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("정보통계학과")&&Lecture.sv.get(i).grade.equals("C")) {
								dep = dep-1;
							}
							
						}
						
						sumratio = sumratio-Lecture.ratioC;
						
						
					}
				}

			});
			add(L_rate);
			add(CB_a);
			add(CB_b);
			add(CB_c);
			add(L_graph);
			add(L_rateInfo_com);
			add(L_rateInfo_math);
			add(L_rateInfo_dep);
			add(L_graphInfo);
			setSize(240,650);
			this.setBackground(MainFrame.colors[3]);
		}
	}

