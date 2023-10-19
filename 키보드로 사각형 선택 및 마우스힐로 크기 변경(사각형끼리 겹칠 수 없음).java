import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.TextUI;

public class HW2 extends JFrame{
	private MyRectangle rects[] = new MyRectangle[4];
	private MyPanel p;
	private int wid,hei;
	private int focusRect_Idx=-1;
	public HW2(int width, int height) {
		wid = width; hei = height;
		int bigger;
		setTitle("[과제 #2]");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		rects[0] = new MyRectangle(Rand.r(0, wid/2-10), Rand.r(0, hei/3-10), Rand.r(10, wid/2), Rand.r(10, hei/2));
		rects[1] = new MyRectangle(Rand.r(rects[0].x+rects[0].w+1, wid-30), Rand.r(0, hei/3-10), Rand.r(10, wid*2/3), Rand.r(10, hei/2));
		bigger = rects[0].y+rects[0].h;
		if(bigger < rects[1].y+rects[1].h) bigger =rects[1].y+rects[1].h;
		rects[2] = new MyRectangle(Rand.r(0, wid/3-10), Rand.r(bigger+1, hei-40), Rand.r(10, wid/2), Rand.r(10, hei*2/3));
		rects[3] = new MyRectangle(Rand.r(rects[2].x+rects[2].w+1, wid-30), Rand.r(bigger+1, hei-40), Rand.r(10, wid*2/3), Rand.r(10, hei*2/3));
		
		setContentPane(p = new MyPanel());
		addMouseWheelListener(new MyWheelHander());
		addKeyListener(new MyKeyHandler());
		setSize(width,height);
		setVisible(true);
		setLocationRelativeTo(null);
		requestFocus();
	} 
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setFont(new Font("고딕체",Font.BOLD,18));
		
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
				setTitle("[과제 #2] 사격형1 선택됨");
				break;
			case KeyEvent.VK_F2:
				focusRect_Idx = 1;
				setTitle("[과제 #2] 사격형2 선택됨");
				break;
			case KeyEvent.VK_F3:
				focusRect_Idx = 2;
				setTitle("[과제 #2] 사격형3 선택됨");
				break;
			case KeyEvent.VK_F4:
				focusRect_Idx = 3;
				setTitle("[과제 #2] 사격형4 선택됨");
				break;
			case KeyEvent.VK_UP:
				if(rects[focusRect_Idx].y-10 >= 0 && NotOverlap(focusRect_Idx, 0, -10,0,0)) {
					rects[focusRect_Idx].y-=10;
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
				}
				else setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 조정 불가");
				break;
			case KeyEvent.VK_DOWN:
				if(rects[focusRect_Idx].y+10 < hei-40 && NotOverlap(focusRect_Idx, 0, 10,0,0)) {
					rects[focusRect_Idx].y+=10;
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
				}
				else setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 조정 불가");
				break;
			case KeyEvent.VK_LEFT:
				if(rects[focusRect_Idx].x-10 >= 0 && NotOverlap(focusRect_Idx, -10, 0,0,0)) {
					rects[focusRect_Idx].x-=10;
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
				}
				else setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 조정 불가");
				break;
			case KeyEvent.VK_RIGHT:
				if(rects[focusRect_Idx].x+10 < wid-20 && NotOverlap(focusRect_Idx, 10, 0,0,0)) {
					rects[focusRect_Idx].x+=10;
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
				}
				else setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 조정 불가");
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
				if(!NotOverlap(focusRect_Idx,0,0, 10, 10)) {
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 조정 불가");
				}
				else {
					rects[focusRect_Idx].w+=10;
					rects[focusRect_Idx].h+=10;
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
				}
			}
			else {
				if(rects[focusRect_Idx].w -10 > 0 && rects[focusRect_Idx].h -10 > 0) {
					setTitle("[과제 #2] "+(focusRect_Idx+1)+"번 사각형 선택됨");
					rects[focusRect_Idx].w-=10; 
					rects[focusRect_Idx].h-=10;
				}
			}
			repaint();
			requestFocus();
			}
		
	}
	boolean NotOverlap(int now_idx,int plus_x,int plus_y, int plus_w, int plus_h) {
		boolean[] all_bool = new boolean[3];
		int j=0;
		
		for(int i=0; i<rects.length; i++) {
			if(i==now_idx) continue;
			if(rects[i].x+rects[i].w < rects[now_idx].x+plus_x||
					 rects[now_idx].x+ plus_x+rects[now_idx].w + plus_w< rects[i].x ||
					 rects[i].y + rects[i].h < rects[now_idx].y +plus_y||
					 rects[now_idx].y+plus_y+ rects[now_idx].h + plus_h< rects[i].y ) {
				all_bool[j++]=true;
			}
			else {
				all_bool[j++]=false;
			}
		}
		if(all_bool[0] && all_bool[1] && all_bool[2]) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("오류! 가로 세로, 2개의 인자를 입력하시오");
			System.exit(0);
		}
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		
		new HW2(width, height);
	}

}
class MyRectangle {
	int x,y,w,h;
	public MyRectangle(int x,int y,int width,int height) {
		this.x = x; this.y=y; w=width; h=height;
	}
}
