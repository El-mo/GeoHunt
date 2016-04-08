package aitp.geohunt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Models.Geocache;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME = 2000;
    ArrayList<Geocache> geocaches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        createData();
        splash();
    }

    public void createData(){
        geocaches = InternalStorage.readGeocacheList(this);
        Geocache geocache1 = new Geocache();
        geocache1.setTitle("Location 1");
        geocaches.add(geocache1);
        Geocache geocache2 = new Geocache();
        geocaches.add(geocache2);
        geocache2.setTitle("Location 2");
        geocaches.add(geocache2);
        Geocache geocache3 = new Geocache();
        geocache3.setTitle("Location 3");
        geocaches.add(geocache3);
        Geocache geocache4 = new Geocache();
        geocache4.setTitle("Location 4");
        geocaches.add(geocache4);
        Geocache geocache5 = new Geocache();
        geocache5.setTitle("Location 5");
        geocaches.add(geocache5);
        Geocache geocache6 = new Geocache();
        geocache6.setTitle("Location 6");
        geocaches.add(geocache6);




        InternalStorage.writeGeocacheList(this, geocaches);
    }

    public void splash(){
        new Handler().postDelayed(new Runnable() {

            //Show splash screen for SPLASH_TIME length
            @Override
            public void run() {
                // Start app main activity after splash minimal time
                endSplash();
            }
        }, SPLASH_TIME);
    }

    public void endSplash(){
        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
