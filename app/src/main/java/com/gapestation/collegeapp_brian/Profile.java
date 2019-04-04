package com.gapestation.collegeapp_brian;

import java.util.Date;

public class Profile {
    String mFirstName;
    String mLastName;
    String mEmail;
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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public Profile(String firstName, String lastName){
        mFirstName = firstName;
        mLastName = lastName;
    }

    public Profile(){
        dateOfBirth = new Date();
    }
}
