package com.cdtasklist.gps;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by dhananjay on 6/11/15.
 */
public class LocationGet extends Service implements LocationListener {

    LocationManager locationManager;
    double Lat, Long;
    Context mContext;
    Location location;

    public LocationGet(Context context) {
        mContext = context;
        getlocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        Lat = location.getLatitude();
        Long = location.getLongitude();


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public double getLogitude() {
        return Lat;
    }

    public double getLongitude() {
        return Long;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Location getlocation() {

        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    Lat = location.getLatitude();
                    Long = location.getLongitude();
                }
            }
        }
        return location;
    }

}
