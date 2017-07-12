package mohamedzaeem.com.weather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mohamedzaeem.com.weather.R;
import mohamedzaeem.com.weather.weather.Day;

/**
 * Created  zaeem on 7/9/2017.
 */

public class DayAdapter extends BaseAdapter {

    private Context context;
    private final LayoutInflater inflater;
    private Day[] mdays;

    public DayAdapter(Context context, Day[] days) {
        this.context = context;
        this.mdays = days;
        inflater =(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mdays.length;
    }

    @Override
    public Object getItem(int position) {
        return mdays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;

        if(convertView== null){
            convertView = inflater.inflate(R.layout.daily_list_item,parent,false);
            holder = new ViewHolder();
            holder.iconimage = (ImageView) convertView.findViewById(R.id.daily_list_icon_view);
            holder.temperatureLabel = (TextView) convertView.findViewById(R.id.daily_list_temp_label);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.daily_list_day_text);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mdays[position];

        holder.iconimage.setImageResource(day.getIconId());
        holder.temperatureLabel.setText(day.getTemperatureMax()+"");
        holder.dayLabel.setText(day.getDayOfTheWeek());

        return convertView;
    }

    public static class ViewHolder{

        ImageView iconimage;
        TextView temperatureLabel, dayLabel;


    }
}
