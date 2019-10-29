package lv.proofit.policy.model;

import lv.proofit.policy.constants.RiskType;

public class PolicySubObject {

    private String name;
    private Price sumInsured;
    private RiskType riskType;

    public PolicySubObject(String name, Price sumInsured, RiskType riskType) {
        this.name = name;
        this.sumInsured = sumInsured;
        this.riskType = riskType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(Price sumInsured) {
        this.sumInsured = sumInsured;
    }

    public RiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PolicySubObject)) {
            return false;
        }

        final PolicySubObject that = (PolicySubObject) obj;

        if (!that.name.equals(this.name)) {
            return false;
        }

        if (!Price.equals(that.getSumInsured(), this.getSumInsured())) {
            return false;
        }

        if (!that.riskType.equals(this.riskType)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PolicySubObject: " + getName() + ", " + getSumInsured() + ", "
                + getRiskType();
    }
}
