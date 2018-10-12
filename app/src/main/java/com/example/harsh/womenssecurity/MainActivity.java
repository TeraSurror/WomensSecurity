package com.example.harsh.womenssecurity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.harsh.womenssecurity.data.ContactDbHelper;
import com.example.harsh.womenssecurity.data.Contract;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;

    private static final String TAG = "MyActivity";

    private String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLocation();
    }

    public void  startEmergency(View v){
        Intent intent = new Intent(this,EmergencyServices.class);
        startActivity(intent);
    }

    public void startViewContacts(View view){
        Intent intent = new Intent(this,ViewContacts.class);
        startActivity(intent);
    }

    public void startAddContacts(View view){
        Intent intent = new Intent(this,EditorActivity.class);
        startActivity(intent);
    }

    public void sendSOS(View view){

        String num = getNumbers().toString();
        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.putExtra("address", num);
        i.putExtra("sms_body", "SOS!!!\n" + loc);
        i.setType("vnd.android-dir/mms-sms");
        startActivity(i);
    }

    private StringBuilder getNumbers(){

        StringBuilder numList = new StringBuilder("");

        ContactDbHelper mDbHelper = new ContactDbHelper(this);

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




        while(cursor.moveToNext()) {
            int phoneIndex = cursor.getColumnIndex(Contract.ContactEntry.COLUMN_CONTACT_NUMBER);
            String phoneNumber = cursor.getString(phoneIndex);
            numList.append(phoneNumber + "; ");
        }

        String num = numList.toString();

        int i = numList.lastIndexOf("; ");
        int end = numList.length();

        numList.delete(i,end);

        return numList;
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        loc = "Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            loc = loc + "\n"+addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2);
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }
}
