import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HW2 extends JFrame {
	public static MyLabel LT[], Lab;
	public HW2(String[] inputs, int type) throws InterruptedException {
		setTitle("Timer Example");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(1,0));

		LT = new MyLabel[inputs.length];
		Color firstC = null;
		switch (type) {
		case 1:
			firstC = Color.red;
			break;
		case 2:
			firstC = Color.blue;
			break;
		case 3:
			firstC = Color.pink;
			break;
		case 4:
			firstC = Color.cyan;
			break;
		case 5:
			firstC = Color.green;
			break;
		default:
			break;
		}
		for(int i=0; i<inputs.length; i++) {
			c.add(LT[i] = new MyLabel(inputs[i],firstC,200));
		}
		
		setVisible(true);
		setLocationRelativeTo(null);
		pack();
		
	}
	public static void main(String[] args) throws InterruptedException {
		String input = args[0];
		int type = Integer.parseInt(args[1]);
		String inputs[] = input.split("X");
		
		new HW2(inputs, type);
		while(true) {
			switch (type) {
			case 1:
				LT[0].Pause();
				Thread.sleep(1000);
				for(int i=0; i<inputs.length-1; i++) {
					LT[i].Pause();
					LT[i+1].Pause();
					Thread.sleep(1000);
				}
				LT[inputs.length-1].Pause();
				break;
			case 2:
				for(int i=1; i<inputs.length; i+=2) {
					LT[i].Pause();
					LT[i].col = Color.black;
					Thread.sleep(1000);
				}
				break;
			case 3:
				for(int i=0; i<inputs.length; i++) {
					LT[i].Pause();
					LT[inputs.length-i-1].Pause();
					Thread.sleep(1000);
				}
				break;
			case 4:
				for(int i=0; i<inputs.length; i+=2) {
					LT[i].Pause();
					Thread.sleep(1000);
				}
				break;
			case 5:
				for(int i=0; i<inputs.length; i+=2) {
					LT[i].Pause();
				}
				Thread.sleep(1000);
				for(int i=0; i<inputs.length; i++) {
					LT[i].Pause();
				}
				break;
			default:
				break;
			}
		}
		
	}
	
}
class MyLabel extends JLabel implements Runnable {
	Color 	col;
	
	int 	delay;
	int		n;
	boolean	pause = false, cont=true;
	Thread	th;
	public MyLabel(String n, Color c, int d) {
		th = new Thread(this, n);
		this.n = 0;
		col = c;
		delay = d;
		setText(n);
		setOpaque(true);
		setBackground(Color.white);
		setForeground(Color.black);
		setFont(new Font("Arial", Font.BOLD, 24));
		setHorizontalAlignment(SwingConstants.CENTER);
		
		th.start();
	}
	public void Pause() {
		if(pause) pause = false;
		else pause = true;
		th.interrupt();
	}
	public void setBasicColor() {
		setBackground(Color.white);
		setForeground(Color.black);
	}
	public void setWorkingColor() {
		setBackground(col);
		setForeground(Color.white);
	}
	public void run() {
		HW2 root = (HW2) getTopLevelAncestor();
		boolean On = true;
		while(cont) {
			setWorkingColor();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				;
			}
			if(pause) {
				setBasicColor();
				while(pause) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						if(!pause) break; 
					}
				}
			}
		}
		
	}
}