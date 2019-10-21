package br.ufc.smd.ufcreminder;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.awareness.fence.FenceState;

import java.util.List;

import smd.ufc.br.easycontext.fence.FenceAction;

public class NoiteReceiver implements FenceAction {
    @Override
    public void doOperation(Context context, FenceState state, Bundle data) {
        if (state.getCurrentState() == FenceState.TRUE) {
            List<Mensagem> mensagemList =
                    AppDb.getInstance(context).mensagemDao().getLocalHorario(Mensagem.Local.PICI, Mensagem.Horario.NOITE);

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
