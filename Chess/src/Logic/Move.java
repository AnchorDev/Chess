package Logic;
public class Move {
	public int x,y;
	public char letter;
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
		Letter();
	}
	@Override
	public String toString() {
		return ""+letter + (y+1);
	}
	void Letter()
	{
		this.letter = (char) (x + 97);
	}
	@Override public boolean equals(Object o) {
        return (o instanceof Move) && (this.x == ((Move) o).x) && (this.y == ((Move) o).y);
    }
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
