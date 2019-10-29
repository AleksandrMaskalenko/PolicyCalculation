package lv.proofit.policy.service;

import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link CoefficientCalculation} class.
 */
class CoefficientCalculationTest {

    @Test
    void getCoefficient() {
        Coefficient coefficientFire1 = new Coefficient(0.013, new PriceRange(0, 100));
        Coefficient coefficientFire2 = new Coefficient(0.023, new PriceRange(101, 999999));
        Coefficient coefficientWater1 = new Coefficient(0.1, new PriceRange(0, 9));
        Coefficient coefficientWater2 = new Coefficient(0.05, new PriceRange(10, 99999));

        List<Coefficient> coefficientsFire = new ArrayList<>();
        List<Coefficient> coefficientsWater = new ArrayList<>();
        coefficientsFire.add(coefficientFire1);
        coefficientsFire.add(coefficientFire2);
        coefficientsWater.add(coefficientWater1);
        coefficientsWater.add(coefficientWater2);

        CoefficientList fire = new CoefficientList(RiskType.FIRE, coefficientsFire);
        CoefficientList water = new CoefficientList(RiskType.WATER, coefficientsWater);

        List<CoefficientList> coefficientList = new ArrayList<>();
        coefficientList.add(fire);
        coefficientList.add(water);

        CoefficientCalculation coefficientCalculation = new CoefficientCalculation(coefficientList);

        assertEquals(new BigDecimal(0.1), coefficientCalculation.getCoefficient(RiskType.WATER, new Price(8)));
        assertEquals(new BigDecimal(0.05), coefficientCalculation.getCoefficient(RiskType.WATER, new Price(12)));
        assertEquals(new BigDecimal(0.05), coefficientCalculation.getCoefficient(RiskType.WATER, new Price(10)));
        assertEquals(new BigDecimal(0.1), coefficientCalculation.getCoefficient(RiskType.WATER, new Price(0)));

        assertEquals(new BigDecimal(0.013), coefficientCalculation.getCoefficient(RiskType.FIRE, new Price(78)));
        assertEquals(new BigDecimal(0.013), coefficientCalculation.getCoefficient(RiskType.FIRE, new Price(100)));
        assertEquals(new BigDecimal(0.023), coefficientCalculation.getCoefficient(RiskType.FIRE, new Price(101)));
        assertEquals(new BigDecimal(0.013), coefficientCalculation.getCoefficient(RiskType.FIRE, new Price(0)));
    }
}