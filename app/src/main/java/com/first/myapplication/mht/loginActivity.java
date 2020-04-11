package com.first.myapplication.mht;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class loginActivity extends AppCompatActivity {

    TextView textView, textViewEmail, textViewDidYouKnow;

    SignInButton btnGoogleSign;
    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    ConstraintLayout constraintLayout;
    ProgressBar pbSignIn;

    ImageView ivProblem;

    private final static int RC_SIGN_IN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewDidYouKnow = findViewById(R.id.tv_did_you_know);
        textView = findViewById(R.id.tv_fact);
        textViewEmail = findViewById(R.id.btn_sign_up);
        constraintLayout = findViewById(R.id.cl_login_screen);
        pbSignIn = findViewById(R.id.pbSignIn);
        btnGoogleSign = findViewById(R.id.btnGoogleSignIn);
        ivProblem = findViewById(R.id.ivFacingProblem);

        mAuth = FirebaseAuth.getInstance();
        Animation animation = AnimationUtils.loadAnimation(loginActivity.this, R.anim.fadein);
        textViewDidYouKnow.startAnimation(animation);
        textView.startAnimation(animation);

        Animation slideUpAnimation = AnimationUtils.loadAnimation(loginActivity.this, R.anim.slide_up);
        constraintLayout.startAnimation(slideUpAnimation);

        btnGoogleSign.setColorScheme(SignInButton.COLOR_DARK);
        btnGoogleSign.setSize(SignInButton.SIZE_WIDE);
        btnGoogleSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbSignIn.setVisibility(View.VISIBLE);
                btnGoogleSign.setVisibility(View.INVISIBLE);
                signIn();
            }
        });

        ivProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendProblem();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, EmailPasswordSignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(loginActivity.this, "Google Sign In Failed", Toast.LENGTH_SHORT).show();
                btnGoogleSign.setVisibility(View.VISIBLE);
                pbSignIn.setVisibility(View.GONE);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(loginActivity.this, "Couldn't Sign in",
                                    Toast.LENGTH_SHORT).show();
                            btnGoogleSign.setVisibility(View.VISIBLE);
                            pbSignIn.setVisibility(View.GONE);
                            updateUI(null);
                        }
                    }
                });
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void updateUI(FirebaseUser user){
        if(user != null){
            pbSignIn.setVisibility(View.GONE);
            Intent i = new Intent(loginActivity.this, themeActivity.class);
            startActivity(i);
        }
    }

    private void sendProblem(){
        Intent problemIntent = new Intent();
        problemIntent.setAction(Intent.ACTION_SEND).
                setDataAndType(Uri.parse("nikhil.pratap.singh.581@gmail.com"),"message/rfc822")
                .putExtra(Intent.EXTRA_SUBJECT, "Problem in PSYCHE");
        startActivity(Intent.createChooser(problemIntent, "Choose an Email client:"));
    }
}
