package com.first.myapplication.mht;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailPasswordSignUpActivity extends AppCompatActivity {

    private EditText mFullName, mEmail, mPassword;
    private Button mSignUp, mSignIn;
    private FirebaseAuth mAuth;
    private static final int PASSWORD_LEN = 8;
    private ProgressDialog mProgDiag;
    private ImageView ivSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password_signup);

        mAuth = FirebaseAuth.getInstance();
        initUI();

        ivSignUp = findViewById(R.id.ivBackSignUp);

        ivSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailPasswordSignUpActivity.this, EmailPasswordLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerNewUser() {
        final String fullName = mFullName.getText().toString();
        final String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (isDataValid(fullName, email, password)) {
            Utils.showProgressDialogWithTitle("Creating account", mProgDiag);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Utils.hideProgressDialogWithTitle(mProgDiag);
                                showToast("Account created successfully.");
                                Intent intent = new Intent(EmailPasswordSignUpActivity.this, themeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                showToast("Sign-up failed");
                            }
                        }
                    });
        }
    }

    private boolean isDataValid(String fullName, String email, String password) {
        if (fullName.isEmpty()) {
            showToast("Name is empty");
            return false;
        }

        if (email.isEmpty()) {
            showToast("Email is empty");
            return false;
        }

        if (password.isEmpty()) {
            showToast("Password is empty");
            return false;
        }

        if (password.length() < PASSWORD_LEN) {
            showToast("Password length should be atleast 8");
            return false;
        }

        return true;
    }

    private void initUI() {
        mEmail = findViewById(R.id.tv_email_id);
        mFullName = findViewById(R.id.tv_full_name);
        mPassword = findViewById(R.id.tv_password);
        mSignUp = findViewById(R.id.btn_register);
        mSignIn = findViewById(R.id.btn_sign_in_email);
        mProgDiag = new ProgressDialog(this);
    }

    private void showToast(String msg) {
        Toast.makeText(EmailPasswordSignUpActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
