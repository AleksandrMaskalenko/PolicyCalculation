package lv.proofit.policy.service;

import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.Policy;
import lv.proofit.policy.model.PolicyObject;
import lv.proofit.policy.model.PolicySubObject;
import lv.proofit.policy.model.Price;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Calculation premium price object {@link PremiumCalculator}.
 *
 */
public class PremiumCalculator {
    private CoefficientCalculation coefficientCalculation;

    public PremiumCalculator(CoefficientCalculation coefficientCalculation) {
        this.coefficientCalculation = coefficientCalculation;
    }

    public void calculate(final Policy policy) {

        HashMap<RiskType, Price> map = new HashMap<>();

        policy.getPolicyObjectList().forEach(p -> subObjSort(p, map));
        policy.setPremium(sumAllPrices(map));
    }

    public void subObjSort(PolicyObject policyObject, HashMap<RiskType, Price> map) {
        for (PolicySubObject policySubObject: policyObject.getPolicySubObjectsList()) {

            RiskType riskType = policySubObject.getRiskType();
            Price price = map.get(riskType);

            if (price == null) {
                map.put(riskType, policySubObject.getSumInsured());
            } else {
                map.put(riskType, price.add(policySubObject.getSumInsured()));
            }
        }

    }

    public Price sumAllPrices(HashMap<RiskType, Price> map) {
        Price premium = new Price(0);

        for (RiskType key : map.keySet()) {
            BigDecimal result = map.get(key).getAmount().multiply(getCoefficientCalculation().getCoefficient(key,map.get(key)));

            premium = premium.add(new Price(result));
        }

        return premium;
    }

    public CoefficientCalculation getCoefficientCalculation() {
        return coefficientCalculation;
    }
}
