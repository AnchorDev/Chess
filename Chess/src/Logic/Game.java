package Logic;

import java.util.List;

import Pieces.Piece;
import application.Board;

public class Game {
	public Turn turn;
	public Fen fen;
	public Checks[] checks;
	public Game()
	{
		turn = Turn.WHITE;
		fen = new Fen();
		fen.resetBoard();
		fen.loadFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		checks = new Checks[2];
		checks[0] = new Checks(Side.white);
		checks[1] = new Checks(Side.black);
	}
	
	public void MakeMove(Move from, Move to)
	{
		Message("");
		Piece pieceFrom = fen.pieceInPos(from.x, from.y);
		if (pieceFrom == null) {
			Message("There is no piece");
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
		PerformChecks(fen.pieces, fen.chessboard);
	}
	public boolean CheckMove(Piece pieceFrom, Move to)
	{

		int turnId = Turn.TurnToId(turn);
		Move pos = new Move(pieceFrom.getX(), pieceFrom.getY());
		Turn oldTurn = turn;
		//checking next move
		Fen next = new Fen(fen);
			
		next.remove(pieceFrom);
		pieceFrom.setX(to.x);
		pieceFrom.setY(to.y);
		next.insert(pieceFrom);
			
		turn = Turn.switchTurn(this.turn);
		PerformChecks(fen.pieces, next.chessboard);
		if (checks[turnId].isChecked) {
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
		fen.remove(pieceFrom);
		pieceFrom.setX(to.x);
		pieceFrom.setY(to.y);
		fen.insert(pieceFrom);
		
		turn = Turn.switchTurn(this.turn);
	}
	public void PerformChecks(List<Piece> pieces, char[][] chessboard)
	{
		checks[0].IsChecked(pieces, chessboard);
		
		checks[1].IsChecked(pieces, chessboard);
	}
	public static Move TranslateMove(String command)
	{
		int x = command.charAt(0)-97;//abc na 123
		int y = Character.getNumericValue(command.charAt(1))-1;//bo od zera sie liczy
		
		return new Move(x, y);
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
