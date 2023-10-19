import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HW1 extends JFrame{
	public static final int Width = 1024, Height=800;
	private Image background = new ImageIcon("backmario.jpg").getImage();
	public static  MyPanel p;
	public static Enemy mario;
	public static Fighter fighter;
	public static Bullet bullet;
	public static Timer timer;
	public static boolean readytime = true, endtime = false;
	public HW1() {
		setTitle("Shooting Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(p = new MyPanel());
		p.setLayout(null);
		p.setSize(Width,Height);
		p.setOpaque(false);
		
		addKeyListener(new MyKeyHandler());
		setSize(Width,Height);
		setVisible(true);
		mario = new Enemy("마리오", "mario.png", 5);
		p.add(mario);
		bullet = new Bullet("총알", "bullet2.png", 30, 10);
		p.add(bullet);
		fighter = new Fighter("전투기", "fighter.png", 30, 10);
		p.add(fighter);
		p.add(timer = new Timer("timer"));
		repaint();
		setLocationRelativeTo(null);
		requestFocus();
	}
	public static void main(String[] args) {
		new HW1();
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.drawImage(background, 0, 0,Width,Height, null);
		}
	}
}
class MyKeyHandler extends KeyAdapter{
	public void ReadyTime() {
		if(HW1.readytime) {
			HW1.mario.pause=false;
			HW1.bullet.pause=false;
			HW1.bullet.Fired = false;
			HW1.timer.pause=false;
			HW1.readytime = false;
		}
	}
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(HW1.endtime) {
			if(code == KeyEvent.VK_ESCAPE) {
				HW1.fighter.Reset();
				HW1.bullet.Reset();
				HW1.timer.Reset();
				HW1.mario.Reset();
				HW1.endtime = false;
				HW1.readytime = true;
			}
			return;
		}
		
