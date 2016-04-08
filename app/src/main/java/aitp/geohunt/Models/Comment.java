package aitp.geohunt.Models;


import java.text.DateFormat;
import java.util.Date;

public class Comment {

    public String name, comment;
    public Date commentDate = new Date();

    public String getDateString(){
        return DateFormat.getDateTimeInstance().format(commentDate.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
