package top.stores.googlemappsapp

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMap()

        if (googleServicesAvaialble()) {
            Toast.makeText(this, "Perfect !!", Toast.LENGTH_LONG).show()

        } else {
            // No google maps layout
        }
    }

    fun initMap() {
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.mapFragment) as SupportMapFragment
//        mapFragment.getMapAsync(this)

        (this.supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?)?.let {
            it.getMapAsync(this)
        }
    }

    fun googleServicesAvaialble(): Boolean {
        var api: GoogleApiAvailability = GoogleApiAvailability.getInstance()
        var isAvailable = api.isGooglePlayServicesAvailable(this)
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true
        } else if (api.isUserResolvableError(isAvailable)) {
            var dialog = api.getErrorDialog(this, isAvailable, 0)
            dialog.show()
        } else {
            Toast.makeText(this, "Can't connect to play services !", Toast.LENGTH_LONG).show()
        }
        return false
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        goToLocationZoom(28.6139, 77.2090, 15F)
//        val latLng = LatLng(28.6139, 77.2090)
//        val markerOptions: MarkerOptions = MarkerOptions().position(latLng).title("New Delhi")
//
//        // moving camera and zoom map
//
//        val zoomLevel = 12.0f //This goes up to 21
//
//
//        googleMap.let {
//            it!!.addMarker(markerOptions)
//            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
//
//        }


    }

    fun goToLocation(lat : Double , lng : Double){
        var ll = LatLng(lat, lng)
        var cameraUpdate = CameraUpdateFactory.newLatLng(ll)
        mGoogleMap?.moveCamera(cameraUpdate)

    }

    fun goToLocationZoom(lat : Double , lng : Double, zomm : Float){
        var ll = LatLng(lat, lng)
        var cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll, zomm)
        mGoogleMap?.moveCamera(cameraUpdate)

    }

    @Throws(IOException::class)
    fun geoLocate(view: View) {
      try {
          var et : EditText = findViewById(R.id.editText)
          var location = et.text.toString()

          var gc = Geocoder(this)
          var list : List<Address> = gc.getFromLocationName(location,1)
          var address = list.get(0)
          var locality = address.locality

          Toast.makeText(this, locality, Toast.LENGTH_LONG).show()

          var lat = address.latitude
          var lng = address.longitude
          goToLocationZoom(lat,lng, 15F)

      }catch (e : IOException){
          e.printStackTrace()
      }


    }

    fun geoLocateButtonClick() {
        try {
            var et : EditText = findViewById(R.id.editText)
            var location = et.text.toString()

            var gc = Geocoder(this)
            var list : List<Address> = gc.getFromLocationName(location,1)
            var address = list.get(0)
            var locality = address.locality

            Toast.makeText(this, locality, Toast.LENGTH_LONG).show()

            var lat = address.latitude
            var lng = address.longitude
            goToLocationZoom(lat,lng, 15F)

        }catch (e : IOException){
            e.printStackTrace()
        }


    }

}














