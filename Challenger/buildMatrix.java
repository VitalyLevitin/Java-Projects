public class buildMatrix {
    private String [][] matrix;
    /**
     * Building the matrix with given values
     * @param N size of the array.
     * "B" stands for Bags.
     * "H" stands for Hunters.
     */
    public buildMatrix(int N)
    {
        matrix = new String[N+1][N+1];
    }
    public void addB(int x, int y) {
        matrix[x][y] = "B";
    }
    public void addH(int x, int y) {
        matrix[x][y] = "H";
    }

    /**
     * Receives a full array and returns how many
     * data points it's possible to add while maintaining balance.
     * @return number of possible points to add (-1 if none).
     */
    public int checkHunters() {
        return (((matrix.length-1) % 2 == 0) ? checkEven() : checkOdd());
    }
    private int checkEven(){
        int result = evenDiag(1,1,matrix.length/2, matrix.length-1);
        if (result <= 0)
            return -1;
        return result;
    }

    private int checkOdd(){
        //The middle point in an odd array is the upper value.
        int oddM = (int)Math.ceil(matrix.length/2);
        int middle = midOddDiag(1,1, oddM, matrix.length-1);
        int main = oddDiag(1, 1, oddM, matrix.length-1);
        if (middle == -1 || main == -1  || (middle == 0 && main == 0))
            return -1;
        return main+middle;
    }
    private int adder(int q1H, int q1S, int q4H, int q4S, int q2H, int q2S, int q3H, int q3S)
    {//Sums the total of possible hunters.
        int mainDiag = countHunters(q1H,q1S,q4H,q4S);
        int secDiag = countHunters(q2H,q2S,q3H,q3S);
        if (mainDiag == -1 || secDiag == -1)
            return -1;//0 check happens in the method that called.
        return mainDiag+secDiag;
    }
    /*
    Checks the middle of the odd array.
    Checks the center of the array separately to avoid double count.
     */
    private int midOddDiag(int row, int col, int oddM, int endM){
        int q1H = quarter(row, oddM, oddM-1, oddM,"H");
        int q1S = quarter(row, oddM, oddM-1, oddM,null);
        int q4H = quarter(oddM+1,oddM, endM, oddM,"H");
        int q4S = quarter(oddM+1,oddM, endM, oddM,null);
        int q2H = quarter(oddM,col,oddM,oddM-1,"H");
        int q2S = quarter(oddM,oddM,oddM,oddM-1, null);
        int q3H = quarter(oddM,oddM+1,oddM,endM,"H");
        int q3S = quarter(oddM,oddM+1,oddM,endM,null);
        int hunters = adder(q1H,q1S,q4H,q4S,q2H,q2S,q3H,q3S);
        if(matrix[oddM][oddM]==null && hunters!=-1)
            return hunters +1;
        return hunters;
    }

    private int oddDiag(int row, int col, int oddM, int endM){
        int q1H = quarter(row, col, oddM-1, oddM-1,"H");
        int q1S = quarter(row, col, oddM-1, oddM-1,null);
        int q4H = quarter(oddM+1,oddM+1, endM, endM,"H");
        int q4S = quarter(oddM+1,oddM+1, endM, endM,null);
        int q2H = quarter(row,oddM+1,oddM-1,endM,"H");
        int q2S = quarter(row,oddM+1,oddM-1,endM, null);
        int q3H = quarter(oddM+1,col,endM,oddM-1,"H");
        int q3S = quarter(oddM+1,col,endM,oddM-1,null);
        return adder(q1H,q1S,q4H,q4S,q2H,q2S,q3H,q3S);
    }

    private int evenDiag(int row, int col, int halfM, int endM){
        int q1H = quarter(row, col, halfM, halfM, "H");
        int q1S = quarter(row, col, halfM, halfM, null);
        int q4H = quarter(halfM+1,halfM+1,endM,endM,"H");
        int q4S = quarter(halfM+1,halfM+1,endM,endM,null);
        int q2H = quarter(row,halfM+1,halfM,endM,"H");
        int q2S = quarter(row,halfM+1,halfM,endM,null);
        int q3H = quarter(halfM+1,col,endM,halfM,"H");
        int q3S = quarter(halfM+1, col, endM, halfM, null);
        return adder(q1H,q1S,q4H,q4S,q2H,q2S,q3H,q3S);
    }

    private int countHunters(int q1H, int q1S, int q2H, int q2S){
        int count;
        if((q1H<q2H && q1S<(q2H-q1H)) || q2H<q1H && q2S<(q1H-q2H))
            return -1;
        else if (q1H==q2H && (q1S==1 && q2S==1))
            return q1S+q2S;
        else if (q1H==q2H)
            return (Math.min(q1S,q2S)*2);
        else if(q1H<q2H) {
            count = q2H - q1H;//How many hunters we need to add in order to balance the array.
            q1S -= count;//Removing empty spaces that went for array balancing.
            return count + (Math.min(q1S,q2S))*2;
        }
        else {
            count = q1H - q2H;
            q2S-=count;
            return count + (Math.min(q1S, q2S))*2;
        }
    }

    private int quarter(int row, int col, int endR, int endC,String value) {
        int count =0;
        for (int i = row; i <=endR; i++) {
            for (int j = col; j <=endC; j++) {
                if (matrix[i][j] == value)
                    count++;
            }
        }
        return count;
    }
}

