package aitp.geohunt.Models;

import java.util.Comparator;


public class SortComparator implements Comparator<Geocache> {
    String arg;
    public SortComparator(String arg){
        this.arg = arg;
    }
    public SortComparator(){
        this.arg = "date";
    }

    //TODO Add Sort Options to Comparator
    @Override
    public int compare(Geocache event, Geocache t1) {
        switch (arg){
            case "date" : return compareDate(event, t1);
            case "title" : return compareTitle(event, t1);
            case "type" : return compareType(event,t1);
            case "location" : return compareToLocation(event, t1);
        }
        return 0;
    }

    private int compareToLocation(Geocache event, Geocache t1) {
        return Float.compare(event.getDistanceTo(), t1.getDistanceTo());
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
