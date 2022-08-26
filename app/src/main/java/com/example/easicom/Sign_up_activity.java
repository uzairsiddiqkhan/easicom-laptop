package com.example.easicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.easicom.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import com.example.easicom.Models.user;

public class
Sign_up_activity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're Creating Your Account");
        
        //to create the instance of firebase Authentication
        mAuth=FirebaseAuth.getInstance();
        //to create the instance of firebase realtime data base
        database=FirebaseDatabase.getInstance();

        // to set on click listner through binding on btnSignup
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            user  u =new user(binding.etUserName.getText().toString(),
                                    binding.etEmail.getText().toString(),
                                    binding.etPassword.getText().toString());




                            // to get user id
                            String id = task.getResult().getUser().getUid();
                            // after getting troubles from firebase while cahtting first time
                            u.setUserId(id);

                            database.getReference().child("Users")
//                                    .child("Users From Email And Password")// due to whats app chat fragment
                                    .child(id).setValue(u);

                            Toast.makeText(Sign_up_activity.this, "user has been registered ",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Sign_up_activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


// Both Methods Work but the user name is not showing in firebse by the below process
//                            user nu=new user();
//                            //just for learning and it actually works:
//                            FirebaseUser ui=mAuth.getCurrentUser();
//                            nu.setUserName(ui.getDisplayName());
//                            nu.setEmail(ui.getEmail());
//                            // unique for password:
//                            nu.setPassword(binding.etPassword.getText().toString());
//                            database.getReference().child("Users").child("Email and password Authentication by new Method").child(ui.getUid()).setValue(nu);

                        }
                        else Toast.makeText(Sign_up_activity.this, task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });





    }
    // moving to sign in activity to login
    public void tvLogin(View view){
        Intent intent = new Intent(this, SignIn_Activity.class);
        startActivity(intent);
        finish();
    }
}