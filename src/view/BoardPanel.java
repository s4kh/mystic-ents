package view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.board.Square;
import utils.BoardUtils;
import utils.GameConfig;

/**
 * Inner panel for displaying game objects.
 * 
 * @author skh, mark
 *
 */
public class BoardPanel extends JPanel {
	
	SquareView[][] squareViewArray;

	public BoardPanel() {
		super();
		this.setPreferredSize(new Dimension(GameConfig.getDefaultWidth(), GameConfig.getDefaultHeight()));
		this.setVisible(true);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Draw the squares
	 */
	private void updateBoard(Square[][] board) {
		ArrayList <Square> rangeList = new ArrayList <Square>();
		rangeList = BoardUtils.getInstance().getRangeList();
		for(int i = 0; i < rangeList.size(); i++) {
			squareViewArray[ rangeList.get(i).getID()[0] ][ rangeList.get(i).getID()[1] ].buildView(rangeList.get(i));
		}		
	}
	
	public void buildFullBoard(Square[][] board) {
		this.removeAll();
		ArrayList <Square> rangeList = new ArrayList <Square>();
		squareViewArray = new SquareView[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				SquareView sqr = new SquareView(board[i][j]);
				squareViewArray[i][j] = sqr;
				rangeList.add(sqr.getSqrObj());
				this.add(sqr);
			}
		}
		BoardUtils.getInstance().setRangeList(rangeList);
	}
	
	public void refreshBoard(Square[][] boardData) {
		updateBoard(boardData);
		this.revalidate();
	}
	

}
