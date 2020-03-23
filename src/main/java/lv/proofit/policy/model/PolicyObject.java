package lv.proofit.policy.model;

import java.util.Collection;

/**
 * A {@link PolicyObject}
 */
public class PolicyObject {

    private String flat;
    private Collection<PolicySubObject> policySubObjectsList;

    public PolicyObject(String flat, Collection<PolicySubObject> policySubObjectsList) {
        this.flat = flat;
        this.policySubObjectsList = policySubObjectsList;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public Collection<PolicySubObject> getPolicySubObjectsList() {
        return policySubObjectsList;
    }

    public void setPolicySubObjectsList(Collection<PolicySubObject> policySubObjectsList) {
        this.policySubObjectsList = policySubObjectsList;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof PolicyObject)) {
            return false;
        }

        final PolicyObject that = (PolicyObject) obj;

        if (!that.getFlat().equals(this.getFlat())) {
            return false;
        }

        if (!that.getPolicySubObjectsList().equals(this.getPolicySubObjectsList())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "PolicyObject: " + getFlat() + ", " + getPolicySubObjectsList();
    }
}
