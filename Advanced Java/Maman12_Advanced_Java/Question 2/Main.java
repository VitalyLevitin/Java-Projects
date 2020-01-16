import java.util.Calendar;
/**
 * This program calculates and stores a workplace Employee database.
 * Stores the full ID of the Employee, and his salary based on what type of worker he is.
 *
 * @author Vitaly Levitin
 *
 */
public class Main {
	public static void main(String[] args) {
		SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00,29,2,1988);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.7, 40,30,6,2000);
		CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06,31,12,1950);
		BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300,28,2,1997);
		PieceWorkerEmployee pieceWorkerEmployee = new PieceWorkerEmployee("Vitaly", "Levitin", "555-55-5555", 100, 150, 30, 9, 1993);

		Employee[] employees = new Employee[5];
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		employees[2] = commissionEmployee;
		employees[3] = basePlusCommissionEmployee;
		employees[4] = pieceWorkerEmployee;

		System.out.printf("Employees processed polymorphicaly: %n%n");
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		for(Employee currentEmployee: employees) {
			System.out.println(currentEmployee);
			if (currentEmployee instanceof BasePlusCommissionEmployee) {
				BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;
				employee.setBaseSalary(1.10 * employee.getBaseSalary());
				System.out.printf("New base salary with 10%% increase is: $%,.2f%n",employee.getBaseSalary());
			}
			System.out.printf("Earned $%,.2f%n", currentEmployee.earnings());
			if(currentMonth == currentEmployee.getMonth())//Birthday bonus if current month == birthday.
				System.out.printf("200$ bonus for their birthday%nEarned $%,.2f%n%n",currentEmployee.earnings() + 200.0);
			else
				System.out.println("\n");
		}

		for(int j=0 ; j<employees.length; j++)
			System.out.printf("Employee %d is %s%n",j,employees[j].getClass().getName());
	}
}
