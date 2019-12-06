package com.example.patitasalcorazon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patitasalcorazon.projectDatabase.Adoption;
import com.example.patitasalcorazon.projectDatabase.AdoptionReceiver;
import com.example.patitasalcorazon.projectDatabase.AdoptionViewModel;
import com.example.patitasalcorazon.projectDatabase.Service;
import com.example.patitasalcorazon.projectDatabase.ServiceReceiver;
import com.example.patitasalcorazon.projectDatabase.ServiceViewModel;

import java.util.List;

public class ServiceActivity extends AppCompatActivity implements ServiceReceiver
{
    ServiceViewModel services;
    ServiceCatalogueAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        services = ViewModelProviders.of(this).get(ServiceViewModel.class);

        RecyclerView recyclerView  = findViewById(R.id.recycler_view);
        adapter = new ServiceCatalogueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        doAction(null);
    }

    public void doAction(View view)
    {
        String src = "%"+((EditText)findViewById(R.id.searchText)).getText()+"%";
        services.searchServices(getApplicationContext(),src).observe(this,
                new Observer<List<Service>>() {
                    @Override
                    public void onChanged(List<Service> services)
                    {
                        adapter.setServices(services);
                    }


                });

    }


    public void goToVolleyScreen(View view) {
        Intent goToNextActivity = new Intent(this, VolleyActivity.class);
        startActivity(goToNextActivity);
    }

    public void sendToNextScreen(final String name) {
        final Intent intent = new Intent(this, DetailedServiceActivity.class);

        // mandar informacion del recyclerView a la siguiente actividad
        services.searchServices(getApplicationContext(),name).observe(this,
                new Observer<List<Service>>() {
                    @Override
                    public void onChanged(List<Service> services)
                    {
                        intent.putExtra("nombre",services.get(0).name);
                        intent.putExtra("historia",services.get(0).description);
                        intent.putExtra("imagen",services.get(0).image);
                        intent.putExtra("edad",services.get(0).price);

                        startActivity(intent);

                    }

                });

    }


    @Override
    public void getAllServices(List<Service> services) {

    }
}
