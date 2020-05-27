package com.example.codeeasy.courses.html;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeeasy.R;

import java.util.ArrayList;
import java.util.List;


public class HTMLFundLists extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private List<String> HTMLLesson;
    private List<String> HTMLTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_fund_list);
        HTMLLesson = new ArrayList<>();
        HTMLTitle = new ArrayList<>();

        HTMLLesson.add("Lesson 1");
        HTMLLesson.add("Lesson 2");
        HTMLTitle.add("What is HTML");
        HTMLTitle.add("HTML Elements");

        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        recyclerView = findViewById(R.id.list_html_fund);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final FundRecycleviewHoder fundRecycleviewHoder = new FundRecycleviewHoder(this, HTMLLesson, HTMLTitle);
        recyclerView.setAdapter(fundRecycleviewHoder);

    }
}
