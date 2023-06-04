package application;
import Pieces.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import application.Main.Choice;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.List;
public class Board{
	
	public static int[] coordinates;
	public int boardSize = 8;
	public static Group gridGroup;
	public static Group pieceGroup;
	public static Group textGroup;
	public Choice choice;
	public Rectangle lastClicked;
	
	private InputStream stream;
	private Image image;
    private ImageView imageView;
    
 

	Board()
	{
		textGroup = new Group();
		gridGroup = new Group();
		pieceGroup = new Group();
	}
	
	public void cleanBoard()
	{
		gridGroup.getChildren().clear();
		textGroup.getChildren().clear();
	}
	
	
	//rysuje szachownice, trzeba dodac rysowanie pionkow
	public void drawBoard(int size)
	{
		int i=0,j=0,wcolor = 0;
		Text a = new Text("11");
		
		textGroup.getChildren().add(a);
		
		if(choice == Choice.kuba)
		{
			for(i = 0; i < boardSize; i++)
			{

				for(j = 0; j < boardSize; j++)
				{
					Rectangle r = new Rectangle();
					
					coordinates = setCoordinates(i,j, size);
					
					r.setX(coordinates[0]);
	                r.setY(coordinates[1]);
	                r.setWidth(size);
	                r.setHeight(size);
	                r.setStroke(Color.BLACK);
	                if(wcolor % 2 == 0)	r.setFill(Color.WHITE);
	                else	r.setFill(Color.rgb(14, 156, 12));
					gridGroup.getChildren().add(r);
					
					if(i == 0)
					{
						String s = Integer.toString(8-j);
						Text t = new Text(s);
						coordinates = setCoordinatesLetters(i+0.05,j+0.15, size);
						
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 != 0)
		                {
			                t.setFill(Color.BLACK);
			                t.setStroke(Color.BLACK);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 == 0)
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
						coordinates = setCoordinatesLetters(i+0.85,j+0.92, size);
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 != 0)
		                {
			                t.setFill(Color.BLACK);
			                t.setStroke(Color.BLACK);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 == 0)
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
		
		gridGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        for (int i = 0; i < gridGroup.getChildren().size(); i++) {
		            Rectangle colorChange = (Rectangle) gridGroup.getChildren().get(i);
		            if (colorChange.equals(event.getTarget())) {
		                Rectangle clickedRectangle = colorChange;
		                
		                if (lastClicked == clickedRectangle) {
		                    // Kliknięto ponownie na to samo pole, przywróć poprzedni kolor
		                    Color previousColor = (Color) clickedRectangle.getUserData();
		                    clickedRectangle.setFill(previousColor);
		                    lastClicked = null; // Wyczyść ostatnio kliknięte pole
		                } else {
		                    // Kliknięcie na inne pole
		                    if (lastClicked != null) {
		                        // Przywróć poprzedni kolor dla poprzednio klikniętego pola
		                        Color previousColor = (Color) lastClicked.getUserData();
		                        lastClicked.setFill(previousColor);
		                    }
		                    
		                    Color currentColor = (Color) clickedRectangle.getFill();
		                    clickedRectangle.setFill(Color.GREEN);
		                    clickedRectangle.setUserData(currentColor);
		                    
		                    lastClicked = clickedRectangle;
		                }
		                
		                break;
		            }
		        }
		    }
		});

		
	}


    
	private void setImg(String filename, double i, double j) throws FileNotFoundException {
		
		 try {
			 stream = new FileInputStream(filename);
			 image = new Image(stream);
			 imageView = new ImageView();
			 imageView.setImage(image);
			 coordinates = setCoordinates(i, j, 100);
			 imageView.setX(coordinates[0]);
			 imageView.setY(coordinates[1]); 
			 imageView.setFitHeight(100);
			 imageView.setFitWidth(100);
			 pieceGroup.getChildren().add(imageView);
		 } catch (FileNotFoundException e) {System.out.println("cannot find file"); e.printStackTrace();}
		
	}
	
	 public void drawPieces(List<Piece> pieces) throws FileNotFoundException {
	        for (Piece piece: pieces) {
	            setImg("src\\PiecesPic\\"+piece.getPieceSide()+piece.getPieceType()+".png", piece.getX(), piece.getY());
	        }
	    }
	
	
	//przypisuje koordynaty poszczegolnych kafelkow szachownicy
	public static int[] setCoordinates(double x, double y, int size)
	{
		
		int[] xy = new int[2];
		double x1 = x*size+25;
		double y1 = (7 - y) * size + 25;
		xy[0] = (int)x1;
		xy[1] = (int)y1;
		
		return xy;
		
	}
	public static int[] setCoordinatesLetters(double x, double y, int size)
	{
		
		int[] xy = new int[2];
		double x1 = x*size+25;
		double y1 = y*size+25;
		xy[0] = (int)x1;
		xy[1] = (int)y1;
		
		return xy;
		
	}
	
	public static int[] findSquare(int x, int y)
	{
		int res[] = new int[2];
		
		res[0] = (x-25)/100;
		res[1] = (y-25)/100;
		
		return res;
	}
	
	
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
}
