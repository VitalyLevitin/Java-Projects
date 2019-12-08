public class Monomial implements Comparable<Monomial>{
    private double coefficient;
    int exponent;
    public Monomial(double coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent= exponent;
    }
    public double getCoefficient(){return coefficient;}

    public int getExponent() {
        return exponent;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    @Override
    public int compareTo(Monomial o) {
        if(this.exponent < o.exponent)
            return 1;
        else return -1;
    }

    public String toString() {
        if(getCoefficient() == 0)
            return "";
        if(getCoefficient() == 1 && getExponent() == 0 || getExponent() == 0)
            return String.format("%s%d", "+", 1); // x^0 = 1
        if(getCoefficient() == 1)
            return String.format("%sx^%d", "+", getExponent()); // 1x^y = x^y
        if(getExponent() == 1)
            return String.format("%s%.1fx", "+",getCoefficient()); // x^1 == x
        else
            return String.format("%+.1fx^%d", getCoefficient(), getExponent()); // x^y
    }
    }


