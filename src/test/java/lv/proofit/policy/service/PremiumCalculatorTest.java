package lv.proofit.policy.service;

import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.*;
import lv.proofit.policy.constants.PolicyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases for {@link PremiumCalculator} class.
 */
class PremiumCalculatorTest {

    private CoefficientCalculation coefficientCalculation;

    @BeforeEach
    public void setup() {
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

        coefficientCalculation = new CoefficientCalculation(coefficientList);


    }

    @Test
    public void calculateTest() {
        PolicySubObject policySubObjectWater1 = new PolicySubObject("TV", new Price(new BigDecimal(8)), RiskType.WATER);
        PolicySubObject policySubObjectFire1 = new PolicySubObject("TV", new Price(new BigDecimal(70)), RiskType.FIRE);

        PolicySubObject policySubObjectWater2 = new PolicySubObject("Radio", new Price(new BigDecimal(10)), RiskType.WATER);
        PolicySubObject policySubObjectFire2 = new PolicySubObject("Radio", new Price(new BigDecimal(102)), RiskType.FIRE);

        ArrayList<PolicySubObject> policySubObjects1 = new ArrayList<PolicySubObject>();
        policySubObjects1.add(policySubObjectWater1);
        policySubObjects1.add(policySubObjectFire1);

        ArrayList<PolicySubObject> policySubObjects2 = new ArrayList<PolicySubObject>();
        policySubObjects2.add(policySubObjectWater2);
        policySubObjects2.add(policySubObjectFire2);

        PolicyObject policyObject1 = new PolicyObject("A flat1", policySubObjects1);
        PolicyObject policyObject2 = new PolicyObject("A flat2", policySubObjects2);


        ArrayList<PolicyObject> policyObjectArrayList = new ArrayList<PolicyObject>();
        policyObjectArrayList.add(policyObject1);
        policyObjectArrayList.add(policyObject2);

        Policy policy = new Policy("LV19-07-100000-1", PolicyStatus.APPROVED, policyObjectArrayList);
        PremiumCalculator premiumCalculator = new PremiumCalculator(coefficientCalculation);
        premiumCalculator.calculate(policy);

        assertEquals(new Price(4.86), policy.getPremium());
    }

    @Test
    public void calculateTest1() {
        PolicySubObject policySubObjectWater = new PolicySubObject("TV", new Price(new BigDecimal(8)), RiskType.WATER);
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", new Price(new BigDecimal(100)), RiskType.FIRE);

        ArrayList<PolicySubObject> policySubObjects = new ArrayList<PolicySubObject>();
        policySubObjects.add(policySubObjectWater);
        policySubObjects.add(policySubObjectFire);


        PolicyObject policyObject = new PolicyObject("A flat1", policySubObjects);

        ArrayList<PolicyObject> policyObjectArrayList = new ArrayList<PolicyObject>();
        policyObjectArrayList.add(policyObject);

        Policy policy = new Policy("LV19-07-100000-1", PolicyStatus.APPROVED, policyObjectArrayList);
        PremiumCalculator premiumCalculator = new PremiumCalculator(coefficientCalculation);
        premiumCalculator.calculate(policy);

        assertEquals(new Price(2.10), policy.getPremium());
    }

    @Test
    public void calculateTest2() {
        PolicySubObject policySubObjectWater = new PolicySubObject("TV", new Price(new BigDecimal(100)), RiskType.WATER);
        PolicySubObject policySubObjectFire = new PolicySubObject("TV", new Price(new BigDecimal(500)), RiskType.FIRE);

        ArrayList<PolicySubObject> policySubObjects = new ArrayList<PolicySubObject>();
        policySubObjects.add(policySubObjectWater);
        policySubObjects.add(policySubObjectFire);


        PolicyObject policyObject = new PolicyObject("A flat1", policySubObjects);

        ArrayList<PolicyObject> policyObjectArrayList = new ArrayList<PolicyObject>();
        policyObjectArrayList.add(policyObject);

        Policy policy = new Policy("LV19-07-100000-1", PolicyStatus.APPROVED, policyObjectArrayList);
        PremiumCalculator premiumCalculator = new PremiumCalculator(coefficientCalculation);
        premiumCalculator.calculate(policy);

        assertEquals(new Price(16.50), policy.getPremium());
    }

    @Test
    void subObjSortTest() {
        PolicySubObject policySubObjectWater1 = new PolicySubObject("TV", new Price(new BigDecimal(8)), RiskType.WATER);
        PolicySubObject policySubObjectFire1 = new PolicySubObject("TV", new Price(new BigDecimal(70)), RiskType.FIRE);

        PolicySubObject policySubObjectWater2 = new PolicySubObject("Radio", new Price(new BigDecimal(10)), RiskType.WATER);
        PolicySubObject policySubObjectFire2 = new PolicySubObject("Radio", new Price(new BigDecimal(102)), RiskType.FIRE);

        ArrayList<PolicySubObject> policySubObjects1 = new ArrayList<PolicySubObject>();
        policySubObjects1.add(policySubObjectWater1);
        policySubObjects1.add(policySubObjectFire1);

        ArrayList<PolicySubObject> policySubObjects2 = new ArrayList<PolicySubObject>();
        policySubObjects2.add(policySubObjectWater2);
        policySubObjects2.add(policySubObjectFire2);

        PolicyObject policyObject1 = new PolicyObject("A flat1", policySubObjects1);
        PolicyObject policyObject2 = new PolicyObject("A flat2", policySubObjects2);

        PremiumCalculator premiumCalculator = new PremiumCalculator(coefficientCalculation);
        HashMap<RiskType, Price> map = new HashMap<>();
        premiumCalculator.subObjSort(policyObject1, map);
        premiumCalculator.subObjSort(policyObject2, map);

        assertEquals(2, map.size());
        assertEquals(new Price(172), map.get(RiskType.FIRE));
        assertEquals(new Price(18), map.get(RiskType.WATER));
    }

    @Test
    void sumAllTest1() {
        PremiumCalculator premiumCalculator = new PremiumCalculator(coefficientCalculation);
        HashMap<RiskType, Price> map = new HashMap<>();
        map.put(RiskType.FIRE, new Price(70));
        map.put(RiskType.WATER, new Price(10));

        assertEquals(new Price(1.41), premiumCalculator.sumAllPrices(map));
    }
}