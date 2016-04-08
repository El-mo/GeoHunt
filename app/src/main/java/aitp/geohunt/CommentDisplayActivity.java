package aitp.geohunt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import aitp.geohunt.Adapters.CommentListAdapter;
import aitp.geohunt.Callbacks.AlertCallBack;
import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Helper.AlertHelper;
import aitp.geohunt.Models.CacheDetails;
import aitp.geohunt.Models.Comment;
import aitp.geohunt.Models.Geocache;

public class CommentDisplayActivity extends AppCompatActivity implements AlertCallBack {

    Comment addComment;
    Geocache geocache;
    ArrayList<Comment> comments;

    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_display);
        index = getIntent().getIntExtra(MainActivity.LISTINDEX, -1);
        geocache = InternalStorage.getGeocacheAtIndex(this, index);
         comments = geocache.getCacheDetails().getComments();
        ListView commentList;
        commentList = (ListView) findViewById(R.id.commentList);
        commentList.setAdapter(new CommentListAdapter(this, comments));

    }

    //MENU (SHARE)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_button:
                new AlertHelper(this, this).makeAlertWithTextInput("What's your name");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void alertHelperTextResponse(String title, String response) {
        if(title.equals("What's your name")) {
            addComment = new Comment();
            addComment.setName(response);
            new AlertHelper(this, this).makeAlertWithTextInput("Comment:");
        }
        if(title.equals("Comment:")){
            addComment.setComment(response);
            comments.add(addComment);
            CacheDetails details = geocache.getCacheDetails();
            details.setComments(comments);
            geocache.setCacheDetails(details);
            ArrayList<Geocache> geocaches = InternalStorage.readGeocacheList(this);
            geocaches.set(index, geocache);
            InternalStorage.writeGeocacheList(this, geocaches);
        }
    }

    @Override
    public void alertHelperBooleanResponse(String title, Boolean response) {

    }
}
