package gui;

import reversi.*;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;


public class GameBoard extends GridPane {
	private Board board;
	private Color color1;
	private Color color2;
	
	//constructor
	public GameBoard(Board board,Color color1,Color color2, double height) {
		this.board=board;
		this.color1=color1;
		this.color2=color2;
		int boardSize=board.getBoardSize();
		double cellSize = height /boardSize-1;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				GameCell cell=new GameCell(cellSize);
				//initialize middle cells
				if((i==boardSize/2-1 && j==boardSize/2-1)||(i==boardSize/2 && j==boardSize/2)){
					cell.changeColor(color2);
				}
				else if((i==boardSize/2-1 && j==boardSize/2)||(i==boardSize/2 && j==boardSize/2-1)){
					cell.changeColor(color1);
				}
				this.add(cell,i,j);
			}
		}
	}
	
	public void draw() {
		for (Node node:this.getChildren()){
			int i =GridPane.getRowIndex(node);
			int j =GridPane.getColumnIndex(node);
			if(node instanceof GameCell){
				GameCell cell=(GameCell) node;
				Contains contains=board.cellAt(i, j).getContains();
				if (contains==Contains.Black){
					cell.changeColor(color1);
				}
				if (contains==Contains.White){
					cell.changeColor(color2);
				}
			}			
		}					 
	}
	
	public Pair<Integer,Integer> chosenMove(){
		for (Node node:this.getChildren()){
			int i =GridPane.getRowIndex(node);
			int j =GridPane.getColumnIndex(node);
			if(node instanceof GameCell){
				GameCell cell=(GameCell) node;
				if (cell.getClicked()){
					cell.setClicked(false);
					return new Pair<Integer,Integer>(i,j);
				}
			}
		}
		return null;
	}

	
}
