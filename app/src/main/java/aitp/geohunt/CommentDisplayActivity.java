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
import aitp.geohunt.Models.Comment;
import aitp.geohunt.Models.Geocache;

public class CommentDisplayActivity extends AppCompatActivity implements AlertCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_display);
        int index = getIntent().getIntExtra(MainActivity.LISTINDEX, -1);
        Geocache geocache = InternalStorage.getGeocacheAtIndex(this, index);
        ArrayList<Comment> comments = geocache.getCacheDetails().getComments();
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

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void alertHelperTextResponse(String title, String response) {

    }

    @Override
    public void alertHelperBooleanResponse(String title, Boolean response) {

    }
}
