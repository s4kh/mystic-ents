package controller;

import java.util.Observable;
import java.util.Observer;

import model.Board;
import view.BoardFrame;

public class BoardController implements Observer {

	private static BoardFrame boardView;
	private static Board boardState;

	public BoardController() {

		boardState = new Board();
		observe(boardState);
		// observer = new BoardObserver(boardState);

		// was created inside view, since view does not create controller moved
		// here.
		PieceActionController pieceActionController = new PieceActionController();
		boardState.init();

		// After we placed pieces inside boardState, initialize boardView
		boardView = new BoardFrame(pieceActionController, boardState.getBoardData());

		// boardState

		// Tell the View that when ever the calculate button
		// is clicked to execute the actionPerformed method
		// in the CalculateListener inner class

		// theView.addBoardListener(new BoardListener());

	}

	public void observe(Observable o) {
		o.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		Object[][] data = ((Board) o).getBoardData();
		System.out.println("Board has changed");
	}

}