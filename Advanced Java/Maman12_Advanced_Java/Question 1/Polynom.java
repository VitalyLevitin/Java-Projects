import java.util.ArrayList;
import java.util.Collections;

public class Polynom {
    private ArrayList<Monomial> polynom;

    public Polynom(){
        polynom = new ArrayList<>();
    }

    public Polynom(double[] coefficient, int[] exponent) {
        if(exponent.length != coefficient.length)
            throw new IllegalArgumentException("The power array must be equal to the coefficient array");
        polynom = new ArrayList<>();
        for (int i = 0; i < coefficient.length; i++)
            polynom.add(i, new Monomial(coefficient[i], exponent[i]));
        Collections.sort(polynom); //The highest exponent comes first. (x^y....1)
        merge(true); //Merging between same exponential values.
    }

    //If the boolean value is true we add the two Polynomials, otherwise we subtract.
    private void merge(boolean plus){
        if(plus) { //Addition
            for (int i = 0; i < polynom.size() - 1;) {
                if (compare(i)) {//Comparing exponents.
                    double coefficient = polynom.get(i).getCoefficient() + polynom.get(i + 1).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    remove(i, coefficient, exponent);//To avoid overlapping values.
                }
                else
                    i++; //We only want to move forward after we made we collected all of the same exponents together.
            }
        }
        else //Subtract.
        {
            for (int i = 0; i < polynom.size() - 1;) {
                if (compare(i)) {
                    double coefficient = polynom.get(i).getCoefficient() - polynom.get(i + 1).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    remove(i, coefficient, exponent);
                    if(coefficient==0) plus = false; //Here we reuse the plus var. It's now checks if the array is empty.
                }
                else
                    i++;
            }
        }
        if (!plus) //If the array is empty we want to remove all the blank location holders.
            polynom.clear();
    }

    private void remove(int i, double coefficient, int exponent) {
        polynom.remove(i);//We use double remove since the ArrayList fixes itself every time.
        polynom.remove(i);
        polynom.add(i, new Monomial(coefficient, exponent)); //Adding the combined form of the Monomial.
    }

    private boolean compare(int i) {
        if (polynom.get(i).getExponent() == polynom.get(i + 1).getExponent())
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Polynom))
            return false;
        Polynom poly = (Polynom) obj;
        if (polynom.size() != poly.getSize())
            return false;
        for (int i = 0; i < poly.getSize(); i++) {
            if (polynom.get(i).getCoefficient() != poly.get(i).getCoefficient() ||
                    polynom.get(i).getExponent() != poly.get(i).getExponent())
                return false;
        }
        return true;
    }

    private Monomial get(int i) { return polynom.get(i);}

    /**
     * This method receives two Polynomials and adds them together.
     * Only adds two monomials if their exponent is the same.
     * @param poly The second Polynomial.
     * @return The new merged Polynomial.
     */
    public Polynom addition (Polynom poly){
        Polynom mergedPoly = new Polynom(); //We need to return a new Polynomial in order to not mess the original values.
        stack(polynom,poly,mergedPoly);
        mergedPoly.merge(true);
        return mergedPoly;
    }

    //Takes both Polynomials and adds them into one big array, then we sort it for easier merge.
    private void stack(ArrayList<Monomial> polynom, Polynom poly, Polynom mergedPoly){
        for (int j = 0; j < polynom.size(); j++) {
            mergedPoly.polynom.add(new Monomial(polynom.get(j).getCoefficient(),polynom.get(j).getExponent()));
        }
        for (int j = 0; j < poly.getSize(); j++) {
            addPoly(poly.polynom.get(j).getCoefficient(),poly.polynom.get(j).getExponent(),mergedPoly);
        }
        Collections.sort((mergedPoly.polynom));
    }

    private void addPoly(double coefficient, int exponent, Polynom mergedPoly){
        mergedPoly.polynom.add(new Monomial(coefficient, exponent));
    }

    /**
     * This method receives two Polynomials and subtracts between them.
     * If the result is 0, we skip to the next Monomial.
     * @param poly The second Polyinomial.
     * @return The new merged Polynomial.
     */
    public Polynom subtract (Polynom poly){
        Polynom mergedPoly = new Polynom();
        stack(polynom,poly,mergedPoly);
        mergedPoly.merge(false);
        return mergedPoly;
    }

    /**
     * This method takes the activating Polynomial and returns
     * it's differentiation form.
     * @return The Polynomial after getting differentiaited once.
     */
    public Polynom differentiation(){
        Polynom poly = new Polynom();
        for (Monomial monom :
                polynom) {
            if (monom.getExponent() != 0){
                monom.setCoefficient(monom.getExponent()*monom.getCoefficient());
                monom.setExponent(monom.getExponent()-1);
                poly.polynom.add(monom);
            }
            else{
                monom.setCoefficient(0);
                poly.polynom.add(monom);
            }
            }
        return poly;
    }

    /**
     * @return the size of the current Polynomial.
     */
    public int getSize () {
            return polynom.size();
    }

    /**
     * Returns the Polynomial in x^y view.
     * @return the Polynomial.
     */
    public String toString(){
        String polyString = "";
        for (Monomial monom : polynom)
            polyString += monom.toString();
        return polyString;
    }
}


