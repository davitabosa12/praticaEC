package br.ufc.smd.ufcreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.awareness.fence.FenceState;

import java.util.List;

public class PiciManhaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);
        if (fenceState.getCurrentState() == FenceState.TRUE) {
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
