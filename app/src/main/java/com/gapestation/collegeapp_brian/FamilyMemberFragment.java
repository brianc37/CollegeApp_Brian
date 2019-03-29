package com.gapestation.collegeapp_brian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FamilyMemberFragment extends Fragment {
    private TextView firstnametext;
    private TextView lastnametext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_family_member, view, false);
        firstnametext = rootView.findViewById(R.id.firstname);
        lastnametext = rootView.findViewById(R.id.lastname);
        lastnametext.setText(FamilyMember.getLastName());
        firstnametext.setText(FamilyMember.getFirstName());
        return rootView;
    }
}
