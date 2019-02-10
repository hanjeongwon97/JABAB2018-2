import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class MainFrame extends JFrame{
	//색상을 한번에 변경/수정가능하도록 선언하여 관리한다.
	public static Color colors[] = {Color.WHITE, Color.decode("#fbe7e8"),Color.decode("#F8CB7B"),Color.decode("#D4EEF4"),Color.decode("#E9EFCA"),Color.decode("#A8DDF4")};
		                  /* colors[0] 하양 : 배경색
		                   * colors[1] 연분홍 : 작은 컴포넌트의 배경색
		                   * colors[2] 진노랑 : 그래프 색
		                   * colors[3] 연하늘 : 작은 컴포넌트의 배경색
		                   * colors[4] 연두 : 미정
		                   * colors[5] 하늘 : 파이차트 테두리색, 텍스트필드 맨위 라벨배경색
		                   * */
	
	public MainFrame() {
		setTitle("자바 성적 처리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		LeftFrame leftFrame = new LeftFrame();
		leftFrame.setLocation(0, 0);
		
		RightFrame rightFrame = new RightFrame();
		rightFrame.setLocation(930, 0);
		
		CenterFrame centerFrame = new CenterFrame();
		centerFrame.setLocation(280, 0);

		
		c.add(leftFrame);
		c.add(centerFrame);
		c.add(rightFrame);
		
		
		setSize(1220, 840);
		setVisible(true);
		
		c.setBackground(MainFrame.colors[0]);
		
	}


	public static void main(String[] args) {

		new MainFrame();
		
		
	}
}
