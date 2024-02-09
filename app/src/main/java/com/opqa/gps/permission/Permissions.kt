package com.opqa.gps.permission

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.opqa.gps.MainActivity


/*  val requestMicCameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){ isGranted ->
      if (isGranted[android.Manifest.permission.CAMERA] == true){   }
      else{  }

      if (isGranted[android.Manifest.permission.MANAGE_DEVICE_POLICY_MICROPHONE] == true){  }
      else{  }

  }*/


    fun getUserLocatioon(){

    }

    fun isGranted(){

        when {
            ContextCompat.checkSelfPermission(
               MainActivity() , android.Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED -> {
               getUserLocatioon()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showDialog(
                    MainActivity() ,
                    "We need your location to determin nearest driver" ,
                    "Show again" ,
                    onPositiveButtonClick = {requestLocationPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)}
                )
            }

            else -> {
                requestLocationPermissionLauncher.launch(
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }


}