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
import mohamedzaeem.com.weather.adapters.HourAdapter;
import mohamedzaeem.com.weather.weather.Hour;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyWeatherFragment extends ListFragment {


    public static Hour[] mhours;


    public HourlyWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle argument = getArguments();
            Parcelable[] parcelables = argument.getParcelableArray("hi");
            mhours = Arrays.copyOf(parcelables, parcelables.length, Hour[].class);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        HourAdapter houradapter = new HourAdapter(getContext(), mhours);
        setListAdapter(houradapter);
        return inflater.inflate(R.layout.fragment_hourly_weather, container, false);
    }

}
