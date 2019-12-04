package com.example.patitasalcorazn.projectDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product
{
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
