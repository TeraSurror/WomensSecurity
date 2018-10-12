package com.example.harsh.womenssecurity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harsh.womenssecurity.data.ContactDbHelper;
import com.example.harsh.womenssecurity.data.Contract;

public class EditorActivity extends AppCompatActivity {

    private EditText name;

    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        name = findViewById(R.id.name);

        number = findViewById(R.id.number);
    }

    public void addContact(View view){
        insertPet();
    }

    private void insertPet(){


        String nameString = name.getText().toString().trim();
        String numString = number.getText().toString().trim();

        ContactDbHelper mDbHelper = new ContactDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(Contract.ContactEntry.COLUMN_CONTACT_NAME, nameString);
        values.put(Contract.ContactEntry.COLUMN_CONTACT_NUMBER, numString);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(Contract.ContactEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving number", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Number successfully added", Toast.LENGTH_SHORT).show();
        }
    }



}
