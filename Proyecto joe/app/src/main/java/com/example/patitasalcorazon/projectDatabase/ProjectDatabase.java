package com.example.patitasalcorazon.projectDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version =1)
public abstract class ProjectDatabase extends RoomDatabase
{
    public abstract ProductDao productDao();

}
