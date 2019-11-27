/**
 * Box class
 * contains all the methods for Box object
 * @author Vitaly Levitin and Yaroslav Miloslavsky
 */
public class Box {

	/*
	 * all the parameters that each Box has.
	 * each Box has a parent, right son, left son and a color.
	 * also each Box has data fields which are : side, height, volume and the size.
	 * the rank of each Box is determined by the size.
	 */
	private double side;
	private double height;
	private double volume;
	private Color color;
	private Box parent;
	private Box left;
	private Box right;
	private int size;
	
	/**
	 * Box constructor
	 * @param side the Side of the Box
	 * @param height The height of The Box
	 */
	public Box(double side, double height) {
		try {
			this.side=side;
			this.height=height;
			volume = Math.pow(side,2)*height;
			color=Color.NONE;
			parent=null;
			left=null;
			right=null;
			if(side<0 || height<0) {
				throw new IllegalArgumentException(); 
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("Parameters must be positive numbers only");
			System.exit(1);
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Inncorect parameters");
			System.exit(1);
		}
	}
	
	/**
	 * Default constructor for Box
	 */
	public Box() {
		side=0;
		height=0;
		color=Color.NONE;
		left=null;
		right=null;
		parent=null;
	}

	/**
	 * @return Side of a given Box
	 */
	public double getSide() {
		return side;
	}
	/**
	 * @return Height of a given Box
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @return Volume of a given Box
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * @return Color of a given Box
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param c The color which a given Box will be set
	 */
	public void setColor(Color c) {
		color=c;
	}
	
	/**
	 * @param b The Box which will be the new left son of a given Box
	 */
	public void setLeft(Box b) {
		left=b;
	}
	/**
	 * @param b The Box which will be the new right son of a given Box
	 */
	public void setRight(Box b) {
		right=b;
	}
	/**
	 * @return The left son of a given Box
	 */
	public Box getLeft() {
		return left;
	}
	/**
	 * @return The right son of a given Box
	 */
	public Box getRight() {
		return right;
	}
	/**
	 * @param p The Box which will be the new parent of a given Box
	 */
	public void setParent(Box p) {
		parent=p;
	}
	/**
	 * @return the parent of a given Box
	 */
	public Box getParent() {
		return parent;
	}
	/**
	 * @param x The Box that we compare to a given Box
	 * @return True if the Boxes equal
	 * @return False if the Boxes are different
	 */
	public boolean equals(Box x) {
		if(this.color==x.color && this.side==x.side&&this.height==x.height)
			return true;
		else
			return false;
	}
	/**
	 * @param x The Box that we copy to a given Box
	 */
	public void copyBox(Box x) {
		side = x.side;
		height = x.height;
		volume = x.volume;
	}
	/**
	 * @return Size of a given Box
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param newSize The size we set to a given Box
	 */
	public void setSize(int newSize) {
		size=newSize;
	}
}
