package com.example.codeeasy.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.codeeasy.CourseActivity;
import com.example.codeeasy.R;
import com.example.codeeasy.admin.AdminActivity;
import com.example.codeeasy.database.DatabaseOpenHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 1;
    private static final String EMAIL_ADMIN = "admin@codeeasy.com";
    private static final String PASSWORD_ADMIN = "Aa123456";

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private DatabaseOpenHelper db;
    private EditText _edEmail, _edPassword;
    private Button _btnLogin, _btnRegister, _btnLoginGoogle;
    private TextView tvResendEmail, tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        db = new DatabaseOpenHelper(this);
        SQLiteDatabase sql = db.getReadableDatabase();
        setControl();
        setupFirebaseAuth();
        setEvent();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    /*
    -----------------Control--------------------------
     */

    private void setControl() {
        _edEmail = findViewById(R.id.editText_Email);
        _edPassword = findViewById(R.id.editText_Password);
        _btnLogin = findViewById(R.id.button_Login);
        _btnRegister = findViewById(R.id.button_Register);
        _btnLoginGoogle = findViewById(R.id.button_SignIn_gg);
        tvResendEmail = findViewById(R.id.textView_resend_email);
        tvForgotPassword = findViewById(R.id.textView_forgot_pw);
    }

    /*
         ---------------------EVENT-----------------------
      */
    private void setEvent() {
        _btnLogin.setOnClickListener(listener);
        _btnRegister.setOnClickListener(listener);
        _btnLoginGoogle.setOnClickListener(listener);
        tvResendEmail.setOnClickListener(listener);
        tvForgotPassword.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_Login:
                    signIn(_edEmail.getText().toString(), _edPassword.getText().toString());
                    break;
                case R.id.button_Register:
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                case R.id.button_SignIn_gg:
                    Toast.makeText(LoginActivity.this, "Sign In Google", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.textView_resend_email:
                    ResendEmail resendEmail = new ResendEmail();
                    resendEmail.show(getSupportFragmentManager(), "dialog_resend_email_verification");
                    break;
                case R.id.textView_forgot_pw:
                    ResetPassword resetPassword = new ResetPassword();
                    resetPassword.show(getSupportFragmentManager(), "reset_password");
                    break;
            }
        }
    };

    /*
    -------------------METHOD-------------------------
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            _edEmail.setText(email);
            _edPassword.setText(password);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
    }

    /*
    -------------------FireBaseAuth----------------------
    */
    private void setupFirebaseAuth() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if (user.isEmailVerified()) {
                        Intent intentCourse = new Intent(LoginActivity.this, CourseActivity.class);
                        intentCourse.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentCourse);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Check email", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                } else {
                    Log.d("Login", "Signin_out");
                }
            }
        };
    }

    private void signIn(final String email, String password) {
        if (email.equals(EMAIL_ADMIN) && password.equals(PASSWORD_ADMIN)) {
            Intent intentAdmin = new Intent(LoginActivity.this, AdminActivity.class);
            startActivity(intentAdmin);
//            finish();
        } else if (!isEmpty(email) && !isEmpty(password)) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "FIll email and password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmpty(String email) {
        return email.equals("");
    }


}
