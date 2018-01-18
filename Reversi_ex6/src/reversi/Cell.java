package reversi;
/**
* class name: Cell
* each object is a cell inside board
*/
public class Cell {
	private boolean option; // true if it's possible to put a disk there, otherwise false
	private Contains contains; //  Black/White/Empty
	private int [][] flipOptions=new int[3][3];
	
	/**
	* constructor name:Cell
	* makes the cell option false,the default value ' ' and calls clearFlipOptions();
	*/
	public Cell(){
		this.option=false;
		this.contains=Contains.Empty; 
	    clearFlipOptions();
	}

	/**
	* function name: getContains
	* @return the value of the cell
    * access directly
	*/
	public Contains getContains() {
	    return contains;
	}

	/**
	* function name: isOption
	* @return true if putting a disk is a legal move, otherwise false
    * access directly
	*/
	public boolean isOption() {
	    return option;
	}

	/**
	* function name: setContains
	* @param  Contains contains
    * changes the cell value according to the value it receives
	*/
	public void setContains(Contains contains) {
	    this.contains = contains;
	}

	/**
	* function name: setOption
	* @param a boolean value
    * access directly
	*/
	public void setOption(boolean option) {
	    this.option = option;
	}

	/**
	* function name: changeFlipOptions
	* @param row, col in the flipOption 3x3 table 
	* @param int sum ,the number of moves that can be made in that direction
	* access directly to the 3x3 board and change
	*/
	public void changeFlipOptions(int row, int col, int sum) {
	    this.flipOptions[row][col]=sum;
	}

	/**
	* function name: clearFlipOptions
	* goes over the table directly and makes every cell equal to zero
	*/
	public void clearFlipOptions() {
	    for (int i=0;i<3;i++)
	        for (int j=0;j<3;j++)
	            this.flipOptions[i][j]=0;
	}

	/**
	* function name: getFlipOptions
	* @param row,col in the flipOptions table
	* @return the sum of moves you can make in that direction
	* access directly 
	*/
	public int getFlipOptions(int row,int col) {
	    return this.flipOptions[row][col];
	}

	/**
	* function name: getFlipSum
	* @return the sum of disks you can flip from all directions
	* goes throw the flipOptions array and adds each direction flips to the sum
	*/
	public int getFlipSum() {
	    int sum=0;
	    for (int i=0;i<3;i++)
	        for (int j=0;j<3;j++)
	            sum+=this.flipOptions[i][j];

	    return sum;
	}

}
