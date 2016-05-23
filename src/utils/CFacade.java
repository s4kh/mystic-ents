package utils;

import java.util.ArrayList;

import controller.ActionController;
import controller.BoardController;
import controller.GameController;
import model.board.BoardData;
import model.board.Square;
import model.piece.Piece;
import model.piece.Team;

import view.BoardPanel;

import utils.subsystem.*;

public class CFacade {

	private static CFacade instance;

	//Subsystems
	private BoardSystem boardSystem = new BoardSystem();
	private RangeSystem rangeSystem = new RangeSystem();
	private GameRulesSystem gameRulesSystem = new GameRulesSystem();
	private FileSystem fileSystem = new FileSystem();
	private PiecesSystem piecesSystem = new PiecesSystem();
	private UndoSystem undoSystem = new UndoSystem();
	private AISystem aiSystem = new AISystem();

	private CFacade() {}

	public static synchronized CFacade getInstance() {
		if (instance == null) {
			instance = new CFacade();
		}
		return instance;
	}

	public Square[][] getRangeCells(int x, int y, Square[][] data) {
		return rangeSystem.getRangeCells(x, y, data);
	}

	public void clearRangeCells() {
		rangeSystem.clearRangeCells();
	}

	public ArrayList <Square> getRangeList(Square[][] data) {
		return rangeSystem.populateRangeList(data);
	}
	
	public void updateBoard() {
		boardSystem.updateBoard(rangeSystem.getRangeList());
	}
	
	public void disableBoard(BoardPanel boardPanel) {
		boardSystem.disableBoard(boardPanel);
	}
	
	public void buildFullBoard(BoardPanel boardPanel, Square[][] data) {
		rangeSystem.setRangeList(boardSystem.buildFullBoard(boardPanel, data));
	}
	
	public Boolean checkMoveRules(ActionController a, Square s) {
		return gameRulesSystem.checkMoveRules(a, s);
	}
	
	public boolean isWinCondition(ActionController a, Square s){
		return gameRulesSystem.isWinCondition(a, s);
	}

	public ArrayList<String> getAllGameMaps() {
		return fileSystem.getAllGameMaps();
	}
	
	public BoardData loadGame() {
		return fileSystem.loadGame();
	}	
	
	public ArrayList<Piece> setUpGameFromLoad(BoardData boardData) {
		return boardSystem.setUpGameFromLoad(boardData);
	}
	
	public Boolean saveGameData(BoardData boardData) {
		return fileSystem.saveGameData(boardData);
	}
	
	public void addGamePiece(Piece p) {
		piecesSystem.addGamePiece(p);
	}
	public void addGameTower(Square s) {
		piecesSystem.addGameTower(s);
	}
	
	public ArrayList <Piece> getGamePieces() {
		return piecesSystem.getPiecesList();
	}
	
	public ArrayList<Piece> getActivePieces(ArrayList<Piece> pieces, Team team) {
		return piecesSystem.getActivePieces(pieces, team);
	}
	
	public int getAvailablePieceCount(ArrayList<Piece> pieces, Team team) {
		return piecesSystem.getAvailablePieceCount(pieces, team);
	}
	public Boolean checkAIStatus(Team team) {
		return aiSystem.checkAIStatus(team);
	}
	public void populateAIObjects(Square[][] data) {
		aiSystem.setTowersList(piecesSystem.getTowersList());
		aiSystem.setPiecesList(piecesSystem.getPiecesList());
		aiSystem.setTeamList(piecesSystem.getAvailableTeamList(piecesSystem.getPiecesList()));
	}
	
	public void initialiseAI() {
		aiSystem.initialiseAI();
	}
	
	public void doAIGameTurn (ActionController a, Team team) {
		Piece p = aiSystem.getNextPiece(team);
		if (p != null) {
			a.startAction(a, p.getParentSquare());
			a.setActionButton(aiSystem.SelectNextAction(p));
			aiSystem.goGameTurn(a, getRangeList(), p);		
		}
	}
	
	public Team getNextTeam (ArrayList<Piece> pieces, Team team) {
		return piecesSystem.getNextTeam (pieces, team);
	}

	public void resetPieceTraitValueToBase(ArrayList<Piece> pieces) {
		piecesSystem.resetPieceTraitValueToBase(pieces);
	}
	
	public ArrayList<Team> getTeamList(ArrayList<Piece> pieces) {
		return piecesSystem.getAvailableTeamList(pieces); 
	}
	
	public Boolean undoMove(int undos, BoardController b) {
		return undoSystem.undoMove(undos, b) ;
	}

	public ArrayList<Square> getRangeList() {
		return rangeSystem.getRangeList();
	}

}
