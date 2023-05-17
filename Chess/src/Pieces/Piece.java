package Pieces;
import java.util.ArrayList;
import java.util.List;

import Logic.*;

public abstract class Piece {
	@Override
	public String toString() {
		return "Piece [" + (char)(x+97) + y + ", " + pieceType + ", " + pieceColor + "]";
	}
	protected int x,y,id;
	protected Side pieceColor;
	protected PieceType pieceType;
	public List<Move> moves;
	public abstract void LegalMoves(char[][] board);
	public Piece(int x, int y, int id, Side pieceColor, PieceType pieceType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.moves = new ArrayList<Move>();
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

	public Side getPieceSide() {
		return pieceColor;
	}
	public void setPieceSide(Side pieceColor) {
		this.pieceColor = pieceColor;
	}
	public PieceType getPieceType() {
		return pieceType;
	}
	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}
	public static void ResetMoves(List<Move> moves)
	{
		moves.clear();
	}
	public void ListLegalMoves()
	{
		if (moves.size() > 0) 
		{
			
				System.out.println(moves.toString());
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
