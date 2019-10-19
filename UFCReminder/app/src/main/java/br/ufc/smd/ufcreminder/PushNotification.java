package br.ufc.smd.ufcreminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class PushNotification {
    private static final String CHANNEL_ID = "ufc-reminder";

    public static void pushMensagem(Context c, Mensagem m){
        NotificationManager nm = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(c)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(m.getConteudo())
                .setContentTitle("UFCReminder");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            assert nm != null;
            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Default", NotificationManager.IMPORTANCE_DEFAULT));
            notification.setChannelId(CHANNEL_ID);
            nm.notify(CHANNEL_ID,321, notification.build());
        }
        else{
            assert nm != null;
            nm.notify(321, notification.build());
        }
    }
}
