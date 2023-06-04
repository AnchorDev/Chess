package Logic;

import Pieces.Piece;

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
	public void MakeMove(String command)
	{
			Message("");
			if (command.length() > 3) 
			{
				Move from = TranslateMove(command.charAt(0)+ "" +command.charAt(1));
				Move to = TranslateMove(command.charAt(2)+ "" +command.charAt(3));
				
				Piece pieceFrom = fen.pieceInPos(from.x, from.y);
				if (pieceFrom == null) {
					fen.writeChessboard();
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
					if (Side.itsTurn(pieceFrom.getPieceSide(), this.turn))  {
						ExecuteMove(pieceFrom, to);
						fen.writeChessboard();
						Message(pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType()+ " from "+ from.toString() + " moved to " + to.toString());
					}
					else
					{
						Message("Not " + pieceFrom.getPieceSide() + " turn!");

					}
					
				}
				else 
				{
					fen.writeChessboard();
					Message("Move of " + pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType() + " from "+ from.toString() + " is impossible");
				}
			}
			else
			{
				fen.writeChessboard();
				Message("Wrong command");
			}
			
		
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
			if (Side.itsTurn(pieceFrom.getPieceSide(), this.turn)) {
				ExecuteMove(pieceFrom, to);
				Message(pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType()+ " from "+ from.toString() + " moved to " + to.toString());
			}
			else
			{
				Message("Not " + pieceFrom.getPieceSide() + " turn!");

			}
			
		}
		else 
		{
			fen.writeChessboard();
			Message("Move of " + pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType() + " from "+ from.toString() + " is impossible");
		}
	}
	public void ExecuteMove(Piece pieceFrom, Move to)
	{
		fen.remove(pieceFrom);
		pieceFrom.setX(to.x);
		pieceFrom.setY(to.y);
		fen.insert(pieceFrom);
		
		Turn.switchTurn(this.turn);
	}
	Move TranslateMove(String command)
	{
		int x = command.charAt(0)-97;//abc na 123
		int y = Character.getNumericValue(command.charAt(1))-1;//bo od zera sie liczy
		
		return new Move(x, y);
	}
	public void Message(String message)
	{
		System.out.println(message);
	}
}
