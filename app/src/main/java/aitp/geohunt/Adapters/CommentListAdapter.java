package aitp.geohunt.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import aitp.geohunt.Models.Comment;
import aitp.geohunt.R;

public class CommentListAdapter extends ArrayAdapter<Comment>{

    public CommentListAdapter(Context context, ArrayList<Comment> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data comment for this position
        Comment comment = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView dateTime = (TextView) convertView.findViewById(R.id.tv_date_time);
        TextView detail = (TextView) convertView.findViewById(R.id.tv_detail);

        title.setText(comment.getName());
        dateTime.setText(comment.getDateString());
        detail.setText(comment.getComment());


        return convertView;
    }

}
