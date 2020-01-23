import java.security.SecureRandom;
import java.util.Scanner;

public class Main {
    final static int size = 10;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        GenericSet<Integer> set1 = new GenericSet<>();
        GenericSet<Integer> set2 = new GenericSet<>();
        GenericSet<Integer> set3 = new GenericSet<>();
        GenericSet<Integer> set4 = new GenericSet<>();
        GenericSet<Person> personSet = new GenericSet<>();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < size; i++) {
            set1.insert(random.nextInt(100));
            set2.insert(random.nextInt(100));
            set3.insert(random.nextInt(100));
        }

        System.out.println("First set is : " + set1);
        System.out.println("Second set is : " + set2);
        System.out.println("Third set is : " + set3);

        set1.union(set2);
        System.out.println("Union between first & second set is: " + set1);

        set1.intersect(set3);
        System.out.println("Intersect between first and third set is: " + set1);

        System.out.println("Please enter two numbers to create a 4th set");
        for (int i = 0; i < 2; i++) {
            set4.insert(scan.nextInt());
        }

        if(set1.isSubset(set4))
            System.out.println("Set4 is a subset of Set1.");
        else if(set2.isSubset(set4))
            System.out.println("Set4 is a subset of Set2.");
        else if(set3.isSubset(set4))
            System.out.println("Set4 is a subset of Set3.");
        else
            System.out.println("Set 4 is not a subset of any of the other sets");

        System.out.println("Please enter a third number for various tests.");
        int x = scan.nextInt();
        if(set1.isMember(x))
            System.out.println(x + "is a member of the first group.");
        set2.insert(x);
        System.out.println("The second set after the addition of "+ x +" is " +set2);
        set3.delete(x);
        System.out.println("The third set after the removal of "+ x +" is " +set3);

        personSet.insert(new Person(3,"Albert","Cornli", "323"));
        personSet.insert(new Person(3,"Blbert","Cornli", "323"));
        personSet.insert(new Person(3,"Clbert","Eornli", "323"));
        personSet.insert(new Person(3,"Dlbert","Dornli", "323"));
        personSet.insert(new Person(3,"Elbert","Fornli", "323"));

        System.out.println("Youngest person in the set is: " + MinimumVar.minSet(personSet));
    }
}
