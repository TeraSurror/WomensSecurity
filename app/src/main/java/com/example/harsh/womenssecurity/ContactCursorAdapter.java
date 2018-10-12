package com.example.harsh.womenssecurity;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.harsh.womenssecurity.data.Contract;

public class ContactCursorAdapter extends CursorAdapter {

    public ContactCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.summary);

        // Find the columns of pet attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_CONTACT_NAME);
        int phoneColumnIndex = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_CONTACT_NUMBER);

        // Read the pet attributes from the Cursor for the current pet
        String name = cursor.getString(nameColumnIndex);
        String phoneNum = cursor.getString(phoneColumnIndex);

        // Update the TextViews with the attributes for the current pet
        nameTextView.setText(name);
        summaryTextView.setText(phoneNum);
    }
}
