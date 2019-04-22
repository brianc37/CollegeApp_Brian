package com.gapestation.collegeapp_brian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class GuardianFragment extends Fragment {
    private TextView firstname;
    private TextView lastname;
    private TextView occupation;
    private EditText occupationEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_guardian, view, false);

        firstname = rootView.findViewById(R.id.guardianfirstname);
        lastname = rootView.findViewById(R.id.guardianlastname);
        occupation = rootView.findViewById(R.id.occupation);
        occupationEdit = rootView.findViewById(R.id.occupationEdit);



        return rootView;
    }
}
