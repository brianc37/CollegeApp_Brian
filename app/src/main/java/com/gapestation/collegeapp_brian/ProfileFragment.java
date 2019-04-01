package com.gapestation.collegeapp_brian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    private TextView firstnametext;
    private TextView lastnametext;
    public Profile Profile1 = new Profile("Alan", "Turing");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_profile, view, false);
        firstnametext = rootView.findViewById(R.id.profilefirstname);
        lastnametext = rootView.findViewById(R.id.profilelastname);
        lastnametext.setText(Profile1.getLastName());
        firstnametext.setText(Profile1.getFirstName());
        return rootView;
    }
}
