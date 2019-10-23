package br.ufc.smd.ufcreminder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.awareness.fence.FenceState;

import smd.ufc.br.easycontext.fence.FenceAction;

public class TesteAction implements FenceAction {
    @Override
    public void doOperation(Context context, FenceState state, Bundle data) {
        Log.d("TesteAction", "From " + state.getFenceKey() + "...");
        if(state.getCurrentState() == FenceState.TRUE){
            Toast.makeText(context, "Horario!", Toast.LENGTH_SHORT).show();
            Log.d("TesteAction", "Fence is true");
        } else if(state.getCurrentState() == FenceState.FALSE){
            Log.d("TesteAction", "Fence is false");
        } else {
            Log.d("TesteAction", "Fence is UNKNOWN");
        }
    }
}
