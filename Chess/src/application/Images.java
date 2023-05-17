package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import Pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
	
	private InputStream stream;
	private Image image;
    private ImageView imageView;
	
	private void setImg(String filename, double i, double j) throws FileNotFoundException {

		 try {
			 stream = new FileInputStream(filename);
			 image = new Image(stream);
			 imageView = new ImageView();
			 imageView.setImage(image);
			 Board.coordinates = Board.setCoordinates(i, j, 100);
			 imageView.setX(Board.coordinates[0]);
			 imageView.setY(Board.coordinates[1]); 
			 imageView.setFitHeight(100);
			 imageView.setFitWidth(100);
			 Board.pieceGroup.getChildren().add(imageView);
		 } catch (FileNotFoundException e) {System.out.println("cannot find file"); e.printStackTrace();}
		
	}
	
	 public void drawPieces(List<Piece> pieces) throws FileNotFoundException {
	        for (Piece piece: pieces) {
	            setImg("PiecesPic/"+piece.getPieceSide()+piece.getPieceType()+".png", piece.getX(), piece.getY());
	        }
	    }

}
