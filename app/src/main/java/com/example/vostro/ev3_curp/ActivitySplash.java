package com.example.vostro.ev3_curp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivitySplash extends AppCompatActivity {

    /**Duracion estiamada de la imagen establecida en el Splash**/
    private final int DURACION_SPLASH = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (ActivitySplash.this, MainActivity.class);
                startActivity(intent);
                finish();
            };
        },DURACION_SPLASH);


    }
}
