package mohamedzaeem.com.weather.weather;

import mohamedzaeem.com.weather.R;

/**
 * Created by zaeem on 7/9/2017.
 */

public class Forecast {

    private Current mCurrent;
    private Hour[] mhourlyForecast;
    private Day[] mdailyForecast;

    public Current getCurrent() {
        return mCurrent;
    }

    public void setCurrent(Current mCurrent) {
        this.mCurrent = mCurrent;
    }

    public Hour[] gethourlyForecast() {
        return mhourlyForecast;
    }

    public void sethourlyForecast(Hour[] mhourlyForecast) {
        this.mhourlyForecast = mhourlyForecast;
    }

    public Day[] getdailyForecast() {
        return mdailyForecast;
    }

    public void setdailyForecast(Day[] mdailyForecast) {
        this.mdailyForecast = mdailyForecast;
    }
    public static int getIconId(String mIconstring) {
        // clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night.
        int iconId = R.drawable.clear_day;

        if (mIconstring.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (mIconstring.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (mIconstring.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (mIconstring.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIconstring.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIconstring.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIconstring.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIconstring.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIconstring.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIconstring.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }

        return iconId;
    }
}