		switch (code) {
		case KeyEvent.VK_SPACE:
			ReadyTime();
			HW1.bullet.Fired = true;
			HW1.bullet.setVisible(true);
			HW1.bullet.Th.interrupt(); 
			break;
		case KeyEvent.VK_LEFT:
			ReadyTime();
			if(HW1.bullet.Fired) return;
			HW1.fighter.MoveLeft();
			HW1.bullet.X = HW1.fighter.X;
			HW1.bullet.Y = HW1.fighter.Y;
			HW1.bullet.setLocation(HW1.bullet.X,HW1.bullet.Y);
			break;
		case KeyEvent.VK_RIGHT:
			ReadyTime();
			if(HW1.bullet.Fired) return;
			HW1.fighter.MoveRight();
			HW1.bullet.X = HW1.fighter.X;
			HW1.bullet.Y = HW1.fighter.Y;
			HW1.bullet.setLocation(HW1.bullet.X,HW1.bullet.Y);
			break;
		default:
			break;
		}
	}
}
class Enemy extends JLabel implements Runnable {
	int X,Ox,Y,Speed;
	boolean PlusTurnX=true,	pause = true;
	Thread Th;
	ImageIcon Ico;
	public Enemy(String n,String path, int spd) {
		X = Ox =  HW1.Width/2;
		Y = 0;
		Speed = spd;
		Ico = new ImageIcon(path);
		setIcon(Ico);
		setBounds(X,Y,Ico.getIconWidth(),Ico.getIconHeight());
		Th = new Thread(this,n);
		Th.start();
	}
	public void HitTwinkle() {
		if(HW1.bullet.CheckHit(HW1.mario)) {
			for(int i=0; i<20;i++) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e1) {
					}
				if(i% 2 == 0) setVisible(true);
				else setVisible(false);
		}
			Reset();
			setVisible(true);
			HW1.timer.score++;
		}
	}
	public void Reset() {
		X = HW1.p.getWidth()/2 - Ico.getIconWidth()/2; 
		Y = 0;
		setLocation(X,Y);
		repaint();
	}
	public void run() {
		while(true) {
			while(pause) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					if(!pause) break;
				}
			}
			int pm = (int) Math.pow(-1, Rand.r(1,2)) ;
			if(PlusTurnX) {
				int movement =  Rand.r(1, HW1.p.getWidth()/Speed)*Speed;
				while( (0 > X+pm *movement) || (X + Ico.getIconWidth()+ pm *movement > HW1.p.getWidth()) )   {
					pm = (int) Math.pow(-1, Rand.r(1,2)) ;
					movement = Rand.r(1, HW1.p.getWidth()/Speed)*Speed;
				}
				for(int i=1; i<=movement/Speed; i++) {
					HitTwinkle();
					X += pm*Speed;
					setLocation(X,Y);
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						if(pause) break;
					}
				}
				PlusTurnX = false;
			}
			else {
				int movement = Rand.r(1, HW1.p.getHeight()/2/Speed)*Speed;
				while( (0 > Y+pm *movement) || (Y + Ico.getIconHeight()+ pm *movement > HW1.p.getHeight()*2/3) )   {
					pm = (int) Math.pow(-1, Rand.r(1,2)) ;
					movement = Rand.r(1, HW1.p.getHeight()/2/Speed)*Speed;
				}
		
				for(int i=1; i<=movement/Speed; i++) {
					HitTwinkle();
					Y += pm*Speed;
					setLocation(X,Y);
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						if(pause) break;
					}
				}
				PlusTurnX = true;
			}	
	}
}
}
class Bullet extends JLabel implements Runnable {
	int X,Y,Speed,Gap;
	boolean Fired, pause = true;
	Thread Th;
	ImageIcon Ico;
	public Bullet(String n,String path, int spd, int g) {
		Ico = new ImageIcon(path);
		setIcon(Ico);
		Reset();
		Speed = spd;
		Gap = g;
		Fired = false; 
		setBounds(X,Y,Ico.getIconWidth(),Ico.getIconHeight());
		Th = new Thread(this,n);
		setVisible(false);
		Th.start();
	}
	public void Reset() {
		X = HW1.p.getWidth()/2- Ico.getIconWidth()/2; 
		Y = HW1.p.getHeight() - Ico.getIconHeight();
		setVisible(false);
		repaint();
	}
	public boolean CheckHit(Enemy e) {
		if( (X + Ico.getIconWidth()  <= e.X+30) || (e.X + e.Ico.getIconWidth()-30 <= X) || 
				(Y + Ico.getIconHeight() <= e.Y+30) || (e.Y + e.Ico.getIconWidth()-30 <= Y) ) return false;
		else return true;
	}
	public void run() {
		while(true) {
			while(pause) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					if(!pause) break;
				}
			}
			if(Fired) { 
				if(CheckHit(HW1.mario) || Y <=0) { 
					for(int i=0; i<20; i++) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							;
						}
						if(i% 2 == 0) setVisible(true);
						else setVisible(false);
					}	
					Fired = false; 
					X = HW1.fighter.X;
					Y = HW1.fighter.Y; 
					setVisible(false); 
				} 
				else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						;
					}
					Y-=Speed;
					if(Y < 0) Y = 0;
				}
			} 
			else {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					continue;
				}
			}
			setLocation(X,Y);
			repaint();
		}
	}
}
class Timer extends JLabel implements Runnable {
	int			sec,min,score;
	boolean	pause = true;
	Thread	Th;
	public Timer(String n) {
		sec=30; min=1; score=0;
		setText(String.format("<html>점수 : %d<br>남은 시간 : %d:%02d</html>",score,min,sec));
		setForeground(Color.white);
		setFont(new Font("Arial", Font.BOLD, 32));
		setBounds(50, HW1.Height-400, 500, 500);
		Th = new Thread(this, n);
		Th.start();
	}
	public void Reset() { 
		sec=30; min=1; score=0;
		setText(String.format("<html>점수 : %d<br>남은 시간 : %d:%02d</html>",score,min,sec));
	}
	public void run() {
		while(true) {
			while(pause) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					if(!pause) break; 
				}
			}
			setText(String.format("<html>점수 : %d<br>남은 시간 : %d:%02d</html>",score,min,sec));
			if(sec==0 && min == 0) {
				HW1.endtime = true;
				pause=true;
				HW1.bullet.pause = true;
				HW1.mario.pause = true;
				HW1.mario.Th.interrupt();
				continue;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				;
			}
			sec--;
			if(sec==-1) {
				min--;
				sec = 59;
			}
			repaint();
		}
	}
}

class Fighter extends JLabel {
	int X,Y,Speed,Gap;
	ImageIcon Ico;
	public Fighter(String n,String path, int spd, int g) {
		Ico = new ImageIcon(path);
		setIcon(Ico);
		Reset();
		Speed = spd;
		Gap = g;
		setBounds(X,Y,Ico.getIconWidth(),Ico.getIconHeight());
	}
	public void Reset() {
		X = HW1.p.getWidth()/2- Ico.getIconWidth()/2; 
		Y = HW1.p.getHeight() - Ico.getIconHeight();
		setLocation(X,Y);
		repaint();
	}
	public void MoveLeft() {
		if(X -Gap<=0) return;
		X -= Speed;
		setLocation(X,Y);
		repaint();
	}
	public void MoveRight() {
		if(X +Gap + Ico.getIconWidth()>= HW1.p.getWidth()) return;
		X += Speed;
		setLocation(X,Y);
		repaint();
	}
}