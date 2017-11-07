package whack_a_mole;
import java.applet.*;
import java.awt.*;

public class MoguraApp1 extends Applet implements Runnable {
	Thread m_MoguraApp1 = null;
	int moveInterval = 500;
	int totalTime = 30000;
	int elapseTime = 0;
	boolean isRunning = false;
	
	Image mogImage;
	int left = 0, top = 0;
	int maxLeft = 220, maxTop = 140;
	int score = 0;
	
	public void init() {
		setBackground(Color.white);
		mogImage = getImage(getDocumentBase(), "Mogura.gif");
		resize(320, 240);
	}
	
	public void start() {
		if(m_MoguraApp1 == null) {
			m_MoguraApp1 = new Thread(this);
			m_MoguraApp1.start();
		}
	}
	
	public void run() {
		isRunning = true;
		while(elapseTime < totalTime) {
			try {
				timerAction();
				Thread.sleep(moveInterval);
				elapseTime = elapseTime + moveInterval;
			}
			catch(InterruptedException e) {
				stop();
			}
		}
		isRunning = false;
		repaint();
	}
	
	public void stop() {
		if(m_MoguraApp1 != null) {
			m_MoguraApp1.stop();
			m_MoguraApp1 = null;
		}
	}
	
	private void timerAction() {
		left = (int)((double)maxLeft * Math.random());
		top = (int)((double)maxTop * Math.random());
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(mogImage, left, top, this);
		if(isRunning) {
			g.drawString("得点 =" + score, 10, 20);
		} else {
			g.drawString("得点 =" + score + " <終了>", 10, 20);
		}
	}
	
	public boolean mouseDown(Event evt, int x, int y) {
		if (x < left) return true;
		if (y < top) return true;
		if (x > (left + mogImage.getWidth(this))) return true;
		if (y > (top + mogImage.getHeight(this))) return true;
		score = score + 10;
		return true;
	}

}
