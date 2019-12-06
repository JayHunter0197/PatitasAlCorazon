package com.example.patitasalcorazon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

public class AboutActivity extends AppCompatActivity {

    private WebView miwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageButton facebookPatitas = findViewById(R.id.facebookPatitas);

        facebookPatitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoFacebook("572920399531724");

            }
        });


        miwebView = findViewById(R.id.wv_main);
        miwebView.getSettings().setJavaScriptEnabled(true);
        miwebView.setWebViewClient(new WebViewClient());
        miwebView.loadUrl("https://www.google.com.mx/maps/place/Veterinar%C3%ADa+Ojuelos/@19.2847973,-99.7112791,14.42z/data=!4m8!1m2!2m1!1sveterinaria!3m4!1s0x85cd881585e1dc81:0x957978adb0d5a2dd!8m2!3d19.28978!4d-99.7013257");

    }

    private void gotoFacebook(String id){
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"+id));
            startActivity(intent);
        }catch(ActivityNotFoundException e){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+id));
            startActivity(intent);
        }
    }


}
