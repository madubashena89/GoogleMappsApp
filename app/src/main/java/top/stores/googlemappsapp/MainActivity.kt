package top.stores.googlemappsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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
}
