package br.ufc.smd.ufcreminder;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import br.ufc.smd.ufcreminder.Mensagem.*;

@Dao
public interface MensagemDao {

    @Query("SELECT * FROM mensagem")
    List<Mensagem> getAll();

    @Query("SELECT * FROM mensagem WHERE local = :local AND horario = :horario")
    List<Mensagem> getLocalHorario(Local local, Horario horario);

    @Insert
    void insert(Mensagem... mensagens);

    @Delete
    void delete(Mensagem mensagem);

    @Query("DELETE FROM mensagem")
    void deleteAll();
}
