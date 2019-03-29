package com.gapestation.collegeapp_brian;

public abstract class FamilyMember {
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

    public FamilyMember(){
        mFirstName = "Family";
        mLastName = "Member";
    }
}
