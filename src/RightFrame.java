import java.awt.*;	
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RightFrame extends JPanel{
	public static Color picolors[] = {Color.decode("#6199F4"),Color.decode("#61EFA0"),Color.decode("#CA88FB")};// ������Ʈ���� ���� �����
	private Right1 right1 = new Right1();
	private Right2 right2 = new Right2();
	private final int r_x_location = 10; // RightFrame�гε��� x��ǥ�� ����
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
	
	/*������Ʈ�� �׸��� �г�*/
	class MyDrawPichartPanel extends JPanel{
		public void paintComponent(Graphics g) {
			if(Right2.drawb==true) { // ������ �־�߸� ������Ʈ�� �׸� �� �ֱ� ������ ���ǹ��� �ʿ�.
				
				super.paintComponent(g);
				g.setColor(MainFrame.colors[5]);//������Ʈ�� Ʋ�� �׷��ִ� ��
				g.drawOval(40, 20, 160, 160);
				g.setColor(picolors[0]);
				g.fillArc(40, 20, 160, 160, 0, (int)(Right2.computer/Right2.sumratio*360));//��ǻ���а� ����
				g.setColor(picolors[1]);
				g.fillArc(40,20,160,160,(int)(Right2.computer/Right2.sumratio*360),(int)(Right2.math/Right2.sumratio*360));//���а� ����
				g.setColor(picolors[2]);
				g.fillArc(40, 20, 160, 160, (int)((Right2.math+Right2.computer)/Right2.sumratio*360), (int)(Right2.dep/Right2.sumratio*360));//��������а� ����
			}
		}
		public MyDrawPichartPanel(){
			setLayout(null);
			setSize(240,200);
			setBackground(MainFrame.colors[3]);
		}
	}
	
	/*��������ǥ�� �׸��� �г�*/
	class MyDrawGraphPanel extends JPanel{
		public void paintComponent(Graphics g) {
			int sarr[] = {0,0,0,0,0,0,0,0,0,0};// ������ �л����� �ǹ��� 
			if(Center2.drawg==true) {//������ ���Ǿ�� ��������ǥ�� �׸� �� �ֱ� ������ ���ǹ��� �ʿ�.
				super.paintComponent(g);
				g.setColor(MainFrame.colors[2]);
				g.fillRect(20, 20, 200, 180); 
				
				for(int i=0;i<Lecture.sv.size();i++) {//���� �������� �л����� ���� �ݺ���
					int t =(int)(Lecture.sv.get(i).total);
					switch(t/10) {
						case 0 : //t�� 0~9
							sarr[0]++;
							break;
						case 1 : //t�� 10~19
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
						case 10 ://100(����)
							sarr[9]++;
							break;
					
					}
				}
				
				for (int i=0;i<sarr.length;i++) {
					g.setColor(MainFrame.colors[3]);
					g.fillRect(20+(i*20), 20, 20, (180/sarr.length)*(sarr.length-sarr[i]));
					//�簢���� ������ ��ǥ�� �������� �׷����⶧���� �Ʒ������� ���̴°Ͱ��� ������ǥǥ�� �׸��� ���ؼ�
					//������ ���� �׷��־���.����������ũ�⿡ �°� �׷����� ������ �� �������� �簢���� �׷��ش�.(60��)
					//(���������ӳ���/������)�� �ϳ��� �л����� �ش��ϴ� ���簢���ǳ��� �̶�� �����Ѵ�.
					//(��ü�л���-�ش籸���л���)���Ͽ� �����ӹ������ �����ϰ� �׷����� �׷��ָ� �Ʒ������� ���ΰͰ��� �׷����� �׷�����.
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

/*������ �������� ��������,������������ ������ �� �ִ� ������ư�� �ִ� �г�*/
	class Right1 extends JPanel{ //��1
		private JLabel L_sort=new JLabel("����(���� ����)");
		private ButtonGroup g=new ButtonGroup();
		private JRadioButton[] radio=new JRadioButton[2];
		private String[] radio_text= {"��������", "��������"};
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
						if(radio[0].isSelected()) { //�������� ������ �ϴ� ����,�ݺ���
							for(int i=0; i<Center2.TF_content.length-1; i++) {//������
									for(int j=i+1; j<Center2.TF_content.length; j++) {//�� �� 
										if (Center2.TF_content[j][8].getText().trim().length() > 0) {
											if (Double.parseDouble(Center2.TF_content[i][8].getText())>Double.parseDouble(Center2.TF_content[j][8].getText())) {
												for(int k=0; k<Center2.TF_content[i].length; k++) { //�� ���� ����
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
							
	
						else if(radio[1].isSelected()) { //�������� ������ �ϴ� ����,�ݺ���
							for(int i=0; i<Center2.TF_content.length-1; i++) {//������
								for(int j=i+1; j<Center2.TF_content.length; j++) {//�� �� 
									if (Center2.TF_content[j][8].getText().trim().length() > 0) {
										if (Double.parseDouble(Center2.TF_content[i][8].getText())<Double.parseDouble(Center2.TF_content[j][8].getText())) {
											for(int k=0; k<Center2.TF_content[i].length; k++) { //�� ���� ����
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
	
	/*������Ʈ�� ��������ǥ ������ ��Ÿ���� �г�
	 * �׷��������� RightFrame���� �Ѵ�.*/
	class Right2 extends JPanel{ //��2
		public static boolean drawb = false;
		
		//������Ʈ�� �׸��� ���� ����((�� �а��� ���� /�и�)*360)���� �� �а��� ������ �ش�Ǵ� double�� ����
		static double computer=0.0;//��ǻ���а�
		static double math = 0.0;//���а�
		static double dep = 0.0;//��������а�
	
		static double sumratio = 0.0;//�и� �ش��ϴ� ����
		
		private JLabel L_rate=new JLabel("�а��� ���� ����");  
		private JLabel L_graph=new JLabel("���� ����ǥ");   
		private JCheckBox CB_a=new JCheckBox("A");
		private JCheckBox CB_b=new JCheckBox("B");
		private JCheckBox CB_c=new JCheckBox("C");
		private JLabel L_rateInfo_com = new JLabel("��ǻ���а�");
		private JLabel L_rateInfo_math = new JLabel("���а�");
		private JLabel L_rateInfo_dep = new JLabel("��������а�");
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
			
			//A ������ �ش��ϴ� üũ�ڽ��� ���õǸ� ����Ǵ� ������ 
			CB_a.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) { //üũ�ڽ� ���� ���°� ���ϸ� ȣ��
					if(math<0||computer<0||dep<0||sumratio<0) {//�ٸ����� �����ϰ� ����/�����Ҷ� ���������ü� �����Ƿ� 0���� �ʱ�ȭ�ǵ��� ����
						math = 0.0;
						computer =0.0;
						dep = 0.0;
						sumratio=0.0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // ���õ� ���
						
						for(int i=0;i<Lecture.sv.size();i++) {//�л� ũ�⸸ŭ �ݺ��ϸ鼭, �ش� �а��� ������ ��ġ�ϸ� 1�� ����
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("A")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("A")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("A")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioA;
						
					}
					else {//���� ���� ���
						for(int i=0;i<Lecture.sv.size();i++) {//�����Ǹ� 1�� ����
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("A")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("A")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("A")) {
								dep = dep-1;
							}
							
						}
						
						sumratio = sumratio-Lecture.ratioA;
						
				
					}
					
				}
				
			});
			
			//B ������ �ش��ϴ� üũ�ڽ��� ���õǸ� ����Ǵ� ������ 
			CB_b.addItemListener(new ItemListener() {
				
				
				@Override
				public void itemStateChanged(ItemEvent e) { //üũ�ڽ� ���� ���°� ���ϸ� ȣ��
					
					if(math<0||computer<0||dep<0||sumratio<0) {//�ٸ����� �����ϰ� ����/�����Ҷ� ���������ü� �����Ƿ� 0���� �ʱ�ȭ�ǵ��� ����

						
						math = 0;
						computer =0;
						dep = 0;
						sumratio=0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // ���õ� ���
						
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("B")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("B")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("B")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioB;
						
					}
					else {//���� ���� ���
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("B")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("B")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("B")) {
								dep = dep-1;
							}
							
						}
						
						sumratio = sumratio-Lecture.ratioB;
						
					}
					
				}

				
			});
			//C ������ �ش��ϴ� üũ�ڽ��� ���õǸ� ����Ǵ� ������ 
			CB_c.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) { //üũ�ڽ� ���� ���°� ���ϸ� ȣ��
					if(math<0||computer<0||dep<0||sumratio<0) {//�ٸ����� �����ϰ� ����/�����Ҷ� ���������ü� �����Ƿ� 0���� �ʱ�ȭ�ǵ��� ����

						
						math = 0;
						computer =0;
						dep = 0;
						sumratio=0;
					}
					drawb = true;
					repaint();
					if(e.getStateChange()==ItemEvent.SELECTED) { // ���õ� ���
						
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("C")) {
								computer = computer+1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("C")) {
								math = math+1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("C")) {
								dep = dep+1;
							}
							
						}
						sumratio = sumratio + Lecture.ratioC;
						
					}
					else {//���� ���� ���
						for(int i=0;i<Lecture.sv.size();i++) {
							if(Lecture.sv.get(i).major.equals("��ǻ���а�")&&Lecture.sv.get(i).grade.equals("C")) {
								computer = computer-1;
							}
							else if(Lecture.sv.get(i).major.equals("���а�")&&Lecture.sv.get(i).grade.equals("C")) {
								math = math-1;
							}
							else if(Lecture.sv.get(i).major.equals("��������а�")&&Lecture.sv.get(i).grade.equals("C")) {
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

