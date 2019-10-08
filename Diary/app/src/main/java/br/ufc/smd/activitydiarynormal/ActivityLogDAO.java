package br.ufc.smd.activitydiarynormal;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ActivityLogDAO {


    @Query("SELECT * FROM activitylog")
    List<ActivityLog> getAll();

    @Query("SELECT * FROM activitylog WHERE activity_type = :type")
    List<ActivityLog> getByActivityType(int type);

    @Insert
    void insertAll(ActivityLog ...logs);

    @Delete
    void delete(ActivityLog log);

    @Query("DELETE FROM activitylog")
    void deleteAll();
}
