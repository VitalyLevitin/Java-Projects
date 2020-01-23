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
        if(done)return null;
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>(2);
        temp.add(array.remove(0));
        temp.add(array.remove(0));
        return temp;
    }

    public synchronized void insert( ArrayList<Integer> arr){
        array.add(0,arr);
        notifyAll();
    }

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
