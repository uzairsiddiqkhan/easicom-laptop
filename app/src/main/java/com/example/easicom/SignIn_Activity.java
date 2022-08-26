package com.example.easicom;

//import com.example.easicom.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
        import android.util.Log;
import android.view.View;
import android.widget.Toast;

        import com.example.easicom.databinding.ActivitySignInBinding;
        import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

        import com.example.easicom.Models.user;

public class SignIn_Activity extends AppCompatActivity {
    ActivitySignInBinding binding;

    ProgressDialog dialog;
    FirebaseAuth auth;
    FirebaseDatabase database;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


// for hiding the action bar
        getSupportActionBar().hide();

// for dialog bar
        dialog = new ProgressDialog(this);
        dialog.setTitle("Account Signing");
        dialog.setMessage("We're Signing to your Account");

// for the instance of firebase and database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestIdToken(getString(R.string.default_web_client_id1)).
                requestEmail().build();

        gsc = GoogleSignIn.getClient(this, gso);


        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // for the appearance of dialog box
                dialog.show();
                auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).addOnCompleteListener
                        (new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // for the dismissing of dialog box
                                dialog.dismiss();
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(SignIn_Activity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else
                                    Toast.makeText(SignIn_Activity.this, task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

// for checking the old credentials

        if (auth.getCurrentUser() != null) {
            onStart();
            Intent intent = new Intent(SignIn_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        binding.btnGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                signIn();
            }
        });
    }

    public void signIn() {

        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 100);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "onActivityResult: firebaseAuthWithGoogle " + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                e.printStackTrace();
                Log.w("TAG", "onActivityResult: GoogleSignInFailed ", e);
            }
        }

    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();

//              in the SignIn Activity all the credentials will be given by Firebase. while
//              Sign UP Activity all the the credentials will be given by user to the Firebase

                            com.example.easicom.Models.user u = new user();
                            u.setUserId(user.getUid());
                            u.setUserName(user.getDisplayName());
                            u.setEmail(user.getEmail());
                            u.setProfilePic(user.getPhotoUrl().toString());
                            database.getReference().child("Users")
//                                    .child("Users From Google")
                                    .child(u.getUserId()).
                                    setValue(u);
//                            updateUI(user);
                            Intent intent = new Intent(SignIn_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                        } else {
                            dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
//                            updateUI(null);
                            finish();

                        }

                    }

                });

    }

//    private void updateUI(FirebaseUser user) {
//        Intent intent=new Intent(this,MainActivity.class);
////        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

    public void tvSignup(View view) {
        Intent intent = new Intent(this, Sign_up_activity.class);
        startActivity(intent);
        finish();
    }

}