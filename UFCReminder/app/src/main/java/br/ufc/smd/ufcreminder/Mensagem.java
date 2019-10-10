package br.ufc.smd.ufcreminder;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import br.ufc.smd.ufcreminder.typeconverters.HorarioConverter;
import br.ufc.smd.ufcreminder.typeconverters.LocalConverter;

@Entity
public class Mensagem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "conteudo")
    private String conteudo;

    @ColumnInfo(name = "local")
    private Local local;

    @ColumnInfo(name = "horario")
    private Horario horario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mensagem(String conteudo, Local local, Horario horario) {
        this.conteudo = conteudo;
        this.local = local;
        this.horario = horario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public enum Local{
        PICI(0), PORANGABUSSU(1), BENFICA(2);

        int code;

        Local(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public enum Horario{
        MANHA(0), TARDE(1), NOITE(2);

        int code;

        Horario(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
