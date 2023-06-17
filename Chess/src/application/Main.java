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
import java.util.Scanner;
import javafx.scene.Node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import Logic.*;
import Pieces.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

@SuppressWarnings("unused")
public class Main extends Application {
	
	//wielkosc szachownicy i potrzebne tablice koordynatow i bierek
	private static int boardSize = 8;
	private static int[] coordinates;
	
	public Group buttonGroup;
	public Game game = new Game();
	//wybor szachownicy
	private static boolean kuba = true;
	private static boolean radek = false;
	Board board = new Board();
	MouseClick mouse = new MouseClick();
	
	public enum Choice{kuba,radek};
	//dodanie sceny i grup (taka tablica elementow, dodaje sie jako argument do paneli zeby sie wyswietlaly)
	public static Scene scene;
	
	//do obrazkow, sprawdzam czy dziala
	private static InputStream stream;
    private static Image image;
    private static ImageView imageView;
    

	
	@Override
	public void start(Stage stage) throws FileNotFoundException {
		
		buttonGroup = new Group();
		Pane pane = new Pane(Board.gridGroup, Board.pieceGroup, Board.textGroup, buttonGroup);
		
		Resolution r1 = new Resolution();
		
		// przycisk od zmiany rozdzielczosci
		Button p1 = new Button("Resolution");
		
		p1.setOnAction(event->{
			buttonFirst(r1);
			scene.getWindow().setWidth(r1.getX());
		    scene.getWindow().setHeight(r1.getY());
			
		});

		buttonGroup.getChildren().addAll(p1);
		buttonGroup.setLayoutX(390);
		buttonGroup.setLayoutY(0);
		
		scene = new Scene(pane, r1.getX(), r1.getY());	
		
		game.fen.writeChessboard();
		
		board.setChoice(Choice.kuba);
		board.drawBoard(100);


		Board.pieceGroup.setMouseTransparent(false);

		//game.MakeMove(new Move(0,1), new Move(0,3));

		
		//game.MakeMove(new Move(0,1), ne	w Move(0,3));
		

		board.drawPieces(game.fen.pieces);
		


		stage.setScene(scene);
        stage.setTitle("Czachy");
        stage.setResizable(false);
      	stage.show();
      	
      	mouseClick();
      	
      	
      	/*Scanner scanner = new Scanner(System.in);
      	for (;;) 
      	{
      	// Enter data using BufferReader
      		String s = scanner.nextLine();
      		Move from = Game.TranslateMove(s.charAt(0)+ "" +s.charAt(1));
			Move to = Game.TranslateMove(s.charAt(2)+ "" +s.charAt(3));
            game.MakeMove(from,to);
      	}*/
	}
	
	public void mouseClick() {
	    Main.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        private int firstX;
	        private int firstY;
	        private boolean clicked = false;

	        @Override
	        public void handle(MouseEvent event) {
	            int fx = (int) event.getX();
	            int fy = (int) event.getY();
	            int[] fsquare = Board.findSquare(fx, fy);

	            fx = fsquare[0];
	            fy = fsquare[1];

	            // Sprawdź, czy kliknięto na figurę
	            if (!clicked) {
	                Piece clickedPiece = Board.getClickedPiece(game.fen.pieces,fx, fy);
	                if (clickedPiece != null) {
	                    // Kliknięto na figurę
	                    firstX = fx;
	                    firstY = fy;

	                    clicked = true;
	                    return;
	                }
	            }

	            // Ruch
	            if (clicked) {
	                int tx = (int) event.getX();
	                int ty = (int) event.getY();
	                int[] tsquare = Board.findSquare(tx, ty);

	                tx = tsquare[0];
	                ty = tsquare[1];

	                game.MakeMove(new Move(firstX, Math.abs(firstY - 7)), new Move(tx, Math.abs(fy - 7)));
	                board.cleanBoard();
	                board.drawBoard(100);
	                try {
	                    board.drawPieces(game.fen.pieces);
	                } catch (FileNotFoundException e) {
	                    e.printStackTrace();
	                }

	                clicked = false;
	            }
	        }
	    });
	}

	private void buttonFirst(Resolution r1)
	{
		if(r1.getX() == 1200) {
			r1.setX(1920);
			r1.setY(1080);
			board.cleanBoard();
			board.drawBoard(120);
			try {
                board.drawPieces(game.fen.pieces);
            } catch (FileNotFoundException e) {
                // Obsługa wyjątku FileNotFoundException
                e.printStackTrace(); // lub inna obsługa błędu
            }
		}
		else
		{
			r1.setX(1200);
			r1.setY(890);
			board.cleanBoard();
			board.drawBoard(100);
			try {
                board.drawPieces(game.fen.pieces);
            } catch (FileNotFoundException e) {
                // Obsługa wyjątku FileNotFoundException
                e.printStackTrace(); // lub inna obsługa błędu
            }
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

