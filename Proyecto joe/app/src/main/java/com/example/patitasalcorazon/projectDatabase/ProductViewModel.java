package com.example.patitasalcorazon.projectDatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;


import java.util.List;

public class ProductViewModel extends ViewModel
{
    private LiveData<List<Product>> products;

    public ProductViewModel(){super();}

    // obtencion de los productos para mandarlos
    public LiveData<List<Product>> getProducts(Context ctx)
    {
        if(products == null)
        {
            ProjectDatabase db = db = Room.databaseBuilder(ctx,
                    ProjectDatabase.class,
                    "projectDB").build();

            products = db.productDao().getAll();
        }
        return products;
    }

    // obtener los productos que se van a mostrar en el catalogo de adopoiones
    public LiveData<List<Product>> searchProducts(Context ctx, String productToSearch)
    {
        // crear bd room
        ProjectDatabase db  = Room.databaseBuilder(ctx,
                ProjectDatabase.class,
                "projectDB").build();

        // si no se recibio nada muestra todos los productos del catalogo
        if(productToSearch.isEmpty())
        {
            products= db.productDao().getAll();
        }

        // busca el nombre de un producto en especifico
        else
        {
            products = db.productDao().search(productToSearch);
        }

        return products;
    }
}
