package com.app.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.app.myapplication.ui.notifications.NotificationsFragment
import com.google.android.gms.location.LocationRequest

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this)
        var btnCont: Button = findViewById(R.id.btnContinuar)
        btnCont.setOnClickListener {
            var resevServ = Intent(this, ReservaActivity::class.java)
            startActivity(resevServ)
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        /*-0.152075, -78.488812*/
        val lavanderia = LatLng(-0.241469, -78.512973)
        mMap.addMarker(MarkerOptions().position(lavanderia).title("Lavanderia").snippet("Burbujas máquinas de lavado rapido").icon(BitmapDescriptorFactory.fromResource(R.drawable.wash)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lavanderia))

        val lavanderia2 = LatLng(-0.152075, -78.488812)
        mMap.addMarker(MarkerOptions().position(lavanderia2).title("Lavanderia").snippet("Lavanderias Norte S.A. lavado en seco").icon(BitmapDescriptorFactory.fromResource(R.drawable.wash)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lavanderia2))

        val lavanderia3 = LatLng(-0.163443, -78.475764)
        mMap.addMarker(MarkerOptions().position(lavanderia3).title("Lavanderia").snippet("A lavar la ropa. Servicio de lavanderia").icon(BitmapDescriptorFactory.fromResource(R.drawable.wash)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lavanderia3))

    }

}