package com.example.maps.ui.slideshow;

import android.location.Location;
import android.location.LocationListener;

import androidx.annotation.NonNull;

public class MiPosition implements LocationListener {

    public static double latitud, longitud, altitud;
    public static boolean statusGPS;

    @Override
    public void onLocationChanged(@NonNull Location location) {

        latitud = location.getLatitude();
        longitud = location.getLongitude();
        altitud = location.getAltitude();

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        statusGPS = true;
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        statusGPS = false;
    }
}
