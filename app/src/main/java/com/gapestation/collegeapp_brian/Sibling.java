package com.gapestation.collegeapp_brian;

public class Sibling extends FamilyMember{
    String mFirstName;
    String mLastName;

    public Sibling(String firstName, String lastName){
        super(firstName, lastName);
    }

    public Sibling(){
        super();
    }

    @Override
    public String toString(){
        return("Sibling: " + mFirstName + " " + mLastName);
    }
}
