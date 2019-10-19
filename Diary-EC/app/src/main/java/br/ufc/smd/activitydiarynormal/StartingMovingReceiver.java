package br.ufc.smd.activitydiarynormal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;

public class StartingMovingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fs = FenceState.extract(intent);

        if(fs.getCurrentState() == FenceState.TRUE){
            Stopwatch stopwatch = new Stopwatch(context);
            stopwatch.startMoving();
            Toast.makeText(context, "Started moving", Toast.LENGTH_SHORT).show();
        }
    }
}
