package mohamedzaeem.com.weather.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import mohamedzaeem.com.weather.R;

/**
 * Created by zaeem on 7/9/2017.
 */

public class Customdialog extends Dialog {

    private final ICustomDialogListner listner;

    private HashMap<String,Boolean> list = new HashMap<String,Boolean>();

    @InjectView(R.id.Custom_retry_rbt)
    RadioButton Retry;

    @InjectView(R.id.Custom_exit_rbt)
    RadioButton exit;

    @InjectView(R.id.Custom_radio_group)
    RadioGroup radioGroup;

    public interface ICustomDialogListner{
        public void onOKClicked(String msg);
    }



    @OnClick(R.id.Custom_ok_bt)
    public void ok(View view){
        String s = "";
        for (Map.Entry<String,Boolean> entry: list.entrySet()){
            if(entry.getValue()){
                s=s+entry.getKey();
            }
        }

        listner.onOKClicked(s);
        if(s.equals("Exit")){
            System.exit(0);
        }else{
            cancel();
        }
    }


    public Customdialog(@NonNull Context context, ICustomDialogListner listner) {
        super(context,R.style.dialog);
        setContentView(R.layout.custom_dialog);
        ButterKnife.inject(this);
        this.listner = listner;
        Retry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.put(buttonView.getText().toString(),isChecked);
            }
        });

        exit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.put(buttonView.getText().toString(),isChecked);
            }
        });
    }
}
