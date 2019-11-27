/**
 * 
 * Maman15.java
 * A project to represent a box factory
 *
 * @author Vitaly Levitin and Yaroslav Miloslavsky
 * @version 24.2.19
 */
import java.io.IOException;
import java.util.Scanner;

public class Factory {

	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		BoxTree T = new BoxTree();
		double side,height;
		int action ;
		System.out.println("Please select the number of an action:\n1 INSERTBOX \n2 REMOVEBOX\n3 GETBOX \n4 CHECKBOX "
				+ "\n5 GETMEDIANBOX\n6 Read from text file \n7 exit");
		System.out.println("Please note that you can bring the menu any time by pressing 8\nin order print by pressing 12");
		do {
			action = scan.nextInt();
			switch(action){
				case 1:
					System.out.println("Please enter the side and the height of the box you wish to insert devided by space");
					side = scan.nextDouble();
					height = scan.nextDouble();
					T.insertBox(side, height);
					System.out.printf("INSERTBOX(%.0f,%.0f)\n",side,height);
					break;
				case 2:
					System.out.println("Please enter the side and the height of the box you wish to remove devided by space");
					 side = scan.nextDouble();
					 height = scan.nextDouble();
					 T.removeBox(side, height);
					System.out.printf("REMOVEBOX(%.0f,%.0f)\n",side,height);
					break;
				case 3:
					System.out.println("Please enter the side and the height of the volume you wish to get devided by space");
					side = scan.nextDouble();
					height = scan.nextDouble();
					T.getBox(side, height);
					System.out.printf("GETBOX(%.0f,%.0f)\n",side,height);
					System.out.println(T.getBox(side, height));
					break;
				case 4:
					System.out.println("Please enter the side and the height of the volume you wish to check devided by space");
					side = scan.nextDouble();
					height = scan.nextDouble();
					T.checkBox(side, height);
					System.out.printf("CHECKBOX(%.0f,%.0f)\n",side,height);
					System.out.println(T.checkBox(side, height));
					break;
				case 5:
					System.out.println("The median volume of the tree is: "+T.getMedianBox().getVolume());
					break;
				case 6:
					T.readFromTxt();
					break;
				case 7:
					System.out.println("Thank you, goodbye");
					break;
				case 8:
					System.out.println("Please select the number of an action:\n1 INSERTBOX \n2 REMOVEBOX\n3 GETBOX \n4 CHECKBOX "
							+ "\n5 GETMEDIANBOX\n6 Read from text file \n7 exit\n8 menu");
					break;
				case 12:
					T.inOrder();
				default:
					System.out.println("Please enter an action");			
			}
		}while(action!=7);
		if(scan!=null)
			scan.close();

		
		
		
		
//		T.insertBox(5,6);//150
//		T.insertBox(4,5);//80
//		T.insertBox(2,3);//12
//		T.insertBox(1,2);//
//		//T.insertBox(10,21);//2100
//		T.insertBox(7,9);//441
//		T.insertBox(5,8);//200
//		T.insertBox(1,1);//1
//		T.insertBox(2,2);//8
//		T.inOrder();
//		
//		System.out.println(T.checkBox(5,7));
//		System.out.println(T.checkBox(8,0));
//		System.out.println("\nAfter deletion");
//		//T.removeBox(10,21);
//		T.removeBox(1,2);
//		System.out.println("\nAfter deletion");
//		T.inOrder();
//		System.out.println(T.getMedianBox().getVolume());
//		T.removeBox(5,6);
//		System.out.println("\nAfter deletion");
//		T.inOrder();
//		System.out.println(T.getMedianBox().getVolume());
//		T.removeBox(5,8);
//		System.out.println("\nAfter deletion");
//		T.inOrder();
//		System.out.println(T.getMedianBox().getVolume());
//		T.removeBox(4,5);
//		System.out.println("\nAfter deletion");
//
//		T.inOrder();
//		System.out.println(T.getMedianBox().getVolume());*/
		

//		T.insertBox(5,6);//150
//		T.insertBox(4,5);//80
//		T.insertBox(2,3);//12
//		T.insertBox(1,2);//2
//		//T.insertBox(10,21);//2100
//		T.insertBox(7,9);//441
//		T.insertBox(5,8);//200
//		T.insertBox(1,1);//1
//		T.insertBox(2,2);//8
//		T.inOrder();
//
//		System.out.println(T.checkBox(5,7));
//		System.out.println(T.checkBox(8,0));
//		System.out.println(T.getBox(5,7));
//		System.out.println(T.getBox(8,0));
//		System.out.println(T.getBox(1,1));
//		System.out.println("\nAfter deletion");
		//T.removeBox(10,21);
/*		T.removeBox(1,2);
		System.out.println("\nAfter deletion");
		T.inOrder();
		System.out.println(T.getMedianBox().getVolume());
		T.removeBox(5,6);
		System.out.println("\nAfter deletion");
		T.inOrder();
		System.out.println(T.getMedianBox().getVolume());
		T.removeBox(5,8);
		System.out.println("\nAfter deletion");
		T.inOrder();
		System.out.println(T.getMedianBox().getVolume());
		T.removeBox(4,5);
		System.out.println("\nAfter deletion");

		T.inOrder();
		System.out.println(T.getMedianBox().getVolume());
		*/

		/*T.readFromTxt();
		T.inOrder();*/

	}
}
