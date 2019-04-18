package com.gapestation.collegeapp_brian;

import java.util.Date;

public class Profile {
    String mFirstName;
    String mLastName;
    String mEmail;
    String mObjectId;
    Date dateOfBirth;
    private final static String photoFilename = "IMG_PROFILE.jpg";

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

    public void setObjectId(String objectId) {
        mObjectId = objectId;
    }

    public String getObjectId() {
        return mObjectId;
    }

    public String getPhotoFilename(){
        return photoFilename;
    }
}
