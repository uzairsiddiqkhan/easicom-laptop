package com.example.easicom.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easicom.R;
import com.example.easicom.chatDetailedActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.easicom.Models.user;

public class mainAdapter extends RecyclerView.Adapter<mainAdapter.viewHolder> {
    ArrayList<user> friends;

    public mainAdapter(ArrayList<user> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    Context context;


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_screen, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        user u = friends.get(position);
        Picasso.get().load(u.getProfilePic()).placeholder(R.drawable.useravatarsmall).into(holder.friendImage);
        holder.friendName.setText(u.getUserName());
        FirebaseDatabase.getInstance().getReference().child("Chats").
                child(FirebaseAuth.getInstance().getUid() + " & " + u.getUserId())
                .orderByChild("timestamp").limitToLast(1).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot dataSnapshotn : snapshot.getChildren()) {
                                holder.lastMsg.setText(dataSnapshotn.child("message").getValue().toString());
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, chatDetailedActivity.class );

                intent.putExtra("friendId", u.getUserId());
                intent.putExtra("friendName", u.getUserName());
                intent.putExtra("friendPic", u.getProfilePic());


                context.startActivity(intent);
                Intent intent1=new Intent(context,chatAdapter.class);
                intent.putExtra("friendId", u.getUserId());
                context.startActivity(intent1);
            }
        });



    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView friendImage;
        TextView friendName, lastMsg;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            friendImage = itemView.findViewById(R.id.friendImage);
            friendName = itemView.findViewById(R.id.friendName);
            lastMsg = itemView.findViewById(R.id.lastMsg);
        }
    }
}
