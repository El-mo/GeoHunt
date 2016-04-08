package aitp.geohunt;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Models.Geocache;

public class ViewLocationsActivity extends FragmentActivity
        implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        ArrayList<Geocache> list = InternalStorage.readGeocacheList(this);

        for(Geocache item : list){
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(item.getLatitude(), item.getLongitude()))
                    .title(item.getTitle()));
        }

        if(!list.isEmpty()){
            Geocache item = list.get(0);
            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(item.getLatitude(), item.getLongitude())));
            CameraUpdate  zoom = CameraUpdateFactory.zoomTo(11);
            map.animateCamera(zoom);
        }
    }

    /*
    <string name="DEVELOPER_KEY">AIzaSyAKSMbskTg2D9ZCJQb5pbvnoosGYq9uiiI</string>

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
