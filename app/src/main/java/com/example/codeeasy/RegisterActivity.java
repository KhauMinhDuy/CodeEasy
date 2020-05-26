package com.example.codeeasy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 1;
    private static String TAG = "Register";
    private static String DOMAIN_NAME = "gmail.com";
    private FirebaseAuth mAuth;
    private EditText _mEmail, _mPassword, _mConfigPassword;
    private Button _mRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        setControl();
        setEvent();
    }

    private void setControl() {
        _mEmail = findViewById(R.id.editText_register_email);
        _mPassword = findViewById(R.id.editText_register_password);
        _mConfigPassword = findViewById(R.id.editText_register_cfpassword);
        _mRegister = findViewById(R.id.button_register_real);
    }

    private void setEvent() {
        _mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check null edittext fields
                if (!isEmpty(_mEmail.getText().toString()) && !isEmpty(_mPassword.getText().toString()) && !isEmpty(_mConfigPassword.getText().toString())) {
                    if (isEmail(_mEmail.getText().toString())) {
                        if (isMatchPassword(_mPassword.getText().toString(), _mConfigPassword.getText().toString())) {
                            Log.d("Register", "Click");
                            createNewEmail(_mEmail.getText().toString().trim(), _mPassword.getText().toString().trim());
                        } else {
                            Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please Register with Gmail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "You nust fill out all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean isEmpty(String string) {
        return string.equals("");
    }

    private boolean isEmail(String email) {
        String domain = email.substring(email.indexOf("@") + 1).toLowerCase();
        return DOMAIN_NAME.equals(domain);
    }

    private boolean isMatchPassword(String pw1, String pw2) {
        return pw1.equals(pw2);
    }

    private void createNewEmail(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendEmailVerification();
                            mAuth.signOut();
                            redirectLogin();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Register Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }

    private void redirectLogin() {
        String email = _mEmail.getText().toString();
        String password = _mPassword.getText().toString();
        Intent i = new Intent();
        i.putExtra("email", email);
        i.putExtra("password", password);
        setResult(REQUEST_CODE, i);
        finish();
    }

    /*
       ------------------------Send Email------------------------
        */
    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Send Email success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Send Email Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

}
