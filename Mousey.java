package kewl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Mousey implements MouseListener {
	
	public Queue<int[]> orders = new LinkedList<int[]>();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int[] i = {e.getX(), e.getY()};
		orders.add(i);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
}
