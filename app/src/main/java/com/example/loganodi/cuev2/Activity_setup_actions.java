package com.example.loganodi.cuev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_setup_actions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setup_actions);

        Button button;
        button = findViewById(R.id.button_pick_actions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });
    }

    public void nextActivity(){
        finish();
        Intent intent = new Intent(this, Activity_edit_actions.class);
        startActivity(intent);
    }
}
