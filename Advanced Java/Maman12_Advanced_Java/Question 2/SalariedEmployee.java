/**
 * Global Salary based Employee.
 */
public class SalariedEmployee extends Employee {
	private double weeklySalary;

	public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double weeklySalary,int day, int month, int year) {
		super(firstName, lastName,socialSecurityNumber,day,month,year);

		if(weeklySalary < 0.0)
			throw new IllegalArgumentException("Weekly salary must be >= 0.0");
		this.weeklySalary = weeklySalary;
	}

	public double getWeeklySalary() {
		return this.weeklySalary;
	}

	@Override
	public double earnings() {
		return getWeeklySalary();
	}

	@Override
	public String toString() {
		return String.format("Salaried employee: %s%n%s: $%,.2f", super.toString(), "Weekly salary", getWeeklySalary());
	}
}
