package br.ufc.smd.activitydiarynormal;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ActivityTimeView extends ConstraintLayout {


    private ImageView imgActivity;
    private ImageView imgHeadphone;
    private TextView txvTime;

    private Activities activity;
    private long time;
    public enum Activities
    {
        STILL, RUNNING, ON_BICYCLE, STILL_HEADPHONE, RUNNING_HEADPHONE, ON_BICYCLE_HEADPHONE
    }


    public ActivityTimeView(Context context) {
        super(context);
        init(context);
    }

    public ActivityTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ActivityTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_menu_view, this);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.imgActivity = findViewById(R.id.img_activity);
        this.imgHeadphone = findViewById(R.id.img_headphone);
        this.txvTime = findViewById(R.id.txv_time);
        this.activity = Activities.STILL;
        this.time = 0;
    }

    public Activities getActivity() {
        return activity;
    }

    public void setActivity(Activities activity) {
        this.activity = activity;
        switch (activity)
        {
            case RUNNING:
                imgActivity.setImageResource(R.drawable.ic_running);
                imgHeadphone.setVisibility(GONE);
                break;
            case ON_BICYCLE:
                imgActivity.setImageResource(R.drawable.ic_running);
                imgHeadphone.setVisibility(GONE);
                break;
            case STILL_HEADPHONE:
                imgActivity.setImageResource(R.drawable.ic_still);
                imgHeadphone.setVisibility(VISIBLE);
                break;
            case RUNNING_HEADPHONE:
                imgActivity.setImageResource(R.drawable.ic_still);
                imgHeadphone.setVisibility(VISIBLE);
                break;
            case ON_BICYCLE_HEADPHONE:
                imgActivity.setImageResource(R.drawable.ic_running);
                imgHeadphone.setVisibility(VISIBLE);
                break;
            default:
            case STILL:
                imgActivity.setImageResource(R.drawable.ic_still);
                imgHeadphone.setVisibility(GONE);
                break;
        }
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        Map<String, String> calc = calculateTime(time);
        String s = calc.get("hours") + "h" + calc.get("minutes") + "min" + calc.get("seconds") + "s";
        txvTime.setText(s);
    }

    private Map<String, String> calculateTime(long time) {
        Map<String, String> calculated = new HashMap<>();
        long hours, minutes, seconds;
        seconds = Math.abs((time / 1000) % 60 );
        minutes = Math.abs((time / (1000*60)) % 60);
        hours   = Math.abs((time / (1000*60*60)) % 24);
        if(hours > 9)
            calculated.put("hours", "" + hours);
        else
            calculated.put("hours",  "0" + hours);
        if(minutes > 9)
            calculated.put("minutes", "" + minutes);
        else
            calculated.put("minutes",  "0" + minutes);
        if(seconds > 9)
            calculated.put("seconds", "" + seconds);
        else
            calculated.put("seconds",  "0" + seconds);
        return calculated;
    }
}
