import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.DirectColorModel;
import java.util.EventListener;

import javax.naming.ldap.Rdn;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HW1 extends JFrame {
	private JPanel jp1 = new JPanel(new GridLayout(3,1)),jp2 = new JPanel(new GridLayout(2,1)),jp3 = new JPanel(new GridLayout(3,1));
	private JButton LL[] = {new JButton("시작"), new JButton("일시중단"), new JButton("초기화")},
			arrow[] = {new JButton(""),new JButton("")};
	private ImageIcon direct[] = {new ImageIcon("up.png"), new ImageIcon("down.png")};
	private JRadioButton hms[] = {new JRadioButton("시"), new JRadioButton("분"),new JRadioButton("초")};
	public static LabTimer LT;
	private boolean stoptime = false;
	private ButtonGroup g = new ButtonGroup();
	private int rb;
	public HW1() {
		setTitle("Timer Example");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(1, 0));
		
		for(int i=0;i < LL.length;i++) {
			jp1.add(LL[i]);
			LL[i].addActionListener(new MybtnHandler());
		}
		c.add(jp1);
		
		
		c.add(LT = new LabTimer("Label"));
		for(int i=0;i < arrow.length;i++) {
			arrow[i].setIcon(direct[i]);
			arrow[i].setBackground(Color.white);
			arrow[i].setBorderPainted(false);
			arrow[i].addActionListener(new MybtnHandler());
			jp2.add(arrow[i]);
		}
		c.add(jp2);
		
		for(int i=0;i < hms.length;i++) {
			hms[i].setBackground(Color.white);
			hms[i].setBorderPainted(false);
			hms[i].addItemListener(new MyItemHandler());
			jp3.add(hms[i]);
			g.add(hms[i]);
		}
		hms[1].setSelected(true);
		c.add(jp3);
		
		setPreferredSize(new Dimension(900, 200));
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}
	class MyItemHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(hms[0].isSelected()) rb = 0;
			else if(hms[1].isSelected()) rb = 1;
			else if(hms[2].isSelected()) rb = 2;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new HW1();
	}
	class MybtnHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String bName = b.getText();
				
			switch (bName) {
			case "시작":
				if(!stoptime) {
					LT.Pause();
					stoptime = true;
					LL[2].setEnabled(false);
				}
				break;
			case "일시중단":
				if(stoptime) {
					LT.Pause();
					stoptime = false;
					LL[2].setEnabled(true);
				}
				break;
			case "초기화":
				LT.Reset();
				break;
			case "":
				if(b.getIcon() == direct[0]) {
					if(rb == 0) {
						LT.hour++;
					}
					else if(rb == 1) {
						LT.min++;
					}
					else if(rb == 2) {
						LT.sec++;
					}
				}
				else if(b.getIcon() == direct[1]) {
					if(rb == 0 && LT.hour >0) LT.hour--;
					else if(rb == 1 && LT.min >0) LT.min--;
					else if(rb == 2 && LT.sec >0) LT.sec--;
				}
				LT.setText(String.format("%02d : %02d : %02d",LT.hour,LT.min,LT.sec));
				break;
			default:
				break;
			}
			
		}
		
	}
}
class LabTimer extends JLabel implements Runnable {
	int		sec,hour,min;
	boolean	pause = true, cont=true;
	Thread	th;
	public LabTimer(String n) {
		th = new Thread(this, n);
		sec=hour=min=0;
		setText(String.format("%2d : %2d : %2d",hour,min,sec));
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setFont(new Font("Arial", Font.BOLD, 38));
		setHorizontalAlignment(SwingConstants.CENTER);
		
		th.start();
	}
	public void Pause() { 
		if(pause) pause = false;
		else pause = true; 
		th.interrupt(); 
		}
	public void Reset() { 
		hour=min=sec=0;
		th.interrupt();
		}
	public void run() {
		HW1 root = (HW1) getTopLevelAncestor();
		while(cont) {
			setText(String.format("%02d : %02d : %02d",hour,min,sec));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				;
			}
			if(pause) {
				while(pause) {
					root.setTitle(String.format("[%s] Paused\n", th.getName()));
					setText(String.format("%02d : %02d : %02d",hour,min,sec));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						if(!pause) break;
					}
				}
			}
			if(sec ==0 && hour ==0 && min ==0) {
				while(true) {
					setBackground(Color.red);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setBackground(Color.blue);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			else {
				sec--;
				if(sec==-1) {
					min--;
					sec =59;
			}
				if(min==-1) {
					hour--;
					min =59;
			}
			}
			
		}
	}
}
