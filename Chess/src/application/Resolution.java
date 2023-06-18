package application;
public class Resolution {

	int x, y;
	Resolution()
	{
		this.x = 1200;
		this.y = 850;
	}

	Resolution(int x, int y)
	{
		this.x = x;
		this.y = y;
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
}
