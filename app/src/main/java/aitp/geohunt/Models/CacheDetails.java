package aitp.geohunt.Models;


import java.io.Serializable;
import java.util.ArrayList;

public class CacheDetails implements Serializable {
    boolean favorite = false;
    boolean found = false;
    ArrayList<Comment> comments;

    public boolean isFavorite() {
        return favorite;
    }
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
