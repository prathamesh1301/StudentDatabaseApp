package com.example.studentmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> names,dob;
    ArrayList<Integer> roll;

    public RecyclerAdapter(Context context, ArrayList<String> names, ArrayList<String> dob, ArrayList<Integer> roll) {
        this.context = context;
        this.names = names;
        this.dob = dob;
        this.roll = roll;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameText.setText("Name: "+names.get(position));
        holder.rollText.setText("Roll No: "+roll.get(position));
        holder.dobText.setText("DOB: "+dob.get(position));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameText,rollText,dobText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText=itemView.findViewById(R.id.nameText);
            rollText=itemView.findViewById(R.id.rollText);
            dobText=itemView.findViewById(R.id.dobText);
        }
    }
}
