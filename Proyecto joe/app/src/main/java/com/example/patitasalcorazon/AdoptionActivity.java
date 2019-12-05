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

import com.example.patitasalcorazon.projectDatabase.Adoption;
import com.example.patitasalcorazon.projectDatabase.AdoptionReceiver;
import com.example.patitasalcorazon.projectDatabase.AdoptionViewModel;


import java.util.List;

public class AdoptionActivity extends AppCompatActivity implements AdoptionReceiver
{
    AdoptionViewModel adoptions;
    AdoptionCatalogueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);

        adoptions = ViewModelProviders.of(this).get(AdoptionViewModel.class);

        RecyclerView recyclerView  = findViewById(R.id.recycler_view);
        adapter = new AdoptionCatalogueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void doAction(View view)
    {
        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        System.out.println("ME VALE "+src);
        adoptions.searchAdoptions(getApplicationContext(),src).observe(this,
                new Observer<List<Adoption>>() {
                    @Override
                    public void onChanged(List<Adoption> adoptions)
                    {
                        adapter.setAdoptions(adoptions);
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
        adoptions.searchAdoptions(getApplicationContext(),name).observe(this,
                new Observer<List<Adoption>>() {
                    @Override
                    public void onChanged(List<Adoption> adoptions)
                    {
                        intent.putExtra("nombre",adoptions.get(0).name);
                        intent.putExtra("historia",adoptions.get(0).historia);
                        intent.putExtra("imagen",adoptions.get(0).image);
                        intent.putExtra("edad",adoptions.get(0).edad);
                        intent.putExtra("tamano",adoptions.get(0).tamano);
                        intent.putExtra("temperamento",adoptions.get(0).temperamento);
                    }

                });

    }


    @Override
    public void getAllAdoptions(List<Adoption> adoptions) {

    }
}
