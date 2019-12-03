import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Polynom {
    private ArrayList<Monomial> polynom;
    public Polynom(){
        polynom = new ArrayList<>();
    }
    public Polynom(double[] coefficient, int[] exponent) {
        polynom = new ArrayList<>();
        for (int i = 0; i < coefficient.length; i++)
            polynom.add(i, new Monomial(coefficient[i], exponent[i]));
        Collections.sort(polynom);
        merge();
    }

    public void merge() {
        for (int i = 0; i < polynom.size() - 1; i++) {
            if (compare(i)) {
                double coefficient = polynom.get(i).getCoefficient() + polynom.get(i + 1).getCoefficient();
                int exponent = polynom.get(i).getExponent();
                polynom.remove(i);
                polynom.remove(i);
                polynom.add(i, new Monomial(coefficient, exponent));
            }
        }
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
        int j = 0;
        for (int i = 0; i < polynom.size(); i++) {
            for (; j < poly.getSize();j++) {
                if (poly.get(j).getExponent() < polynom.get(i).getExponent()) {
                    j = i;
                    break;
                }
                else if (poly.get(j).getExponent() == polynom.get(i).getExponent()) {
                    double coefficient = polynom.get(i).getCoefficient() + poly.get(j).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    polynom.remove(i);
                    polynom.add(i, new Monomial(coefficient, exponent));
                }
                else if(polynom.get(i-1).getExponent()!= poly.get(j).getExponent()){
                    double coefficient = poly.get(j).getCoefficient();
                    int exponent = poly.get(j).getExponent();
                    polynom.add(i, new Monomial(coefficient, exponent));
                }
            }
        }
        return poly;
    }

    public Polynom subtract (Polynom poly){
        int j = 0;
        for (int i = 0; i < polynom.size(); i++) {
            for (; j < poly.getSize();j++) {
                if (poly.get(j).getExponent() < polynom.get(i).getExponent()) {
                    j = i;
                    break;
                }
                else if (poly.get(j).getExponent() == polynom.get(i).getExponent()) {
                    double coefficient = polynom.get(i).getCoefficient() - poly.get(j).getCoefficient();
                    int exponent = polynom.get(i).getExponent();
                    polynom.remove(i);
                    polynom.add(i, new Monomial(coefficient, exponent));
                }
                else if(polynom.get(i-1).getExponent()!= poly.get(j).getExponent()){
                    double coefficient = poly.get(j).getCoefficient();
                    int exponent = poly.get(j).getExponent();
                    polynom.add(i, new Monomial(coefficient, exponent));
                }
            }
        }
        return poly;
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


