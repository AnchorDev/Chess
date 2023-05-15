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
	public Fen fen = new Fen();
	//wybor szachownicy
	private static boolean kuba = true;
	private static boolean radek = false;
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
		
		buttonGroup = new Group();
		Pane pane = new Pane(board.gridGroup, board.pieceGroup, board.textGroup, buttonGroup);
		
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
				

		fen.resetBoard();

		fen.loadFenPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		
		fen.writeChessboard();
		
		board.setChoice(Choice.kuba);
		board.drawBoard(100);

		stage.setScene(scene);
        stage.setTitle("Czachy");
        stage.setResizable(false);
      	stage.show();
      	
      	

	}
	
	/*
>>>>>>> branch 'master' of https://orkan.tu.kielce.pl/gitlab/radek.dev/chess.git
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

