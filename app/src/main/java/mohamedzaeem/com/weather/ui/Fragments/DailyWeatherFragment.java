package mohamedzaeem.com.weather.ui.Fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import mohamedzaeem.com.weather.R;
import mohamedzaeem.com.weather.adapters.DayAdapter;
import mohamedzaeem.com.weather.weather.Day;


/**
 * A simple {@link Fragment} subclass.
 */
public class DailyWeatherFragment extends ListFragment {

    public static Day[] mdays;




    public DailyWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle argument = getArguments();
            Parcelable[] parcelables = argument.getParcelableArray("hello");
            mdays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_daily_weather, container, false);

        DayAdapter dayAdapter = new DayAdapter(getContext(),mdays);
        setListAdapter(dayAdapter);


        return v;
    }

}
