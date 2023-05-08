package Pieces;
import application.Main.color;

public class Rook extends Pawn{
	public Rook(int x, int y, int id, color pieceColor, String name, String filename) {
		super(x, y, id, pieceColor, name, filename);
	}
	public void LegalMoves(int[][] board)
	{
		Move[] dir = {new Move(1,0),new Move(0,-1),new Move(-1,0),new Move(0,1)};
		for (int i = 0; i < 8; i++) 
		{
			
		}
	}

}
