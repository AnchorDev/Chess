package Pieces;
import java.util.List;

import application.Main.color;

public class Piece {
	protected int x,y,id;
	protected String name, filename;
	protected color pieceColor;
	public Piece(int x, int y, int id, color pieceColor, String name, String filename) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.pieceColor = pieceColor;
		this.name = name;
		this.filename = filename;
	}	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public static void ResetBoard(int[][] board)
	{
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) 
			{
				board[i][j] = -1;
			}
		}
	}
	public static void ResetMoves(List<Move> moves)
	{
		moves.clear();
	}

}
