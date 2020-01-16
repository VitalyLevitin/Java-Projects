import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Phonebook implements Serializable {
    private Map <String, Contact> contact;

    public Phonebook() {
        contact = new TreeMap<>(new Comparator<String>() {
            public int compare(String a, String b) {
                return a.toLowerCase().compareTo(b.toLowerCase());
            }
        });
    }

    public Map <String, Contact> myMap(){
        return contact;
    }

    public void removePhonebook(){
        contact.clear();
    }

    public boolean addContact (Contact details) throws AlreadyExistsContactException, IlegalPhoneNumberException {
        if(contact.containsKey(details.getName())) {
            throw new AlreadyExistsContactException(details.getName() + " already exists");
        }
        else if(!details.isNumber(details.getNumber())){
            throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
        }
        else {
            contact.put(details.getName(), details);
            return true;
        }
    }

    public void removeContact(Contact details) throws DoesNotExistContactException {
        if(!contact.containsKey(details.getName()))
            throw new DoesNotExistContactException(details.getName() + " does not exists");
        else
            contact.remove(details.getName());
    }

    public boolean updateContact(Contact details, String oldName) throws DoesNotExistContactException, IlegalPhoneNumberException, AlreadyExistsContactException {
        if(!myMap().containsKey(oldName)){
            throw new DoesNotExistContactException(details.getName() + " does not exists");
            }
        if(!details.isNumber(details.getNumber()))
            throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
        removeContact(new Contact(oldName, myMap().get(oldName).getNumber()));
        boolean status = addContact(new Contact(details.getName(), details.getNumber()));
        return status;
        }

    public boolean searchContact(Contact details) throws DoesNotExistContactException {
        if(details.getName().compareTo("")==0 ||!myMap().containsKey(details.getName())){
            throw new DoesNotExistContactException(details.getName() + " does not exists");
        }
        else if(!myMap().containsValue(details.getNumber())){
            throw new DoesNotExistContactException(details.getNumber() + " does not exists");
        }
        return true;
    }
    public int size(){return contact.size();}

}
