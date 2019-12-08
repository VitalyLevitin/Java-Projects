import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter how many variables you wish to add to the first polynom.");
        int size = scan.nextInt();
        double[] firstPolyCoefficients = new double[size];
        int[] firstPolyExponents = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter the coefficient");
            firstPolyCoefficients[i] = scan.nextDouble();
            System.out.println("Please enter the responding exponent");
            firstPolyExponents[i] = scan.nextInt();
        }
        Polynom pol = new Polynom(firstPolyCoefficients,firstPolyExponents);
        System.out.println("Please enter how many variables you wish to add to the second polynom.");
        size = scan.nextInt();
        double[] secondPolyCoefficients = new double[size];
        int[] secondPolyExponents = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Please enter the coefficient");
            secondPolyCoefficients[i] = scan.nextDouble();
            System.out.println("Please enter the responding exponent");
            secondPolyExponents[i] = scan.nextInt();
        }
        Polynom pol2 = new Polynom(secondPolyCoefficients,secondPolyExponents);
        System.out.println("The polynoms you have enter are:");
        System.out.println(pol + "\n" + pol2);
        System.out.println((pol.equals(pol2))? "The polynoms are equal":"The polynoms are not equal");
        System.out.println("The sum of the polynoms is: " + pol.addition(pol2));
        System.out.println("The substraction between the polynoms is: " +  ((pol.subtract(pol2).getSize()==0)?"0":pol.subtract(pol2)));
        System.out.println("The first polynom differentiation is " + pol.differentiation());
        System.out.print("The second polynom differentiation is " + pol2.differentiation());
    }
}
