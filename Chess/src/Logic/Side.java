package Logic;
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
	public static boolean itsTurn(Side side, Turn turn)
	{
		if (side == Side.white && turn == Turn.WHITE)
			return true;
		if (side == Side.black && turn == Turn.BLACK)
			return true;
		return false;
	}
}
