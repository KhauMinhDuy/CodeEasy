package com.example.codeeasy.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.codeeasy.courses.html.HTMLFundLists;

public class DataManager {

    public static void loadFromDatabase(DatabaseOpenHelper dbOpenHelper) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        final String[] htmlLessonColumn = {DatabaseContract.HTMLLesson.COLUMN_LESSON_ID,
                DatabaseContract.HTMLLesson.COLUMN_LESSON_TITLE};
        final Cursor htmlLesson = db.query(DatabaseContract.HTMLLesson.TABLE_NAME, htmlLessonColumn, null, null, null, null, null);
        loadHtmlLesson(htmlLesson);
    }

    private static void loadHtmlLesson(Cursor cursor) {
        int lessonIdPos = cursor.getColumnIndex(DatabaseContract.HTMLLesson.COLUMN_LESSON_ID);
        int lessonTitlePos = cursor.getColumnIndex(DatabaseContract.HTMLLesson.COLUMN_LESSON_TITLE);
        while (cursor.moveToNext()) {
            String lesson = cursor.getString(lessonIdPos);
            String title = cursor.getString(lessonTitlePos);
            HTMLFundLists.HTMLLesson.add(lesson);
            HTMLFundLists.HTMLTitle.add(title);
        }
    }
}
