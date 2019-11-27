import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Vitaly Levitin.
 * This program receives a 2d array (with correct input)
 * and outputs how many additional data can be added
 * while maintaining balance betwen all sides.
 * (Up/Down, Left/Right)
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader("task-1.txt"));
        PrintStream out = new PrintStream(new FileOutputStream("even_output.txt"));
        System.setOut(out);
        int i =1;
        System.out.println("There are "+scan.nextInt()+" test cases");
        while(scan.hasNext()){
            int N = scan.nextInt();
            int b = scan.nextInt();
            int h = scan.nextInt();
            buildMatrix matrix = new buildMatrix(N);
            for (int j = 0; j < b; j++) {
                matrix.addB(scan.nextInt(),scan.nextInt());
            }
            for (int j = 0; j < h; j++) {
                matrix.addH(scan.nextInt(),scan.nextInt());
            }
            int hunters = matrix.checkHunters();
            System.out.println("Case #"+i+":"+" "+hunters+"\n");
            i++;
        }

    }
}
