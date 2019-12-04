package com.example.patitasalcorazon;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.patitasalcorazon.ui.login.LoginActivity;


public class MainActivity extends AppCompatActivity
{
    private Button inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicio = findViewById(R.id.bInicio);
        inicio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToLogin();
            }
        });
    }

    public void goToLogin()
    {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);


    }
}
