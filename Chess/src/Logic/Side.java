package Logic;
/**
 * Tryb wyliczeniowy, odpowiedzialny za przechowywanie strony (biala, czarna)
 */
public enum Side{
 	white, black;
	public static Side whatSide(char c)
	{
		if (Character.isUpperCase(c)) 
		{
			return Side.white;
		}
		else {
			return Side.black;
		}
	}
	/**
	 * Funkcja sprawdzajaca, czy podana tura jest rowna podanej stronie
	 */
	public static boolean itsTurn(Side side, Turn turn)
	{
		if (side == Side.white && turn == Turn.WHITE)
			return true;
		if (side == Side.black && turn == Turn.BLACK)
			return true;
		return false;
	}
	/**
	 * Funkcja zamieniajace strone na ture
	 */
	public static Side TurnToSide(Turn turn)
	{
		if (turn == Turn.BLACK) {
			return Side.black;
		}
		else
			return Side.white;
	}
}
