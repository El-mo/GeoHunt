package aitp.geohunt.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import aitp.geohunt.Models.Geocache;
import aitp.geohunt.R;


public class ListViewAdapter extends ArrayAdapter<Geocache>{

    public ListViewAdapter(Context context, ArrayList<Geocache> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Geocache item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView dateTime = (TextView) convertView.findViewById(R.id.tv_date_time);
        TextView detail = (TextView) convertView.findViewById(R.id.tv_detail);

        String detailStr = item.getDescription();
        if(detailStr.length() > 30){
            detailStr = detailStr.substring(0, 30) + "...";
        }

        title.setText(item.getTitle());
        dateTime.setText(item.getDateString());
        detail.setText(detailStr);


        return convertView;
    }
}
