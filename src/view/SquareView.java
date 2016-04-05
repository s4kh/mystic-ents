package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.lang.reflect.Field;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.PieceActionController;
import model.GameUtils;
import model.Square;

public class SquareView extends JPanel implements MouseListener {

	// Used for mapping with the back-end model
	private int[] ID = new int[2];
	public Square sqrObj;
	private PieceActionController pac;

	public SquareView(PieceActionController p, Square o) {
		super();
		pac = p;
		this.sqrObj = o;
		this.setLayout(new BorderLayout());
		this.setBorder(new LineBorder(Color.BLACK, 1));
		//BorderFactory.createStrokeBorder(new BasicStroke(5.0f)
		this.setPreferredSize(new Dimension(20, 20));

		//System.out.println(o.getID()[0] + " : " + o.getID()[1] + " : " + o.getOccupant());
		/*
		 * Elements based on Square model:
		 */

		this.setBackground(getBackgroundColor(o));

		addMouseListener(this);
	}

	private Color getBackgroundColor(Square o) {
		Color bg = Color.WHITE;
		bg = o.getInrange() ? Color.YELLOW : bg;
		bg = !o.getAccessible() ? Color.BLACK : bg;
		bg = o.getTeamTower() ? Color.GREEN : bg;
		if (o.getOccupant() != null) {
			bg = GameUtils.stringToColor(o.getOccupant().getTeam().name(), bg);
		}		
		return bg;
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		pac.performAction(arg0, this);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
