package com.example.patitasalcorazon.projectDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;


public class ServiceTask extends AsyncTask<Service, Void, List<Service>>
{
    ProjectDatabase db;
    ServiceReceiver receiver;

    public ServiceTask(Context ctx, ServiceReceiver receiver)
    {
        this.receiver = receiver;

        db = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();
    }

    // insertar productos en la BD si esta vacia
    @Override
    protected List<Service> doInBackground(Service... params) {

        if(db.serviceDao().getBusinessCount() <= 0)
        {
            db.serviceDao().insertService(params);
        }

        // actualizar BD
        else
        {
            for(int i = 0; i< params.length; i++)
            {
                db.serviceDao().updateServices(params[i].sID, params[i].name, params[i].description, params[i].image, params[i].price);
            }
        }
        return null;
    }
}
