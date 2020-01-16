/**
 * Per Piece based Employee.
 */
public class PieceWorkerEmployee extends Employee {
    private int numberOfPieces;
    private double pricePerPiece;

    public PieceWorkerEmployee(String firstName, String lastName, String socialSecurityNumber, int numberOfPieces,
                               double pricePerPiece, int day, int month, int year) {
        super(firstName, lastName, socialSecurityNumber, day, month, year);
        //Valid check.
        if(numberOfPieces < 1)
            throw new IllegalArgumentException("Number of pieces must be >= 1");
        if(pricePerPiece < 0.0)
            throw new IllegalArgumentException("Price per piece must be >= 0.0");
        this.numberOfPieces = numberOfPieces;
        this.pricePerPiece = pricePerPiece;
    }

    private int getNumberOfPieces() { return this.numberOfPieces; }

    private double getPricePerPiece() { return this.pricePerPiece; }

    private double getTotalWage() { return getNumberOfPieces() * getPricePerPiece(); }

    @Override
    public double earnings() { return getTotalWage(); }

    @Override
    public String toString() {
        return String.format("Piece worker employee: %s%n%s: %d; %s: $%,.2f",super.toString(), "Pieces made", getNumberOfPieces(),"Price per piece",getPricePerPiece());
    }
}
