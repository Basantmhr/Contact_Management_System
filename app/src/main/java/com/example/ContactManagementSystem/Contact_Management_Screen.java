package com.example.ContactManagementSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ContactManagementSystem.Adapter.AdapterRecyclerView;
import com.example.ContactManagementSystem.Data.ControllerDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ContactManagementSystem.Data.ControllerDatabase;
import com.example.ContactManagementSystem.Model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class Contact_Management_Screen extends AppCompatActivity {
    public static final String TAG = Contact_Management_Screen.class.getSimpleName();
    private RecyclerView recyclerView;
    private AdapterRecyclerView adapterRecyclerView;
    private AppCompatImageButton addbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__management__screen);
        ControllerDatabase db = new ControllerDatabase(Contact_Management_Screen.this);
        ContactModel contactModel1 = new ContactModel();

        recyclerView = findViewById(R.id.contactRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List <ContactModel> ContactAll = new ArrayList<>();
        ContactAll = db.getAllContacts();
        for( int i =0;i<ContactAll.size() ; i++)
        {
            Log.d(TAG, "onCreate: ContactAll "+ContactAll.get(i).getName() + "  and " +ContactAll.get(i).getNumber() + " at " + i);
        }
       adapterRecyclerView = new AdapterRecyclerView(Contact_Management_Screen.this, db.getAllContacts());
       recyclerView.setAdapter(adapterRecyclerView);
       addbutton =findViewById(R.id.addImageButton);
       addbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent I = new Intent(Contact_Management_Screen.this , addContact.class);
               startActivity(I);

           }
       });

//        contactModel1.setName("Basant");
//        contactModel1.setNumber("9862354719");
//        contactModel1.setName("Basant Singh");
//        contactModel1.setNumber("9862354719");
//        db.addContact(contactModel1);
//        contactModel1.setName("Basant mhr");
//        contactModel1.setNumber("9862350009");
//        db.addContact(contactModel1);

//        contactModel1 = db.getContact("Basant");
//        Log.d(TAG, "onCreate1: " + contactModel1.getName() + " " + contactModel1.getNumber());

//        db.getAllContacts();
//        contactModel1.setNumber("9862354718");
//        db.updateContact(contactModel1);
//        db.getAllContacts();


    }
}