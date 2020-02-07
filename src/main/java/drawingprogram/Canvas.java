package drawingprogram;

public class Canvas {
	private int width;
	private int height;
	private char[][] grid;

	public Canvas() {}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public char[][] getGrid() {
		return grid;
	}

	public boolean initGrid(int w, int h) {
		if(w <= 0 || h <= 0) {
			System.out.println("Dimensions must be positive");
			return false;
		}
		width = w;
		height = h;
		grid = new char[h][];
		for(int i = 0; i < h; i++) {
			grid[i] = new char[w];
			for(int j = 0; j < w; j++) {
				grid[i][j] = ' ';
			}
		}
		return true;
	}

	public void print() {
		if(grid == null) {
			return;
		}
		String hFrame = new String(new char[width + 2]).replace("\0", "-");
		System.out.println(hFrame);
		for (int i = 0; i < height; i++) {
			String s = "|" + new String(grid[i]) + "|";
			System.out.println(s);
		}
		System.out.println(hFrame);
	}
}
