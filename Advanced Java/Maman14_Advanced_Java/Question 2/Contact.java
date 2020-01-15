import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contact {
    private String name;
    private String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public static Comparator<Contact> contactCompartor = new Comparator<Contact>() {
//        @Override
//        public int compare(Contact o1, Contact o2) {
//            return o1.name.toLowerCase().compareTo(o2.name.toLowerCase());
//        }
//    };

//    @Override
//    public int compareTo(Object o) {
//        Contact compare = (Contact) o;
//        return this.getName().toLowerCase().compareTo(compare.getName().toLowerCase());
//    }

//    @Override
//    public int compareTo(Contact o) {
//        return this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
//    }
//}
}
