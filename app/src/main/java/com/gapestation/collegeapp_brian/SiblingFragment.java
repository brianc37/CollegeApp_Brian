package com.gapestation.collegeapp_brian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SiblingFragment extends Fragment {
    private TextView firstname;
    private TextView lastname;
    private int index;
    Sibling mSibling;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_sibling, view, false);

        firstname = rootView.findViewById(R.id.siblingfirstname);
        lastname = rootView.findViewById(R.id.siblinglastname);




        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        mSibling = (Sibling) Family.getFamilyList().get(index);
        firstname.setText(mSibling.getFirstName());
        lastname.setText(mSibling.getLastName());
    }
}
