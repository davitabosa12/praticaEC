package br.ufc.smd.activitydiarynormal;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;

import smd.ufc.br.easycontext.fence.FenceAction;

public class StartingStillAction implements FenceAction {
    @Override
    public void doOperation(Context context, FenceState state, Bundle data) {
        if(state.getCurrentState() == FenceState.TRUE){
            Stopwatch stopwatch = new Stopwatch(context);
            stopwatch.startStill();
            Toast.makeText(context, "Started still", Toast.LENGTH_SHORT).show();
        }
    }
}
