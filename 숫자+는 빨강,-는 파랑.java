import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class HW3  extends JFrame{
		private static Container c;
		private static JButton b ;
		private static boolean tf = false;
		private static boolean tf_2 = false;
		private static JTextField outPane = new JTextField("0",5){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		private static final ImageIcon up = new ImageIcon("up.png"),down = new ImageIcon("down.png");
		public HW3() {
			setTitle("문제 #3");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new FlowLayout());
			c = getContentPane();
			c.setBackground(Color.white);
			addKeyListener(new MyKeyHandler());
			
			
	        add(outPane);
			outPane.setFont(new Font("Arial",Font.BOLD,24));
			outPane.setHorizontalAlignment(JTextField.RIGHT);
			outPane.setBackground(Color.white);
			
			outPane.setEditable(false);
			
				
			b = new JButton();
			b.setIcon(up);
			b.setBorderPainted(false);
			b.addKeyListener(new MyKeyHandler());
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					outPane.setText(String.format("%d",Integer.parseInt(outPane.getText())+1));
					if(Integer.parseInt(outPane.getText()) > 0) outPane.setForeground(Color.blue);
					else if (Integer.parseInt(outPane.getText()) == 0) outPane.setForeground(Color.black);
					else outPane.setForeground(Color.red);
				}
			});
			add(b);
			b = new JButton();
			b.setIcon(down);
			b.setBorderPainted(false);
			b.addKeyListener(new MyKeyHandler());
			b.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					outPane.setText(String.format("%d",Integer.parseInt(outPane.getText())-1));
					if(Integer.parseInt(outPane.getText()) > 0) outPane.setForeground(Color.blue);
					else if (Integer.parseInt(outPane.getText()) == 0) outPane.setForeground(Color.black);
					else outPane.setForeground(Color.red);
				}
			});
			add(b);
			
			
			setSize(500,500);  
			pack();
			setResizable(false);
			setLocationRelativeTo(null);
			setVisible(true);	
			setFocusable(true);
			requestFocus();
		}
		
		
		
	public static void main(String[] args) {
		new HW3();

	}
	class MyKeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int 	Code = e.getKeyCode();
			char 	ch = e.getKeyChar();

			String  s = outPane.getText();
			if(Code == KeyEvent.VK_ESCAPE) System.exit(0);
			
			
			switch (Code) {
			case  KeyEvent.VK_F1:
				if(tf = false) tf = true;
				else tf = false;
				break;
			case KeyEvent.VK_F2:
				if(tf_2 = false) tf_2 = true;
				else tf_2 = false;
				break;
			case  KeyEvent.VK_UP:
				outPane.setText(String.format("%d",Integer.parseInt(outPane.getText())+1));
				if(Integer.parseInt(outPane.getText()) > 0) outPane.setForeground(Color.blue);
				else if (Integer.parseInt(outPane.getText()) == 0) outPane.setForeground(Color.black);
				else outPane.setForeground(Color.red);
				
				if(tf && Integer.parseInt(outPane.getText()) <0) {
					outPane.setText(s);
				}
				if(tf_2 && Integer.parseInt(outPane.getText()) >0) {
					outPane.setText(s);
				}
				break;
			case  KeyEvent.VK_DOWN:
				outPane.setText(String.format("%d",Integer.parseInt(outPane.getText())-1));
				if(Integer.parseInt(outPane.getText()) > 0) outPane.setForeground(Color.blue);
				else if (Integer.parseInt(outPane.getText()) == 0) outPane.setForeground(Color.black);
				else outPane.setForeground(Color.red);
				
				if(tf && Integer.parseInt(outPane.getText()) <0) {
					outPane.setText(s);
				}
				if(tf_2 && Integer.parseInt(outPane.getText()) >0) {
					outPane.setText(s);
				}
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
