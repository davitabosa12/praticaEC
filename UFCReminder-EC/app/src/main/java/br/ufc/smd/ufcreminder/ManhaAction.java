package br.ufc.smd.ufcreminder;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.SnapshotClient;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.awareness.snapshot.TimeIntervalsResponse;
import com.google.android.gms.awareness.state.TimeIntervals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.List;

import smd.ufc.br.easycontext.fence.FenceAction;

public class ManhaAction implements FenceAction {
    @SuppressLint("MissingPermission")
    @Override
    public void doOperation(Context context, FenceState state, Bundle data) {
        SnapshotClient client = Awareness.getSnapshotClient(context);

        client.getTimeIntervals().addOnCompleteListener(new OnCompleteListener<TimeIntervalsResponse>() {
            @Override
            public void onComplete(@NonNull Task<TimeIntervalsResponse> task) {
                if (task.isSuccessful()) {
                    TimeIntervals ti = task.getResult().getTimeIntervals();
                    Log.d("ManhaAction", "Time intervals: " + Arrays.toString(ti.getTimeIntervals()));
                }
            }
        });
        if(state.getCurrentState() == FenceState.TRUE){
            List<Mensagem> mensagemList =
                    AppDb.getInstance(context).mensagemDao().getLocalHorario(Mensagem.Local.PICI, Mensagem.Horario.MANHA);

            if (mensagemList.isEmpty())
                return;
            else {
                for (Mensagem m :
                        mensagemList) {
                    PushNotification.pushMensagem(context, m);
                    AppDb.getInstance(context).mensagemDao().delete(m);
                }
            }
        }
    }
}
