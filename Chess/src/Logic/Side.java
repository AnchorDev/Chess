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
}
