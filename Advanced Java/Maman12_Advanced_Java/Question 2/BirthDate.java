import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class calculates the Date of birth of the Employee.
 * If the Employee's birthday is in the current month, we make sure to add a hefty bonus.
 */
public class BirthDate {
    private Calendar dayOfBirth;
    private int year;
    private int month;

    public BirthDate(int day, int month, int year) {
        GregorianCalendar c = new GregorianCalendar();
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12: {
                if (day < 1 || day > 31)
                    throw new IllegalArgumentException("This day does not exist");
                break;
            }
            case 4: case 6: case 9: case 11: {
                if (day < 1 || day > 30)
                    throw new IllegalArgumentException("This day does not exist");
                break;
            }
            case 2: {
                if (c.isLeapYear(year)) {
                    if (day < 1 || day > 29)
                        throw new IllegalArgumentException("This day does not exist");
                } else {
                    if (day < 1 || day > 28) {
                        throw new IllegalArgumentException("This day does not exist");
                    }
                }
            }
        }
        this.year = year;
        this.month = month;
        dayOfBirth = new GregorianCalendar(year, month-1, day);
    }

    public int getYear() { return year; }

    public int getMonth() { return this.month; }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); //Normal humans time template.
        return sdf.format(dayOfBirth.getTime());
    }
}
