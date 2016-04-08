package aitp.geohunt.Models;

import android.content.Context;
import android.location.Location;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import aitp.geohunt.Helper.ImageHelper;
import aitp.geohunt.Helper.LocationHelper;


public class Geocache implements Serializable {

    String title = "";          //name
    String description = "";
    String type = "";
    Date date = new Date();
    LocationHelper location;
    float distanceTo = -1;
    CacheDetails cacheDetails = new CacheDetails();

    ArrayList<ImageHelper> images = new ArrayList<>();

    public Geocache(){}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public String getDateString(){
        return DateFormat.getDateTimeInstance().format(date.getTime());
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public LocationHelper getLocation() {
        return location;
    }
    public void setLocation(Location location, Context context) {
        this.location = new LocationHelper(location, context);
    }
    public void setLocation(float longitude, float latitude, Context context){
        Location loc = new Location("");
        loc.setLatitude(latitude);
        loc.setLongitude(longitude);
        this.setLocation(loc, context);
    }
    public ArrayList<ImageHelper> getImages() {
        return images;
    }
    public void addImage(ImageHelper image) {if(!images.contains(image)) images.add(image);}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setLocation(LocationHelper location) {
        this.location = location;
    }

    public float getDistanceTo() {
        return distanceTo;
    }
    public void setDistanceTo(float distanceTo) {
        this.distanceTo = distanceTo;
    }

    public void setImages(ArrayList<ImageHelper> images) {
        this.images = images;
    }

    public CacheDetails getCacheDetails() {
        return cacheDetails;
    }

    public void setCacheDetails(CacheDetails cacheDetails) {
        this.cacheDetails = cacheDetails;
    }

    public boolean isFavorite(){
        return cacheDetails.isFavorite();
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Geocache))
            return false;
        if (obj == this)
            return true;

        Geocache otherObject = (Geocache) obj;
        return (title.equals(otherObject.getTitle()) && (description.equals(otherObject.getDescription())));
    }

}
