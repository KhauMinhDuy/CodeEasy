package com.example.codeeasy.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.codeeasy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ResetPassword extends DialogFragment {

    private EditText mEmail;
    private TextView mCancel, mConfirm;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_forgot_password, container, false);
        mEmail = view.findViewById(R.id.editText_forgot_password);
        mCancel = view.findViewById(R.id.textView_cancel_forgot_pw);
        mConfirm = view.findViewById(R.id.textView_confirm_forgot_pw);
        mCancel.setOnClickListener(listener);
        mConfirm.setOnClickListener(listener);
        context = getActivity();
        return view;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView_cancel_forgot_pw:
                    Objects.requireNonNull(getDialog()).dismiss();
                    break;
                case R.id.textView_confirm_forgot_pw:
                    if (!isEmpty(mEmail.getText().toString())) {
                        resetPassword(mEmail.getText().toString());
                        Objects.requireNonNull(getDialog()).dismiss();
                    }
                    break;
            }
        }
    };

    private void resetPassword(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Password reset link sent to Email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "No user is Associated with that Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean isEmpty(String email) {
        return email.equals("");
    }
}
