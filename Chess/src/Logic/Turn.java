package Logic;
public enum Turn 
{
	WHITE('w'),
	BLACK('b');
	private final char symbol;
	Turn(char symbol) {
		this.symbol = symbol;
	}
	public char getSymbol() {
		return symbol;
	}
	public static Turn switchTurn(Turn turn)
	{
		if (turn == Turn.WHITE) 
			return Turn.BLACK;
		else
			return Turn.WHITE;
	}
	public static int TurnToId(Turn turn)
	{
		if (turn == Turn.WHITE)
			return 0;
		else
			return 1;
	}
}
