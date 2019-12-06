package com.example.patitasalcorazon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patitasalcorazon.projectDatabase.AdoptionViewModel;
import com.squareup.picasso.Picasso;

public class DetailedAdoptionActivity extends AppCompatActivity
{
    AdoptionViewModel adoptions;

    private Button inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_adoption);

        inicio = findViewById(R.id.adoptarButton);
        inicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showMaps();
            }
        });

        fillScreenData();

        adoptions = ViewModelProviders.of(this).get(AdoptionViewModel.class);
    }

    // recibir info del recyclerView para desplegarla
    private void fillScreenData()
    {
        final String name = getIntent().getExtras().getString("nombre");
        String histo = getIntent().getExtras().getString("historia");
        String img = getIntent().getExtras().getString("imagen");
        String edad = getIntent().getExtras().getString("edad");
        String tam = getIntent().getExtras().getString("tamano");


        final String id = getIntent().getExtras().getString("id");

        TextView nameView = findViewById(R.id.businessName);
        nameView.setText(name);

        ImageView logo = findViewById(R.id.logoView);
        Picasso.get()
                .load(img)
                .into(logo);

        TextView descView = findViewById(R.id.businessDesc);
        descView.setText(histo);


        TextView edadView =findViewById(R.id.businessAge);
        edadView.setText("Edad: "+edad);

        TextView sizeView = findViewById(R.id.businessSize);
        sizeView.setText("Tamaño: "+tam);

    }

    private void showMaps()
    {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }



}
