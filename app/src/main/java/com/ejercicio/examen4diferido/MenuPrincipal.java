package com.ejercicio.examen4diferido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void NuevoClie(View view){
        Intent NuevoClie = new Intent(this, MainActivity.class);
        startActivity(NuevoClie);
    }

    public void NuevoVehiculo(View view){
        Intent NuevoVehiculo = new Intent(this, Vehiculos.class);
        startActivity(NuevoVehiculo);
    }

    public void Retro3(View view){
        Intent Retro3 = new Intent(this, ClienteVehiculo.class);
        startActivity(Retro3);
    }

}