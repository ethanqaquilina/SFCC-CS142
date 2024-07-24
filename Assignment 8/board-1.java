package ChickGame;

public class board {

	private char[][] board;
	int rows, columns;

	
	public board() {
		this.rows = 10;
		this.columns = 10;
	}
	
	public void initialize_board() {
		this.rows = 10;
		this.columns = 10;
		this.board = new char[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				this.board[i][j] = '-';
			}
		}
	}

	public void display_board() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				System.out.printf(" %c " , this.board[i][j]);
			}
			System.out.println();
		}
	}

	public void mark_board(int y, int x, char symbol) {
		this.board[y][x] = symbol;

	}

}
