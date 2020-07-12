package com.example.ContactManagementSystem.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ContactManagementSystem.Model.ContactModel;
import com.example.ContactManagementSystem.R;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    private Context context;
    private List<ContactModel> allContact;

    public AdapterRecyclerView(Context context, List<ContactModel> allContact) {
        this.context = context;
        this.allContact = allContact;
        for (int i =0;i<allContact.size();i++)
            Log.d("Const", "AdapterRecyclerView: "+allContact.get(i).getNumber() + allContact.get(i).getNumber());
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);
        return new ViewHolder(View);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contactModel = allContact.get(position);
        holder.textView.setText(contactModel.getName());
    }

    @Override
    public int getItemCount() {
        return allContact.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contactRowTextName);
        }
    }
}
