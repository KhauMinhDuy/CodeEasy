package com.example.codeeasy.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract() {
    }

    public static final class HTMLCourse implements BaseColumns {
        public static final String TABLE_NAME = "HTML_Course";
        public static final String COLUMN_COURSE_ID = "Course_ID";
        public static final String COLUMN_COURSE_TITLE = "Title_Course";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_COURSE_ID + " TEXT NOT NULL, " +
                        COLUMN_COURSE_TITLE + " TEXT NOT NULL)";
    }

    public static final class HTMLLesson implements BaseColumns {
        public static final String TABLE_NAME = "Fundamentals";
        public static final String COLUMN_LESSON_ID = "Lesson_ID";
        public static final String COLUMN_LESSON_TITLE = "Lesson_Title";
        public static final String COLUMN_COURSE_ID = "Course_ID";
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_LESSON_ID + " TEXT NOT NULL, " +
                        COLUMN_LESSON_TITLE + " TEXT NOT NULL, " +
                        COLUMN_COURSE_ID + " TEXT NOT NULL)";
    }
}
