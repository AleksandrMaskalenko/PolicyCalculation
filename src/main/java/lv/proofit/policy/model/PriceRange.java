package lv.proofit.policy.model;

public class PriceRange {
    private final Price minimum;
    private final Price maximum;

    public PriceRange(final int minimum, final int maximum) {
        this.minimum = new Price(minimum);
        this.maximum = new Price(maximum);
    }

    public PriceRange(final Price minimum, final Price maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Price getMinimum() {
        return minimum;
    }

    public Price getMaximum() {
        return maximum;
    }

    @Override
    public boolean equals(Object obj) {
        // includes obj == null and obj is nont a PriceRange object
        if (false == (obj instanceof PriceRange)) {
            return false;
        }

        // now that we are sure that obj != null and obj is a PriceRange
        // object, do the cast and compare minimum and maxium
        final PriceRange that = (PriceRange) obj;

        if (false == Price.equals(this.getMinimum(), that.getMinimum())) {
            return false;
        }

        if (false == Price.equals(this.getMaximum(), that.getMaximum())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Price Range: " + getMinimum() + "-" + getMaximum();
    }

    /**
     * Returns whether the passed price is inside "this" range. Null values
     * minimum/maximum will be ignored (considered as "no limit").
     *
     * @param price
     * @return
     */
    public boolean contains(final Price price) {
        if (getMinimum() != null) {
            if (getMinimum().getAmount().compareTo(price.getAmount()) > 0) {
                return false;
            }
        }

        if (getMaximum() != null) {
            if (getMaximum().getAmount().compareTo(price.getAmount()) < 0) {
                return false;
            }
        }

        return true;
    }
}
