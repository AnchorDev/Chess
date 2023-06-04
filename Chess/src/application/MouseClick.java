package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseClick {

	public void mouseClick()
	{
		Main.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				int x = (int)event.getX();
				int y = (int)event.getY();
				int[] square = Board.findSquare(x, y);
				
				x = square[0];
				y = square[1];
				
				//ruch
				/*if(board.lastClicked!=null)
				{
					game.MakeMove();
				}*/
				//System.out.println(x);
				//System.out.println(y);
				
			}

				
				
		});
	}
	
	
   
}
