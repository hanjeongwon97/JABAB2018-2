/*������ �ҷ�����, ���Ͽ� �ش��ϴ� �پ��� �޼ҵ���� ������ Ŭ����. �����ڴ� ����*/

import java.io.*;
import java.util.*;
public class Lecture {
	
	public static Vector<String> lineVector = new Vector<String>();
	public static String[] str = new String[8];
	public static Vector<Student> sv = new Vector<Student>();
	
	//������Ʈ�� �׸��� ���� ���Ǵ� �����̴�.
	public static int ratioA = 0; // A������ ���� �л� ��
	public static int ratioB = 0; // B������ ���� �л� ��
	public static int ratioC = 0; // C������ ���� �л� ��
	
	
	public static void readFile(String fileName) {
		
		//�޺��ڽ��� �ٽ� ���õɶ� ������ ����� �����Ǿ���Ѵ�.(�������� ������ ���õɶ����� ������ �л��� ��� ���Ϳ� �߰��Ǳ� �����̴�.)
		lineVector.removeAllElements();
		sv.removeAllElements();
		 
		readLine(fileName);//fileName.txt������ ���پ� �а� �л���ü�� ����� lineVecotr�� �����ϴ� �޼ҵ� ȣ��

		
		//�� lineVector�� Student��ü�� ���� sv�� �߰�
		for (int i=0;i<lineVector.size();i++) {
			str = lineVector.get(i).split(" ");//" "(��ĭ�ϳ�)�� �������� String�� ������ str[]�� �����ϰ� ����� ������ �°� �л���ü �����.
			Student s = new Student(str[0],str[1],str[2]);//�а�,�й�,�̸�
			s.midterm =Integer.parseInt(str[3]);//�߰�
			s.finalExam = Integer.parseInt(str[4]);//�⸻
			s.attend = Integer.parseInt(str[5]);//�⼮
			s.assignment1 = Integer.parseInt(str[6]);//����1
			s.assignment2 = Integer.parseInt(str[7]);//����2
			
			sv.add(s);
		}
		
	}
	
	/*fileName.txt ���Ͽ� �ش��ϴ� ������ ����� �ؽ�Ʈ�ʵ忡 setText()�ϴ� �޼ҵ�*/
	public static void showInfo(String fileName) {
		readFile(fileName);
		for(int i=0;i<sv.size();i++) {
			Center2.TF_content[i][0].setText(sv.get(i).major);
			Center2.TF_content[i][1].setText(sv.get(i).studentNum);
			Center2.TF_content[i][2].setText(sv.get(i).name);
			Center2.TF_content[i][3].setText(""+sv.get(i).midterm);
			Center2.TF_content[i][4].setText(""+sv.get(i).finalExam);
			Center2.TF_content[i][5].setText(""+sv.get(i).attend);
			Center2.TF_content[i][6].setText(""+sv.get(i).assignment1);
			Center2.TF_content[i][7].setText(""+sv.get(i).assignment2);
		}
	}
	
	/*fileName.txt�� ���پ� �о lineVector�� �ִ� �޼ҵ�*/
	public static void readLine(String fileName) {
		
		try {
			FileReader filereader = new FileReader(fileName+".txt");
			Scanner s = new Scanner(filereader);
			while(s.hasNext()) { 
				String line = s.nextLine();
				lineVector.add(line);
			}
			filereader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/* ������ ����ϴ� �޼ҵ�. ����2 �ؽ�Ʈ�ʵ�κп��� ����Ű �Է½� ����*/
	public static void calGrade(String fileName) {
		
		double  gstr[] = new double[sv.size()];
		//�������� : A 30% B 35% C 35%
		double cG = 0.35*gstr.length;
		double bG = 0.70*gstr.length;		
		
		for(int i=0; i<sv.size();i++) {
			double totald = Double.parseDouble(Center2.TF_content[i][8].getText());
			sv.get(i).total=totald;
			gstr[i] = totald; //��� �л��� ������ ������ �迭
			
		}
		
		Arrays.sort(gstr);//��� �л��� ������ �ִ� �迭�� ������������ ����
		//gstr�� �ε�����  30%�� C, 70%�� B �������� A
 		
		int j=0;
		while(j<sv.size()) {
			
			double s = sv.get(j).total;// sv������ j��°�л��� ����
			
			for (int i=0;i<gstr.length;i++) {
				
				if (s==gstr[i]) {//j��° �л��� ������ �gstr[i]�� ���� ���ٸ�
					
					if(i<=cG) {  //gstr�� �ε����� 35���� �̳��� C
						sv.get(j).grade = "C";
						ratioC = ratioC+1; 
						
					}
					else if(i<=bG) { //gstr�� �ε����� 70���� �̳��� C
						sv.get(j).grade = "B";
						ratioB = ratioB+1;
					}
					else {//������ A
						sv.get(j).grade = "A";
						ratioA = ratioA+1;
				
					}
				}
			}
			j++;
			ratioC = sv.size()-ratioA-ratioB;
		}
		
		//TF_content[i][9]�� ���� setText()�ϱ�
		for(int i=0;i<gstr.length;i++) {
			Center2.TF_content[i][9].setText(sv.get(i).grade);
		}


	}
	
		


	/* ������ �����ϴ� �޼ҵ� .�����ϱ��ư���� ȣ��� */
	public static void saveFile(String fileName) {
		FileWriter fout = null;
		String es = new String("");
		
		try {
			fout = new FileWriter(fileName+".txt");
			for(int i=0;i<Center2.TF_content.length;i++) {
				for(int j=0;j<Center2.TF_content[i].length;j++) {
					es = es.concat(Center2.TF_content[i][j].getText()+" ");
				}
			
				fout.write(es+"\n");
				es = "";
			}
			fout.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

		
		
}
	
	

