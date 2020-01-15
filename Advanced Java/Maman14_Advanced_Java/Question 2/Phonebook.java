import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook {
    private Map <String, Contact> contact;

    public Phonebook() {
        contact = new TreeMap<>(new Comparator<String>() {
            public int compare(String a, String b) {
                return a.toLowerCase().compareTo(b.toLowerCase());
            }
        });
    }

    public boolean addContact (Contact details){
        if(!contact.containsKey(details.getName())) {
            contact.put(details.getName(), details);
            return true;
        }
        else {
            System.out.println("Already there");
            return false;
        }
    }

    public void removeContact(Contact details){
        if(!contact.containsKey(details.getName()))
            System.out.println("No contact");
        else
            contact.remove(details);
    }

    public void updateContact(Contact details){
        this.contact.put(details.getName(), new Contact(details.getName(),details.getNumber()));
    }

    public int size(){return contact.size();}

//    public Contact getContact(int index){
//        return contact.
//    }

    public String contactName(Object obj){
        return contact.get(obj).getName();
    }
}
