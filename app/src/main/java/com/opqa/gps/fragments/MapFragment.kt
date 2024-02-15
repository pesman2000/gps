/*
package com.opqa.gps.fragments

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.opqa.gps.R

class MapFragment(private val activity: FragmentActivity) : OnMapReadyCallback {

    private var googleMaps: GoogleMap? = null

    fun mapFragment() {
        val mapFragment =
            activity.supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMaps ->
            this.googleMaps = googleMaps
        }
    }

    fun updateUserLocationMarker(latitude: Double, longitude: Double) {
        val options = MarkerOptions()
        options.position(LatLng(latitude, longitude))
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.wheel))
        googleMaps?.addMarker(options)
    }
}


*/
