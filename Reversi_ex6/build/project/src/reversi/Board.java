package reversi;

/**
 * Class name: Board
 * each object is a board of the game reversi
*/
public class Board {
	private static final int DEFAULT_BOARD_SIZE =8;
	private int m_BoardSize;
	private Cell[][] board;
	

	/**
	* constructor name: Board
	* sets the beginning board
	*/
	public Board(){
		this.m_BoardSize=DEFAULT_BOARD_SIZE;
		setInitialState();
}
	/**
	* constructor name: Board
	* @param i_BoardSize
	* sets the beginning board
	*/
	public Board(int i_BoardSize){
		this.m_BoardSize=((1 == i_BoardSize % 2) || (i_BoardSize < 4) ? 8 : i_BoardSize);
		setInitialState();
	}

	/**
	* function name: setInitialState
	* sets the initial state of the board
	*/
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
	
	/**
	* function name: getBoardSize
	* @return size of the board
	* access directly
	*/
	public int getBoardSize(){ 
		return m_BoardSize;
	}
	
	/**
	* function name: cellAt
	* @param row
	* @param col
	* @return Cell ,he cell which is located in (row,col) in the board
	* access directly
	*/
	public Cell cellAt(int row, int col) {
	    return this.board[row][col];
	}
	
}
