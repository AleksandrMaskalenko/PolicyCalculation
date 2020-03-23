package lv.proofit.policy.model;

/**
 *  Coefficient for calculation of  price range {@link PriceRange} objects.
 */
public class Coefficient {
    private double coef;
    private PriceRange priceRange;

    public Coefficient(double coef, PriceRange priceRange) {
        this.coef = coef;
        this.priceRange = priceRange;
    }

    public double getCoef() {
        return coef;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }
}
