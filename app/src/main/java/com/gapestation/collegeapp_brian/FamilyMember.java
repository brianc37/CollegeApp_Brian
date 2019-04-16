package com.gapestation.collegeapp_brian;

public abstract class FamilyMember extends ApplicantData {
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

    public FamilyMember(String firstName, String lastName){
        mFirstName = firstName;
        mLastName = lastName;
    }
    
    public boolean equals(FamilyMember familyMember){
        if(familyMember.getFirstName() == mFirstName && familyMember.getLastName() == mLastName) return true;
        else return false;
    }
}
