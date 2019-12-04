package com.example.patitasalcorazn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.patitasalcorazn.projectDatabase.Product;
import com.example.patitasalcorazn.projectDatabase.ProductViewModel;



import java.util.List;

public class ProductActivity extends AppCompatActivity
{
    ProductViewModel products;
    ProductCatalogueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }



    public void doAction(View view)
    {
        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        products.searchProducts(getApplicationContext(),src).observe(this,
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        adapter.setProducts(products);
                    }
                });
    }


    public void goToVolleyScreen(View view)
    {

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


}
