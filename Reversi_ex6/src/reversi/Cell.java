package reversi;

public class Cell {
	private boolean option; // true if it's possible to put a disk there, otherwise false
	private Contains contains; //  Black/White/Empty
	private int [][] flipOptions=new int[3][3];
	
	public Cell(){
		this.option=false;
		this.contains=Contains.Empty; 
	    clearFlipOptions();
	}

	public Contains getContains() {
	    return contains;
	}

	public boolean isOption() {
	    return option;
	}

	public void setContains(Contains contains) {
	    this.contains = contains;
	}

	public void setOption(boolean option) {
	    this.option = option;
	}

	public void changeFlipOptions(int row, int col, int sum) {
	    this.flipOptions[row][col]=sum;
	}

	public void clearFlipOptions() {
	    for (int i=0;i<3;i++)
	        for (int j=0;j<3;j++)
	            this.flipOptions[i][j]=0;
	}

	public int getFlipOptions(int row,int col) {
	    return this.flipOptions[row][col];
	}

	public int getFlipSum() {
	    int sum=0;
	    for (int i=0;i<3;i++)
	        for (int j=0;j<3;j++)
	            sum+=this.flipOptions[i][j];

	    return sum;
	}

}
