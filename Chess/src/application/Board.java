package application;

import application.Main.Choice;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Board{
	
	private static int[] coordinates;
	public int boardSize = 8;
	public Group gridGroup;
	public Group pieceGroup;
	public Group textGroup;
	public Choice choice;



	Board()
	{
		textGroup = new Group();
		gridGroup = new Group();
		pieceGroup = new Group();
	}
	
	
	//rysuje szachownice, trzeba dodac rysowanie pionkow
	public void drawBoard()
	{
		int i,j,wcolor = 0;
		Text a = new Text("11");
		textGroup.getChildren().add(a);
		if(choice == Choice.kuba)
		{
			for(i = 0; i < boardSize; i++)
			{
				for(j = 0; j < boardSize; j++)
				{
					Rectangle r = new Rectangle();
					
					coordinates = setCoordinates(i,j);
					
					r.setX(coordinates[0]);
	                r.setY(coordinates[1]);
	                r.setWidth(100);
	                r.setHeight(100);
	                r.setStroke(Color.BLACK);
	                
	                if(wcolor % 2 == 0)	r.setFill(Color.WHITE);
	                else	r.setFill(Color.BLACK);
					gridGroup.getChildren().add(r);
					
					if(i == 0)
					{
						String s = Integer.toString(8-j);
						Text t = new Text(s);
						coordinates = setCoordinates(i+0.05,j+0.15);
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 == 0)
		                {
			                t.setFill(Color.BLACK);
			                t.setStroke(Color.BLACK);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 != 0)
		                {
		                	t.setFill(Color.WHITE);
			                t.setStroke(Color.WHITE);
			                t.setStrokeWidth(0.2);
		                }

		                t.setScaleX(1.5);
		                t.setScaleY(1.5);
						textGroup.getChildren().add(t);
					}
					if(j == 7)
					{
						String[] s = {"a", "b", "c", "d", "e", "f", "g", "h"};
						Text t = new Text(s[i]);
						coordinates = setCoordinates(i+0.85,j+0.92);
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 == 0)
		                {
			                t.setFill(Color.BLACK);
			                t.setStroke(Color.BLACK);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 != 0)
		                {
		                	t.setFill(Color.WHITE);
			                t.setStroke(Color.WHITE);
			                t.setStrokeWidth(0.2);
		                }
		                t.setScaleX(1.5);
		                t.setScaleY(1.5);
						textGroup.getChildren().add(t);
					}
				
					
					wcolor++;
				}

				wcolor++;
			}
		}
		
		else if(choice == Choice.radek)
		{
			for(i = 0; i < boardSize; i++)
			{
				for(j = 0; j < boardSize; j++)
				{
					Rectangle r = new Rectangle();
					
					coordinates = setCoordinates(i,j);
					
					r.setX(coordinates[0]);
	                r.setY(coordinates[1]);
	                r.setWidth(100);
	                r.setHeight(100);
	                r.setStroke(Color.BLACK);
	                
	                if(wcolor % 2 == 0)	r.setFill(Color.WHITE);
	                else	r.setFill(Color.rgb(14, 156, 12));
	                
					gridGroup.getChildren().addAll(r);
					
					wcolor++;
				}
				wcolor++;
			}
		}
		/*for(i = 0; i < piece.length; i++)
		{
			setImg(piece[i].getFilename(), piece[i].getX(), piece[i].getY());
		}*/
		
		
	}
	
	//przypisuje koordynaty poszczegolnych kafelkow szachownicy
	private int[] setCoordinates(double x, double y)
	{
		
		int[] xy = new int[2];
		double x1 = x*100+25;
		double y1 = y*100+25;
		xy[0] = (int)x1;
		xy[1] = (int)y1;
		
		return xy;
		
	}
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
}
