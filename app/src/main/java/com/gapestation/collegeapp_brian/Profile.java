package com.gapestation.collegeapp_brian;

public class Profile {
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

    public Profile(String firstName, String lastName){
        mFirstName = firstName;
        mLastName = lastName;
    }
}
