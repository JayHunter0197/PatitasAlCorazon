package com.example.patitasalcorazn.projectDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long[] insertProduct(Product... products);

    @Query("UPDATE product SET name=:nom, description=:desc, image=:img WHERE pID=:id")
    public void updateProducts(String id, String nom, String desc, String img);


    @Query("SELECT * FROM product")
    public LiveData<List<Product>> getAll();

    @Query("SELECT * FROM product WHERE name LIKE :productToSearch")
    public LiveData<List<Product>> search(String productToSearch);

    @Query("SELECT count(*) FROM product")
    public int getBusinessCount();
}
