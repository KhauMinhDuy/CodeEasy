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

public class DialogAdd extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add, container, false);
        final EditText lesson = view.findViewById(R.id.add_lesson);
        final EditText title = view.findViewById(R.id.add_title);
        TextView cancel = view.findViewById(R.id.cancel_add);
        final TextView confirm = view.findViewById(R.id.confirm_add);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_ID, lesson.getText().toString());
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_LESSON_TITLE, title.getText().toString());
                contentValues.put(DatabaseContract.HTMLLesson.COLUMN_COURSE_ID, "HTML_Fund");
                DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getActivity());
                SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
                db.insert(DatabaseContract.HTMLLesson.TABLE_NAME, null, contentValues);
                getDialog().dismiss();
            }
        });
        return view;
    }
}
