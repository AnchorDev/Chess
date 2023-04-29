package application;
	
//wszystkie importy do javyfx
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class Main extends Application {
	
	//wielkosc szachownicy i potrzebne tablice koordynatow i bierek
	private static int boardSize = 8;
	private static int[] coordinates;
	private Pieces[] piece = new Pieces[32];
	
	//wybor szachownicy
	private static boolean kuba = true;
	private static boolean radek = false;
	
	//dodanie sceny i grup (taka tablica elementow, dodaje sie jako argument do paneli zeby sie wyswietlaly)
	private static Scene scene;
	private static Group gridGroup;
	private static Group pieceGroup;
	
	
	//do obrazkow, sprawdzam czy dziala
	private static InputStream stream;
    private static Image image;
    private static ImageView imageView;
	
	
	@Override
	public void start(Stage stage) {
		
		gridGroup = new Group();
		pieceGroup = new Group();
		Pane pane = new Pane(gridGroup, pieceGroup);
		scene = new Scene(pane, 850, 850);

		addPieces();
		drawBoard(kuba);
		stage.setScene(scene);
        stage.setTitle("Chess");
        stage.setResizable(false);
      	stage.show();
      	
      	

	}
	
	//rysuje szachownice, trzeba dodac rysowanie pionkow
	private void drawBoard(boolean board)
	{
		int i,j,wcolor = 0;
		
		if(board == kuba)
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
					
					wcolor++;
				}
				wcolor++;
			}
		}
		
		else if(board == radek)
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
					
					gridGroup.getChildren().add(r);
					
					wcolor++;
				}
				wcolor++;
			}
			
			for(i = 0; i < piece.length; i++)
			{
				setImg(piece[i].getFilename(), piece[i].getX(), piece[i].getY());
			}
		}
		
		
		
	}
	
	//ustawia zdjecia figur
	private void setImg(String filename, int i, int j)
	{
		try {
			stream = new FileInputStream(filename);
			image = new Image(stream);
			imageView = new ImageView();
			imageView.setImage(image);
			coordinates = setCoordinates(i, j);
            imageView.setX(coordinates[0]);
            imageView.setY(coordinates[1]); 
            pieceGroup.getChildren().add(imageView);
		}catch (FileNotFoundException e) {System.out.println("file not find"); e.printStackTrace();}
		
		
		
	}

	
	//przypisuje koordynaty poszczegolnych kafelkow szachownicy
	private int[] setCoordinates(int x, int y)
	{
		
		int[] xy = new int[2];
		
		xy[0] = x*100+25;
		xy[1] = y*100+25;
		
		return xy;
		
	}
	
	
	//dodaje bierki do listy elementow
	private void addPieces()
	{
		int count = 0;
		for(int i = 0; i < boardSize; i++)
		{
			for(int j = 0; j < boardSize; j++)
			{
	               if (j == 0) { // 8th rank
	                    if (i == 0 || i == 7) {piece[count] = new Pieces(i, j, "black", "rook", "D:\\eclipse prace\\szachy\\src\\Pieces\\bRook");
	                    count++;
	                    }
	               }
			}

				

		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

