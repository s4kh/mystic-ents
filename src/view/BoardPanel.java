package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import controller.PieceActionController;
import model.Board;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh
 *
 */
public class BoardPanel extends JPanel implements Observer {

	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 700;
	private PieceActionController pieceActionController;
	private int timerDelay = 1000;
	private final Timer gameTimer;
	// Testing piece for demo
	private JLabel piece;

	public BoardPanel(PieceActionController pieceActionController, Object[][] boardData) {
		super();

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new GridLayout(Board.ROW_COL, Board.ROW_COL));
		addMouseListener(pieceActionController);

		setupSquares(boardData);

		// Updater of the view
		gameTimer = new Timer(timerDelay, timerListener);
		// gameTimer.start();
	}

	/**
	 * Draw the squares
	 */
	private void setupSquares(Object[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Component comp=null;
				if(board[i][j]==null){
					comp = new SquareView();
				}else{
					comp = new PieceView(Color.BLUE, true);
				}
				this.add(comp);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public JLabel getPiece() {
		return piece;
	}

	public void setPiece(JLabel piece) {
		this.piece = piece;
	}

	ActionListener timerListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("listener triggered:" + e.getActionCommand());
		}
	};

	public void mousePressed(MouseEvent e) {
		piece = null;
		Component c = findComponentAt(e.getX(), e.getY());

		if (c instanceof JPanel)
			return;

		Point parentLocation = c.getParent().getLocation();
		int xAdjustment = parentLocation.x - e.getX();
		int yAdjustment = parentLocation.y - e.getY();
		piece = (JLabel) c;
		piece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
		piece.setSize(piece.getWidth(), piece.getHeight());
		add(piece, JLayeredPane.DRAG_LAYER);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
