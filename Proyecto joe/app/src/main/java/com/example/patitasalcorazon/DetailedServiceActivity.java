package com.example.patitasalcorazon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patitasalcorazon.projectDatabase.ServiceViewModel;
import com.squareup.picasso.Picasso;

public class DetailedServiceActivity extends AppCompatActivity
{
    ServiceViewModel services;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_service);

        fillScreenData();

        services = ViewModelProviders.of(this).get(ServiceViewModel.class);
    }


    // recibir info del recyclerView para desplegarla // arreglar formato para desplegar
    private void fillScreenData()
    {
        final String name = getIntent().getExtras().getString("nombre");
        String desc = getIntent().getExtras().getString("descripcion");
        String img = getIntent().getExtras().getString("imagen");
        String pre = getIntent().getExtras().getString("precio");
        final String id = getIntent().getExtras().getString("id");

        TextView nameView = findViewById(R.id.businessName);
        nameView.setText(name);
        ImageView logo = findViewById(R.id.logoView);
        Picasso.get()
                .load(img)
                .into(logo);

        TextView descView = findViewById(R.id.businessDesc);
        descView.setText(desc);

        TextView priceView  = findViewById(R.id.businessPrecio);
        priceView.setText(pre);
    }


}
