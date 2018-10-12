package com.example.harsh.womenssecurity.data;

import android.provider.BaseColumns;

public final class Contract {

    public Contract(){}

    public final class ContactEntry implements BaseColumns{

        public final static String TABLE_NAME = "contacts";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_CONTACT_NAME  = "name";

        public final static String COLUMN_CONTACT_NUMBER = "pnum";

    }

}
