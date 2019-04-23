package com.gapestation.collegeapp_brian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class GuardianFragment extends Fragment {
    private TextView firstname;
    private TextView lastname;
    private TextView occupation;
    private EditText occupationEdit;
    private int index;
    private Button guardianSubmitButton;
    Guardian mGuardian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_guardian, view, false);

        firstname = rootView.findViewById(R.id.guardianfirstname);
        lastname = rootView.findViewById(R.id.guardianlastname);
        occupation = rootView.findViewById(R.id.occupation);
        occupationEdit = rootView.findViewById(R.id.occupationEdit);
        guardianSubmitButton = (Button)rootView.findViewById(R.id.guardianSubmitButton);

        guardianSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToBackendless();
            }
        });

        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        mGuardian = (Guardian) Family.getFamilyList().get(index);
        firstname.setText(mGuardian.getFirstName());
        lastname.setText(mGuardian.getLastName());

    }

    private void saveToBackendless() {
        String whereClause = "email = 'brianusa2001@gmail.com'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Guardian.class).find(query, new AsyncCallback<List<Guardian>>() {
            @Override
            public void handleResponse(List<Guardian> response) {
                if (!response.isEmpty()) {
                    String guardianId = response.get(0).getObjectId();
                    Log.d("Guardian Fragment", "Object ID: " + guardianId);
                    mGuardian.setObjectId(guardianId);
                    Backendless.Data.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {
                        @Override
                        public void handleResponse(Guardian response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
                else{
                    Backendless.Data.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {
                        @Override
                        public void handleResponse(Guardian response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                            mGuardian.mObjectId = response.mObjectId;
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Guardian Fragment", "Failed to find guardian: " + fault.getMessage());
            }
        });
    }
}
