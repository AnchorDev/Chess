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
	
	public enum Choice{kuba,radek};
	//dodanie sceny i grup (taka tablica elementow, dodaje sie jako argument do paneli zeby sie wyswietlaly)
	private static Scene scene;
	
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
		
		board.setChoice(Choice.radek);
		board.drawBoard(100);

		//game.MakeMove(new Move(0,1), new Move(0,3));
		
		board.drawPieces(game.fen.pieces);
		

		stage.setScene(scene);
        stage.setTitle("Czachy");
        stage.setResizable(false);
      	stage.show();
      	
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
	private void buttonFirst(Resolution r1)
	{
		if(r1.getX() == 1200) {
			r1.setX(1920);
			r1.setY(1080);
			board.cleanBoard();
			board.drawBoard(120);
			
		}
		else
		{
			r1.setX(1200);
			r1.setY(890);
			board.cleanBoard();
			board.drawBoard(100);
		}
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

