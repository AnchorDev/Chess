package Pieces;
import Logic.*;
public class Queen extends Piece{
	public Queen(int x, int y, int id, Side pieceColor) {
		super(x, y, id, pieceColor, PieceType.QUEEN);
	}
	/**
	 * Liczy legalne ruchy
	 */
	public void LegalMoves(char[][] board)
	{
		ResetMoves(moves);
		Rook rook = new Rook(this.x, this.y, 0, this.getPieceSide());
		rook.LegalMoves(board);
		this.moves.addAll(rook.moves);
		
		Bishop bishop = new Bishop(this.x, this.y, 0, this.getPieceSide());
		bishop.LegalMoves(board);
		this.moves.addAll(bishop.moves);
	}
}
