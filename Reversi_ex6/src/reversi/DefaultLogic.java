package reversi;

public class DefaultLogic extends GameLogic{

	public DefaultLogic(Player player1p, Player player2p, Board board) {
		super(player1p, player2p, board);
	}

	@Override
	public void makeMove(int row, int col) {
		 board.cellAt(row,col).setContains(player.getType());
		    player.setScore(1);
		    for (int i=0;i<3;i++) {
		        for (int j=0;j<3;j++){
		            int sum= board.cellAt(row,col).getFlipOptions(i,j);
		            while(sum!=0){
		                board.cellAt(row+sum*(i-1),col+sum*(j-1)).setContains(player.getType());
		                player.setScore(1);
		                rival.setScore(-1);
		                sum--;
		            }
		        }
		    }
	
	}

	@Override
	public void possibleMoves() {
		player.setNoMoves(true);
	    for (int row = 0; row < board.getBoardSize() ; row++) {
	        for (int col = 0; col < board.getBoardSize(); col++) {
	            if (board.cellAt(row,col).getContains()==player.getType())
	                checkCell(row,col);
	        }
	    }
	}
	
	 private void checkCell(int row, int col) {
		 for(int i=row-1;i<=row+1;i++){
		        for(int j=col-1;j<=col+1;j++){
		            if(i>=0 && i<board.getBoardSize() && j>=0 && j<board.getBoardSize() ){
		                if (board.cellAt(i,j).getContains()==rival.getType())
		                    checkDirection(row,col,i-row,j-col);
		            }
		        }
		    }
	}

	 private void checkDirection(int row,int col,int deltaRow,int deltaCol) {
		 int i = row + deltaRow;
		    int j = col + deltaCol;
		    int sum=0;
		    while (i>=0 && i<board.getBoardSize()  && j>=0 && j<board.getBoardSize()  && board.cellAt(i,j).getContains() == rival.getType()){
		        i += deltaRow;
		        j += deltaCol;
		        sum++;
		    }
		    if(i>=0 && i<board.getBoardSize()  && j>=0 && j<board.getBoardSize()  && board.cellAt(i,j).getContains()==Contains.Empty) {
		        board.cellAt(i,j).setOption(true);
		        board.cellAt(i,j).changeFlipOptions(1-deltaRow,1-deltaCol,sum);// we save the sum in the direction we came from
		        player.setNoMoves(false);
		    }
	}
}
