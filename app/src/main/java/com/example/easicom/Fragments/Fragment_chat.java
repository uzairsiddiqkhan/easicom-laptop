package com.example.easicom.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easicom.Adapter.mainAdapter;
import com.example.easicom.databinding.FragmentChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.example.easicom.Models.user;


public class Fragment_chat extends Fragment {
FragmentChatBinding binding;
ArrayList<user> friends=new ArrayList<>();
FirebaseDatabase firebaseDatabase;

    public Fragment_chat() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentChatBinding.inflate(inflater, container, false);
        firebaseDatabase=FirebaseDatabase.getInstance();


        mainAdapter adapter=new mainAdapter(friends,getContext());
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(linearLayoutManager);

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                friends.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    user users=dataSnapshot.getValue(user.class);
//                    !users.getUserId().equals(FirebaseAuth.getInstance().getUid());

                        users.setUserId(dataSnapshot.getKey());
                        if(!users.getUserId().equals(FirebaseAuth.getInstance().getUid())) {
                            friends.add(users);
                        }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });



        return binding.getRoot();


    }

}