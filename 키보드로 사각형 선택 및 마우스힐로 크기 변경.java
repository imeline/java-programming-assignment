import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class HW1 extends JFrame{
	private MyRect rects[] = new MyRect[4];
	private MyPanel p;
	private int wid,hei;
	private int focusRect_Idx=-1;
	public HW1(int width, int height) {
		wid = width; hei = height;
		setTitle("[과제 #1]");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(p = new MyPanel());
		for(int i=0; i<rects.length; i++) {
			rects[i] = new MyRect(Rand.r(0, wid*2/3), Rand.r(0, hei*2/3), Rand.r(10, wid-10), Rand.r(10, hei-10));
			}
		addMouseWheelListener(new MyWheelHander());
		addKeyListener(new MyKeyHandler());
		setSize(width,height);
		setVisible(true);
		setLocationRelativeTo(null);
		requestFocus();
	} 
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setFont(new Font("고딕체",Font.BOLD,20));
		
			for(int i=0; i<rects.length; i++) {
				g.setColor(Color.black);
				g.drawRect(rects[i].x, rects[i].y, rects[i].w, rects[i].h);
				if(i==focusRect_Idx) g.setColor(Color.red);
				else g.setColor(Color.blue);
				g.drawString("[사각형 "+(i+1)+"]",rects[i].x, rects[i].y+20);
			}
			
		}
	}
	class MyKeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			
			switch (code) {
			case KeyEvent.VK_F1:
				focusRect_Idx = 0;
				setTitle("[과제 #1] 사격형1 선택됨");
				break;
			case KeyEvent.VK_F2:
				focusRect_Idx = 1;
				setTitle("[과제 #1] 사격형2 선택됨");
				break;
			case KeyEvent.VK_F3:
				focusRect_Idx = 2;
				setTitle("[과제 #1] 사격형3 선택됨");
				break;
			case KeyEvent.VK_F4:
				focusRect_Idx = 3;
				setTitle("[과제 #1] 사격형4 선택됨");
				break;
			case KeyEvent.VK_UP:
				if(rects[focusRect_Idx].y-10 >= 0) 
					rects[focusRect_Idx].y-=10;
				break;
			case KeyEvent.VK_DOWN:
				if(rects[focusRect_Idx].y+10 < hei-40) 
					rects[focusRect_Idx].y+=10;
				break;
			case KeyEvent.VK_LEFT:
				if(rects[focusRect_Idx].x-10 >= 0) 
					rects[focusRect_Idx].x-=10;
				break;
			case KeyEvent.VK_RIGHT:
				if(rects[focusRect_Idx].x+10 < wid-20) 
					rects[focusRect_Idx].x+=10;
				break;
			default:
				break;
			}
			repaint();
			requestFocus();
		}

	}
	class MyWheelHander implements MouseWheelListener{
		public void mouseWheelMoved(MouseWheelEvent e) {
			if(e.getWheelRotation()<0) {
				rects[focusRect_Idx].w+=10;
				rects[focusRect_Idx].h+=10;
			}
			else {
				if(rects[focusRect_Idx].w -10 > 0 && rects[focusRect_Idx].h -10 > 0) {
					rects[focusRect_Idx].w-=10; 
					rects[focusRect_Idx].h-=10;
				}
			}
			repaint();
			requestFocus();
			}
		
	}
	
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("오류! 가로 세로, 2개의 인자를 입력하시오");
			System.exit(0);
		}
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		
		new HW1(width, height);
	}

}
class MyRect {
	int x,y,w,h;
	public MyRect(int x,int y,int width,int height) {
		this.x = x; this.y=y; w=width; h=height;
	}
}
