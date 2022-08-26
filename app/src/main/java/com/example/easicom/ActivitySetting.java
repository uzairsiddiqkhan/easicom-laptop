package com.example.easicom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.easicom.Models.user;
import com.example.easicom.databinding.ActivitySettingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.HashMap;

public class ActivitySetting extends AppCompatActivity {
    ActivitySettingBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
// back Button Intent:
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySetting.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
// plus Button Intent:
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
                Intent intent = new Intent();
                intent.setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }

        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName=binding.UserName.getText().toString();
                String about= binding.Aboutuser.getText().toString();

                HashMap<String,Object> obj=new HashMap<>();
                obj.put("userName",userName);
                obj.put("status",about);


                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).updateChildren(obj);
                Toast.makeText(ActivitySetting.this, "profile updated Successfully", Toast.LENGTH_SHORT).show();



            }


        });
        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user i= snapshot.getValue(user.class);
                Picasso.get().load(i.getProfilePic().toString()).
                        placeholder(R.drawable.useravatarsmall).into(binding.profile);

                binding.Aboutuser.setText(i.getStatus());
                binding.UserName.setText(i.getUserName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public static final int PICK_IMAGE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( requestCode==PICK_IMAGE){

            Uri sFile = data.getData();
            binding.profile.setImageURI(sFile);

            final StorageReference reference=
            storage.getReference().child("profile_pic").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(ActivitySetting.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                }

            });

        }
        else {
            Toast.makeText(this, "exeception occur ", Toast.LENGTH_SHORT).show();
        }


    }

    }
