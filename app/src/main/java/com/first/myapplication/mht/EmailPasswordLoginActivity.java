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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailPasswordLoginActivity extends AppCompatActivity {
    private EditText mEmail, mPassword;
    private Button mSignUp, mSignIn;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgDiag;
    private ImageView ivBackLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_password_login);

        ivBackLogin = findViewById(R.id.ivBackLogin);

        ivBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        
        mAuth = FirebaseAuth.getInstance();
        initUI();
        
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn();
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailPasswordLoginActivity.this, EmailPasswordSignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userSignIn() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (isDataValid(email, password)) {
            Utils.showProgressDialogWithTitle("Signing in", mProgDiag);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Utils.hideProgressDialogWithTitle(mProgDiag);
                                showToast("Successfully signed in");
                                Intent intent = new Intent(EmailPasswordLoginActivity.this, themeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                showToast("Wrong email or password");
                            }
                        }
                    });
        }
    }

    private boolean isDataValid(String email, String password) {
        if (email.isEmpty()) {
            showToast("Email is empty");
            return false;
        }

        if (password.isEmpty()) {
            showToast("Password is empty");
            return false;
        }

        return true;
    }

    private void showToast(String msg) {
        Toast.makeText(EmailPasswordLoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        mEmail = findViewById(R.id.tv_email_id);
        mPassword = findViewById(R.id.tv_password);
        mSignUp = findViewById(R.id.btnSignUp);
        mSignIn = findViewById(R.id.btnSignIn);
        mProgDiag = new ProgressDialog(this);
    }
}
