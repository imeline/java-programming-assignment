import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Dimension2D;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalIconFactory.FileIcon16;

public class P1 extends JFrame {
	
	protected double n2=0,n1=0;
	public P1() {
		JPanel p1 = new JPanel(new FlowLayout()), p2 = new JPanel(new FlowLayout()),p3 = new JPanel(new FlowLayout()); 
		JLabel lab, lab1 ,lab2,lab3;
		setTitle("Text Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		Container c = getContentPane();
		
		JTextField txtF = new JTextField(8);
		JTextArea txtA = new JTextArea(5, 8);
		lab = new JLabel("입력값 :   ");
		lab1= new JLabel();
		lab2= new JLabel();
		lab3= new JLabel();
		p1.add(lab);
		p1.add(txtF);
		txtF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = (JTextField) e.getSource();
				txtA.append(tf.getText()+"\n");
				
				if(Double.parseDouble(tf.getText()) % 1 == 0.0) {
					if(Double.parseDouble(tf.getText())==0) {
						n1=n2=0;
					}
					else {
						n2 += Double.parseDouble(tf.getText());
						n1 += 1.0;
					}
						
					lab2.setText(String.format("합계:%d",(int)n2));
					lab1.setText(String.format("개수:%d",(int)n1));
					if(n2==0 || n1==0) {
						lab3.setText("평균 :"+0);
					}
					else {
						lab3.setText(String.format("평균 :%.3f", (Double)(n2/n1)));
					}
					
					tf.setText(null);
				}
				
			}
		});
		lab = new JLabel("입력된 숫자들 : ");
		p2.add(lab);
		p2.add(new JScrollPane(txtA));
		txtA.setEditable(false);
		add(p1);
		add(p2);
		p3.add(lab1);
		p3.add(lab2);
		p3.add(lab3);
		add(p3);
		
		setPreferredSize(new Dimension(200, 200));
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new P1();
	}
}