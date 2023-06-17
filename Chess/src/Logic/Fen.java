package Logic;

import java.util.ArrayList;
import java.util.List;

import Pieces.*;
public class Fen {

		public char[][] chessboard;
		public List<Piece> pieces;
		public Fen()
		{	
			chessboard = new char[8][8];
			pieces = new ArrayList<Piece>();
		}
		public Fen(Fen fen)
		{
			chessboard = new char[8][8];
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.chessboard[i][j] = fen.chessboard[i][j];
				}
			}
			pieces = null;
		}
		public void insert(Piece p)
		{
			char symbol = p.getPieceType().getSymbol();
			if (p.getPieceSide() == Side.white) 
			{
				symbol = Character.toUpperCase(symbol);
			}
			chessboard[p.getX()][p.getY()] = symbol;
			
		}
		public void remove(Piece p)
		{
			chessboard[p.getX()][p.getY()] = 'x';
		}
		public void loadFenPosition(String fen)
		{
			resetBoard();
			int jumps = 0;
			for (int i = 0; i < fen.length(); i++) 
			{
				if (Character.isDigit(fen.charAt(i))) 
				{
					jumps += Character.getNumericValue(fen.charAt(i)) - 1;
				}
				if (fen.charAt(i) == '/') 
				{
					jumps--;
				}
				if (PieceType.isPiece(fen.charAt(i)) != null) 
				{
					Move pos = iterationToBoard(i+jumps);
					switch(PieceType.isPiece(fen.charAt(i)))
					{
					case BISHOP:
						Bishop bishop = new Bishop(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(bishop);
						pieces.add(bishop);
						break;
					case KING:
						King king = new King(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(king);
						pieces.add(king);
						break;
					case KNIGHT:
						Knight knight = new Knight(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(knight);
						pieces.add(knight);
						break;
					case PAWN:
						Pawn pawn = new Pawn(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(pawn);
						pieces.add(pawn);
						break;
					case QUEEN:
						Queen queen = new Queen(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(queen);
						pieces.add(queen);
						break;
					case ROOK:
						Rook rook = new Rook(pos.x, pos.y, i, Side.whatSide(fen.charAt(i)));
						insert(rook);
						pieces.add(rook);
						break;
					default:
						break;
					
					}
				}
			}
		}
		Move iterationToBoard(int iteration)
		{
			if(iteration < 64)
				return new Move(iteration % 8, Math.abs((iteration / 8)-7));
			else
				return new Move(0, 0);
		}
		
		public void writeChessboard()
		{
			for(int i = 7; i >= 0; i--)
			{
				System.out.print((i+1) + "  ");
				for(int j = 0; j < 8;j++)
				{
					System.out.print(chessboard[j][i]);
					System.out.print(' ');
				}
				System.out.println();
			}
			System.out.println();
			System.out.print("   ");
			for(int j = 0; j < 8;j++)
			{
				System.out.print((char)(j+97));
				System.out.print(' ');
			}
			System.out.println();
		}
		
		public void resetBoard()
		{
			for (int i = 0; i < chessboard.length; i++) {
				for (int j = 0; j < chessboard[i].length; j++) 
				{
					chessboard[i][j] = 'x';
				}
			}
		}
		public Piece pieceInPos(int x, int y)
		{
			for (Piece piece : pieces) 
			{
				if (piece.getX() == x && piece.getY() == y) 
				{
					return piece;
				}
			}
			return null;
		}
}
