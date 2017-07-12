package mohamedzaeem.com.weather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.Pair;

import java.util.ArrayList;

/**
 * Created by zaeem on 7/7/2017.
 */

public class viewpageradapter extends FragmentPagerAdapter {

    private final ArrayList<Pair<String,Fragment>> list;

    public viewpageradapter(FragmentManager fm, ArrayList<Pair<String, Fragment>> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).second;
        }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).first;
    }
}
