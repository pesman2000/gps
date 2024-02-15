package com.opqa.gps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
/*import com.opqa.gps.fragments.MapFragment*/
import com.opqa.gps.permission.Permissions


class MainActivity : AppCompatActivity() {
    private lateinit var permissions: Permissions
    var googleMaps : GoogleMap? = null
  /*  private lateinit var mapFragment: MapFragment*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMaps ->
            this.googleMaps = googleMaps
        }

        permissions = Permissions(this)
        permissions.firstRequestPermission()
        permissions.getUserLocation()
        permissions.requestLocationUpdates()

       /* mapFragment = MapFragment(this)
        mapFragment.mapFragment()
        mapFragment.requestLocationUpdates()
        mapFragment.getUserLocation()*/

    }


}