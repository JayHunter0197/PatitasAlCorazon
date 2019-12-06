package com.example.patitasalcorazon.projectDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertService(Service... services);

    @Query("UPDATE service SET name=:nom, description=:desc, image=:img, price=:precio WHERE sID=:id")
    public void updateServices(String id, String nom, String desc, String img, String precio);


    @Query("SELECT * FROM service")
    public LiveData<List<Service>> getAll();

    @Query("SELECT * FROM service WHERE name LIKE :serviceToSearch")
    public LiveData<List<Service>> search(String serviceToSearch);

    @Query("SELECT count(*) FROM service")
    public int getBusinessCount();
}
