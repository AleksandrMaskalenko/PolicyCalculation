package lv.proofit.policy.service;

import lv.proofit.policy.constants.PolicyStatus;
import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.Policy;
import lv.proofit.policy.model.PolicyObject;
import lv.proofit.policy.model.PolicySubObject;
import lv.proofit.policy.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link Policy} class.
 */
class PolicyTest {

    private Policy policy;

    @BeforeEach
    public void setup() {
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

        policy = new Policy("LV19-07-100000-1", PolicyStatus.APPROVED, policyObjectArrayList);
    }

    @Test
    void testEquals() {

        assertEquals(policy, policy);
        assertNotEquals(policy, null);
    }

    @Test
    void testToString() {

        assertNotNull(policy.toString());
    }
}