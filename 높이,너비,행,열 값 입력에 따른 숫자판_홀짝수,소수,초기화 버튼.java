import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HW extends JFrame { 
	public static int n =1;
	public HW( int W,int H,int C, int R) {
		int WIDTH = W; int HEIGHT =H ; int COLS = C; int ROWS = R;
		JButton[][] bts = new JButton[ROWS][COLS];
		JButton b;
		setTitle(String.format("[과제 #1] %d행 %d열 (%dX%d)",ROWS, COLS,WIDTH,HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null); 
		
		for(int i=0; i<ROWS; i++) {
			for(int j=0;j<COLS; j++) {
				b= new JButton(""+(n++));
				bts[i][j] = b; 
				b.setBounds(j*WIDTH,i*HEIGHT,WIDTH,HEIGHT);
				b.setBackground(Color.yellow);
				b.setForeground(Color.blue);	
				add(b);

			}
		}
		
		b = new JButton("홀수");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				JFrame frame = (JFrame) b.getTopLevelAncestor();
				frame.setTitle(String.format("[과제 #1] %d행 %d열 (%dX%d) - 홀수만 표시",ROWS, COLS,WIDTH,HEIGHT));
				for(int i=0; i<ROWS; i++) {
						for(int j=0;j<COLS; j++) {
							if(Integer.parseInt(bts[i][j].getText())% 2 !=0) {
								bts[i][j].setBackground(Color.blue);
								bts[i][j].setForeground(Color.white);	
							}
							else {
								bts[i][j].setBackground(Color.yellow);
								bts[i][j].setForeground(Color.blue);	
							}
						}
					}
				}
		});
		b.setBounds(0,ROWS*HEIGHT,(int)(WIDTH*COLS/4.0f),HEIGHT);
		add(b);
		b = new JButton("짝수");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				JFrame frame = (JFrame) b.getTopLevelAncestor();
				frame.setTitle(String.format("[과제 #1] %d행 %d열 (%dX%d) - 짝수만 표시",ROWS, COLS,WIDTH,HEIGHT));
				for(int i=0; i<ROWS; i++) {
						for(int j=0;j<COLS; j++) {
							if(Integer.parseInt(bts[i][j].getText())% 2 == 0) {
								bts[i][j].setBackground(Color.blue);
								bts[i][j].setForeground(Color.white);	
							}
							else {
								bts[i][j].setBackground(Color.yellow);
								bts[i][j].setForeground(Color.blue);	
							}
						}
					}
			}
			});
		b.setBounds((int)(WIDTH*COLS/4.0f),ROWS*HEIGHT,(int)(WIDTH*COLS/4.0f),HEIGHT);
		add(b);
		b = new JButton("소수");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				JFrame frame = (JFrame) b.getTopLevelAncestor();
				frame.setTitle(String.format("[과제 #1] %d행 %d열 (%dX%d) - 소수만 표시",ROWS, COLS,WIDTH,HEIGHT));
				for(int i=0; i<ROWS; i++) {
						for(int j=0;j<COLS; j++) {
							if(Is_Prime(Integer.parseInt(bts[i][j].getText())) == 2) {
								bts[i][j].setBackground(Color.blue);
								bts[i][j].setForeground(Color.white);	
							}
							else {
								bts[i][j].setBackground(Color.yellow);
								bts[i][j].setForeground(Color.blue);	
							}
						}
					}
			}
			});
		b.setBounds((int)(WIDTH*COLS*(2/4.0f)),ROWS*HEIGHT,(int)(WIDTH*COLS/4.0f),HEIGHT); 
		add(b);
		b = new JButton("초기화");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				JFrame frame = (JFrame) b.getTopLevelAncestor();
				frame.setTitle(String.format("[과제 #1] %d행 %d열 (%dX%d)",ROWS, COLS,WIDTH,HEIGHT));
				for(int i=0; i<ROWS; i++) {
						for(int j=0;j<COLS; j++) {
								bts[i][j].setBackground(Color.yellow);
								bts[i][j].setForeground(Color.blue);	
							}
						}
					}
			});
		b.setBounds((int)(WIDTH*COLS*(3/4.0f)),ROWS*HEIGHT,(int)(WIDTH*COLS/4.0f),HEIGHT); 
		add(b);
		
		setResizable(false);
		setSize(new Dimension(WIDTH*COLS+10,HEIGHT*(ROWS+1)+38));
		setVisible(true);
		

	}

	public static void main(String[] args) {
		int WIDTH = Integer.parseInt(args[0]);
		int HEIGHT = Integer.parseInt(args[1]);
		int COLS=Integer.parseInt(args[2]);
		int ROWS =Integer.parseInt(args[3]);
		
		new HW(WIDTH,HEIGHT,COLS,ROWS );
	}
	 public static int Is_Prime(int num) {
    int count = 0;

    for(int i = 1; i <= num; i++){
        if(num % i == 0)
            count += 1;
        if(count >= 3)
            return count;
    }
    
    return count;
}
}

