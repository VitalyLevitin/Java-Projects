import java.io.*;
import java.util.Scanner;

/**
 * The class that represents the BoxTree.
 * BoxTree is a Red Black OS tree.
 * The rank is maintained through the various methods throughout the class.
 * All the methods by the book.
 * 
 * @author Vitaly Levitin and Yaroslav Miloslavsky
 */
public class BoxTree {

	// Each tree has a root and a nill sentinel.
	// A counter is maintained for finding the median box by the volume.
	private Box root;
	private Box nil;
	private int counter;

	/**
	 * BoxTree constructor
	 */
	public BoxTree() {
		root = null;
		nil = new Box();
		nil.setColor(Color.BLACK);
		nil.setParent(root);
		nil.setSize(0);
		counter=0;
	}

	
	/**
	 * Insertion of a new node into the Tree.
	 * Each time a new Box is inserted, the rank(size) and the counter are maintained.
	 * The rank of each node that we pass, increases by 1
	 * The rotation keeps all the order of rank.
	 * 
	 * @param side Side of the the Box we want to insert into the tree
	 * @param height Height of the Box we want to insert into the tree
	 */
	public void insertBox(double side, double height) {
		Box z = new Box(side, height);
		Box y = nil;
		Box x = root;
		while (!this.equals(x, nil)) {
			x.setSize(x.getSize()+1);
			y = x;
			if (z.getSide() < x.getSide() || z.getSide() == x.getSide() && z.getHeight() < x.getHeight())
				x = x.getLeft();
			else
				x = x.getRight();
		}
		z.setParent(y);
		if (equals(y, nil))
			this.root = z;
		else if (z.getSide() < y.getSide() || z.getSide() == y.getSide() && z.getHeight() < y.getHeight())
			y.setLeft(z);
		else
			y.setRight(z);
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(Color.RED);
		z.setSize(1);
		insertFixup(z);
		counter++;
	}

	/**
	 * Deletion of a Box from the tree.
	 * Each time a Box is removed, the rank(size) and the counter are maintained.
	 * The rank of each Box from the Box we about to remove, up to the root is decreased by 1 before the fixup.
	 * 
	 * @param side Side of the Box we wish to remove
	 * @param height Height of the Box we wish to remove
	 * @return The removed box
	 */
	public Box removeBox(double side, double height) {
		Box z = treeSearch(root, side, height);
		Box y, x;
		if(equals(z.getLeft() , nil) || equals(z.getRight(),nil))
			y = z;
		else if (!equals(z.getLeft() , nil) && !equals(z.getRight(),nil))
			y=predecesssor(z);
		else y=treeSuccessor(z);
		if(!equals(y.getLeft(),nil))
			x=y.getLeft();
		else x=y.getRight();

		x.setParent(y.getParent());
		if(equals(y.getParent() , nil))
			this.root=x;
		else if(y.equals(y.getParent().getLeft()))
				y.getParent().setLeft(x);
			else y.getParent().setRight(x);
		if(!y.equals(z)) {
			z.copyBox(y);
		}	
		Box tmp = y;
		while(!tmp.equals(root)) {
			tmp.setSize(tmp.getSize()-1);
			tmp = tmp.getParent();
		}
		root.setSize(root.getSize()-1);

		if(y.getColor() == Color.BLACK)
			removeFixup(x);	
		counter--;
		return y;
	}

