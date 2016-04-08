package aitp.geohunt;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Helper.ImageHelper;
import aitp.geohunt.Helper.LocationHelper;
import aitp.geohunt.Models.CacheDetails;
import aitp.geohunt.Models.Geocache;


public class DetailActivity extends AppCompatActivity implements LocationListener {

    int index;
    Geocache item;
    EditText etTitle, etDesc, etType;
    TextView tvAddress;
    CheckBox cbFav;
    ImageHelper imageFromCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        index = getIntent().getIntExtra(MainActivity.LISTINDEX, -1);
        if(index != -1){
            item = InternalStorage.getGeocacheAtIndex(this, index);
            fillForm();
        }
        else
            finish();
    }

    // FORM OPERATIONS
    public void fillForm(){
        //TODO fill form logic
        etTitle = (EditText) findViewById(R.id.detail_et_title);
        etDesc = (EditText) findViewById(R.id.detail_et_desc);
        etType = (EditText) findViewById(R.id.detail_et_Type);
        tvAddress = (TextView) findViewById(R.id.edit_tv_address);
        cbFav = (CheckBox) findViewById(R.id.checkBox_Fav);

        etTitle.setText(item.getTitle());
        etDesc.setText(item.getDescription());
        etType.setText(item.getType());
        cbFav.setChecked(item.getCacheDetails().isFavorite());

        LocationHelper locationHelper = item.getLocation();
        if(locationHelper == null)
            tvAddress.setText("Address: Location not added");
        else{
            if(locationHelper.getAddress().length>1){
                String address = "Location: " + locationHelper.getAddress()[1];
                tvAddress.setText(address);
            }
        }

    }

    public void getFromForm(){

        item.setTitle(etTitle.getText().toString());
        item.setDescription(etDesc.getText().toString());
        CacheDetails details = item.getCacheDetails();
        details.setFavorite(cbFav.isChecked());
        item.setCacheDetails(details);
    }


    // BUTTON OnClicks
    public void seeComments(View view){
        Intent i = new Intent(DetailActivity.this, CommentDisplayActivity.class);
        i.putExtra(MainActivity.LISTINDEX, index);
        startActivity(i);
    }

    public void seePictures(View view){
        Intent i = new Intent(DetailActivity.this, ImageDisplayActivity.class);
        i.putExtra(ImageHelper.IMAGEHELPER, item.getImages());
        startActivity(i);
    }

    public void foundIt(View view){
        CacheDetails details = this.item.getCacheDetails();
        if (!details.isFound())
            details.setFound(true);
        Toast.makeText(this, "Congrats!", Toast.LENGTH_LONG).show();
    }

    public void takePictureEditForm(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            this.imageFromCam = new ImageHelper();
            try {
                photoFile = imageFromCam.createImageFile();
            } catch (IOException ex) {
                Log.d(MainActivity.DEBUGSTR, "Create file error");
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, ImageHelper.IMAGE_REQUEST_CODE);
            }
        }
    }

    // PHOTOS

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageHelper.IMAGE_REQUEST_CODE) {
            if(resultCode == RESULT_OK)
            {
                if(this.imageFromCam != null) {
                    item.addImage(imageFromCam);
                    Toast.makeText(this, "Image Saved", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Error Saving Image", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "Image Not Added", Toast.LENGTH_LONG).show();
                imageFromCam = null;
            }
        }
    }

    //MENU (SHARE)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: EDIT Share Info
        switch (item.getItemId()) {
            case R.id.share_detail:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "I'm looking for " + this.item.getTitle() + " with GeoHunt!!\n" +
                                "It's somewhere in " + this.item.getLocation().getAddress()[1]);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share Event"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    //LIFECYCLE (SAVE)
    @Override
    protected void onStop() {
        super.onStop();
        ArrayList<Geocache> geocaches = InternalStorage.readGeocacheList(this);
        getFromForm();
        geocaches.set(index, this.item);
        InternalStorage.writeGeocacheList(this, geocaches);
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
