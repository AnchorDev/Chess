package Logic;
/**
 * Tryb wyliczeniowy, odpowiedzialny za ture i jej symbol
 */
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
<<<<<<< Chess/src/Logic/Turn.java
	
=======
	/**
	 * Zamiana tur
	 */
>>>>>>> Chess/src/Logic/Turn.java
	public static Turn switchTurn(Turn turn)
	{
		if (turn == Turn.WHITE) 
			return Turn.BLACK;
		else
			return Turn.WHITE;
	}
	/**
	 * Zamiana tury na indeks 0,1
	 */
	public static int TurnToId(Turn turn)
	{
		if (turn == Turn.WHITE)
			return 0;
		else
			return 1;
	}
}
