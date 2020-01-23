public class Person implements Comparable<Person> {
    /**
     * This class holds the attributes of a Person.
     * With a compareable implemntation to compare between persons.
     */
    private double age;
    private String firstName, surName, id;

    public Person(double age, String firstName, String surName, String id) {
        this.age = age;
        this.firstName = firstName;
        this.surName = surName;
        this.id = id;
    }

    @Override
    public int compareTo(Person o) {
        if(surName.compareTo(o.surName)<0)
            return -1;
        else if (surName.compareTo(o.surName)>0)
            return 1;
        if(surName.compareTo(o.surName)==0){
            if(firstName.compareTo(o.firstName)<0)
                return -1;
            else if(firstName.compareTo(o.firstName)>0)
                return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "" + firstName +" " + surName;
    }
}
