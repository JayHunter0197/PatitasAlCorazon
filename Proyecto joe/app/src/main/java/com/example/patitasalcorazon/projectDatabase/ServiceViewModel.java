package com.example.patitasalcorazon.projectDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class ServiceViewModel extends ViewModel
{
    private LiveData<List<Service>> services;

    public ServiceViewModel () {super();}

    // obtencion de los productos para mandarlos
    public LiveData<List<Service>> getServices(Context ctx)
    {
        if(services == null)
        {
            ProjectDatabase db = db = Room.databaseBuilder(ctx,
                    ProjectDatabase.class,
                    "projectDB").build();

            services = db.serviceDao().getAll();
        }
        return services;
    }

    // obtener los productos que se van a mostrar en el catalogo de adopoiones
    public LiveData<List<Service>> searchServices(Context ctx, String serviceToSearch)
    {
        // crear bd room
        ProjectDatabase db  = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();

        // si no se recibio nada muestra todos los productos del catalogo
        if(serviceToSearch.isEmpty())
        {
            services= db.serviceDao().getAll();
        }
        // busca el nombre de un producto en especifico
        else
        {
            services = db.serviceDao().search(serviceToSearch);
        }

        return services;
    }
}
