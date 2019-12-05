package com.example.patitasalcorazon.projectDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AdoptionDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertAdoption(Adoption... adoptions);

    @Query("UPDATE adoption SET name=:nom, historia=:hist, image=:img, edad=:age, tamano=:size, temperamento=:temp WHERE aID=:id")
    public void updateProducts(String id, String nom, String hist, String img, int age, String size, int temp);


    @Query("SELECT * FROM adoption")
    public LiveData<List<Adoption>> getAll();

    @Query("SELECT * FROM adoption WHERE name LIKE :adoptionToSearch")
    public LiveData<List<Adoption>> search(String adoptionToSearch);

    @Query("SELECT count(*) FROM adoption")
    public int getBusinessCount();
}
