package com.example.patitasalcorazn.projectDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;

public class ProductTask extends AsyncTask<Product, Void, List<Product>>
{
    ProjectDatabase db;
    DatabaseReceiver receiver;

    public ProductTask(Context ctx, DatabaseReceiver receiver)
    {
        this.receiver = receiver;

        db = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();
    }

    // insertar productos en la BD si esta vacia
    @Override
    protected List<Product> doInBackground(Product... params) {

        if(db.productDao().getBusinessCount() <= 0)
        {
            db.productDao().insertProduct(params);
        }

        // actualizar BD
        else
        {
            for(int i = 0; i< params.length; i++)
            {
                db.productDao().updateProducts(params[i].pID, params[i].name, params[i].description, params[i].image);
            }
        }
        return null;
    }
}
