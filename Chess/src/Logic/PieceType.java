package Logic;
public enum PieceType {
	PAWN('p'),
	ROOK('r'),
	KNIGHT('n'),
	BISHOP('b'),
	KING('k'),
	QUEEN('q');
	private final char symbol;
	PieceType(char symbol) {
		this.symbol = symbol;
	}
	public char getSymbol() {
		return symbol;
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
	

}
