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

import java.util.List;

public class SiblingFragment extends Fragment {
    private TextView firstname;
    private TextView lastname;
    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private Button submit;
    private int index;
    Sibling mSibling;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        View rootView = inflater.inflate(R.layout.fragment_sibling, view, false);

        firstname = rootView.findViewById(R.id.siblingfirstname);
        lastname = rootView.findViewById(R.id.siblinglastname);
        firstnameEdit = rootView.findViewById(R.id.siblingfirstnameEdit);
        lastnameEdit = rootView.findViewById(R.id.siblinglastnameEdit);
        submit = rootView.findViewById(R.id.siblingSubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname.setText(firstnameEdit.getText());
                lastname.setText(lastnameEdit.getText());
                mSibling.setFirstName(firstname.getText().toString());
                mSibling.setLastName(lastname.getText().toString());
                saveToBackendless();
            }
        });

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

    private void saveToBackendless() {
        DataQueryBuilder query = DataQueryBuilder.create();
        Backendless.Data.of(Sibling.class).find(query, new AsyncCallback<List<Sibling>>() {
            @Override
            public void handleResponse(List<Sibling> response) {
                if (!response.isEmpty()) {
                    String siblingId = response.get(0).getObjectId();
                    Log.d("Sibling Fragment", "Object ID: " + siblingId);
                    mSibling.setObjectId(siblingId);
                    Backendless.Data.of(Sibling.class).save(mSibling, new AsyncCallback<Sibling>() {
                        @Override
                        public void handleResponse(Sibling response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
                else{
                    Backendless.Data.of(Sibling.class).save(mSibling, new AsyncCallback<Sibling>() {
                        @Override
                        public void handleResponse(Sibling response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                            mSibling.mObjectId = response.mObjectId;
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
                Log.e("Sibling Fragment", "Failed to find sibling: " + fault.getMessage());
            }
        });
    }
}
