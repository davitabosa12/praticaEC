package br.ufc.smd.activitydiarynormal;

import android.content.Context;
import android.content.SharedPreferences;

public class AppDb {
    private SharedPreferences prefs;
    private static AppDb instance;
    private static final String STILL = "stillTime";
    private static final String MOVING = "movingTime";
    private AppDb(Context context){
        prefs = context.getSharedPreferences("activity_diary", Context.MODE_PRIVATE);
    }

    public static AppDb getInstance(Context context) {
        if (instance == null) {
            instance = new AppDb(context);
        }
        return instance;
    }

    public SharedPreferences getPrefs() {
        return prefs;
    }

    public void updateStillTime(long seconds){
        prefs.edit().putLong(STILL, seconds).apply();
    }

    public void updateMovingTime(long seconds){
        prefs.edit().putLong(MOVING, seconds).apply();
    }

    public long getStillTime(){
        return prefs.getLong(STILL, 0);
    }
    public long getMovingTime(){
        return prefs.getLong(MOVING, 0);
    }

    public void clear(){
        updateMovingTime(0);
        updateStillTime(0);
    }
}
