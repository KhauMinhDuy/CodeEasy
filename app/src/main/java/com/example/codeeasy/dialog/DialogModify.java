package com.example.codeeasy.dialog;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.codeeasy.R;
import com.example.codeeasy.database.DatabaseContract;
import com.example.codeeasy.database.DatabaseOpenHelper;

public class DialogModify extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_modify, container, false);
        final EditText _id = view.findViewById(R.id.modify_id);
        final EditText lesson = view.findViewById(R.id.modify_lesson);
        final EditText title = view.findViewById(R.id.modify_title);
        TextView cancel = view.findViewById(R.id.modify_cancel);
        TextView confirm = view.findViewById(R.id.modify_confirm);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = DatabaseContract.HTMLLesson._ID + " = ?";
                String[] selectionArgs = {_id.getText().toString()};

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_ID, lesson.getText().toString());
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_TITLE, title.getText().toString());
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_COURSE_ID, "HTML_Fund");

                DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getActivity());
                SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
                db.update(DatabaseContract.HTMLLesson.TABLE_NAME, contentValues, selection, selectionArgs);
                getDialog().dismiss();
            }
        });
        return view;
    }
}
