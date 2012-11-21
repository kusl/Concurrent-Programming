package geom;

public class LockSplittingShape implements Shape {
	Object position = new Object();
	Object dimension = new Object();
	
	int x, y;
	int width, height;
	

	@Override
	public void changePosition() {
		synchronized (position) {
			x *= 1.1;
			y *= 0.8;
		}
	}

	@Override
	public void changeDimension() {
		synchronized (dimension) {
			width *= 1.5;
			height *= 0.2;
		}
	}

	@Override
	public void changePositionAndDimension() {
		synchronized (position) {
			synchronized (dimension) {
				y *= 1.4;
				height *= 0.6;
			}
		}
	}

}
