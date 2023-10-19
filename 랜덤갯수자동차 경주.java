
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class P5 extends JFrame{
	public static final int Width = 1024, Height=800;
	private Image background = new ImageIcon("bg1.png").getImage();
	public static  MyPanel p;
	public static Vector<Enemy> carth = new Vector<>();
	public static int n;
	public static ImageIcon[] img = {new ImageIcon("car1.png"),new ImageIcon("car2.png"),new ImageIcon("car3.png"),new ImageIcon("car4.png"),
			new ImageIcon("car5.png"),new ImageIcon("car6.png"),new ImageIcon("car7.png"),new ImageIcon("car8.png"),new ImageIcon("car9.png")};
	
	public P5() {
		n = Rand.r(2, 9);
		setTitle("[문제 #5] "+n+"개 자동차 경주");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(p = new MyPanel());
		p.setLayout(null);
		p.setSize(Width,Height);
		p.setOpaque(false);

		setSize(Width,Height);
		setVisible(true);
		
		
		for(int i=0; i<n; i++) {
			carth.add(new Enemy("car "+(i+1), "car"+(i+1)+".png", 0,0+i*75, Rand.r(3, 9)));
			p.add(carth.get(i));
		}
	
		
		repaint();
		setLocationRelativeTo(null);
		requestFocus();
	}
	public static void main(String[] args) {
		new P5();
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0,Width,Height, null);
		}
	}
}
class Enemy extends JLabel implements Runnable {
	int X,Y,Ox,Speed;
	boolean To_right = true, Hit = false;
	Thread Th;
	ImageIcon Ico;
	public Enemy(String n,String path, int x,int y, int spd) {
		X = Ox = x;
		Y = y;
		Speed = spd;
		Ico = new ImageIcon(path);
		setIcon(Ico);
		setBounds(X,Y,Ico.getIconWidth(),Ico.getIconHeight());
		Th = new Thread(this,n);
		Th.start();
	}
	public void run() {
		while(true) {
			P5 root = (P5) getTopLevelAncestor();
			if(X + Ico.getIconWidth() >= P5.p.getWidth()) {
				root.setTitle("[문제 #5] 최종 우승 차량은 "+Thread.currentThread().getName());
				for(int i=0; i<P5.n; i++) {
					P5.carth.get(i).Th.interrupt();
				}
			}
			else {
				X += Speed;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				break;
			}
			
			setLocation(X,Y);
			repaint();
		}
		
	}
	
}