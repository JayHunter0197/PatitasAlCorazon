package com.example.patitasalcorazon.projectDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Service {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="sID")
    public String sID;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="price")
    public String price;



}
