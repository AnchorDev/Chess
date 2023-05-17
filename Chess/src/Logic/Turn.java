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
}
