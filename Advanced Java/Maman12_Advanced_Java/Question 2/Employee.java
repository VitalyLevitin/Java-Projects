import java.util.Calendar;

/**
 * Employees skeleton.
 */

public abstract class Employee {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private final BirthDate date;

	public Employee(String firstName, String lastName, String socialSecurityNumber, int day, int month, int year) {
		this.date = new BirthDate(day, month, year);
		this.firstName = firstName;
		this.lastName  = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public abstract double earnings(); //To be implemented in inherit classes.

	private String getFirstName() { return firstName; }

	private String getLastName() { return lastName; }

	private String getSocialSecurityNumber() { return socialSecurityNumber; }

	private String getBirthDay() { return this.date.toString(); }

	public int getMonth() { return this.date.getMonth()-1; }

	private int getAge() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		return currentYear - date.getYear(); //Returns Employee age.
	}

	@Override
	public String toString() {
		return String.format("%s %s%n%d years old %nSocial security number: %s;%nDate of birth: %s", getFirstName(),getLastName(),getAge(),getSocialSecurityNumber(),getBirthDay());
	}
}
