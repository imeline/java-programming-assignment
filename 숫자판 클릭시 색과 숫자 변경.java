import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.plaf.IconUIResource;

public class HW2 extends JFrame {
	private static int WIDTH = 100, HEIGHT = 80,ROWS, COLS ;
	private static MyButton[][] btns;
	private static MyButton prevBtn;
	public HW2(int row, int col) {
		ROWS = row;
		COLS = col;
		boolean chk;
		int		n = 0, rd[]= new int[ROWS*COLS];
		btns = new MyButton[ROWS][COLS];
		setTitle("[과제 #2]");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		

		for(int i=0; i<ROWS*COLS-1; i++) {
			while(true) {
				rd[i]= Rand.r(1, ROWS*COLS-1); 
				chk = false;
				
				for(int j=0; j<i; j++) {
					if(rd[j] == rd[i]) {
						chk = true;
						break;
					}
					if(rd[j] != rd[i]) {
						chk = false;
					}
				}
				if(!chk) break;
			}
		}
		for(int i=0;i < ROWS;i++) {
			for(int j=0;j < COLS;j++) {
				if(i==ROWS-1 && j==COLS-1) {
					btns[i][j] = new MyButton("",i,j);
					btns[i][j].setBackground(Color.white);
				}
				else {
					btns[i][j] = new MyButton(""+(rd[n++]),i,j);
					btns[i][j].setBackground(Color.yellow);
					btns[i][j].setForeground(Color.blue);
				}	
				btns[i][j].setBounds(j*WIDTH, i*HEIGHT, WIDTH, HEIGHT);
				btns[i][j].addActionListener(new MyButtonHandler());
				btns[i][j].addKeyListener(new MyKeyHandler());
				btns[i][j].setFont(new Font("Arial",Font.BOLD,28));
				add(btns[i][j]);
			}
		}
		setSize(new Dimension(COLS*WIDTH+16,(ROWS)*HEIGHT+40));
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}
	public static void main(String[] args) {
		int row = Integer.parseInt(args[0]);
		int col = Integer.parseInt(args[1]);
		new HW2(row,col);
	}
	class MyButton extends JButton {
		int row,col;
		String t;
		
		public MyButton(String t, int r, int c) {
			super(t);
			row = r; col = c; this.t = t;
		}
	}
	class MyButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			prevBtn = (MyButton) e.getSource();
			if(prevBtn.getBackground() == Color.white) return;
			prevBtn.setForeground(Color.red);
			prevBtn.setBackground(Color.pink);
			setTitle("[과제 #2] "+prevBtn.getText()+" 선택 됨");
			prevBtn.requestFocus();	
		}
	}
	public static void swapButtons(int r, int c, int rr,int cc) {
		String tmp_t = btns[r][c].getText();
		Color tmp_fg= btns[r][c].getForeground(), tmp_bg= btns[r][c].getBackground();
		
		btns[r][c].setText(btns[rr][cc].getText());
		btns[r][c].setForeground(btns[rr][cc].getForeground());
		btns[r][c].setBackground(btns[rr][cc].getBackground());
	
		btns[rr][cc].setText(tmp_t);
		btns[rr][cc].setForeground(tmp_fg);
		btns[rr][cc].setBackground(tmp_bg);	
			
	}
	class MyKeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			int row = prevBtn.row, col = prevBtn.col;
			int	empty=-1;		
			
			if(prevBtn.getBackground() == Color.white) return;
			
			switch (code) {
			case KeyEvent.VK_UP:
				prevBtn.setBackground(Color.yellow);
				prevBtn.setForeground(Color.blue);
				for(int i=0; i<row; i++) {
					if (btns[i][col].getText() == "") {
						empty = i;
						break;
					}
				}
				if(empty >= 0) {
					for(int i=empty;i<row;i++) {
						swapButtons(i,col,i+1,col);
					}
				}
				else setTitle("윗쪽 이용 불가!!");
				break;
			case KeyEvent.VK_DOWN:
				prevBtn.setBackground(Color.yellow);
				prevBtn.setForeground(Color.blue);
				for(int i=row; i<ROWS; i++) {
					if (btns[i][col].getText() == "") {
						empty = i;
						break;
					}
				}
				if(empty >= 0) {
					for(int i=empty;i>row;i--) {
						swapButtons(i,col,i-1,col);
					}
				}
				else setTitle("아랫쪽 이용 불가!!");
				break;
			case KeyEvent.VK_LEFT:
				prevBtn.setBackground(Color.yellow);
				prevBtn.setForeground(Color.blue);
				for(int i=0; i<col; i++) {
					if (btns[row][i].getText() == "") {
						empty = i;
						break;
					}
				}
				if(empty >= 0) {
					for(int i=empty;i<col;i++) {
						swapButtons(row,i,row,i+1);
					}
				}
				else setTitle("왼쪽 이용 불가!!");
				break;
			case KeyEvent.VK_RIGHT:
				prevBtn.setBackground(Color.yellow);
				prevBtn.setForeground(Color.blue);
				for(int i=col; i<COLS; i++) {
					if (btns[row][i].getText() == "") {
						empty = i;
						break;
					}
				}
				if(empty >= 0) {
					for(int i=empty;i>col;i--) {
						swapButtons(row,i,row,i-1);
					}
				}
				else setTitle("오른쪽 이용 불가!!");
				break;
			default:
				break;
			}
			requestFocus();
			
		}	
	}
}
