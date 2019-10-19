package br.ufc.smd.activitydiarynormal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;

public class StopwatchService extends Service {
    private final IBinder binder = new StopwatchBinder();
    private long stillSeconds, movingSeconds;
    private Handler stopwatchHandler;
    private Runnable stillRunnable, movingRunnable;
    private BroadcastReceiver controlReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String command = intent.getStringExtra("command");
            if(command.equals("still")){
                startStill();
            } else if(command.equals("moving")){
                startMoving();
            } else if(command.equals("stop")){
                stopAll();
            }
        }
    };
    private Timer stillTimer, movingTimer;
    private AppDb db;
    public StopwatchService() {
        db = AppDb.getInstance(this);

        stillSeconds = db.getStillTime();
        movingSeconds = db.getMovingTime();
        stopwatchHandler = new Handler(Looper.getMainLooper());
        stillRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("StopwatchService", "run: still = " + stillSeconds);
                stillSeconds++;
                db.updateStillTime(stillSeconds);
                stopwatchHandler.postDelayed(this, 1000);
            }
        };

        movingRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("StopwatchService", "run: moving = " + movingSeconds);
                movingSeconds++;
                db.updateMovingTime(movingSeconds);
                stopwatchHandler.postDelayed(this, 1000);
            }
        };

    }

    @Override
    public void onCreate() {
        Notification.Builder notificationBuilder = new Notification.Builder(this);

        notificationBuilder.setSmallIcon(R.drawable.ic_running);
        notificationBuilder.setContentTitle("Activity Diary");
        notificationBuilder.setContentText("Monitorando");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(NotificationChannel.DEFAULT_CHANNEL_ID);
        }

        startForeground(123, notificationBuilder.build());
        registerReceiver(controlReceiver, new IntentFilter("stopwatch_control"));
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(controlReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    public class StopwatchBinder extends Binder {
        StopwatchService getService(){
            return StopwatchService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    public void startStill(){
        Toast.makeText(this, "Start service 1", Toast.LENGTH_SHORT).show();
        stopwatchHandler.removeCallbacks(movingRunnable);
        stopwatchHandler.removeCallbacks(stillRunnable);
        stopwatchHandler.postDelayed(stillRunnable, 1000);
    }

    public void startMoving(){
        Toast.makeText(this, "Start service 2", Toast.LENGTH_SHORT).show();
        stopwatchHandler.removeCallbacks(stillRunnable);
        stopwatchHandler.removeCallbacks(movingRunnable);
        stopwatchHandler.postDelayed(movingRunnable, 1000);
    }

    public void stopAll(){
        stopwatchHandler.removeCallbacks(movingRunnable);
        stopwatchHandler.removeCallbacks(stillRunnable);
        stopSelf();
    }
}
