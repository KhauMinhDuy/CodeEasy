package com.example.codeeasy.courses.html;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeeasy.R;
import com.example.codeeasy.database.DataManager;
import com.example.codeeasy.database.DatabaseOpenHelper;
import com.example.codeeasy.dialog.DialogAdd;
import com.example.codeeasy.dialog.DialogDelete;
import com.example.codeeasy.dialog.DialogModify;
import com.example.codeeasy.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;


public class HTMLFundLists extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public static List<String> HTMLLesson;
    public static List<String> HTMLTitle;
    private DatabaseOpenHelper databaseOpenHelper;

    private FundRecyclerviewAdapter fundRecyclerviewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.html_fund_list);
        HTMLLesson = new ArrayList<>();
        HTMLTitle = new ArrayList<>();

//        HTMLLesson.add("Lesson 1");
//        HTMLLesson.add("Lesson 2");
//        HTMLLesson.add("Lesson 3");
//        HTMLLesson.add("Lesson 4");
//        HTMLLesson.add("Lesson 5");
//        HTMLLesson.add("Lesson 6");
//        HTMLLesson.add("Lesson 7");
//        HTMLLesson.add("Lesson 8");

//        HTMLTitle.add("What is HTML");
//        HTMLTitle.add("HTML Elements");
//        HTMLTitle.add("HTML Attributes");
//        HTMLTitle.add("HTML Heading");
//        HTMLTitle.add("HTML Paragraphs");
//        HTMLTitle.add("HTML Styles");
//        HTMLTitle.add("HTML Formatting");
//        HTMLTitle.add("HTML Comment");

        initializeDisplayContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fundRecyclerviewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        databaseOpenHelper.close();
        super.onDestroy();
    }

    private void initializeDisplayContent() {
        databaseOpenHelper = new DatabaseOpenHelper(this);
        DataManager.loadFromDatabase(databaseOpenHelper);
        recyclerView = findViewById(R.id.list_html_fund);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fundRecyclerviewAdapter = new FundRecyclerviewAdapter(this, HTMLLesson, HTMLTitle);
        recyclerView.setAdapter(fundRecyclerviewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_exit:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(HTMLFundLists.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                DialogAdd dialogAdd = new DialogAdd();
                dialogAdd.show(getSupportFragmentManager(), "add");
                break;
            case R.id.modify:
                DialogModify dialogModify = new DialogModify();
                dialogModify.show(getSupportFragmentManager(), "modify");
                break;
            case R.id.delete:
                DialogDelete dialogDelete = new DialogDelete();
                dialogDelete.show(getSupportFragmentManager(), "delete");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
