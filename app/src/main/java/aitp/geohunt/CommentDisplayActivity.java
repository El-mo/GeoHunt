package aitp.geohunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import aitp.geohunt.Adapters.CommentListAdapter;
import aitp.geohunt.DataLayer.InternalStorage;
import aitp.geohunt.Models.Comment;
import aitp.geohunt.Models.Geocache;

public class CommentDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_display);
        int index = index = getIntent().getIntExtra(MainActivity.LISTINDEX, -1);
        Geocache geocache = InternalStorage.getGeocacheAtIndex(this, index);
        ArrayList<Comment> comments = geocache.getCacheDetails().getComments();
        ListView commentList;
        commentList = (ListView) findViewById(R.id.commentList);
        commentList.setAdapter(new CommentListAdapter(this, comments));

    }
}
