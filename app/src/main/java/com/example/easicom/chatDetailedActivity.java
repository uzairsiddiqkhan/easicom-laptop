package com.example.easicom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easicom.Adapter.chatAdapter;
import com.example.easicom.Models.messagesModel;
import com.example.easicom.databinding.ActivityChatDetailedBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatDetailedActivity extends AppCompatActivity {
    ActivityChatDetailedBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();


        final String userId = auth.getUid();

        String friendId = getIntent().getStringExtra("friendId");
        String friendName = getIntent().getStringExtra("friendName");
        String friendPic = getIntent().getStringExtra("friendPic");


        binding.freindNameChat.setText(friendName);
        Picasso.get().load(friendPic).placeholder(R.drawable.useravatarsmall).into(binding.friendImage);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            // it can also be done through parenting in manifest
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatDetailedActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        ArrayList<messagesModel> messagesModels = new ArrayList<>();
        chatAdapter adapter = new chatAdapter(messagesModels, this, friendId );
        binding.chatDetailedRV.setAdapter(adapter);


        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        binding.chatDetailedRV.setLayoutManager(linearLayoutManager);

        final String userRoom = userId + " & " + friendId;
        final String friendRoom = friendId + " & " + userId;


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.chatBox.getText().toString();
                final messagesModel model = new messagesModel(message, userId);

                model.setTimestamp(new Date().getTime());


                binding.chatBox.setText("");

                database.getReference().child("Chats").
                        child(userRoom).push().
                        setValue(model).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                database.getReference().child("Chats").child(friendRoom).push().
                                        setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }

                        });
            }
        });

        database.getReference().child("Chats").child(userRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModels.clear();
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                            messagesModel model = snapshot1.getValue(messagesModel.class);
                            // to set message id in message model

                            model.setMessageId(snapshot1.getKey());

                            messagesModels.add(model);
                        }
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


    }
}