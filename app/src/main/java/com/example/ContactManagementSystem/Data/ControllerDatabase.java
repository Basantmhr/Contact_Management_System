package com.example.ContactManagementSystem.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;



import com.example.ContactManagementSystem.Model.ContactModel;
import com.example.ContactManagementSystem.R;
import com.example.ContactManagementSystem.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ControllerDatabase extends SQLiteOpenHelper {

    public ControllerDatabase(Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String CREATE_TABLE = "CREATE TABLE "+Utils.DATABASE_TABLE+"("+Utils.KEY_ID+" INT PRIMARY KEY,"
//                +Utils.KEY_NAME+" TEXT , "+Utils.KEY_PHONE_NUMBER+" TEXT)";
        String CREATE_TABLE = "CREATE TABLE " + Utils.DATABASE_TABLE + "(" + Utils.KEY_NAME + " TEXT PRIMARY KEY , " + Utils.KEY_PHONE_NUMBER + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf(R.string.stringDropTable);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Utils.DATABASE_NAME});
        onCreate(sqLiteDatabase);

    }

    public void addContact(ContactModel Contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_NAME, Contact.getName());
        contentValues.put(Utils.KEY_PHONE_NUMBER, Contact.getNumber());
        db.insert(Utils.DATABASE_TABLE, null, contentValues);
        db.close();
        Log.d("Created", "addContact: " + Contact.getName() + " : " + Contact.getNumber());
    }

    public ContactModel getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("data", "addContact: " + getDatabaseName());
        Cursor cursor = db.query(Utils.DATABASE_TABLE, new String[]{Utils.KEY_NAME, Utils.KEY_PHONE_NUMBER}, Utils.KEY_NAME + "=?"
                , new String[]{name}, null, null, null);
//        Cursor cursor = db.query(Utils.DATABASE_TABLE, null, null, null,  null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ContactModel contact = new ContactModel();
//        ContactModel contact = new ContactModel("James", "213986");
//        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(0));
        contact.setNumber(cursor.getString(1));
//        for (int i=0;i <= cursor.getCount()-1;i++) {
//
//            Log.d("Column name", "getContact: " + i + cursor.getString(0) + cursor.getString(1));
//            cursor.moveToNext();
//        }

//        Log.d(TAG, "getContact: "+cursor);
        return contact;
    }

    public List<ContactModel> getAllContacts() {
        List<ContactModel> contactmodels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.DATABASE_TABLE, null, null, null, null, null, null);
        if (cursor != null) {

            cursor.moveToFirst();
            Log.d("check", "getAllContacts:check ");
            for (int i = 0; i < cursor.getCount() ; i++) {
                ContactModel contactModel = new ContactModel();
                contactModel.setName(cursor.getString(0));
                contactModel.setNumber(cursor.getString(1));
                contactmodels.add(contactModel);
                Log.d("Allcontact", "getAllContactsinside: "+contactModel.getName()+"  "+contactModel.getNumber() + "at" + i + "cursor"+(cursor.getCount() - 1));
                cursor.moveToNext();

            }
            cursor.close();
        }

        for (int i =0;i<contactmodels.size();i++)
            Log.d("Contactmodels", "AdapterRecyclerView: "+contactmodels.get(i).getName() + contactmodels.get(i).getNumber());
        return contactmodels;
    }

    public void deleteContact(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = Utils.KEY_NAME + "=?";
        String[] whereArgs = {name};
        db.delete(Utils.DATABASE_TABLE, whereClause, whereArgs);

    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.DATABASE_TABLE, null, null);
    }

    public void updateContact(ContactModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_NAME, contact.getName());
        contentValues.put(Utils.KEY_PHONE_NUMBER, contact.getNumber());
        String whereClause = Utils.KEY_NAME + "=?";
        String[] whereArgs = {contact.getName()};
        db.update(Utils.DATABASE_TABLE, contentValues, whereClause, whereArgs);
    }
}
