package com.gapestation.collegeapp_brian;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.io.File;
import java.util.Date;
import java.util.List;

import static com.backendless.media.SessionBuilder.TAG;

public class ProfileFragment extends Fragment {
    private TextView firstnametext;
    private TextView lastnametext;
    private EditText firstnameEdit;
    private EditText lastnameEdit;
    private Button submit;
    private ImageButton mSelfieButton;
    private ImageView mSelfieView;
    private File mSelfieFile;
    public Profile Profile1 = new Profile("Alan", "Turing");
    public Profile mProfile;
    public static final int REQUEST_DATE_OF_BIRTH = 0;
    public final int REQUEST_SELFIE = 1;
    Button DatePickerButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle bundle) {
        super.onCreateView(inflater, view, bundle);

        mProfile = new Profile("Brian", "Chamberlain");
        mSelfieFile = getPhotoFile();

        String whereClause = "email = 'brianusa2001@gmail.com'";
        //Retrieve from Backendless
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> response) {
                if (!response.isEmpty()) {
                    mProfile = response.get(0);
                }

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Profile Fragment", "Failed to find profile: " + fault.getMessage());
            }
        });

        View rootView = inflater.inflate(R.layout.fragment_profile, view, false);
        firstnametext = rootView.findViewById(R.id.profilefirstname);
        lastnametext = rootView.findViewById(R.id.profilelastname);
        lastnametext.setText(mProfile.getLastName());
        firstnametext.setText(mProfile.getFirstName());
        DatePickerButton = (Button) rootView.findViewById(R.id.DatePickerButton);
        firstnameEdit = rootView.findViewById(R.id.profilefirstnameEdit);
        lastnameEdit = rootView.findViewById(R.id.profilelastnameEdit);
        submit = (Button) rootView.findViewById(R.id.submitButton);
        mSelfieButton = (ImageButton)rootView.findViewById(R.id.profile_camera);
        mSelfieView = (ImageView)rootView.findViewById(R.id.profile_pic);

        DatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mProfile.dateOfBirth);
                dialog.setTargetFragment(ProfileFragment.this, REQUEST_DATE_OF_BIRTH);
                dialog.show(fm, "DialogDateOfBirth");

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstnametext.setText(firstnameEdit.getText());
                lastnametext.setText(lastnameEdit.getText());
                saveToBackendless();
            }
        });

        final Intent captureSelfie = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakeSelfie = mSelfieFile != null &&
                captureSelfie.resolveActivity(getActivity().getPackageManager()) != null;
        mSelfieButton.setEnabled(canTakeSelfie);
        if (canTakeSelfie) {
            Uri uri = Uri.fromFile(mSelfieFile);
            captureSelfie.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        mSelfieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureSelfie, REQUEST_SELFIE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.i("ProfileFragment", "" + requestCode + " " + resultCode);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_DATE_OF_BIRTH) {
                mProfile.dateOfBirth = (Date) intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE_OF_BIRTH);
                Log.i("ProfileFragment", mProfile.dateOfBirth.toString());
                DatePickerButton.setText(mProfile.dateOfBirth.toString());
                saveToBackendless();
            }
        }
    }


    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences =
                getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> profile) {
                if (!profile.isEmpty()) {
                    String profileId = profile.get(0).getObjectId();
                    Log.d(TAG, "Object ID: " + profileId);
                    mProfile = profile.get(0);
                    firstnametext.setText(mProfile.getFirstName());
                    lastnametext.setText(mProfile.getLastName());
                    if (mProfile.dateOfBirth != null) {
                        DatePickerButton.setText(mProfile.dateOfBirth.toString());
                    }
                }
            }


            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG, "Failed to find profile: " + fault.getMessage());
            }
        });
    }


    private void saveToBackendless() {
        String whereClause = "email = 'brianusa2001@gmail.com'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
            @Override
            public void handleResponse(List<Profile> response) {
                if (!response.isEmpty()) {
                    String profileId = response.get(0).getObjectId();
                    Log.d("Profile Fragment", "Object ID: " + profileId);
                    mProfile.setObjectId(profileId);
                    Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
                        @Override
                        public void handleResponse(Profile response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("Error", fault.getMessage());
                        }
                    });
                }
                else{
                    Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
                        @Override
                        public void handleResponse(Profile response) {
                            Log.i("success", response.getFirstName() + " has been saved");
                            mProfile.mObjectId = response.mObjectId;
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
                Log.e("Profile Fragment", "Failed to find profile: " + fault.getMessage());
            }
        });
    }

    public File getPhotoFile() {
        File externalFilesDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File (externalFilesDir, mProfile.getPhotoFilename());
    }
}

