package com.example.patitasalcorazon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.patitasalcorazon.projectDatabase.Adoption;
import com.example.patitasalcorazon.projectDatabase.AdoptionTask;
import com.example.patitasalcorazon.projectDatabase.Product;
import com.example.patitasalcorazon.projectDatabase.ProductTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class VolleyActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        getDataAdoption();
        getData();

    }


    // obtener info del Json
    public void getData() {

        final TextView t = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.25.246.43:8000";

        final ProductTask productTask = new ProductTask(getApplicationContext(), null);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try
                        {
                            // seleccionar el catalogo del que se va a obtener info
                            JSONArray jsonArray = response.getJSONArray("productos");

                            Product[] products = new Product[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject e = jsonArray.getJSONObject(i);

                                String id = e.getString("id");
                                String nombre = e.getString("nombre");
                                String descripcion = e.getString("descripcion");
                                String imagen = e.getString("imagen");
                                String precio= e.getString("precio");

                                Product pro = new Product();
                                pro.pID = id;
                                pro.name = nombre;
                                pro.description = descripcion;
                                pro.image = imagen;
                                pro.price = precio;

                                t.append(id+"\n"+nombre+"\n"+descripcion+"\n"+imagen+"\n"+precio+"\n\n");

                                products[i] = pro;
                            }
                            productTask.execute(products);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }

    public void getDataAdoption() {

        final TextView t = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.25.246.43:8000";

        final AdoptionTask adoptionTask = new AdoptionTask(getApplicationContext(), null);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            // seleccionar el catalogo del que se va a obtener info
                            JSONArray jsonArray = response.getJSONArray("adopciones");

                            Adoption[] adoptions = new Adoption[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject e = jsonArray.getJSONObject(i);

                                String id = e.getString("id");
                                String nombre = e.getString("nombre");
                                String historia = e.getString("historia");
                                String imagen = e.getString("imagen");
                                String edad = e.getString("edad");
                                String tamano = e.getString("tamano");

                                System.out.println(id+nombre+historia+imagen+edad+tamano);

                                Adoption ado = new Adoption();
                                ado.aID = id;
                                ado.name = nombre;
                                ado.historia = historia;
                                ado.image = imagen;
                                ado.edad = edad;
                                ado.tamano = tamano;
                                ado.temperamento=0;

                                t.append(id+"\n"+nombre+"\n"+historia+"\n"+imagen+"\n"+edad+"\n"+tamano+"\n\n");

                                adoptions[i] = ado;
                            }
                            adoptionTask.execute(adoptions);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
