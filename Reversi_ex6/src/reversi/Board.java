package reversi;

public class Board {
	private static final int DEFAULT_BOARD_SIZE =8;
	private int m_BoardSize;
	private Cell[][] board;
	
	public Board(){
		this.m_BoardSize=DEFAULT_BOARD_SIZE;
		setInitialState();
}

	public Board(int i_BoardSize){
		this.m_BoardSize=((1 == i_BoardSize % 2) || (i_BoardSize < 4) ? 8 : i_BoardSize);
		setInitialState();
	}


	public void setInitialState()
	{
		board = new Cell[m_BoardSize][m_BoardSize];
		for(int i=0;i<m_BoardSize;i++){
			for(int j=0;j<m_BoardSize;j++){
				Cell cell=new Cell();
				board[i][j]=cell;
			}
		}
		int centerTopLeftRow = (m_BoardSize / 2) - 1;
		int centerTopLeftColumn = (m_BoardSize / 2) - 1;
	
		board[centerTopLeftRow][centerTopLeftColumn].setContains(Contains.White);
		board[centerTopLeftRow][centerTopLeftColumn].setOption(false);
		board[centerTopLeftRow + 1][centerTopLeftColumn + 1].setContains(Contains.White);
		board[centerTopLeftRow + 1][centerTopLeftColumn + 1].setOption(false);
		board[centerTopLeftRow][centerTopLeftColumn + 1].setContains(Contains.Black);
		board[centerTopLeftRow][centerTopLeftColumn + 1].setOption(false);
		board[centerTopLeftRow + 1][centerTopLeftColumn].setContains(Contains.Black);
		board[centerTopLeftRow + 1][centerTopLeftColumn].setOption(false);
	}

	public int getBoardSize(){ 
		return m_BoardSize;
	}
	
	
	public Cell cellAt(int row, int col) {
	    return this.board[row][col];
	}
	
}
