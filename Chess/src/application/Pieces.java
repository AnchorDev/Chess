package application;

public class Pieces {

	protected int x,y;
	protected String color, name, filename;
	
	public Pieces(int x, int y, String color, String name, String filename) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.name = name;
		this.filename = filename;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
