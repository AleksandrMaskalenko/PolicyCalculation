package lv.proofit.policy.service;

import lv.proofit.policy.constants.RiskType;
import lv.proofit.policy.model.Coefficient;
import lv.proofit.policy.model.CoefficientList;
import lv.proofit.policy.model.Price;

import java.math.BigDecimal;
import java.util.List;

public class CoefficientCalculation {
    private List<CoefficientList> coefficientList;

    public CoefficientCalculation(List<CoefficientList> coefficientList) {
        this.coefficientList = coefficientList;
    }

    public BigDecimal getCoefficient(RiskType riskType, Price price) {
        CoefficientList list = getCoefficientList().stream()
                .filter(c -> c.getType().equals(riskType))
                .findFirst()
                .get();

        Coefficient coefficient = list.find(price);
        return BigDecimal.valueOf(coefficient.getCoef());

    }

    public List<CoefficientList> getCoefficientList() {
        return coefficientList;
    }
}
