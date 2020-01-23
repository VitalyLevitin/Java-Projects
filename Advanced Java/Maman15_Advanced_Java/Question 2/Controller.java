import java.util.ArrayList;

public class Controller {
    private ArrayList<ArrayList<Integer>> array;
    private int numOfThreads, waiting;
    private boolean done;

    public Controller(ArrayList<ArrayList<Integer>> array, int n) {
        this.array = new ArrayList<>();
        numOfThreads = n;
        this.array.addAll(array);
    }

    /**
     * Takes two ArrayLists and returns them as one ArrayList.
     * Using thread logic to make sure the same job isn't done twice
     * or all the threads fall asleep.
     * @return Merged ArrayList.
     */
    public synchronized ArrayList<ArrayList<Integer>> removeCouple(){
        while(array.size()<=1 && !done) {
            if (waiting == numOfThreads-1) {
                notifyAll();
                done = true;
            }
            else {
                waiting++;
                try{
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waiting--;
            }
        }
        if(done)return null;//The array size is 1 and the task is completed.
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>(2);
        temp.add(array.remove(0));
        temp.add(array.remove(0));
        return temp;
    }

    /**
     * Adds the merge sorted array at the start of the full array.
     * @param arr the merge sorted array.
     */
    public synchronized void insert( ArrayList<Integer> arr){
        array.add(0,arr);
        notifyAll(); //After each insert we need to awake different threads that waiting for their couple.
    }

    /**
     * Prints out the merged array.
     * The array is completely at the first location at t his point.
     */
    public synchronized void result(){
        if(!done) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            for (Integer i : array.get(0)) {
                System.out.print(i + " ");
            }
    }

}
