package lv.proofit.policy.model;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * An immutable {@link Price} object.
 *
 * For simplicity reasons, the price class does not have a currency.
 *
 * Scale can be ignored when checking for equality, e.g. "1.2" is the same as "1.20".
 */
public final class Price {
    private final BigDecimal amount;

    /**
     * Constructs a new {@link Price} instance.
     *
     * @param amount (if null, a NullPointerException will be thrown).
     */
    public Price(BigDecimal amount) {
        super();

        if (amount == null) {
            throw new NullPointerException("Tried to construct a new Price objects with amount==null");
        }

        this.amount = round(amount);
    }

    /**
     * Constructs a new {@link Price} instance.
     *
     * @param amount (if null, a NullPointerException will be thrown).
     */
    public Price(final Integer amount) {
        super();

        if (amount == null) {
            throw new NullPointerException("Tried to construct a new Price objects with amount==null");
        }

        this.amount = round(new BigDecimal(amount));
    }

    /**
     * Constructs a new {@link Price} instance.
     *
     * @param amount (if null, a NullPointerException will be thrown).
     */
    public Price(final Double amount) {
        super();

        if (amount == null) {
            throw new NullPointerException("Tried to construct a new Price objects with amount==null");
        }

        this.amount = round(new BigDecimal(amount));
    }

    /**
     * Constructs a new {@link Price} instance.
     *
     * @param amount (if null, a NullPointerException will be thrown).
     */
    public Price(final String amount) {
        super();

        if (amount == null) {
            throw new NullPointerException("Tried to construct a new Price objects with string amount==null");
        }

        this.amount = round(new BigDecimal(amount));
    }

    /**
     * Returns the amount; since a {@link BigDecimal} itself is immutable, no
     * defensive copies will be made here.
     *
     * @return
     */
    public final BigDecimal getAmount() {
        return amount;
    }

    /**
     * Adds the passed {@link Price} object to "this" object. A new instance of
     * {@link Price} will be returned.
     *
     * @param price
     * @return
     */
    public Price add(final Price price) {
        return new Price(getAmount().add(price.getAmount()));
    }

    @Override
    public boolean equals(Object obj) {
        if (false == (obj instanceof Price)) {
            return false;
        }

        final Price that = (Price) obj;

        return safeEqualsIgnoreScale(this.getAmount(), that.getAmount());
    }

    private final static boolean safeEqualsIgnoreScale(final BigDecimal value1, final BigDecimal value2) {
        if (value1 == value2) {
            // includes both null
            return true;
        }

        if (value1 == null || value2 == null) {
            // one value null, other value not null
            return false;
        }

        final int scale;
        final int scale1 = value1.scale();
        final int scale2 = value2.scale();

        if (scale1 == scale2) {
            return value1.equals(value2);
        }

        if (scale1 > scale2) {
            scale = scale1;
        } else {
            scale = scale2;
        }

        return value1.setScale(scale).equals(value2.setScale(scale));
    }

    /**
     * Returns whether the two {@link Price} instances are equal allowing null
     * values. If both prices are null, they will be considered equal.
     *
     * @param price1
     * @param price2
     * @return
     */
    public final static boolean equals(final Price price1, final Price price2) {
        if (price1 == null && price2 == null) {
            return true;
        }

        if (price1 == null || price2 == null) {
            return false;
        }

        return price1.equals(price2);
    }

    private BigDecimal round(BigDecimal value) {
        MathContext mc = new MathContext(3); // 3 precision
        return value.round(mc);
    }

    @Override
    public String toString() {
        return getAmount().toString();
    }

    @Override
    public int hashCode() {
        return getAmount().hashCode();
    }
}
