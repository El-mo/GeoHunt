package aitp.geohunt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        Geocache geocache = new Geocache();
        geocache.setTitle("Find me on Federal");
        geocache.setDescription("I've hidden one of my favorite little office toys around this location. Good luck!");
        geocache.setType("Toy");



        CacheDetails details = new CacheDetails();
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(new Comment("John Smith", "See if you can find what I hid!"));
        details.setComments(comments);
        geocache.setCacheDetails(details);
        geocache.setLocation(Float.parseFloat("-87.629798"), Float.parseFloat("41.878114"), this);
        geocaches.add(geocache);



        //
        geocache = new Geocache();
        geocache.setTitle("Brewers Bat");
        geocache.setDescription("Don't ask where the bat came from");
        geocache.setType("Funky Object");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, -15);
        geocache.setDate(cal.getTime());


        details = new CacheDetails();
        comments = new ArrayList<>();
        comments.add(new Comment("Jenny", "I have to find this!"));
        comments.add(new Comment("Jill", "No chance, I'll find it first"));
        details.setComments(comments);
        geocache.setCacheDetails(details);
        geocache.setLocation(Float.parseFloat("-87.129798"), Float.parseFloat("41.178114"), this);
        geocaches.add(geocache);


        ///
        geocache = new Geocache();
        geocache.setTitle("This is tricky");
        geocache.setDescription("Hard, but totally worth it");
        geocache.setType("Funky Object");
        cal = Calendar.getInstance();
        date = new Date();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -3);
        geocache.setDate(cal.getTime());

        details = new CacheDetails();
        comments = new ArrayList<>();
        comments.add(new Comment("Marshal", "I don't expect you to find this easily"));
        details.setComments(comments);
        geocache.setCacheDetails(details);
        geocache.setLocation(Float.parseFloat("-87.914979"), Float.parseFloat("43.038810"), this);
        geocaches.add(geocache);

        //

        geocache = new Geocache();
        geocache.setTitle("Don't Chicken Out");
        geocache.setDescription("I hid this object I got from Kentucky in a place you'll never find it!");
        geocache.setType("Funky Object");
        cal = Calendar.getInstance();
        date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        geocache.setDate(cal.getTime());

        details = new CacheDetails();
        comments = new ArrayList<>();
        comments.add(new Comment("Aaron", "Why is this at a KFC?"));
        details.setComments(comments);
        geocache.setCacheDetails(details);
        geocache.setLocation(Float.parseFloat("-89.612882"), Float.parseFloat("39.788282"), this);
        geocaches.add(geocache);




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