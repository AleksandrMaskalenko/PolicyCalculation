package lv.proofit.policy.model;

import lv.proofit.policy.constants.PolicyStatus;

import java.util.Collection;

/**
 * A list of {@link Price} objects.
 */
public class Policy {

    private String number;
    private PolicyStatus status;
    private Collection<PolicyObject> policyObjectList;
    private Price premium;

    public Policy(String number, PolicyStatus status, Collection<PolicyObject> policyObjectList) {
        this.number = number;
        this.status = status;
        this.policyObjectList = policyObjectList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PolicyStatus getStatus() {
        return status;
    }

    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    public Collection<PolicyObject> getPolicyObjectList() {
        return policyObjectList;
    }

    public void setPolicyObjectList(Collection<PolicyObject> policyObjectList) {
        this.policyObjectList = policyObjectList;
    }

    public Price getPremium() {
        return premium;
    }

    public void setPremium(Price premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Policy)) {
            return false;
        }

        final Policy that = (Policy) obj;

        if (!that.getNumber().equals(this.getNumber())) {
            return false;
        }

        if (!that.getStatus().equals(this.getStatus())) {
            return false;
        }

        if (!that.getPolicyObjectList().equals(this.getPolicyObjectList())) {
            return false;
        }

        if (!Price.equals(that.getPremium(), this.getPremium())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Policy: " + getNumber() + ", " + getStatus() + ", " + getPolicyObjectList().toString() + ", "
                + getPremium();
    }
}
