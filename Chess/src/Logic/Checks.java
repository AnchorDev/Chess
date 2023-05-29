package Logic;
import java.util.List;
import Pieces.Piece;
public class Checks {
	Side side;
	boolean isChecked;
	boolean hasMoves;
	boolean canCastle;
	public Checks(Side side) {
		this.side = side;
		this.isChecked = false;
		this.hasMoves = true;
		this.canCastle = true;
	}
	public void IsChecked(List<Piece> pieces, char[][] board)
	{
		boolean checking = false;
		for (Piece piece : pieces) 
		{
			if (piece.getPieceSide() != this.side) 
			{
				piece.LegalMoves(board);
				for (Move move : piece.moves) {
					if (board[move.x][move.y] == Piece.findPiece(pieces, this.side, PieceType.KING).getId()) {
						checking = true;
					}
				}
			}
		}
		this.isChecked = checking;
	}
	public void HasMoves(List<Piece> pieces, char[][] board)
	{
		boolean having = false;
		for (Piece piece : pieces) 
		{
			if (piece.getPieceSide() == this.side) 
			{
				piece.LegalMoves(board);
				if(piece.moves.size() > 0)
				{
					having = true;
					break;
				}
			}
		}
		this.hasMoves = having;
	}
}
