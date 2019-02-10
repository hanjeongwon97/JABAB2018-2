/*학생 클래스
 * 각 항목에 해당하는 변수를 작성하여 다른 클래스에서 사용할때 알기 쉽게 하였음.*/
public class Student {
	public String major;
	public String studentNum;
	public String name;
	public int midterm;
	public int finalExam;
	public int attend;
	public int assignment1;
	public int assignment2;
	public double total;
	public String grade;
	
	public Student(String major,String studentNum, String name) {
		this.major = major;
		this.studentNum = studentNum;
		this.name = name;
	}

}
