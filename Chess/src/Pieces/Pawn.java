package Pieces;

import Logic.*;

public class Pawn extends Piece{
	public Pawn(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.PAWN);
	}
	public void LegalMoves(char[][] board)
	{
		ResetMoves(moves);
		if (pieceColor == Side.black) {
			if (this.y == 1 && board[this.x][this.y + 2] == 'x' && board[this.x][this.y + 1] == 'x' ) {
				moves.add(new Move(this.x, this.y+2));
			}
			if (this.y + 1 < 8) 
			{
				if (board[this.x][this.y + 1] == 'x') {
					moves.add(new Move(this.x, this.y+1));
				}
			}
		}
		else if (pieceColor == Side.white) {
			if (this.y == 6 && board[this.x][this.y - 2] == 'x' && board[this.x][this.y - 1] == 'x' ) {
				moves.add(new Move(this.x, this.y-2));
			}
			if (this.y - 1 >= 0) 
			{
				if (board[this.x][this.y - 1] == 'x') {
					moves.add(new Move(this.x, this.y-1));
				}
			}
		}
	}

	
}
