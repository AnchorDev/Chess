package Pieces;
import application.Main.color;

public class Queen extends Rook{
	public Queen(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		ResetMoves(moves);
		Rook rook = new Rook(this.x, this.y, 0, null, null, null);
		rook.LegalMoves(board);
		this.moves.addAll(rook.moves);
		
		Bishop bishop = new Bishop(this.x, this.y, 0, null, null, null);
		bishop.LegalMoves(board);
		this.moves.addAll(bishop.moves);
	}
}
