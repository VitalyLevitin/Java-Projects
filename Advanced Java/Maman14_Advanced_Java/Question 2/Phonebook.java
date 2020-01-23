import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * This Class decides which numbers
 * are in the phonebook and manipulates
 * the data as it sees fit.
 */
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

<<<<<<< HEAD
=======
    /**
     * This method removes all contacts from the phonebook.
     * (Needed when new file is loaded)
     */
>>>>>>> aad646b56e1b3a40fa4ebaeb8b3c84fde5c6de65
    public void removePhonebook(){
        contact.clear();
    }

<<<<<<< HEAD
=======
    /**
     * Adds a new contact.
     * @param details the contact to add.
     * @return true if the contact was added.
     * @throws AlreadyExistsContactException the contact already in the phonebook.
     * @throws IlegalPhoneNumberException number doesn't stand in the standard.
     */
>>>>>>> aad646b56e1b3a40fa4ebaeb8b3c84fde5c6de65
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

<<<<<<< HEAD
=======
    /**
     *This methods removes a contact from the phonebook.
     * (We only work by name since a few contacts can have the same number)
     * @param details the contact to remove.
     * @throws DoesNotExistContactException No contact under this name in our phonebook.
     */
>>>>>>> aad646b56e1b3a40fa4ebaeb8b3c84fde5c6de65
    public void removeContact(Contact details) throws DoesNotExistContactException {
        if(!contact.containsKey(details.getName()))
            throw new DoesNotExistContactException(details.getName() + " does not exists");
        else
            contact.remove(details.getName());
    }

<<<<<<< HEAD
    public boolean updateContact(Contact details, String oldName) throws DoesNotExistContactException, IlegalPhoneNumberException, AlreadyExistsContactException {
=======
    /**
     * This methods receives a given contact and manipulates it's data as needed.
     * @param details the new details of the contact
     * @param oldName of the contact to find it in the phonebook.
     * @return true if the contact was updated.
     * @throws DoesNotExistContactException no such contact.
     * @throws IlegalPhoneNumberException number not in the standard.
     * @throws AlreadyExistsContactException The new name already in the phonebook.
     * @throws NameCantBeEmpty Can't have a number without a name.
     */
    public boolean updateContact(Contact details, String oldName) throws DoesNotExistContactException, IlegalPhoneNumberException, AlreadyExistsContactException, NameCantBeEmpty {
>>>>>>> aad646b56e1b3a40fa4ebaeb8b3c84fde5c6de65
        if(!myMap().containsKey(oldName)){
            throw new DoesNotExistContactException(details.getName() + " does not exists");
            }
        if(!details.isNumber(details.getNumber()))
            throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
<<<<<<< HEAD
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
=======
        if(details.getName().length()==0)
            throw new NameCantBeEmpty("Name String can't be empty.");
        //Trying to add the contact, if successful we remove the old contact.
        boolean status = addContact(new Contact(details.getName(), details.getNumber()));
        removeContact(new Contact(oldName, myMap().get(oldName).getNumber()));
        return status;
        }

    /**
     * This method searches for the contact via name or phone.
     * @param details the contact to search for
     * @return true if the contact was found.
     * @throws DoesNotExistContactException if the contact is not in the phonebook.
     */
    public boolean searchContact(Contact details) throws DoesNotExistContactException {
        if(details.getName().length()!=0 && !myMap().containsKey(details.getName())){
            throw new DoesNotExistContactException(details.getName() + " does not exists");
        }
        else if(details.getNumber().length()>0 && this.myMap().containsValue(this)!= myMap().containsValue(details)){
            throw new DoesNotExistContactException(details.getNumber() + " does not exists");
        }
        else return true;
    }

    /**
     * Returns the size of the phonebook.
     * @return size of the phonebook.
     */
>>>>>>> aad646b56e1b3a40fa4ebaeb8b3c84fde5c6de65
    public int size(){return contact.size();}

}
