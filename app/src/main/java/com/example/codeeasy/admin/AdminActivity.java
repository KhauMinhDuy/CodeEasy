package com.example.codeeasy.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codeeasy.R;

public class AdminActivity extends AppCompatActivity {

    private Button html;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_admin);
        setControl();
        setEvent();
    }

    private void setControl() {
        html = findViewById(R.id.button_html);
    }

    private void setEvent() {
        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
