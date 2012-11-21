package geom;

public class PassThroughShape implements Shape {

	class Position {
		int x, y;
		
		public synchronized void modify(double fx, double fy) {
			x *= fx;
			y *= fy;
		}
	}
	class Dimension {
		int width, height;
		public synchronized void modify(double fx, double fy) {
			width *= fx;
			height *= fy;
		}
	}
	class Combined {
		public Combined(Position pos, Dimension dim) {
			super();
			this.pos = pos;
			this.dim = dim;
		}
		Position pos;
		Dimension dim;
		public synchronized void modify(double fx, double fy, double fw, double fh) {
			pos.modify(fx, fy);
			dim.modify(fw, fh);
		}
	}
	
	Position position = new Position();
	Dimension dimension = new Dimension();
	Combined combined = new Combined(position, dimension);
	

	@Override
	public void changePosition() {
		position.modify(1.1, 0.8);
	}

	@Override
	public void changeDimension() {
		dimension.modify(1.5, 0.2);
	}

	@Override
	public void changePositionAndDimension() {
		combined.modify(1, 1.4, 1, 0.6);
	}
}
