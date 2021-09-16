package graph;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Falling extends JPanel {

	public ArrayList<Money> moneys;
	private int width = 66;
	private int height = 30;

	public Falling(int score) {
		moneys = new ArrayList<Money>();
		for (int q = 0; q < score; q++) {
			int x = (int)(Math.random()*(850-width));
			int y = (int)(Math.random()*200+10);
			moneys.add(new Money(x, y, width, height));
		}
	}

	public void paintComponent(Graphics window) {

		super.paintComponent(window);
		
		window.setColor(Color.GREEN);
		for (int x = moneys.size()-1; x >= 0; x--) {
			window.fillRect(moneys.get(x).x, moneys.get(x).y, moneys.get(x).width, moneys.get(x).height);
			
			if (moneys.get(x).fall())
				moneys.remove(x);
		}
	}
	
	public int killThem(int x, int y) {
		int ret = 0;
		for (int i = moneys.size()-1; i >= 0; i--)
			if (moneys.get(i).contains(x, y)) {
				moneys.remove(i);
				ret++;
				break;
			}
		return ret;
	}
	
	public boolean hasAny() {
		return (!moneys.isEmpty());
	}

	public class Money extends Rectangle{

		public Money(int x, int y, int w, int h) {
			super(x, y, w, h);
		}

		public boolean fall() {
			y += 1;
			return (y > 650);
		}

	}

}
