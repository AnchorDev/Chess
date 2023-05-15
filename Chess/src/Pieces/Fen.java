package Pieces;

import java.util.List;

public class Fen {

		public int[][] chessboard;
		public Fen()
		{
			chessboard = new int[8][8];
		}
		public void insert(int i, int j, Piece p)
		{
			chessboard [i][j] = p.id;
		}
		public void writeToChessboard(int i, int row, char f)
		{
			if(i - row*8 < 8 & i - row*8 > 0)
				chessboard[i - row*8][row] = f;
		}
		void loadFenPosition(String fen)
		{
			int row = 0; int blank = 0;
			for(int i = 0; i < fen.length();i++)
			{
				if (blank > 0) {
					writeToChessboard(i,row,'x');
					blank--;
				}
				else {
					char f = fen.charAt(i);
					if(f == '/')
					{
						row++;
					}
					else if (Character.isDigit(f)) {
						blank += f-1;
						writeToChessboard(i,row,'x');
					}
					else
					{
						writeToChessboard(i, row, f);
					}
				}
				
				
			}
		}
		void writeChessboard()
		{
			for(int i = 0; i < 8;i++)
			{
				for(int j = 0; j < 8;j++)
				{
					System.out.print(chessboard[j][i]);
				}
				System.out.println();
			}
		}
		public void resetBoard()
		{
			for (int i = 0; i < chessboard.length; i++) {
				for (int j = 0; j < chessboard[i].length; j++) 
				{
					chessboard[i][j] = -1;
				}
			}
		}
}
