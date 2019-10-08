package br.ufc.smd.activitydiarynormal;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database( entities = {ActivityLog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract ActivityLogDAO activityLogDAO();

    public static AppDatabase getInstance(Context context) {
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDatabase.class, "activity-diary")
                .enableMultiInstanceInvalidation().allowMainThreadQueries().build();
        return instance;
    }

}
