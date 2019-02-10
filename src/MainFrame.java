import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class MainFrame extends JFrame{
	//������ �ѹ��� ����/���������ϵ��� �����Ͽ� �����Ѵ�.
	public static Color colors[] = {Color.WHITE, Color.decode("#fbe7e8"),Color.decode("#F8CB7B"),Color.decode("#D4EEF4"),Color.decode("#E9EFCA"),Color.decode("#A8DDF4")};
		                  /* colors[0] �Ͼ� : ����
		                   * colors[1] ����ȫ : ���� ������Ʈ�� ����
		                   * colors[2] ����� : �׷��� ��
		                   * colors[3] ���ϴ� : ���� ������Ʈ�� ����
		                   * colors[4] ���� : ����
		                   * colors[5] �ϴ� : ������Ʈ �׵θ���, �ؽ�Ʈ�ʵ� ���� �󺧹���
		                   * */
	
	public MainFrame() {
		setTitle("�ڹ� ���� ó�� ���α׷�");
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
