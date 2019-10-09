package br.ufc.smd.activitydiarynormal;

import android.content.Context;
import android.content.Intent;

public class Stopwatch {
    private Context context;
    private Intent still, moving, stop;
    public Stopwatch(Context context){
        this.context = context;
        still = new Intent("stopwatch_control").putExtra("command", "still");
        moving = new Intent("stopwatch_control").putExtra("command", "moving");
        stop = new Intent("stopwatch_control").putExtra("command", "stop");
    }

    public void startStill(){
        context.sendBroadcast(still);
    }

    public void startMoving(){
        context.sendBroadcast(moving);
    }

    public void stopMonitoring(){
        context.sendBroadcast(stop);
    }
}
