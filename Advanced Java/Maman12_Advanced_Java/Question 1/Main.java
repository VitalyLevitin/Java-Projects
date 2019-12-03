

public class Main {
    public static void main(String[] args) {
        /*    double[] numArr = {1,-2,-3,-1};*/
        double[] numArr = {-1,2,3,4,1};
        int[] powArr = {4,2,2,1,0};
        int[] powerArray = {4,3,2,1,0};
        Polynom pol = new Polynom(numArr,powArr);
        Polynom pol2 = new Polynom(numArr,powerArray);
        System.out.println(pol.equals(pol2));
        System.out.println(pol);
        System.out.println(pol2);
        pol.addition(pol2);
        System.out.println(pol);
        pol.subtract(pol2);
        System.out.println(pol);
    }
}
