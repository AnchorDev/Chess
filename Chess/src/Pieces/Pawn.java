package Pieces;
import java.util.List;
import application.Main.color;
import java.util.ArrayList;

public class Pawn extends Piece{
	List<Move> moves;
	public Pawn(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
		moves = new ArrayList<Move>();
	}
	public void LegalMoves(int[][] board)
	{
		if (pieceColor == color.white) {
			if (this.y == 1 && board[this.x][this.y + 2] == -1 && board[this.x][this.y + 1] == -1 ) {
				moves.add(new Move(this.x, this.y+2));
			}
			if (this.y + 1 < 8) 
			{
				if (board[this.x][this.y + 1] == -1) {
					moves.add(new Move(this.x, this.y+1));
				}
			}
		}
		else if (pieceColor == color.black) {
			if (this.y == 6 && board[this.x][this.y - 2] == -1 && board[this.x][this.y - 1] == -1 ) {
				moves.add(new Move(this.x, this.y-2));
			}
			if (this.y - 1 >= 0) 
			{
				if (board[this.x][this.y - 1] == -1) {
					moves.add(new Move(this.x, this.y-1));
				}
			}
		}
	}
	public void ListLegalMoves()
	{
		if (moves != null) {
			
				System.out.println(moves.toString());
		}

	}
	
}
