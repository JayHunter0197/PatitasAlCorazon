package com.example.patitasalcorazon.projectDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;


import java.util.List;

public class AdoptionViewModel
{
    private LiveData<List<Adoption>> adoptions;

    public AdoptionViewModel(){super();}

    // obtencion de los productos para mandarlos
    public LiveData<List<Adoption>> getAdoptions(Context ctx)
    {
        if(adoptions == null)
        {
            ProjectDatabase db = db = Room.databaseBuilder(ctx,
                    ProjectDatabase.class,
                    "projectDB").build();

            adoptions = db.adoptionDao().getAll();
        }
        return adoptions;
    }

    // obtener los productos que se van a mostrar en el catalogo de adopoiones
    public LiveData<List<Adoption>> searchAdoptions(Context ctx, String adoptionToSearch)
    {
        // crear bd room
        ProjectDatabase db  = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();

        // si no se recibio nada muestra todos los productos del catalogo
        if(adoptionToSearch.isEmpty())
        {
            adoptions= db.adoptionDao().getAll();
        }
        // busca el nombre de un producto en especifico
        else
        {
            adoptions = db.adoptionDao().search(adoptionToSearch);
        }

        return adoptions;
    }
}
