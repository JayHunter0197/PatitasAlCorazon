package com.example.patitasalcorazn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.patitasalcorazn.projectDatabase.Product;
import com.example.patitasalcorazn.projectDatabase.ProductTask;

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

        getData();

    }


    // obtener info del Json
    public void getData() {

        final TextView t = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.25.246.43";

        final ProductTask productTask = new ProductTask(getApplicationContext(), null);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try
                        {
                            // seleccionar el catalogo del que se va a obtener info
                            JSONArray jsonArray = response.getJSONArray("empresas");

                            Product[] products = new Product[jsonArray.length()];

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject e = jsonArray.getJSONObject(i);

                                String id = e.getString("id");
                                String nombre = e.getString("nombre");
                                String descripcion = e.getString("descripcion");
                                String imagen = e.getString("imagen");

                                Product pro = new Product();
                                pro.pID = id;
                                pro.name = nombre;
                                pro.description = descripcion;
                                pro.image = imagen;
                                pro.price = 0;

                                t.append(id+"\n"+nombre+"\n"+descripcion+"\n"+imagen+"\n\n");

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
}
