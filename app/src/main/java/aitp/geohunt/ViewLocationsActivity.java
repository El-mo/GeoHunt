package aitp.geohunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewLocationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);
    }

    /*

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_find_apizzeria);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap map) {
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng sydney = new LatLng(-34, 151);
            map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        abstract public class LocationActivity extends MapsActivity implements LocationListener {
            private GoogleMap map;
            private LocationManager locationManager;
            private static final long MIN_TIME = 400;
            private static final float MIN_DISTANCE = 1000;


            @Override
            public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
                map.animateCamera(cameraUpdate);
            }
        }
        }
     */
}
