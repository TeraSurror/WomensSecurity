package com.example.harsh.womenssecurity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.harsh.womenssecurity.data.ContactDbHelper;
import com.example.harsh.womenssecurity.data.Contract;

public class ViewContacts extends AppCompatActivity {

    private ContactDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);
        mDbHelper = new ContactDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayInfo();
    }

    private void displayInfo(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Contract.ContactEntry._ID,
                Contract.ContactEntry.COLUMN_CONTACT_NAME,
                Contract.ContactEntry.COLUMN_CONTACT_NUMBER};


        Cursor cursor = db.query(
                Contract.ContactEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        ListView petListView = (ListView) findViewById(R.id.list);


        ContactCursorAdapter adapter = new ContactCursorAdapter(this, cursor);


        petListView.setAdapter(adapter);

    }
}
