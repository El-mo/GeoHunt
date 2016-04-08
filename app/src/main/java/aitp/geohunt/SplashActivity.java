package aitp.geohunt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Models.CacheDetails;
import aitp.geohunt.Models.Comment;
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
        geocaches = new ArrayList<>();
        Geocache geocache1 = new Geocache();
        geocache1.setTitle("Location 1");
        geocache1.setType("Toy");

        CacheDetails details = new CacheDetails();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("John Smith", "See if you can find what I hid!"));
        details.setComments(comments);
        geocache1.setCacheDetails(details);
        geocache1.setLocation(Float.parseFloat("-87.629798") , Float.parseFloat("41.878114"), this);
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