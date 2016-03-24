package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import view.BoardFrame;

public class PieceCreationController {
	
	final int REGULARPIECES_IN_TEAM = 8;
	final int USURPERS_IN_TEAM = 1;
	
	private PieceBuilder pieceBuilder = new PieceBuilder();


	public PieceCreationController() {
		
		PieceActionController pac = new PieceActionController();
		
	}
	
	public ArrayList<Piece> generateGamePieces(){
		
		ArrayList<Piece> piecesArrayList = new ArrayList<Piece>();
		
		for(int i = 0; i < REGULARPIECES_IN_TEAM; i++){
			
			RegularPiece newPiece = new RegularPiece();
			pieceBuilder.buildPiece(newPiece);
			piecesArrayList.add(newPiece);
		}
		
		for(int i = 0; i < USURPERS_IN_TEAM; i++){
			
			UsurperPiece newPiece = new UsurperPiece();
			pieceBuilder.buildPiece(newPiece);
			piecesArrayList.add(newPiece);
		}
		
		
		return piecesArrayList;

	}

}
