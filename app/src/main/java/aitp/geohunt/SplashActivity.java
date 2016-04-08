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
        //geocaches = InternalStorage.readGeocacheList(this);
        geocaches = new ArrayList<>();
        Geocache geocache1 = new Geocache();
        geocache1.setTitle("Location 1");
        geocaches.add(geocache1);





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