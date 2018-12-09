package com.example.loganodi.cuev2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Activity_setup_add_habit extends AppCompatActivity {
    Button button;
    TextView habitText;
    String habit;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setup_add_habit);
       button = findViewById(R.id.button_continue);
       habitText = findViewById(R.id.habit_text);
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
       habitText.setHint(prefs.getString(getString(R.string.user_habit_key), null));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habit = habitText.getText().toString();
                saveHabitToLocalStorage();
                nextProcess();
            }
        });

    }
    private void nextProcess() {
        finish();
        Intent intent = new Intent(this, Activity_setup_actions.class);
        startActivity(intent);
    }

    public void saveHabitToLocalStorage(){
            SharedPreferences prefs = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(getString(R.string.user_habit_key), habit.toString());
            editor.commit();
            String userHabit = prefs.getString(getString(R.string.user_habit_key), "No value");
        Toast toast = Toast.makeText(this, "You saved this habit: " + userHabit, Toast.LENGTH_SHORT);
        toast.show();
    }
}
