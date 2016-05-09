package model.state;

import controller.ActionController;
import model.board.BoardMemento;
import model.board.Square;

public class StateAttack implements IGameState {

	private StateAttack() { }

	private static StateAttack instance;

	public static StateAttack getInstance(ActionController a) {
		if (instance == null) {
			instance = new StateAttack();
		}
		return instance;
	}	
	
	@Override
	public void startAction(ActionController a, Square s) {
		if (!s.getInRange()) return;
		System.out.println("Start attack");
		
		//Save state before making action
		a.saveToMemento(new BoardMemento(a.getActiveSquare(), s));
		
		a.getActivePiece().attackOut(s.getOccupant());
		int targetHealthValue = s.getOccupant().getTraitSet().getHealthTrait().getTraitValue();
		if(targetHealthValue < 1){
			s.getOccupant().setInPlay(false);
			s.setOccupant(null);
			a.getBoardController().getBoardData().doCellsUpdate();
		}		
		endAction(a, s);
	}

	@Override
	public void endAction(ActionController a, Square s) {
		System.out.println("End attack");
		a.setActivePiece(null);
		a.getBoardController().clearRangeCells();
		a.changeState(StateMove.getInstance(a));		
	}

	@Override
	public void updateTurn(ActionController a) {
		// TODO Auto-generated method stub
		
	}


}