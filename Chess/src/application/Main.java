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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {
	
	private Pieces[] piece = new Pieces[32];
	Board board = new Board();
	
	public enum Choice{kuba,radek};
	
	//dodanie sceny i grup (taka tablica elementow, dodaje sie jako argument do paneli zeby sie wyswietlaly)
	private static Scene scene;


	
	/*
	//do obrazkow, sprawdzam czy dziala
	private static InputStream stream;
    private static Image image;
    private static ImageView imageView;
	*/
	
	@Override
	public void start(Stage stage) {
		
		
		
		
		Pane pane = new Pane(board.gridGroup, board.pieceGroup, board.textGroup);
		scene = new Scene(pane, 850, 850);
		
		addPieces();
		
		board.setChoice(Choice.kuba);
		board.drawBoard();

		stage.setScene(scene);
        stage.setTitle("Czachy");
        stage.setResizable(false);
      	stage.show();
      	
      	

	}
	

	/*
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
*/
	
	//dodaje bierki do listy elementow
	private void addPieces()
	{
		int count = 0;
		for(int i = 0; i < board.boardSize; i++)
		{
			for(int j = 0; j < board.boardSize; j++)
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

