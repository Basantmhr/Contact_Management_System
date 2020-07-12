package com.example.ContactManagementSystem.Model;

public class ContactModel {
    private int Id;
    private String Name;
    private String phoneNumber;

    public ContactModel() {
    }
    public ContactModel(String name, String phoneNumber)
    {
        Name = name;
        this.phoneNumber=phoneNumber;
    }
    public ContactModel(int id, String name, String phoneNumber) {
        Id = id;
        Name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return phoneNumber;
    }

    public void setNumber(String number) {
        phoneNumber = number;
    }
}
