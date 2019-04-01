package com.gapestation.collegeapp_brian;

public class Guardian extends FamilyMember{
    String mLastName;
    String mFirstName;

    @Override
    public String getLastName() {
        return mLastName;
    }

    @Override
    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    @Override
    public String getFirstName() {
        return mFirstName;
    }

    @Override
    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Guardian(String firstName, String lastName){
        mFirstName = firstName;
        mLastName = lastName;

    }

}
