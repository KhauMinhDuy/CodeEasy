package com.example.codeeasy.courses.html;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codeeasy.R;

import java.util.ArrayList;
import java.util.List;

public class LessonActivity extends AppCompatActivity {

    private List<Integer> Lists;
    private List<Integer> Activitys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display();
    }


    private void Display() {
        Lists = new ArrayList<>();
        Activitys = new ArrayList<Integer>();
        Activitys.add(R.layout.activity_lesson1_html);
        Activitys.add(R.layout.activity_lesson2_html);
        Activitys.add(R.layout.activity_lesson3_html);
        Activitys.add(R.layout.activity_lesson4_html);
        Activitys.add(R.layout.activity_lesson5_html);
        Activitys.add(R.layout.activity_lesson6_html);
        Activitys.add(R.layout.activity_lesson7_html);
        Activitys.add(R.layout.activity_lesson8_html);
        for (int i = 0; i < Activitys.size(); i++) {
            Lists.add(i);
        }
        Intent intent = getIntent();
        int mPosition = intent.getIntExtra("position", -1);
        for (int i = 0; i < Lists.size(); i++) {
            if (i == mPosition) {
                setContentView(Activitys.get(i));
            }
        }
    }
}
