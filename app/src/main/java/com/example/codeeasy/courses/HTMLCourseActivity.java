package com.example.codeeasy.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codeeasy.R;
import com.example.codeeasy.courses.html.HTMLFundLists;
import com.example.codeeasy.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

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
        Intent intent = new Intent(HTMLCourseActivity.this, HTMLFundLists.class);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_exit) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(HTMLCourseActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
