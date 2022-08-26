package com.example.easicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.easicom.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.easicom.Models.fragment_adapter;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding ;
FirebaseAuth auth;
FirebaseUser firebaseUser;
GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new fragment_adapter(getSupportFragmentManager()));
        binding.tabView.setupWithViewPager(binding.viewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.settings:
                Intent intente=new Intent(this,ActivitySetting.class);
                startActivity(intente);
                break;

            case R.id.logout:
                auth.signOut();

                Intent intent=new Intent(this,SignIn_Activity.class);
                startActivity(intent);
                finish();


                break;


        }


        return true;

    }

}