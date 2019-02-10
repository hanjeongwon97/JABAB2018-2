/*과목을 불러오고, 파일에 해당하는 다양한 메소드들을 포함한 클래스. 생성자는 없음*/

import java.io.*;
import java.util.*;
public class Lecture {
	
	public static Vector<String> lineVector = new Vector<String>();
	public static String[] str = new String[8];
	public static Vector<Student> sv = new Vector<Student>();
	
	//파이차트를 그리기 위해 사용되는 변수이다.
	public static int ratioA = 0; // A학점을 받은 학생 수
	public static int ratioB = 0; // B학점을 받은 학생 수
	public static int ratioC = 0; // C학점을 받은 학생 수
	
	
	public static void readFile(String fileName) {
		
		//콤보박스가 다시 선택될때 이전의 기록이 삭제되어야한다.(삭제되지 않으면 선택될때마다 기존의 학생이 계속 벡터에 추가되기 때문이다.)
		lineVector.removeAllElements();
		sv.removeAllElements();
		 
		readLine(fileName);//fileName.txt파일을 한줄씩 읽고 학생객체를 만들어 lineVecotr에 저장하는 메소드 호출

		
		//한 lineVector를 Student객체로 만들어서 sv에 추가
		for (int i=0;i<lineVector.size();i++) {
			str = lineVector.get(i).split(" ");//" "(빈칸하나)를 기준으로 String을 나눠서 str[]에 저장하고 저장된 순서에 맞게 학생객체 만든다.
			Student s = new Student(str[0],str[1],str[2]);//학과,학번,이름
			s.midterm =Integer.parseInt(str[3]);//중간
			s.finalExam = Integer.parseInt(str[4]);//기말
			s.attend = Integer.parseInt(str[5]);//출석
			s.assignment1 = Integer.parseInt(str[6]);//과제1
			s.assignment2 = Integer.parseInt(str[7]);//과제2
			
			sv.add(s);
		}
		
	}
	
	/*fileName.txt 파일에 해당하는 수강생 명단을 텍스트필드에 setText()하는 메소드*/
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
	
	/*fileName.txt를 한줄씩 읽어서 lineVector에 넣는 메소드*/
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
	
	/* 학점을 계산하는 메소드. 과제2 텍스트필드부분에서 엔터키 입력시 실행*/
	public static void calGrade(String fileName) {
		
		double  gstr[] = new double[sv.size()];
		//학점비율 : A 30% B 35% C 35%
		double cG = 0.35*gstr.length;
		double bG = 0.70*gstr.length;		
		
		for(int i=0; i<sv.size();i++) {
			double totald = Double.parseDouble(Center2.TF_content[i][8].getText());
			sv.get(i).total=totald;
			gstr[i] = totald; //모든 학생의 총점을 저장한 배열
			
		}
		
		Arrays.sort(gstr);//모든 학생의 총점이 있는 배열을 오름차순으로 정렬
		//gstr의 인덱스가  30%면 C, 70%는 B 나머지는 A
 		
		int j=0;
		while(j<sv.size()) {
			
			double s = sv.get(j).total;// sv벡터의 j번째학생의 총점
			
			for (int i=0;i<gstr.length;i++) {
				
				if (s==gstr[i]) {//j번째 학생의 총점과 어떤gstr[i]의 값이 같다면
					
					if(i<=cG) {  //gstr의 인덱스가 35프로 이내면 C
						sv.get(j).grade = "C";
						ratioC = ratioC+1; 
						
					}
					else if(i<=bG) { //gstr의 인덱스가 70프로 이내면 C
						sv.get(j).grade = "B";
						ratioB = ratioB+1;
					}
					else {//나머지 A
						sv.get(j).grade = "A";
						ratioA = ratioA+1;
				
					}
				}
			}
			j++;
			ratioC = sv.size()-ratioA-ratioB;
		}
		
		//TF_content[i][9]에 학점 setText()하기
		for(int i=0;i<gstr.length;i++) {
			Center2.TF_content[i][9].setText(sv.get(i).grade);
		}


	}
	
		


	/* 파일을 저장하는 메소드 .저장하기버튼에서 호출됨 */
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
	
	

