import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class HW1 extends JFrame implements ActionListener {
	private static	JTextField	outPane = new JTextField("0", 10);
	private	static final String DIV = "\u00f7", MINUS = "\u2212", PLUS = "\u002B", MULTI = "\u00D7", DOT = "\u2219", SIGNS = "\u207a/\u208B";
	private static final GridBagConstraints gbc =  new GridBagConstraints();
	private static Container c;
	private static String op1,op2,operator;
	public HW1() {
		String	btnName[][] = {
				{"EXIT","CE","%", DIV},
				{"7","8","9", MULTI},
				{"4","5","6", MINUS},
				{"1","2","3", PLUS},
				{SIGNS,"0",DOT,"="}
		};
		JButton btn;
		c= getContentPane();
		setTitle("[과제 #1] 계산기 2.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0; 
		gbc.weighty = 1.0;
		gbc.insets = new Insets(3, 3, 3, 3);
		
		gbc.ipady = 20;
		addComp(outPane,0,0,3);
		outPane.setHorizontalAlignment(JTextField.RIGHT);
		outPane.setBackground(Color.yellow);
		outPane.setForeground(Color.GREEN);
		outPane.setFont(new Font("Arial",Font.BOLD,24));
		outPane.setEditable(false); 
		gbc.ipady = 0;
		addComp(btn =  new JButton("DEL"),3,0,1);
		btn.addActionListener(this); 
		
		for(int i=0;i<btnName.length;i++) {
			for(int j=0;j<btnName[i].length;j++) {
				addComp(btn=new JButton(btnName[i][j]),j,i+1,1);
				btn.addActionListener(this);
			}
		}
		addKeyListener(new MyKeyHandler());
		setPreferredSize(new Dimension(400, 300));
		pack();
		setResizable(false); // 땡기면 크기조절 알아서
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(true); //한번만 키보드입력가능 버튼누르면 포커스가 버튼으로감
		requestFocus();
	}
	public static void main(String[] args) {
		new HW1();
	}
	private static void addComp(JComponent comp, int x, int y, int cols) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = cols; 
		c.add(comp,gbc);
		
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		String bName = b.getText();
		
		switch (bName) {
		case "EXIT":
				System.exit(0);
			break;
		case "0":case "1":case "2":case "3":case "4":case "5":case "6":case "7":case "8":case "9":
			if(outPane.getText().equals("0")) outPane.setText(bName);
			else outPane.setText(outPane.getText()+bName);
			break;
		case "DEL":
			if(outPane.getText().length() == 1) outPane.setText("0");
			else outPane.setText(outPane.getText().substring(0,outPane.getText().length()-1));
			break;
		case PLUS:case MINUS: case MULTI: case DIV: case "%":
			op1 = outPane.getText();
			outPane.setText("0");
			operator = bName;
			break;
		case "=":
			op2 = outPane.getText();
			outPane.setText(compute(op1, operator, op2));
			break;	
		case "CE":
			outPane.setText("0");
			break;
		case SIGNS:
			if(outPane.getText().equals("0")) break;
			else if(outPane.getText().charAt(0)=='-') outPane.setText(outPane.getText().substring(1));
			else outPane.setText("-"+outPane.getText());
			break;
		case DOT:
			if(!outPane.getText().contains(".")) outPane.setText(outPane.getText()+".");
			break;	
		default:
			break;
		}
	}
	private static String compute(String AS, String oper, String BS) {
		double		A, B;
		double	result=0;
		
		if(AS.charAt(0)=='-') A= Double.parseDouble(AS.substring(1))*-1;
		else A = Double.parseDouble(AS);
		
		if(BS.charAt(0)=='-') B= Double.parseDouble(BS.substring(1))*-1;
		else B = Double.parseDouble(BS);
		
		
		switch (oper) {
		case PLUS:
			result = A + B;
			break;
		case MINUS:
			result = A - B;
			break;
		case DIV:
			if(B == 0) return "Divide by ZERO";
			result = (float)A / B;
			break;
		case MULTI:
			result = A * B;
			break;
		case "%":
			if(B == 0) return "Divide by ZERO";
			if((A-(int)A) != 0) return "% 연산 불가";
			result =A % B;
			break;	
		default:
			break;
		}
		
		return (oper.equals(DIV) || (result -(int)result) != 0) ? String.format("%.3f", result) : String.format("%d", (int)result);
	}
	class MyKeyHandler implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			char	ch = e.getKeyChar();
			
			if(ch >= '0' && ch <= '9') {
				if(outPane.getText().equals("0")) outPane.setText(""+ch);
				else outPane.setText(outPane.getText()+ch); 
			}
			else {
				switch (ch) {
				case '+':case'%':
					op1 = outPane.getText();
					outPane.setText("0");
					operator = ch+"";
					break;
				case '-':
					op1 = outPane.getText();
					outPane.setText("0");
					operator = MINUS;
					break;
				case '*':
					op1 = outPane.getText();
					outPane.setText("0");
					operator = MULTI;
					break;
				case'/': 
					op1 = outPane.getText();
					outPane.setText("0");
					operator = DIV;
					break;
				case '=':
					op2 = outPane.getText();
					outPane.setText(compute(op1, operator, op2));
					break;
				case '.':
					if(!outPane.getText().contains(".")) outPane.setText(outPane.getText()+".");
					break;	
				default:
					break;
				}
			
			
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int		code = e.getKeyCode();
			
			if(code == KeyEvent.VK_ESCAPE) System.exit(0);
			
			switch (code) {
			case KeyEvent.VK_ENTER:
				op2 = outPane.getText();
				outPane.setText(compute(op1, operator, op2));
				break;
			case KeyEvent.VK_F1:
				outPane.setText("0");
				break;
			case KeyEvent.VK_BACK_SPACE:
				if(outPane.getText().length() == 1) outPane.setText("0");
				else outPane.setText(outPane.getText().substring(0,outPane.getText().length()-1));
				break;
			case KeyEvent.VK_F2:
				if(outPane.getText().equals("0")) break;
				else if(outPane.getText().charAt(0)=='-') outPane.setText(outPane.getText().substring(1));
				else outPane.setText("-"+outPane.getText());
				break;	
	
			default:
				break;
			}
	
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}


