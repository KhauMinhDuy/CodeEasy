package com.example.codeeasy.dialog;

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

public class DialogDelete extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogdelete, container, false);
        final EditText id = view.findViewById(R.id.edittext_delete);
        TextView cancel = view.findViewById(R.id.cancel_delete);
        TextView confirm = view.findViewById(R.id.confirm_delete);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper(getActivity());
                String selection = DatabaseContract.HTMLLesson._ID + " = ?";
                String[] selectionArgs = {id.getText().toString()};
                SQLiteDatabase db = databaseOpenHelper.getWritableDatabase();
                db.delete(DatabaseContract.HTMLLesson.TABLE_NAME, selection, selectionArgs);
                getDialog().dismiss();
            }
        });

        return view;
    }
}
