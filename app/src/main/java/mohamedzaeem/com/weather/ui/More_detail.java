package mohamedzaeem.com.weather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mohamedzaeem.com.weather.R;
import mohamedzaeem.com.weather.adapters.viewpageradapter;
import mohamedzaeem.com.weather.ui.Fragments.DailyWeatherFragment;
import mohamedzaeem.com.weather.ui.Fragments.HourlyWeatherFragment;
import mohamedzaeem.com.weather.weather.Day;
import mohamedzaeem.com.weather.weather.Hour;


public class More_detail extends AppCompatActivity {

    private Day[] mdays;
    private Hour[] mhours;
    private ArrayList<Pair<String, Fragment>> list = new ArrayList<Pair<String, Fragment>>();

    @InjectView(R.id.More_detail_viewpager)
    ViewPager viewPager;

    @InjectView(R.id.More_detail_Tablayout)
    TabLayout tabLayout;

    private viewpageradapter mviewpageradapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_detail);
        ButterKnife.inject(this);



        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("daily forecast");

        mdays = Arrays.copyOf(parcelables,parcelables.length,Day[].class);


        DailyWeatherFragment dailyWeatherFragment = new DailyWeatherFragment();
        Bundle bundle =new Bundle();
        bundle.putParcelableArray("hello",mdays);
        dailyWeatherFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.More_detail_viewpager,dailyWeatherFragment);
        transaction.addToBackStack(null);


        Parcelable[] parcelables1 = intent.getParcelableArrayExtra("hourly forecast");

        mhours = Arrays.copyOf(parcelables1,parcelables1.length,Hour[].class);

        HourlyWeatherFragment hourlyWeatherFragment = new HourlyWeatherFragment();
        bundle.putParcelableArray("hi",mhours);
        hourlyWeatherFragment.setArguments(bundle);

        transaction.replace(R.id.More_detail_viewpager,hourlyWeatherFragment);
        transaction.addToBackStack(null);
        transaction.commit();

        list.add(new Pair("Daily",new DailyWeatherFragment()));
        list.add(new Pair("Hourly",new HourlyWeatherFragment()));

        viewPager = (ViewPager) findViewById(R.id.More_detail_viewpager);
        mviewpageradapter = new viewpageradapter(this.getSupportFragmentManager(), list);

        viewPager.setAdapter(mviewpageradapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
