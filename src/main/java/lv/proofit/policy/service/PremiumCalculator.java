package lv.proofit.policy.service;

import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.Policy;
import lv.proofit.policy.model.PolicyObject;
import lv.proofit.policy.model.PolicySubObject;
import lv.proofit.policy.model.Price;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

        Map<RiskType, Price> map = new HashMap<>();

        for (PolicyObject p: policy.getPolicyObjectList()) {
            map = getSumPriceByRiskType(p);
        }

        policy.setPremium(sumAllPrices(map));
    }

    public Map<RiskType, Price> getSumPriceByRiskType(PolicyObject policyObject) {
        Map<RiskType, Price> map = new HashMap<>();

        for (PolicySubObject policySubObject: policyObject.getPolicySubObjectsList()) {

            RiskType riskType = policySubObject.getRiskType();
            Price price = map.get(riskType);

            if (price == null) {
                map.put(riskType, policySubObject.getSumInsured());
            } else {
                map.put(riskType, price.add(policySubObject.getSumInsured()));
            }
        }

        return map;

    }

    public Price sumAllPrices(Map<RiskType, Price> map) {
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
