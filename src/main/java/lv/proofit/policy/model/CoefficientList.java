package lv.proofit.policy.model;

import lv.proofit.policy.constants.RiskType;
import java.util.List;

public class CoefficientList {
    private RiskType type;
    private List<Coefficient> coefficientList;

    public CoefficientList(RiskType type, List<Coefficient> coefficientList) {
        this.type = type;
        this.coefficientList = coefficientList;
    }

    public Coefficient find(final Price price) {
        return getCoefficientList().stream()
                .filter((Coefficient d) -> d.getPriceRange() == null || d.getPriceRange().contains(price))
                .findFirst().orElse(null);
    }

    public List<Coefficient> getCoefficientList() {
        return coefficientList;
    }

    public RiskType getType() {
        return type;
    }
}
