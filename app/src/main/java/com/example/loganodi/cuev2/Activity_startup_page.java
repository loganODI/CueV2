package com.example.loganodi.cuev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_startup_page extends AppCompatActivity {

    private static final String TAG = "debugmsg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startup_page);
        checkCurrentUser();

    }

    public void checkCurrentUser() {
        // [START check_current_user]
        SharedPreferences prefs = getSharedPreferences(getString(R.string.user_preference_file_key), MODE_PRIVATE);
        String userName = prefs.getString(getString(R.string.user_name_key), null);

        if(userName != null){
            //Go To Profile
            Log.d(TAG, "checkCurrentUser: Sent To Profile!");
            checkWhereToSendUser();


        }
        else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                //go to home page
                checkWhereToSendUser();
                Log.d(TAG, "checkCurrentUser: user is signed in!");
            } else {
                Intent intent = new Intent(this, Activity_sign_in.class);
                startActivity(intent);
            }
        }

        // [END check_current_user]
    }

    public void checkWhereToSendUser(){
        Integer userCompleted = 1;
        if(userCompleted != null)
        {
            //Go to profile
        }

        startSetupProscess();
    }

    private void startSetupProscess() {
        finish();
        Intent intent = new Intent(this, Activity_welcome_video.class);
        startActivity(intent);
    }
}
