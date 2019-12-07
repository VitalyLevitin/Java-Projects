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
        Collections.sort(polynom);
        merge(true);
    }
    private void merge(boolean plus){
        if(plus) {
            for (int i = 0; i < polynom.size() - 1; i++) {
                if (compare(i)) {
                    double coefficient = polynom.get(i).getCoefficient() + polynom.get(i + 1).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    remove(i, coefficient, exponent);
                }
            }
        }
        else
        {
            for (int i = 0; i < polynom.size() - 1; i++) {
                if (compare(i)) {
                    double coefficient = polynom.get(i).getCoefficient() - polynom.get(i + 1).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    remove(i, coefficient, exponent);
                    if(coefficient==0) plus = false;
                }
            }
        }
        if (!plus)
            polynom.clear();
    }

    private void remove(int i, double coefficient, int exponent) {
        polynom.remove(i);
        polynom.remove(i);
        polynom.add(i, new Monomial(coefficient, exponent));
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

    public Polynom addition (Polynom poly){
        Polynom mergedPoly = new Polynom();
        stack(polynom,poly,mergedPoly);
        mergedPoly.merge(true);
        return mergedPoly;
    }
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

    public Polynom subtract (Polynom poly){
        Polynom mergedPoly = new Polynom();
        stack(polynom,poly,mergedPoly);
        mergedPoly.merge(false);
        return mergedPoly;
    }

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

    public int getSize () {
            return polynom.size();
        }
        public String toString(){
            String polyString = "";
            for (Monomial monom : polynom)
                polyString += monom.toString();
            return polyString;
        }
    }


