package model;

import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import model.maps.MapLoader;

/**
 * Generates board board data for view to draw
 * 
 * @author ms
 *
 */

public class BoardGenerator {
	
	private ArrayList<Piece> gamePieces;
	private ArrayList<ArrayList<Integer>> map;
	
	public BoardGenerator() {		

	}
	
	public void loadMapData() {		
		MapLoader mapData = new MapLoader();
		try {
			map = mapData.getMapData();
			System.out.println("Loading Map Size: " + map.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public Square[][] generateStartBoard(ArrayList<Piece> piecesList) {
		
		gamePieces = piecesList;
		
	    int col = 0;
	    int row = 1;
	    int size = map.size();
	    
	    Square[][] boardData = new Square[map.size()][map.size()];
		Iterator<ArrayList<Integer>> mapIterator = map.iterator();

	    while (mapIterator.hasNext()) {

	        ArrayList<Integer> line = mapIterator.next();

	        if (row > line.size()) {
	            break;
	        }
	        
	        Iterator<Integer> val = line.iterator();
	        
	        int index = 0;

	        Integer cell = null;
	        while (index != row) {
	        	cell = val.next();
	        	index++;
	        }
	        
	        boardData[col][row-1] = null;// blank
	        Square gsqr = processMapData(col, row-1, cell);
			//Assign the Square to the board
			boardData[col][row-1] = gsqr;

			//Reset the iterator values
	        index = 0;
	        col++;
	        
	        if (col == size) { //as column count is equal to column size
	        	mapIterator = map.iterator();  //reset the iterator
	            row++;
	            col = 0;
	        }
	        
	    }

		return boardData;
	}
	
	private Square processMapData(int col, int row, int cell) {
		
		Square gsqr = new Square();
		
		//WALLS
		if (cell == 1) {
			gsqr.setAccessible(false);
		}
		
		//TEAMS
		if (cell == 2) { 
			gsqr.setOccupant(findAvailablePiece(gamePieces, "RED"));
		}
		if (cell == 3) {
			gsqr.setOccupant(findAvailablePiece(gamePieces, "BLUE"));
		}

		//TOWERS
		if (cell == 8 || cell == 9) {
			gsqr.setTeamTower(true);
		}
		
		int[] ids = {col, row};		
		gsqr.setID(ids);
		
		return gsqr;
		
	}

	private Piece findAvailablePiece(ArrayList<Piece> gamePieces, String team) {
		for (Piece i : gamePieces) {
			if (i.getInPlay() == false) {
				if (i.getTeam().toString() == team) {
					i.setInPlay(true);
					return i;
				}
			}
		}
		return null;
	}
	
	/*public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}*/


}
