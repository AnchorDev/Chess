package Pieces;
import java.util.ArrayList;
import java.util.List;

import Logic.*;
public abstract class Piece {
	
	/**
	 * Wypisywanie klasy jako string
	 */
	@Override
	public String toString() {
		return "Piece [" + (char)(x+97) + (y+1) + ", " + pieceType + ", " + pieceColor + "]";
	}
	protected int x,y,id;
	protected Side pieceColor;
	protected PieceType pieceType;
	public List<Move> moves;
	public boolean castleable;
	/** 
	* Liczy legalne ruchy
	*/
	public abstract void LegalMoves(char[][] board);
	/**
	 * 
	 * @param x Wspolrzedne x na szachownicy (0-7) to (1-8)
	 * @param y Wspolrzedne y na szachownicy (0-7) to (A-B)
	 * @param id Indeks figury (liczac od zera)
	 * @param pieceColor Strona figury (biala, czarna)
	 * @param pieceType Typ figury (pionek, kon, itd.)
	 */
	public Piece(int x, int y, int id, Side pieceColor, PieceType pieceType) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.pieceColor = pieceColor;
		this.pieceType = pieceType;
		this.moves = new ArrayList<Move>();
		this.castleable = false;
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
	/**
	 * Resetowanie legalnych ruchow
	 */
	public static void ResetMoves(List<Move> moves)
	{
		moves.clear();
	}
	/**
	 * Wypisywanie legalnych ruchow do konsoli
	 */
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
	/**
	 * Funkcja znajdujaca krola na szachownicy
	 */
	public static Piece findKing(char c, List<Piece> pieces)
	{
		PieceType pieceType; Side pieceSide;
		if (Character.isUpperCase(c)) {
			pieceSide = Side.white;
		}
		else
		{
			pieceSide = Side.black;
		}
		pieceType = PieceType.isPiece(c);
		if (pieceType == PieceType.KING) {
			for (Piece piece : pieces) {
				if (piece.getPieceSide() == pieceSide && piece.getPieceType() == PieceType.KING) {
					return piece;
				}
			}
		}
		
		return null;
	}
	/**
	 * Funkcja znajdujaca podanego pionka na podstawie wspolrzednych
	 */
	public static Piece findPiece(Move move, List<Piece> pieces)
	{
			for (Piece piece : pieces) {
				if (piece.getX() == move.x && piece.getY() == move.y) {
					return piece;
				}
			}
		return null;
	}
}
