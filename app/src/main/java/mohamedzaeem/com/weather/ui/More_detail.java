package mohamedzaeem.com.weather.ui;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import mohamedzaeem.com.weather.R;
import mohamedzaeem.com.weather.Util.UtilDensity;
import mohamedzaeem.com.weather.adapters.viewpageradapter;
import mohamedzaeem.com.weather.ui.Fragments.DailyWeatherFragment;
import mohamedzaeem.com.weather.ui.Fragments.HourlyWeatherFragment;
import mohamedzaeem.com.weather.weather.Day;
import mohamedzaeem.com.weather.weather.Hour;


public class More_detail extends AppCompatActivity implements View.OnTouchListener {

    private Day[] mdays;
    private Hour[] mhours;
    private boolean isOpen = false;
    private GestureDetector gestureDetector;
    private ValueAnimator openAnimator;
    private ValueAnimator closeAnimator;
    private viewpageradapter mPageradapter;


    private ArrayList<Pair<String, Fragment>> list = new ArrayList<Pair<String, Fragment>>();

    @InjectView(R.id.More_detail_main_layout)
    View view;

    @InjectView(R.id.More_detail_viewpager)
    ViewPager viewPager;

    @InjectView(R.id.More_detail_Tablayout)
    TabLayout tabLayout;






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
        mPageradapter = new viewpageradapter(this.getSupportFragmentManager(), list);

        viewPager.setAdapter(mPageradapter);
        tabLayout.setupWithViewPager(viewPager);

        gescondec();
    }

    public void gescondec(){
        gestureDetector = new GestureDetector(this, new GestureListener());
        view.setOnTouchListener(this);
        view.setLongClickable(true);
        viewPager.setOnTouchListener(this);
        openAnimator = openAnimatorListener();
        closeAnimator = closeAnimatorListener();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private class GestureListener extends GestureDetector.SimpleOnGestureListener{

        private boolean isMove = false;
        private int sumX = 0;
        private boolean isSwap = false;
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float x = e1.getRawX();
            //if (x<100){
            isSwap = true;
            //}
            sumX+=distanceY;
            if (!isMove){
                if(sumX<0){
                    if(Math.abs(sumX)>500){
                        //if(isSwap){
                        isMove = true;
                        Toast.makeText(More_detail.this,"top to bottom", Toast.LENGTH_LONG).show();
                        if(!isOpen){
                            openAnimator.start();
                            isOpen=true;
                            // }
                        }
                    }
                }

                if(sumX>0){
                    if(Math.abs(sumX)>500){
                        isMove = true;
                        if(isOpen){
                            closeAnimator.start();
                            isOpen=false;
                        }
                    }
                }

            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
//            shortToast("onDown");
            isMove = false;
            sumX = 0;
            isSwap = false;
            return super.onDown(e);
        }
    }

    private ValueAnimator openAnimatorListener(){
        ValueAnimator animator = ValueAnimator.ofInt(UtilDensity.dip2px(this,-700), 200);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                viewPager.layout(viewPager.getLeft(),curValue,viewPager.getRight(),curValue+viewPager.getHeight());

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(view.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(2000);
        return animator;
    }


    private ValueAnimator closeAnimatorListener(){
        ValueAnimator animator = ValueAnimator.ofInt(0, UtilDensity.dip2px(this,-700));

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int)animation.getAnimatedValue();
                viewPager.layout(viewPager.getLeft(),curValue,viewPager.getRight(),curValue+viewPager.getHeight());

            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(view.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                viewPager.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(view.INVISIBLE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animator.setDuration(2000);
        return animator;
    }


}














