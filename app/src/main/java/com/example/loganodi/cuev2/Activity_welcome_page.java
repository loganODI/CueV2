package com.example.loganodi.cuev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_welcome_page extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static final String TAG = "debugmsg" ;
    private static final String USER_DISPLAY_NAME = "UserName" ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome_page);
        createSignInIntent();

    }
    public void createSignInIntent(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Toast toast = Toast.makeText(this, "Sucsessfully signed in", Toast.LENGTH_SHORT);
                toast.show();
                 FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                checkIfUserExists();

                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Toast toast = Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }




    public void addUserToDb() {
//
//         [START add_document]
//         Add a new document with a generated id.
         FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
         String email = currentUser.getEmail();
        User user = new User(currentUser.getEmail(), currentUser.getDisplayName(), currentUser.getPhoneNumber(), false, false, "nope", null );


        db.collection("users").document(email)

                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: Added to db");
                handleLocalStorage();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
//         [END add_document]
    }


    public void checkIfUserExists(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       DocumentReference docRef = db.collection("users").document(user.getEmail());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               if (documentSnapshot.exists()){
                   //go to profile
                   Log.d(TAG, "onSuccess: user exists");
                   handleLocalStorage();
               }
               else{
                   addUserToDb();
               }
            }
        });
    }

    public void addUserToLocalStorage(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SharedPreferences prefs = getSharedPreferences(getString(R.string.user_preference_file_key), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.user_name_key), auth.getCurrentUser().getDisplayName());
        editor.commit();
    }

    public void handleLocalStorage(){
        SharedPreferences prefs = getSharedPreferences(getString(R.string.user_preference_file_key), MODE_PRIVATE);
        String userName = prefs.getString(getString(R.string.user_name_key), null);

        if(userName != null){
            //Go To Profile
            Log.d(TAG, "checkCurrentUser: Sent To Profile!");
        }
        else {
            addUserToLocalStorage();
        }
    }
}
