

public class Main {
    public static void main(String[] args) {
        /*    double[] numArr = {1,-2,-3,-1};*/
        double[] numArr = {-1,2,3,4,1};
        int[] powArr = {7,5,3,1,2};
        int[] powerArray = {7,5,3,1,2};
        Polynom pol = new Polynom(numArr,powArr);
        Polynom pol2 = new Polynom(numArr,powerArray);
        System.out.println(pol.equals(pol2));
        System.out.println(pol);
        System.out.println(pol2);
        System.out.println(pol.addition(pol2));
        System.out.println(pol);
        System.out.println(pol2);
        System.out.println((pol.subtract(pol2).getSize()==0)?"0":pol.subtract(pol2));
        System.out.println(pol);
        System.out.println(pol2);
        System.out.println(pol.differentiation());
    }
}