	/**
	 * The methods fixes possible faults caused by the insertion.
	 * In the book, pages 236 to 241, all the possible faults and fix up ideas are explored and explained.
	 * 
	 * @param z The Box that the fixup methods applied on it.
	 */
	private void insertFixup(Box z) {
		while (!z.getParent().equals(nil) && z.getParent().getColor() == Color.RED) {
			if (z.getParent().equals(z.getParent().getParent().getLeft())) {//if the parent of z is the left kid of z's grand-parent
				Box y = z.getParent().getParent().getRight();
				if (!equals(y, nil) && y.getColor() == Color.RED) {
					z.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();//TEST
				} else {
					if (z.equals(z.getParent().getRight())) {
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					rightRotate(z.getParent().getParent());
				}
			} else {
				Box y = z.getParent().getParent().getLeft();
				if (!equals(y, nil) && y.getColor() == Color.RED) {
					z.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();//TEST
				} else {
					if (z.equals(z.getParent().getLeft())) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		root.setColor(Color.BLACK);
	}

	/**
	 * he methods fixes possible faults caused by the deletion.
	 * In the book, pages 243 to 245, all the possible faults and fix up ideas are explored and explained.
	 * 
	 * @param x The box that caused the faults and needs fixup
	 */
	private void removeFixup(Box x) {
		while (!x.equals(root) && x.getColor() == Color.BLACK) {
			if (x.equals(x.getParent().getLeft())) {
				Box w = x.getParent().getRight();
				if (w.getColor() == Color.RED) {
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else if (w.getRight().getColor() == Color.BLACK) {
					w.getLeft().setColor(Color.BLACK);
					w.setColor(Color.RED);
					rightRotate(w);
					w = x.getParent().getRight();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Color.BLACK);
				w.getRight().setColor(Color.BLACK);
				leftRotate(x.getParent());
				x = root;
			} else {// mirrors
				Box w = x.getParent().getLeft();
				if (w.getColor() == Color.RED) {
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if (w.getLeft().equals(nil) || w.getLeft().getColor() == Color.BLACK
						&& w.getRight().equals(nil) || w.getRight().getColor() == Color.BLACK) {
					w.setColor(Color.RED);
					x = x.getParent();
				} else {
					if (w.getLeft().getColor() == Color.BLACK) {
						w.getRight().setColor(Color.BLACK);
						w.setColor(Color.RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Color.BLACK);
					w.getLeft().setColor(Color.BLACK);
					rightRotate(x.getParent());
					x = root;
				}
			}
		}
		x.setColor(Color.BLACK);
	}



	/**
	 * Rotation to the left in order to fix faults, or cause other faults that then will be fixed.
	 * pages 233 to 234 in the book.
	 * 
	 * @param x The Box we rotate left
	 */
	private void leftRotate(Box x) {
		Box y = x.getRight();
		x.setRight(y.getLeft());
		if (!equals(y.getLeft(), nil))
			y.getLeft().setParent(x);
		y.setParent(x.getParent());
		if (equals(x.getParent(), nil))
			root = y;
		else if (x.equals(x.getParent().getLeft()))
			x.getParent().setLeft(y);
		else
			x.getParent().setRight(y);
		y.setLeft(x);
		x.setParent(y);
		
		y.setSize(x.getSize());
		x.setSize(x.getLeft().getSize() + x.getRight().getSize()+1);
	}
	
	/**
	 * Rotation to the right in order to fix faults, or cause other faults that then will be fixed.
	 * pages 233 to 234 in the book.
	 * 
	 * @param x The Box we rotate right
	 */
	private void rightRotate(Box x) {
		Box y = x.getLeft();
		x.setLeft(y.getRight());
		if (!equals(y.getRight(), nil))
			y.getRight().setParent(x);
		y.setParent(x.getParent());
		if (equals(x.getParent(), nil))
			root = y;
		else if (x.equals(x.getParent().getRight()))
			x.getParent().setRight(y);
		else
			x.getParent().setLeft(y);
		y.setRight(x);
		x.setParent(y);
		
		y.setSize(x.getSize());
		x.setSize(x.getLeft().getSize() + x.getRight().getSize()+1);
	}



	/*
	 * Aux methods
	 */

	/**
	 * Searches the tree successor of a given Box
	 * 
	 * @param x the Box that we search its' successor
	 * @return the Box that is the successor of the Box 
	 */
	private Box treeSuccessor(Box x) {
		if (!equals(x.getRight(), nil))
			return treeMin(x.getRight());
		Box y = x.getParent();
		while (!equals(y, nil) && x.equals(y.getRight())) {
			x = y;
			y = y.getParent();
		}
		return y;
	}

	/**
	 * Searches the tree predecessor of a given Box
	 * 
	 * @param x the Box that we search its' predecessor
	 * @return the Box that is the predecessor of the Box 
	 */
	private Box predecesssor(Box x){
		if(!equals(x.getLeft(),nil))
			return treeMax(x.getLeft());
		Box y = x.getParent();
		while (!equals(y,nil) && x.equals(y.getLeft()))
		{
			x = y;
			y  = y.getParent();
		}
		return y;
	}

	/**
	 * @param x The root of the subtree that we search the minimum key in 
	 * @return the minimum key in a given subtree
	 */
	private Box treeMin(Box x) {
		while (!equals(x.getLeft(), nil))
			x = x.getLeft();
		return x;
	}
	
 	/**
 	 * @param x The root of the subtree that we search the maximum key in 
	 * @return the maximum key in a given subtree
 	 */
 	private Box treeMax(Box x){
		while (!equals(x.getRight(), nil))
			x = x.getRight();
		return x;
	}
 	
	/**
	 * @param x The root of the subtree we search in
	 * @param side Side of the Box we search for
	 * @param height Height of the Box we search for
	 * @return The box we searched for
	 */
	private Box treeSearch(Box x, double side, double height) {
		if (equals(x, nil) || x.getSide() == side && x.getHeight() == height)
			return x;
		if (side < x.getSide() || side == x.getSide() && height < x.getHeight())
			return treeSearch(x.getLeft(), side, height);
		else return treeSearch(x.getRight(), side, height);
	}

    /**
     * @param x The root of the subtree we search the volume in
     * @param k The key(volume) we are looking for in the sub tree
     * @return The Box with the key(volume) we search
     */
    private Box treeSearchVolume(Box x,double k) {
        if (equals(x, nil))
            return (nil);
        else if (k == x.getVolume()) {
            return (x);
        }
        if (k < x.getVolume())
            return treeSearchVolume(x.getLeft(), k);
        else return treeSearchVolume(x.getRight(), k);
    }

	/**
	 * @param x The root of the subtree we search in
	 * @param i The smallest i key in the subtree we search in (book page 254)
	 * @return The smallest Box in i place
	 */
	private Box osSelect(Box x , double i) {
		int r = x.getLeft().getSize()+1;
		if(i==r)
			return x;
		else if(i<r)
			return osSelect(x.getLeft(),i);
		else return osSelect(x.getRight(),i-r);
	}
	
	/**
	 * @return The median of the tree by the volume
	 */
	public Box getMedianBox() {
		double i = Math.floor((counter+1)/2);
		return osSelect(root,i);
	}
	
	/**
	 * We check if there is a Box with at least the searched side and height in the tree
	 * 
	 * @param side The side that we search at least for
	 * @param height The height we search at least for
	 * @return True if there is a Box that is at least the side and height that we search
	 */
	public boolean checkBox(double side, double height){
		Box x = new Box(side,height);
		Box z = root;
		while ((!equals(z,nil)))
		{
			if (z.getSide()>=x.getSide() && z.getHeight()>=x.getHeight())
				return true;
			else z= z.getRight();
		}
		return false;
	}

    /**
     * The method returns a Box with minimal volume that the side is at least searched side and the height is at least searched height
     * 
     * @param side The minimal side for the Box we search
     * @param height The minimal height for the Box we search
     * @return The box with minimal volume that has at least the given side and at least the given height
     */
    public String getBox(double side, double height) {
        Box x = new Box(side,height);
        if (checkBox(side,height)) {
            Box z = root;
            int rank = 0;
            while (!equals(z, nil)) {
                if (z.getSide() >= x.getSide() && z.getHeight() >= x.getHeight()) {
                    rank = z.getSize();
                    break;
                } else if (z.getSide() < x.getSide() && z.getHeight() < x.getHeight())
                    z = z.getRight();
                else z = z.getLeft();
            }
            Double[] minVol = new Double[rank];
            int i = 0;
            Box smallest = root;
            while (!equals(smallest.getLeft(), nil))
                smallest = smallest.getLeft();
            while (!equals(smallest, nil)) {
                if (smallest.getSide() >= x.getSide() && smallest.getHeight() >= x.getHeight()) {
                    minVol[i] = smallest.getVolume();
                    smallest = treeSuccessor(smallest);
                    i++;
                } else
                    smallest = treeSuccessor(smallest);
            }
            double min = minVol[0];
            for (int j = 0; j < minVol.length; j++) {
                if (minVol[j] != null && minVol[j] < min)
                    min = minVol[j];
            }
            z = treeSearchVolume(root, min);
            return (z.getSide()) + " , " + (z.getHeight());

        }
        return "No such value";
    }
	 
	/**
	 * Despite having an equal method in Box class already,
	 * this one is used ONLY for nil comparison, however, this could
	 * be used for two boxes, but was no tested for such. use with precaution
	 * Was tested for Box and nil and is working for that
	 * 
	 * @param x First Box we want to compare
	 * @param y Second Box we want to compare
	 * @return True if the Boxes are equal
	 */
	private boolean equals(Box x, Box y) {
		if (x != null && y != null) {
			if (x.getSide() == y.getSide() &&
					x.getHeight() == y.getSide() &&
					x.getVolume() == y.getVolume() &&
					x.getColor() == y.getColor() &&
					x.getParent() == y.getParent() &&
					x.getLeft() == y.getLeft() &&
					x.getRight() == y.getRight())
				return true;
		} else {
			if (x == null && equals(y, nil))
				return true;
			if (y == null && equals(x, nil))
				return true;
			if (equals(y, nil) && equals(x, nil))
				return true;
		}
		return false;
	}

	/**
	 * Methods that reads commands from text and executes them
	 */
	static Scanner scan = new Scanner(System.in);
	public void readFromTxt() {
		System.out.println("Please enter the name of the text file you wish to read from");
		String fileName = scan.next();
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName + ".txt"));
			String buffer = new String();
			while (input.hasNext()) {
				buffer = input.next();
				int i;
				double side;
				double height;
				String buf = new String();
				if (buffer.substring(0, 2).equals("in")) {
					i = 10;
					while (buffer.charAt(i) != (',')) {
						buf += buffer.charAt(i);
						i++;
					}
					i++;
					side = Double.valueOf(buf);
					buf = "";
					while (buffer.charAt(i) != (')')) {
						buf += buffer.charAt(i);
						i++;
					}
					height = Double.valueOf(buf);
					this.insertBox(side, height);
					System.out.println(buffer.toUpperCase());
				}

				if (buffer.substring(0, 2).equals("re")) {
					i = 10;
					while (buffer.charAt(i) != (',')) {
						buf += buffer.charAt(i);
						i++;
					}
					i++;
					side = Double.valueOf(buf);
					buf = "";
					while (buffer.charAt(i) != (')')) {
						buf += buffer.charAt(i);
						i++;
					}
					height = Double.valueOf(buf);
					this.removeBox(side, height);
					System.out.println(buffer.toUpperCase());
				}
				
				if (buffer.substring(0,4).equals("getM")) {
					getMedianBox();
					System.out.println(buffer.toUpperCase());
					System.out.println("The median volume is: "+getMedianBox().getVolume());
				}
				
				if (buffer.substring(0,2).equals("ch")) {
					i = 9;
					while (buffer.charAt(i) != (',')) {
						buf += buffer.charAt(i);
						i++;
					}
					i++;
					side = Double.valueOf(buf);
					buf = "";
					while (buffer.charAt(i) != (')')) {
						buf += buffer.charAt(i);
						i++;
					}
					height = Double.valueOf(buf);
					checkBox(side,height);
					System.out.println(buffer.toUpperCase());
					System.out.println(checkBox(side,height));
				}
				
				if (buffer.substring(0,4).equals("getB")) {
					i = 7;
					while (buffer.charAt(i) != (',')) {
						buf += buffer.charAt(i);
						i++;
					}
					i++;
					side = Double.valueOf(buf);
					buf = "";
					while (buffer.charAt(i) != (')')) {
						buf += buffer.charAt(i);
						i++;
					}
					height = Double.valueOf(buf);
					getBox(side,height);
					System.out.println(buffer.toUpperCase());
					System.out.println(getBox(side,height));
				}
			}
		} catch (Exception e) {
			System.out.println("There is no such file");
			System.exit(1);
		} finally {
			if (input != null)
				input.close();
		}
	}


	
	/**
	 * Inorder print
	 */
	public void inOrder() {
		Box ptr = root;
		inOrder(ptr);
	}

	/**
	 * Inner recursive method of the previous method
	 * @param B The Box that we read the data
	 */
	private void inOrder(Box B) {
		if (!equals(B, nil)) {
			inOrder(B.getLeft());
			System.out.println("color:" + B.getColor() + " side:" + B.getSide() + " height:" + B.getHeight() + " key: " + B.getVolume() + " rank: "+ B.getSize());
			inOrder(B.getRight());
		}
	}
}
	
