package com.example.patitasalcorazon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.example.patitasalcorazon.projectDatabase.DatabaseReceiver;
import com.example.patitasalcorazon.projectDatabase.Product;
import com.example.patitasalcorazon.projectDatabase.ProductViewModel;


import java.util.List;

public class ProductActivity extends AppCompatActivity implements DatabaseReceiver
{
    ProductViewModel products;
    ProductCatalogueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        products = ViewModelProviders.of(this).get(ProductViewModel.class);

        RecyclerView recyclerView  = findViewById(R.id.recycler_view);
        adapter = new ProductCatalogueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        doAction(null);
    }



    public void doAction(View view)
    {
        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        System.out.println("ME VALE "+src);
       products.searchProducts(getApplicationContext(),src).observe(this,
               new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        adapter.setProducts(products);
                    }
                });

    }


    public void goToVolleyScreen(View view) {
        Intent goToNextActivity = new Intent(this, VolleyActivity.class);
        startActivity(goToNextActivity);
    }

    public void sendToNextScreen(final String name) {
        final Intent intent = new Intent(this, DetailedProductActivity.class);

        // mandar informacion del recyclerView a la siguiente actividad
        products.searchProducts(getApplicationContext(),name).observe(this,
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> companies) {
                        intent.putExtra("nombre", companies.get(0).name);
                        intent.putExtra("descripcion", companies.get(0).description);
                        intent.putExtra("imagen", companies.get(0).image);
                        intent.putExtra("precio", companies.get(0).price);
                        intent.putExtra("id", companies.get(0).pID);

                        startActivity(intent);
                    }
                });

    }


    @Override
    public void getAll(List<Product> products) {

    }
}
