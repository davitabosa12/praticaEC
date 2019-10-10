package br.ufc.smd.ufcreminder.typeconverters;

import androidx.room.TypeConverter;
import br.ufc.smd.ufcreminder.Mensagem;
import br.ufc.smd.ufcreminder.Mensagem.*;

import static br.ufc.smd.ufcreminder.Mensagem.Horario.MANHA;


public class HorarioConverter {

    @TypeConverter
    public static Mensagem.Horario toHorario(int horario){
        if(horario == Horario.MANHA.getCode()){
            return Horario.MANHA;
        } else if(horario == Horario.TARDE.getCode()){
            return Horario.TARDE;
        } else if(horario == Horario.NOITE.getCode()){
            return Horario.NOITE;
        } else {
            throw new IllegalArgumentException("Os valores devem ser MANHA, TARDE ou NOITE");
        }
    }

    @TypeConverter
    public static int toInteger(Mensagem.Horario horario){
        return horario.getCode();
    }
}
