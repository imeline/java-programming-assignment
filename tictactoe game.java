import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HW2 extends JFrame{
	private static final ImageIcon X = new ImageIcon("X.png"),O = new ImageIcon("O.png");
	private static final MyButton[][] mybtns = new MyButton[3][3];
	private static final JLabel lab = new JLabel("[Tic-Tac-Toe] 다음 차례는 O");
	private static boolean myTurn = true;
	private static boolean endGame = false;
	private static Container c;

	
	public HW2() {
		JPanel up = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0)), down = new JPanel(new GridLayout(3, 3));
		c= getContentPane();
		setTitle("[과제 #2] Tic Tac Toe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c.setLayout(new BorderLayout());
		lab.setFont(new Font("그래픽",Font.BOLD,19));
		up.add(lab);
		
		
		for(int i=0; i<mybtns.length;i++) {
			for(int j=0; j<mybtns[i].length;j++) {
				mybtns[i][j]= new MyButton(j,i) ;
				mybtns[i][j].addActionListener(new MyBtnEvent());
				down.add(mybtns[i][j]);
			}
		}
		;
		c.addKeyListener(new MyKeyHandler());
		c.add(up,BorderLayout.NORTH);
		c.add(down,BorderLayout.CENTER);
		setPreferredSize(new Dimension(300,300));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		c.setFocusable(true);
		c.requestFocus();
	}
	public static void main(String[] args) {
		new HW2();
	}
	class MyButton extends JButton {
		int		x,y;
		public MyButton(int x,int y) {
			super();
			this.x = x; this.y = y;
		}
	}
	class MyBtnEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MyButton btn = (MyButton) e.getSource();
			Icon win ;
			if (endGame) return;
			if(btn.getIcon() != null) {
				setTitle("이 버튼은 "+
						((btn.getIcon()==X)? "X다." : "O다."));
				return;
			}
			
			if(myTurn) {
				myTurn = false;
				btn.setIcon(O);
				lab.setText("[Tic-Tac-Toe] 다음 차례는 X");
				
			}
			else {
				myTurn = true;
				btn.setIcon(X);
				lab.setText("[Tic-Tac-Toe] 다음 차례는 O");
			} 
			if ((win = gameWinner()) != null) {
				if (win == O) lab.setText("[Tic-Tac-Toe] O가 승리함!!");
				else lab.setText("[Tic-Tac-Toe] X가 승리함!!");
				endGame= true;
			
		}
		
	}
	}
	class MyKeyHandler implements KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) { //유니코드 입력했을때만
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int 	Code = e.getKeyCode();
			
			if(Code == KeyEvent.VK_ESCAPE) {			
				for(int i=0; i<mybtns.length;i++) {
						for(int j=0; j<mybtns[i].length;j++) {
								mybtns[i][j].setIcon(null);
						}
					}
				lab.setText("[Tic-Tac-Toe] 다음 차례는 O");
			}
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public static Icon gameWinner(){

        for(int i = 0; i < mybtns.length; i++){
            if(mybtns[i][0].getIcon() == mybtns[i][1].getIcon() && mybtns[i][1].getIcon() == mybtns[i][2].getIcon() && mybtns[i][0].getIcon()!= null ){
                return mybtns[i][0].getIcon();
            }
            if(mybtns[0][i].getIcon() == mybtns[1][i].getIcon() && mybtns[1][i].getIcon() == mybtns[2][i].getIcon() && mybtns[0][i].getIcon() != null ){
                return mybtns[0][i].getIcon();
            }
        }

        if(mybtns[0][0].getIcon() == mybtns[1][1].getIcon() && mybtns[1][1].getIcon() == mybtns[2][2].getIcon() && mybtns[0][0].getIcon() != null){
            return mybtns[0][0].getIcon();
        }

        if(mybtns[0][2].getIcon()== mybtns[1][1].getIcon() && mybtns[1][1].getIcon() == mybtns[2][0].getIcon() && mybtns[0][2].getIcon() !=  null){
            return mybtns[0][2].getIcon();
        }

        return null;
    }
}