package com.example.patitasalcorazon;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.patitasalcorazon.projectDatabase.AdoptionViewModel;
import com.squareup.picasso.Picasso;

public class DetailedAdoptionActivity extends AppCompatActivity
{
    AdoptionViewModel adoptions;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);

        fillScreenData();

        adoptions = ViewModelProviders.of(this).get(AdoptionViewModel.class);
    }

    // recibir info del recyclerView para desplegarla
    private void fillScreenData()
    {
        final String name = getIntent().getExtras().getString("nombre");
        String desc = getIntent().getExtras().getString("historia");
        String img = getIntent().getExtras().getString("imagen");
        String edad = getIntent().getExtras().getString("edad");
        String tam = getIntent().getExtras().getString("tamano");
        String tempo = getIntent().getExtras().getString("temperamento");

        final String id = getIntent().getExtras().getString("id");

        TextView nameView = findViewById(R.id.businessName);
        nameView.setText(name);
        ImageView logo = findViewById(R.id.logoView);
        Picasso.get()
                .load(img)
                .into(logo);

        TextView descView = findViewById(R.id.businessDesc);
        descView.setText(desc);

        /*
        TextView edadView =findViewById(R.id.businessAge);
        edadView.setText(edad);

        TextView sizeView = findViewById(R.id.businessSize);
        sizeView.setText(tam);

        TextView tempView = findViewById(R.id.businessTemp);
        tempView.setText(tempo);


         */
    }

}
