import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;

public class P4 extends JFrame {
	public static final int WIDTH = 100, HEIGHT = 80;
	public static int ROWS = 3, COLS = 4;
	private static MyButton btns[][] = null, prevBtn = null;
	public static	int ClickNum = 0;
	public P4() {
		setTitle("[문제 #4]");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		btns = new MyButton[ROWS][COLS];
		for(int i=0;i < ROWS;i++) {
			for(int j=0;j < COLS;j++) {
				add(btns[i][j] = new MyButton(""+(i*COLS+j),j,i));
				btns[i][j].addActionListener(new MyButtonListener());
			}
		}
		setSize(new Dimension(COLS*WIDTH+16,(ROWS)*HEIGHT+40));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new P4();
	}
	
	class MyButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MyButton	selectedBtn = (MyButton) e.getSource();
			Thread t;
			
			selectedBtn.setText(selectedBtn.txt);
			if(ClickNum++ == 0) {
				prevBtn = selectedBtn;
				return;
			}
			// ActionListener에서 기다리는 Thread
			t = new Thread(()-> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					selectedBtn.setText(null);
					prevBtn.setText(null);	
				}	
			});
			t.start();
			ClickNum = 0;
		}
	}
}
class MyButton extends JButton {
	String	txt;
	boolean	found = false;
	
	public MyButton(String n, int j, int i) {
		txt = n;
		setBounds(j*P4.WIDTH, i*P4.HEIGHT, P4.WIDTH, P4.HEIGHT);
		setBackground(new Color(0,128,128));
		setForeground(new Color(224,255,255));
		setFont(new Font("Arial",Font.BOLD,28));
	}
}
