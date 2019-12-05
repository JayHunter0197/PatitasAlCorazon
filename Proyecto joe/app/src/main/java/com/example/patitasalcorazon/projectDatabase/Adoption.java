package com.example.patitasalcorazon.projectDatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Adoption {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="aID")
    public String aID;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="historia")
    public String historia;

    @ColumnInfo(name="image")
    public String image;

    @ColumnInfo(name="edad")
    public int edad;

    @ColumnInfo(name="tamano")
    public String tamano;

    @ColumnInfo(name="temperamento")
    public int temperamento;
}
