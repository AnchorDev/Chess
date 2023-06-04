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
	public Turn switchTurn(Turn turn)
	{
		if (turn == Turn.WHITE) 
			return Turn.BLACK;
		else
			return Turn.WHITE;
	}
}
