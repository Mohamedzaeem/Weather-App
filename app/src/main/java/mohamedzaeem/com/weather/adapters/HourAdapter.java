package mohamedzaeem.com.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mohamedzaeem.com.weather.R;
import mohamedzaeem.com.weather.weather.Hour;

/**
 * Created by zaeem on 7/11/2017.
 */

public class HourAdapter extends BaseAdapter {

    private Hour[] mHours;
    private Context mContext;
    private final LayoutInflater inflater;

    public HourAdapter(Context context, Hour[] hours) {
        mContext = context;
        mHours = hours;
        inflater =(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mHours.length;
    }

    @Override
    public Object getItem(int position) {
        return mHours[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView== null){
            convertView = inflater.inflate(R.layout.hourly_list_item,parent,false);
            holder = new ViewHolder();
            holder.iconimage = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.temperatureLabel);
            holder.TimeLabel = (TextView) convertView.findViewById(R.id.timeLabel);
            holder.SummaryLabel = (TextView) convertView.findViewById(R.id.summaryLabel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Hour hour = mHours[position];

        holder.iconimage.setImageResource(hour.getIconId());
        holder.temperatureLabel.setText(hour.getTemperature()+"");
        holder.SummaryLabel.setText(hour.getSummary());
        holder.TimeLabel.setText(hour.getTimeAsHour());

        return convertView;
    }

    public static class ViewHolder{

        ImageView iconimage;
        TextView temperatureLabel, TimeLabel, SummaryLabel;


    }
}