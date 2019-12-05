package com.example.patitasalcorazon.projectDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class AdoptionTask extends AsyncTask<Adoption, Void, List<Adoption>>
{

    ProjectDatabase db;
    AdoptionReceiver receiver;

    public AdoptionTask(Context ctx, AdoptionReceiver receiver)
    {
        this.receiver = receiver;

        db = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();
    }

    // insertar productos en la BD si esta vacia
    @Override
    protected List<Adoption> doInBackground(Adoption... params) {

        if(db.adoptionDao().getBusinessCount() <= 0)
        {
            db.adoptionDao().insertAdoption(params);
        }

        // actualizar BD
        else
        {
            for(int i = 0; i< params.length; i++)
            {
                db.adoptionDao().updateProducts(params[i].aID, params[i].name, params[i].historia, params[i].image, params[i].edad, params[i].tamano, params[i].temperamento);
            }
        }
        return null;
    }
}
