package mohamedzaeem.com.weather.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import mohamedzaeem.com.weather.R;


/**
 * Created by zaeem on 7/14/2017.
 */

public class Receiver extends Service {


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int temp = intent.getIntExtra("Current", 0);
        if (temp < 60) {
           Notification.Builder notificationBuilder = new Notification.Builder(this)
                   .setSmallIcon(R.drawable.ic_launcher)
                   .setContentTitle("it getting colder")
                   .setContentText(""+temp)
                    .setAutoCancel(true);
            Notification notifitation = notificationBuilder.build();
            startForeground(11,notifitation);
        }

            return Service.START_NOT_STICKY;
        }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
