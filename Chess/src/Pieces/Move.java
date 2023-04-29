package Pieces;
public class Move {
	int x,y;
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "Move [x=" + x + ", y=" + y + "]";
	}
	
}
