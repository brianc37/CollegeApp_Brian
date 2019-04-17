package com.gapestation.collegeapp_brian;

public abstract class FamilyMember extends ApplicantData {
    public static final String EXTRA_RELATION = "org.pltw.examples.collegeapp.relation";
    public static final String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";
    String mFirstName;
    String mLastName;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public FamilyMember() {
        mFirstName = "Family";
        mLastName = "Member";
    }

    public FamilyMember(String firstName, String lastName) {
        mFirstName = firstName;
        mLastName = lastName;
    }

    public boolean equals(Object o) {
        if ((o instanceof Guardian) && (this instanceof Guardian)) {
            // both are guardians so cast the Object
            Guardian g = (Guardian) o;
            if(g.getFirstName() == mFirstName && g.getLastName() == mLastName) return true;
            else return false;
        }
        else if((o instanceof Sibling) && (this instanceof Sibling)){

            Sibling g = (Sibling) o;
            if(g.getFirstName() == mFirstName && g.getLastName() == mLastName) return true;
            else return false;
        }
        else return false;
    }
}