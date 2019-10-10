package br.ufc.smd.ufcreminder.typeconverters;

import androidx.room.TypeConverter;
import br.ufc.smd.ufcreminder.Mensagem;
import br.ufc.smd.ufcreminder.Mensagem.Local;

public class LocalConverter {
    @TypeConverter
    public static Local toHorario(int horario){
        if(horario == Local.PICI.getCode()){
            return Local.PICI;
        } else if(horario == Mensagem.Horario.TARDE.getCode()){
            return Local.PORANGABUSSU;
        } else if(horario == Mensagem.Horario.NOITE.getCode()){
            return Local.BENFICA;
        } else {
            throw new IllegalArgumentException("Os valores devem ser PICI, PORANGEBUSSU ou BENFICA");
        }
    }

    @TypeConverter
    public static int toInteger(Mensagem.Horario horario){
        return horario.getCode();
    }
}
