package com.opqa.gps.permission

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.LocationRequest.*
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.opqa.gps.MainActivity
import com.opqa.gps.R

class Permissions(private val activity: FragmentActivity, val  googleMaps: GoogleMap?) {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private val requestLocationPermission =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                getUserLocation(googleMaps)
            } else {
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private fun showDialog(
        context: Context ,
        message: String? = null,
        positiveButton: String? = null,
        onPositiveButtonClick: (() -> Unit)? = null,
    ) {

        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setPositiveButton(positiveButton
        ) { dialog, _ ->
            onPositiveButtonClick?.invoke()
            dialog?.dismiss()
        }

        builder.show()
    }
    fun firstRequestPermission() {
        when {
            ContextCompat.checkSelfPermission(
                activity  , android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                activity , android.Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                showDialog(
                    activity,
                    "We need your location to determine nearest driver",
                    "Show again",
                    onPositiveButtonClick = { requestLocationPermission.launch(android.Manifest.permission.ACCESS_FINE_LOCATION) }
                )
            }

            else -> {
                requestLocationPermission.launch(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(googleMaps: GoogleMap?){
        val locationUpdate = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,3000).build()
        val callback = object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                Log.e("LOCATION TAG", "onLocationResult: ${locationResult.lastLocation?.longitude}")
                Log.e("LOCATION TAG", "onLocationResult: ${locationResult.lastLocation?.latitude}")
                updateUserLocationMarker(
                    locationResult.lastLocation?.latitude ?: 0.0 ,
                    locationResult.lastLocation?.longitude ?: 0.0,
                    googleMaps
                )
            }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationUpdate , callback , Looper.getMainLooper())
    }
    @SuppressLint("MissingPermission")
    fun getUserLocation(googleMaps: GoogleMap?) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
        val request = CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_HIGH_ACCURACY).setDurationMillis(300).build()
        fusedLocationProviderClient.getCurrentLocation(request , null).addOnSuccessListener { location ->

        }
        //   fun add(num1 : Int, num2 : Int)
        requestLocationUpdates(googleMaps =googleMaps )
    }

    fun updateUserLocationMarker(latitude : Double , longitude : Double, googleMaps: GoogleMap?){
        val options = MarkerOptions()
        options.position(LatLng(latitude , longitude))
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.wheel))
        Log.e("PIC TAG", "updateUserLocationMarker: ${options.icon}")
        Log.e("TAG", "updateUserLocationMarker: Google maps -> $googleMaps", )
       googleMaps?.addMarker(options)
    }


}

