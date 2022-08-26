package com.example.easicom.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easicom.Models.messagesModel;
import com.example.easicom.Models.user;
import com.example.easicom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter {

    public chatAdapter(ArrayList<messagesModel> list, Context context, String recId) {
        this.list = list;
        this.context = context;
        this.recId = recId;
    }

    ArrayList<messagesModel> list;
    Context context;
    String recId;

    public chatAdapter(ArrayList<messagesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    int INCOMING_VIEW_TYPE = 1;
    int OUTGOING_VIEW_TYPE = 2;

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())) {
            return OUTGOING_VIEW_TYPE;
        } else {
            return INCOMING_VIEW_TYPE;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == INCOMING_VIEW_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.incoming_rv, parent, false);
            return new incomingViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.outgoing_rv, parent, false);
            return new outgoingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        messagesModel messagesModel = list.get(position);
        if (holder.getClass() == outgoingViewHolder.class) {
            ((outgoingViewHolder) holder).messageOut.setText(messagesModel.getMessage());
//    ((outgoingViewHolder)holder).dateOut.setText(messagesModel.getTimestamp());
        } else {
            ((incomingViewHolder) holder).messageIn.setText(messagesModel.getMessage());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Do You want to delete this message")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                String sender=FirebaseAuth.getInstance().getUid()+ " & " + recId;

                                database.getReference().child("Chats").child(sender).child(messagesModel.
                                        getMessageId()).setValue(null);

                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    // view holder for incoming chats
    public class incomingViewHolder extends RecyclerView.ViewHolder {
        TextView messageIn, dateIn;

        public incomingViewHolder(@NonNull View itemView) {
            super(itemView);
            messageIn = itemView.findViewById(R.id.messageIn);
            dateIn = itemView.findViewById(R.id.dateIn);
        }
    }

    // view holder for outgoing chats:
    public class outgoingViewHolder extends RecyclerView.ViewHolder {
        TextView messageOut, dateOut;

        public outgoingViewHolder(@NonNull View itemView) {
            super(itemView);
            messageOut = itemView.findViewById(R.id.messageOut);
            dateOut = itemView.findViewById(R.id.dateOut);
        }
    }
}
