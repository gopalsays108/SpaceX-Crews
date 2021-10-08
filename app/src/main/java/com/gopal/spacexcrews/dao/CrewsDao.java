package com.gopal.spacexcrews.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.gopal.spacexcrews.modal.CrewsModal;

import java.util.List;

@Dao
public interface CrewsDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE) // Here it will replace all duplicate field
    void insertCrews(List<CrewsModal> crewsModal);

    @Query( "SELECT * FROM crew_members" )
    LiveData<List<CrewsModal>> getAllCrews();

    @Query( "DELETE FROM crew_members " )
    void deleteAll();

    @Query("DELETE FROM crew_members WHERE id = :id")
    void deleteByUserId(String id);
}