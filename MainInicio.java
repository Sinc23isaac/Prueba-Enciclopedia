package com.example.animaltfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamo y hago la conexion del incio de mi app para que se vea la animacion
        ImageView imagenlogo=(ImageView)findViewById(R.id.logoimg);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion);
        //para que se ejecute la animacion
        imagenlogo.startAnimation(animacion);

        //inicio del buton para ir a la parte registro de la pagina
        Button inicio=(Button) findViewById(R.id.btnIniciar);
        //cuando se presiona hara lo que esta dentro
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambiamos de vista es decir nos vamos al login ahora es decir para ingresar ya directamente a la pagina
                Intent cambiLogin=new Intent(MainInicio.this, Login_reg.class);
                //para iniciar y comenzar la otra vista
                startActivity(cambiLogin);
            }
        });
    }
}