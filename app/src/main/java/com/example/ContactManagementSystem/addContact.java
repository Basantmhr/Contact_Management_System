package com.example.ContactManagementSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.view.View;
import android.widget.Toast;

import com.example.ContactManagementSystem.Data.ControllerDatabase;
import com.example.ContactManagementSystem.Model.ContactModel;

public class addContact extends AppCompatActivity {
    private AppCompatImageButton saveImageButton;
    private AppCompatImageButton notSaveImageButton;
    private AppCompatEditText name;
    private AppCompatEditText phone;
    private String nameString , phoneString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        saveImageButton = findViewById(R.id.imageViewAddContactSave);
        notSaveImageButton = findViewById(R.id.imageViewAddContactNotSave);
        saveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = findViewById(R.id.editTextAddContactName);
                phone = findViewById(R.id.editTextAddContactPhone);
                nameString = name.getText().toString();
                phoneString = phone.getText().toString();
                if ( !nameString.isEmpty() & !phoneString.isEmpty()) {
                    if(phoneString.matches("[0-9]+")) {
                        new ControllerDatabase(addContact.this).addContact(new ContactModel(nameString, phoneString));
                        Intent I = new Intent(addContact.this, Contact_Management_Screen.class);
                        startActivity(I);
                        finish();
                    }
                    else
                        Toast.makeText(addContact.this, "Number Should contain numerical value", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(addContact.this, "Both Name and Phone Number Field Cannot be Empty.", Toast.LENGTH_SHORT).show();

            }

        });
        notSaveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}