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
				Move from = new Move(command.charAt(0)-97, Character.getNumericValue(command.charAt(1))-1);
				Move to = new Move(command.charAt(2)-97, Character.getNumericValue(command.charAt(3))-1);
				
				Piece pieceFrom = fen.pieceInPos(from.x, from.y);
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
					fen.remove(pieceFrom);
					pieceFrom.setX(to.x);
					pieceFrom.setY(to.y);
					fen.insert(pieceFrom);
					fen.writeChessboard();
					Message(pieceFrom.getPieceSide() + " " + pieceFrom.getPieceType()+ " from "+ from.toString() + " moved to " + to.toString());
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
	public void Message(String message)
	{
		System.out.println(message);
	}
}
