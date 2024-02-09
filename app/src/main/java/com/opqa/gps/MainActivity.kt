package com.opqa.gps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.opqa.gps.permission.Permissions
import com.opqa.gps.permission.getUserLocatioon
import com.opqa.gps.permission.isGranted

class MainActivity : AppCompatActivity() {
    val requestLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                getUserLocatioon()
            } else {
                Toast.makeText(this, "Permission not granted!", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestLocationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        isGranted()


    }
}