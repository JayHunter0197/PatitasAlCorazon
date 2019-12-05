package com.example.patitasalcorazon.projectDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


public class Adoption {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="pID")
    public String pID;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="price")
    public int price;
}
