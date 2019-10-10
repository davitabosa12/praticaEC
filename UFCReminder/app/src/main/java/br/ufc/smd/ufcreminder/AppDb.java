package br.ufc.smd.ufcreminder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import br.ufc.smd.ufcreminder.typeconverters.HorarioConverter;
import br.ufc.smd.ufcreminder.typeconverters.LocalConverter;

@Database(entities = {Mensagem.class}, version = 1)
@TypeConverters({LocalConverter.class, HorarioConverter.class})
public abstract class AppDb extends RoomDatabase {
    private static AppDb instance;
    public abstract MensagemDao mensagemDao();

    public static AppDb getInstance(Context context) {
        if(instance == null)
            instance = Room.databaseBuilder(context, AppDb.class, "ufc-lembrete")
                    .enableMultiInstanceInvalidation()
                    .allowMainThreadQueries()
                    .build();
        return instance;
    }
}
