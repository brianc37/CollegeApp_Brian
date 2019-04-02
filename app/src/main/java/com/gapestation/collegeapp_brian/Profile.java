package com.gapestation.collegeapp_brian;

import java.util.Date;

public class Profile {
    String mFirstName;
    String mLastName;
    Date dateOfBirth;

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

    public Profile(){
        dateOfBirth = new Date();
    }
}
