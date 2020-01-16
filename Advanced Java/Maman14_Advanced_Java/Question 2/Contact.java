import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Contact implements Serializable {
    private String name;
    private String number;
    private final int NUM_LENGTH = 10;
    public Contact(String name, String number) throws IlegalPhoneNumberException {
        this.name = name;
        if(isNumber(number))this.number = number;
        else throw new IlegalPhoneNumberException("Phone number must contain 10 digits");
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean isNumber(String number){
        try{ Integer.parseInt(number);}
        catch (NumberFormatException e){return false;}
        if(number.length()!=NUM_LENGTH) return false;
        return true;
    }
}
