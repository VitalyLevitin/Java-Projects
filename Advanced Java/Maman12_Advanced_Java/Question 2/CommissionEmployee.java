/**
 * Commission based Employee.
 */
public class CommissionEmployee extends Employee {
	private double grossSales;
	private double commissionRate;

	public CommissionEmployee(String firstname, String lastName, String socialSecurityNumber, double grossSales, double commissionRate,int day, int month, int year) {
		super(firstname, lastName, socialSecurityNumber,day,month,year);

		if(commissionRate <= 0.0 || commissionRate >= 1.0)
			throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");

		if(grossSales < 0.0)
			throw new IllegalArgumentException("Gross sales must be > 0.0");

		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}

	public double getGrossSales() {
		return grossSales;
	}

	public void setCommissionRate(double commissionRate) {
		if(commissionRate <= 0.0 || commissionRate >= 1.0)
			throw new IllegalArgumentException("Commission rate must be  >0.0 and <1.0");

		this.commissionRate = commissionRate;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	@Override
	public double earnings() {
		return getCommissionRate() * getGrossSales();
	}

	@Override
	public String toString() {
		return String.format("%s: %s%n%s: $%,.2f; %s: %.2f", "Commission employee",super.toString(),"Gross sales",getGrossSales(),
				"Commission rate", getCommissionRate());
	}

}
