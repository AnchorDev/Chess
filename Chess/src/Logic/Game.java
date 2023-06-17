package Logic;

import java.util.List;


import Pieces.Piece;
import application.Board;


public class Game {
	public Turn turn;
	public Fen fen;
	public Game()
	{
		turn = Turn.WHITE;
		fen = new Fen();
		fen.resetBoard();
		fen.loadFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
	}
	boolean Castle(Move from, Move to, Side side)
	{
		if (side != Side.TurnToSide(turn)) {
			return false;
		}
		int lastRow = 0;
		if (side == Side.black)
			lastRow = 7;
		Piece pieceFrom = fen.pieceInPos(from.x, from.y);
		if (pieceFrom.getPieceSide() == side) {
			if (from.x == 4 && from.y == lastRow && pieceFrom.getPieceType() == PieceType.KING && pieceFrom.castleable) {
				Piece pieceTo = null;
				if (to.x == 2 && to.y == lastRow) {
					pieceTo = fen.pieceInPos(0, lastRow);
				}
				else if (to.x == 6 && to.y == lastRow) {
					pieceTo = fen.pieceInPos(7, lastRow);
				}
				if (pieceTo == null)
					return false;
				if ((pieceTo.getPieceSide() == side && pieceTo.getPieceType() == PieceType.ROOK && pieceTo.castleable)) {
					if (to.x == 2) {
						boolean empty = true;
						for (int i = 1; i < 4; i++) {
							if(fen.chessboard[i][lastRow] != 'x')
							{
								empty = false;
								Message("Long castle is impossible");
								return false;
							}
						}
						if(empty)
						{
							if(CheckMove(fen.pieceInPos(4,lastRow), new Move(6,lastRow)))
							{
								if(CheckMove(fen.pieceInPos(7,lastRow), new Move(5,lastRow)))
								{
									Message("Long castle by " + side);
									ExecuteMove(fen.pieceInPos(4,lastRow), new Move(2,lastRow));
									turn = Turn.switchTurn(this.turn);
									ExecuteMove(fen.pieceInPos(0,lastRow), new Move(3,lastRow));
									return true;
								}
							}
						}
					}
					else if (to.x == 6) {
						boolean empty = true;
						for (int i = 5; i < 7; i++) {
							if(fen.chessboard[i][lastRow] != 'x')
							{
								empty = false;
								Message("Short castle is impossible");
								return false;
							}
						}
						if(empty)
						{
							if(CheckMove(fen.pieceInPos(4,lastRow), new Move(6,lastRow)))
							{
								if(CheckMove(fen.pieceInPos(7,lastRow), new Move(5,lastRow)))
								{
									Message("Short castle by " + side);
									ExecuteMove(fen.pieceInPos(4,lastRow), new Move(6,lastRow));
									turn = Turn.switchTurn(this.turn);
									ExecuteMove(fen.pieceInPos(7,lastRow), new Move(5,lastRow));
									return true;
								}
							}
							
						}
					}
					Message("Castle is impossible");
				}
				Message("Castle is impossible");
			}
		}
		return false;
	}
	public void MakeMove(Move from, Move to)
	{
		Message("");
		Piece pieceFrom = fen.pieceInPos(from.x, from.y);
		if (pieceFrom == null) {
			Message("There is no piece");
			return;
		}
		if (Castle(from,to,pieceFrom.getPieceSide())) {
			return;
		}
		pieceFrom.LegalMoves(fen.chessboard);
		boolean moveFound = false;
		for (Move move : pieceFrom.moves) 
		{
			if (move.x == to.x && move.y == to.y) 
			{
				moveFound = true;
			}
		}
		if (moveFound) 
		{
			if(CheckMove(pieceFrom, to))
			{
				Piece pieceTo = fen.pieceInPos(to.x, to.y);
				if (Side.itsTurn(pieceFrom.getPieceSide(), this.turn)) {
					if (pieceTo == null) {
						Message(pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType()+ " from "+ from.toString() + " moved to " + to.toString());
						Board.writeMove(from.toString() + to.toString());
					}
					else
					{
						Message(pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType()+ " from "+ from.toString() + " moved to " + to.toString() + " taking " + pieceTo.getPieceType());
						Board.writeMove(from.toString() + "x" + to.toString());
						fen.pieces.remove(pieceTo);
					}
					ExecuteMove(pieceFrom, to);
				}
				else
				{
					Message("Not " + pieceFrom.getPieceSide() + " turn!");
				}
			}
			else 
			{
				Message("You are in check!");
			}
		}
		else 
		{
			Message("Move " + from.toString() + " to " + to.toString() + " is impossible");
		}
		fen.writeChessboard();
	}
	public boolean CheckMove(Piece pieceFrom, Move to)
	{

		Move pos = new Move(pieceFrom.getX(), pieceFrom.getY());
		Turn oldTurn = turn;
		//checking next move
		Fen next = new Fen(fen);
		next.pieces.addAll(fen.pieces);
		
		Piece taken = Piece.findPiece(to, next.pieces);
		if (taken != null) {
			next.pieces.remove(taken);
		}
		next.remove(pieceFrom);
		pieceFrom.setX(to.x);
		pieceFrom.setY(to.y);
		next.insert(pieceFrom);
		
		//turn = Turn.switchTurn(this.turn);
		if (IsChecked(next.pieces, next.chessboard, Side.TurnToSide(turn))) {
			turn = oldTurn;
			pieceFrom.setX(pos.x);
			pieceFrom.setY(pos.y);
			return false;
		}
		else
		{
			turn = oldTurn;
			pieceFrom.setX(pos.x);
			pieceFrom.setY(pos.y);
			return true;
		}
	}
	public void ExecuteMove(Piece pieceFrom, Move to)
	{
		Piece taken = Piece.findPiece(to, fen.pieces);
		if (taken != null) {
			fen.pieces.remove(taken);
		}
		fen.remove(pieceFrom);
		pieceFrom.setX(to.x);
		pieceFrom.setY(to.y);
		fen.insert(pieceFrom);
		
		if(pieceFrom.getPieceType() == PieceType.KING | pieceFrom.getPieceType() == PieceType.ROOK)
			pieceFrom.castleable = false;
		turn = Turn.switchTurn(this.turn);
		if(!HasMoves(fen.pieces, fen.chessboard, Side.TurnToSide(turn)))
		{
			Board.comGroup.getChildren().clear();
			Message("CHECK MATE!! " + Side.TurnToSide(turn) + " HAS NO MOVES!");
		}
			
		
	}
	public static Move TranslateMove(String command)
	{
		int x = command.charAt(0)-97;//abc na 123
		int y = Character.getNumericValue(command.charAt(1))-1;//bo od zera sie liczy
		
		return new Move(x, y);
	}
	public boolean IsChecked(List<Piece> pieces, char[][] board, Side side)
	{
		boolean checking = false;
		for (Piece piece : pieces) 
		{
			if (piece.getPieceSide() != side) 
			{
				piece.LegalMoves(board);
				
				for (Move move : piece.moves) {
					if (Piece.findKing(board[move.x][move.y], pieces) != null) {
						checking = true;
					}
				}
			}
		}
		return checking;
	}
	public boolean HasMoves(List<Piece> pieces, char[][] board, Side side)
	{
		boolean having = false;
		for (Piece piece : pieces) 
		{
			if (piece.getPieceSide() == side) 
			{
				piece.LegalMoves(board);
				if(piece.moves.size() > 0)
				{
					for (int i = 0; i < piece.moves.size(); i++) {
						if(CheckMove(piece,piece.moves.get(i))) {
							having = true;
							break;
						}
					}
				}
			}
		}
		return having;
	}
	public static void Message(String message)
	{

		Board.writeCom(message);
	}
	
	public Turn getTurn() {
		return turn;
	}
	
	private char pieceChar(int x)
	{
		char output = (char)x;
		return output;
	}
}
