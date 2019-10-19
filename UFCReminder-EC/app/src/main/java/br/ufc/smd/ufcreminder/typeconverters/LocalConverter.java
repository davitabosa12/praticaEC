package br.ufc.smd.ufcreminder.typeconverters;

import androidx.room.TypeConverter;
import br.ufc.smd.ufcreminder.Mensagem;
import br.ufc.smd.ufcreminder.Mensagem.Local;

public class LocalConverter {
    @TypeConverter
    public static Local toLocal(int local){
        if(local == Local.PICI.getCode()){
            return Local.PICI;
        } else {
            throw new IllegalArgumentException("Os valores devem ser PICI, PORANGEBUSSU ou BENFICA");
        }
    }

    @TypeConverter
    public static int toLocalInteger(Mensagem.Local local){
        return local.getCode();
    }
}
