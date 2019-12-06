package com.example.patitasalcorazon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class CatalogActivity extends AppCompatActivity
{
    private ImageButton products, services, adoptions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        products = findViewById(R.id.productosIB);
        products.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                goToProducts();
            }
        });

        services = findViewById(R.id.serviciosIB);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToServices();
            }
        });

        adoptions = findViewById(R.id.adopcionesIB);
        adoptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAdoptions();
            }
        });
    }

    private void goToProducts()
    {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

    private void goToServices()
    {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
    }

    private void goToAdoptions()
    {
        Intent intent = new Intent(this, AdoptionActivity.class);
        startActivity(intent);
    }


}
