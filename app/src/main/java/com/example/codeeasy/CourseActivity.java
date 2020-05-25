package com.example.codeeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CourseActivity extends AppCompatActivity {

    private Button btn_Html, btn_Css, btn_Bootstrap, btn_Js, btn_React, btn_Note;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        setControl();
        setEvent();
    }

    private void setControl() {
        btn_Html = findViewById(R.id.button_html);
        btn_Css = findViewById(R.id.button_css);
        btn_Bootstrap = findViewById(R.id.button_bootstrap);
        btn_Js = findViewById(R.id.button_js);
        btn_React = findViewById(R.id.button_react);
        btn_Note = findViewById(R.id.button_note);
    }

    private void setEvent() {
        btn_Html.setOnClickListener(listener);
        btn_Css.setOnClickListener(listener);
        btn_Bootstrap.setOnClickListener(listener);
        btn_Js.setOnClickListener(listener);
        btn_React.setOnClickListener(listener);
        btn_Note.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_html:
                    Intent intentHtml = new Intent(CourseActivity.this, HTMLCourseActivity.class);
                    startActivity(intentHtml);
                    break;
                case R.id.button_css:
                    Toast.makeText(CourseActivity.this, "Css", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_bootstrap:
                    Toast.makeText(CourseActivity.this, "Bootstrap", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_js:
                    Toast.makeText(CourseActivity.this, "Js", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_react:
                    Toast.makeText(CourseActivity.this, "React", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_note:
                    Toast.makeText(CourseActivity.this, "Note", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
