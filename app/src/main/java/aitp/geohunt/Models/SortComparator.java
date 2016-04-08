package aitp.geohunt.Models;

import java.util.Comparator;

import aitp.geohunt.Helper.LocationHelper;


public class SortComparator implements Comparator<Geocache> {
    String arg;
    LocationHelper location;
    public SortComparator(String arg){
        this.arg = arg;
    }
    public SortComparator(){
        this.arg = "date";
    }

    @Override
    public int compare(Geocache event, Geocache t1) {
        switch (arg){
            case "date" : return compareDate(event, t1);
            case "title" : return compareTitle(event, t1);
            case "type" : return compareType(event,t1);
            case "distance" : return compareToLocation(event, t1);
        }
        return 0;
    }

    private int compareToLocation(Geocache event, Geocache t1) {
        if(this.location == null)
            return 0;
        return Float.compare(this.location.getLocation().distanceTo(event.getLocation().getLocation()), this.location.getLocation().distanceTo(t1.getLocation().getLocation()));
    }
    public int compareDate(Geocache e1, Geocache e2){
        return e1.getDate().compareTo(e2.getDate());
    }
    private int compareTitle(Geocache event, Geocache t1) {
        return event.getTitle().compareTo(t1.getTitle());
    }
    public int compareType(Geocache e1, Geocache e2){
        return e1.getType().compareTo(e2.getType());
    }
    public void setLocation(LocationHelper loc){
        this.location = loc;
        this.arg = "distance";
    }

    public void setCompareToDate(){
        arg = "date";
    }
    public void setCompareToTitle() {
        arg = "title";
    }
    public void setCompareToType(){
        arg = "type";
    }
    public void setCompareToLocation(){
        arg = "location";
    }
}
