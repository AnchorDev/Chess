package Logic;

import Pieces.Piece;

public enum PieceType {
	PAWN('p', 9817),
	ROOK('r', 9814),
	KNIGHT('n', 9816),
	BISHOP('b', 9815),
	KING('k', 9812),
	QUEEN('q', 9813);
	private final char symbol;
	private final int id;
	PieceType(char symbol, int id) {
		this.symbol = symbol;
		this.id = id;
	}
	public char getSymbol() {
		return symbol;
	}
	public int getId() {
		return id;
	}
	public static PieceType isPiece(char symbol)
	{
		for (PieceType piece : PieceType.values()) {
			if (symbol == piece.symbol || symbol == Character.toUpperCase(piece.symbol)) 
			{
				return piece;
			}
		}
		return null;
	}
	public static int PieceChar(Piece piece)
	{
		if (piece.getPieceSide() == Side.white) {
			return piece.getPieceType().id + 6;
		}
		return piece.getPieceType().id;
	}

}
