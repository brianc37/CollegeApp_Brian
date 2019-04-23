package com.gapestation.collegeapp_brian;

import java.util.ArrayList;

public class Family {
    private final String TAG = Family.class.getName();
    private static ArrayList<FamilyMember> family;
    private static Family sFamily;

    private Family(){
        family = new ArrayList<>();
        family.add(new Guardian("test","testy", "tester"));
        family.add(new Guardian("lester", "tester", "testyboi"));
        family.add(new Sibling("johnny", "test"));
    }

    public static Family getFamily(){
        if(sFamily == null){
            sFamily = new Family();
        }
        return sFamily;
    }

    public static ArrayList<FamilyMember> getFamilyList(){
        return family;
    }

    public void setFamily(ArrayList<FamilyMember> family) {
        this.family = family;
    }

    public void addFamilyMember(FamilyMember familyMember){
        family.add(familyMember);
    }

    public void deleteFamilyMember(FamilyMember familyMember){
        family.remove(familyMember);
    }
}
