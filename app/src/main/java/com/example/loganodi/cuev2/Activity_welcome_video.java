package com.example.loganodi.cuev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_welcome_video extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome_video);
        button = findViewById(R.id.button_continue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextProcess();
            }
        });

    }
    private void nextProcess() {
        finish();
        Intent intent = new Intent(this, Activity_setup_add_habit.class);
        startActivity(intent);
    }
}
