package application;
import Pieces.*;
import Logic.*;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.List;
public class Board{
	public static int[] coordinates;
	public int boardSize = 8;
	public static Group gridGroup;
	public static Group pieceGroup;
	public static Group textGroup;
	public static Group turnGroup;
	public static Group moveGroup;
	public static Group comGroup;
	public Choice choice;
	public Rectangle lastClicked;
	public Boolean nextClick = false;
	
	private InputStream stream;
	private Image image;
    private ImageView imageView;
    
    public Color previousColor;
    
    private static int moveCount = 1;
    private static int coXW = 900;
    private static int coXB = 1050;
    private static int coY = 30;

    /**
     * Konstruktor klasy 'Board', tworzacy grupy przechowujace elementy takie jak ruchy, figury oraz szachownica
     */
	Board()
	{
		turnGroup = new Group();
		textGroup = new Group();
		gridGroup = new Group();
		pieceGroup = new Group();
		moveGroup = new Group();
		comGroup = new Group();
	}
	/**
	 * Funckja do czyszczenia ekranu gry
	 */
	public void cleanBoard()
	{
		gridGroup.getChildren().clear();
		textGroup.getChildren().clear();
		pieceGroup.getChildren().clear();
		turnGroup.getChildren().clear();
		comGroup.getChildren().clear();
	}
	
	
	/**
	 * Funkcja do rysowania szachownicy, wypisywania kogo jest tura oraz podswietla klikniete pole
	 * @param size
	 */
	public void drawBoard(int size)
	{
		int i=0,j=0,wcolor = 0;
		Text a = new Text("11");
		
		textGroup.getChildren().add(a);
			
			for(i = 0; i < boardSize; i++)
			{
				/**
				 * instrukcja warunkowa sprawdzajaca czyj obecniej jest ruch
				 */
				if(i == 0)
				{
					Text whichSide = new Text((Main.game.getTurn() == Turn.WHITE) ? "White Turn" : "Black Turn");
					if(Main.game.getTurn() == Turn.WHITE)
					{
						whichSide.setX(748);
						whichSide.setY(18);
						whichSide.setFill(Color.WHITE);
		                whichSide.setStroke(Color.BLACK);
		                whichSide.setStrokeWidth(0.1);
		                whichSide.setScaleX(1.5);
		                whichSide.setScaleY(1.5);
		                turnGroup.getChildren().add(whichSide);
					}
					else {
						whichSide.setX(753);
						whichSide.setY(18);
						whichSide.setFill(Color.WHITE);
		                whichSide.setStroke(Color.BLACK);
		                whichSide.setStrokeWidth(0.1);
		                whichSide.setScaleX(1.5);
		                whichSide.setScaleY(1.5);
		                turnGroup.getChildren().add(whichSide);
					}

				}

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
						coordinates = setCoordinates(i+0.05,j+0.15, size);
						
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 != 0)
		                {
			                
			                t.setFill(Color.WHITE);
			                t.setStroke(Color.WHITE);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 == 0)
		                {
		                	t.setFill(Color.BLACK);
			                t.setStroke(Color.BLACK);
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
						coordinates = setCoordinates(i+0.85,j+0.92, size);
						t.setX(coordinates[0]);
		                t.setY(coordinates[1]);
		                if(wcolor % 2 != 0)
		                {
			               
			                t.setFill(Color.WHITE);
			                t.setStroke(Color.WHITE);
			                t.setStrokeWidth(0.2);
		                }
		                else if(wcolor % 2 == 0)
		                {
		                	t.setFill(Color.BLACK);
				            t.setStroke(Color.BLACK);
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
		
		/**
		 * Sprawdza czy myszka zostala kliknieta na danym polu
		 */
		gridGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
		    public void handle(MouseEvent event) {
		    	
		        for (int i = 0; i < gridGroup.getChildren().size(); i++) {
		            Rectangle colorChange = (Rectangle) gridGroup.getChildren().get(i);
		            if (colorChange.equals(event.getTarget())) {
		                Rectangle clickedRectangle = colorChange;
		                
		                if (lastClicked == clickedRectangle) {
		                    previousColor = (Color) clickedRectangle.getUserData();
		                    clickedRectangle.setFill(previousColor);
		                    lastClicked = null;
		                    nextClick = false;
		                } else {

		                    if (lastClicked != null) {
		                        previousColor = (Color) lastClicked.getUserData();
		                        lastClicked.setFill(previousColor);
		                        nextClick = true;
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



    /**
     * Funkcja do przypisywania zdjec odpowiednim figura
     * @param filename sciezka do pliku
     * @param i wspolrzedne x
     * @param j wspolrzedne y
     * @throws FileNotFoundException wyswietla blad w momencie kiedy plik nie zostanie znaleziony
     */
	public void setImg(String filename, double i, double j) throws FileNotFoundException {
		
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
	
	/**
	 * Funkcja do rysowania figur na ekranie
	 * @param pieces przechowuje odpowiednie informacje przypisane do danej figury
	 * @throws FileNotFoundException wyswietla blad w momencie kiedy plik nie zostanie znaleziony
	 */
	 public void drawPieces(List<Piece> pieces) throws FileNotFoundException {
	        for (Piece piece: pieces) {
	            setImg("src\\PiecesPic\\"+piece.getPieceSide()+piece.getPieceType()+".png", piece.getX(), Math.abs(piece.getY()-7));
	        }
	    }
	
	
	/**
	 * Funcja do przypisywania koordynatow do poszegolnych kafelkow szachownicy
	 * @param x Wspolrzedne X
	 * @param y Wspolrzedne Y
	 * @param size Wielkosc szachownicy
	 * @return Zwraca tablice w ktorej przechowywane sa wspolrzedne
	 */
	public static int[] setCoordinates(double x, double y, int size)
	{
		
		int[] xy = new int[2];
		double x1 = x*size+25;
		double y1 = y * size + 25;
		xy[0] = (int)x1;
		xy[1] = (int)y1;
		
		return xy;
		
	}
	
	/**
	 * Funkcja do wyszukiwania odpowiedniego kafelka szachownicy
	 * @param x Wspolrzedne X
	 * @param y Wspolrzedne Y
	 * @return Zwraca tablice w ktorej przechowywane sa wspolrzedne odpowiedniego kafelka
	 */
	public static int[] findSquare(double x, double y)
	{
		int res[] = new int[2];
		System.out.println(x+" " + y);
		res[0] = (int) ((x-25)/100);
		res[1] = (int) ((y-25)/100);
		
		return res;
	}
	
	/**
	 * Funkcja sprawdzajaca na jaka figure kliknal gracz
	 * @param pieces Lista figur
	 * @param x Wspolrzedne X
	 * @param y Wspolrzedne Y
	 * @return Zwraca na jaka figure klinal gracz lub zwraca wartosc 'null' w momencie klikniecia w inne miejsce na szachownicy
	 */
	public static Piece getClickedPiece(List<Piece> pieces,int x, int y) {
	    for (Piece piece : pieces) {
	        if (piece.getX() == x && piece.getY() == Math.abs(y - 7)) {
	            return piece;
	        }
	    }
	    return null;
	}
	
	/**
	 * Funkcja do wypisywania ruchow na ekranie wykonanych przez gracza
	 * @param move String przechowujacy informacje jaki ruch zostal wykonany
	 */
	public static void writeMove(String move)
	{
		Text moveP = new Text();
		
        if(Main.game.getTurn() == Turn.BLACK)
        {
        	moveP.setText(move);
            moveCount++;
            moveP.setX(coXB);
        }
        else
        {
        	coY += 22;
        	moveP.setText(moveCount + ". " + move);
        	moveP.setX(coXW);
        }
        moveP.setY(coY);
		moveP.setFill(Color.WHITE);
		moveP.setStroke(Color.BLACK);
		moveP.setStrokeWidth(0.1);
		moveP.setScaleX(2);
		moveP.setScaleY(2);
		moveP.setFont(Font.font("Lucida Sans Unicode"));
        moveGroup.getChildren().add(moveP);
	}
	
	/**
	 * Funkcja do wypisywania komunikatow o tym co sie dzieje w trakcie gry (czy gracz jest szachowany, czy moze wykonac dany ruch)
	 * @param com String przechowujacy informacje dla gracza
	 */
	public static void writeCom(String com)
	{
		Text comment = new Text();
        comment.setText(com);
        comment.setX(920);
        comment.setY(790);
        comment.setFill(Color.WHITE);
        comment.setStroke(Color.BLACK);
        comment.setStrokeWidth(0.1);
        comment.setScaleX(1.5);
        comment.setScaleY(1.5);
        comment.setWrappingWidth(200);
        comment.setFont(Font.font("Lucida Sans Unicode"));
        comGroup.getChildren().add(comment);
	}
}