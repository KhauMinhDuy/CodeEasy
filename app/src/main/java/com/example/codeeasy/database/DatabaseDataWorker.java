package com.example.codeeasy.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseDataWorker {

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseDataWorker(SQLiteDatabase db) {
        sqLiteDatabase = db;
    }

    public void insertCourses() {
        insertCourse("HTML_Fund", "HTML Fundamentals");
        insertCourse("HTML_Advan", "HTML Advanced");
        insertCourse("HTML_Exer", "HTML Exercise");
        insertCourse("HTML_Inter", "HTML Interview");
    }

    public void insertLessons() {
        insertLesson("Lesson 1", "What is HTML", "HTML_Fund");
        insertLesson("Lesson 2", "HTML Elements", "HTML_Fund");
        insertLesson("Lesson 3", "HTML Attributes", "HTML_Fund");
        insertLesson("Lesson 4", "HTML Headings", "HTML_Fund");
        insertLesson("Lesson 5", "HTML Paragraphs", "HTML_Fund");
        insertLesson("Lesson 6", "HTML Styles", "HTML_Fund");
        insertLesson("Lesson 7", "HTML Formatting", "HTML_Fund");
        insertLesson("Lesson 8", "HTML Comment", "HTML_Fund");


    }

    private void insertCourse(String course_id, String course_title) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.HTMLCourse.COLUMN_COURSE_ID, course_id);
        contentValues.put(DatabaseContract.HTMLCourse.COLUMN_COURSE_TITLE, course_title);
        long newRowId = sqLiteDatabase.insert(DatabaseContract.HTMLCourse.TABLE_NAME, null, contentValues);
    }

    private void insertLesson(String Lesson_id, String Lesson_Title, String Course_Id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_ID, Lesson_id);
        contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_TITLE, Lesson_Title);
        contentValues.put(DatabaseContract.HTMLLesson.COLUMN_COURSE_ID, Course_Id);
        long newRowId = sqLiteDatabase.insert(DatabaseContract.HTMLLesson.TABLE_NAME, null, contentValues);
    }

}
