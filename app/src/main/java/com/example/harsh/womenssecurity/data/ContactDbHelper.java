package com.example.harsh.womenssecurity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";

    private static final int DATABASE_VERSION = 1;

    public ContactDbHelper(Context context){
        super( context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_CONTACTS_TABLE =  "CREATE TABLE " + Contract.ContactEntry.TABLE_NAME + " ("
                + Contract.ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.ContactEntry.COLUMN_CONTACT_NAME + " TEXT NOT NULL, "
                + Contract.ContactEntry.COLUMN_CONTACT_NUMBER + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
