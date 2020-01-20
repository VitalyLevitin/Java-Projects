import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This Class holds the name and number of each
 * contact in the phonebook.
 */
public class Contact implements Serializable {
    private String name;
    private String number;
    private final int NUM_LENGTH = 10; //Usual number length is 10.

    public Contact(String name, String number) throws IlegalPhoneNumberException {
        this.name = name;
        if(isNumber(number) || number.length()==0)this.number = number;
        else throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    /**
     * This methods checks if the given number
     * is numbers only and following the format
     * of 10 numbers exactly.
     * @param number The number.
     * @return true if it's a number, false else.
     */
    public boolean isNumber(String number){
        try{ Integer.parseInt(number);}
        catch (NumberFormatException e){return false;}
        if(number.length()!=NUM_LENGTH) return false;
        return true;
    }
}
