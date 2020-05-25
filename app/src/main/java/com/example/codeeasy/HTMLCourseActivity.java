package com.example.codeeasy;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HTMLCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_html);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void clickFundamentals(View view) {
        Toast.makeText(this, "Fundamentals", Toast.LENGTH_SHORT).show();
    }

    public void clickAdvanced(View view) {
        Toast.makeText(this, "Advanced", Toast.LENGTH_SHORT).show();
    }


    public void clickExercise(View view) {
        Toast.makeText(this, "Exercise", Toast.LENGTH_SHORT).show();
    }

    public void clickInterview(View view) {
        Toast.makeText(this, "Interview", Toast.LENGTH_SHORT).show();
    }
}
