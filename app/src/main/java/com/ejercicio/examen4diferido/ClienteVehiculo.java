package com.ejercicio.examen4diferido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ejercicio.examen4diferido.Helper.AdminSQLiteOpenHelper;

public class ClienteVehiculo extends AppCompatActivity {
EditText txtclientes, txtVehi, txtMatri,txtKilome;
Button btnRegist, btnUpd, btndelet, btnBusc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_vehiculo);

        txtclientes = findViewById(R.id.txtcliente);
        txtVehi = findViewById(R.id.txtVehi);
        txtMatri = findViewById(R.id.txtMatricula);
        txtKilome = findViewById(R.id.txtKilo);
        btnRegist = findViewById(R.id.btnRegis);
        btnUpd = findViewById(R.id.btnUp);
        btndelet = findViewById(R.id.btnDele);
        btnBusc = findViewById(R.id.btnBus);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String codigo = txtclientes.getText().toString();
                String codigoVe = txtVehi.getText().toString();
                String Matri = txtMatri.getText().toString();
                String Kilo = txtKilome.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Cliente",codigo);
                informacion.put("ID_Vehiculo",codigoVe);
                informacion.put("sMatricula",Matri);
                informacion.put("iKilometros",Kilo);

                sqLiteDatabase.insert("MD_ClienteVehiculo",null,informacion);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "Se inserto correctamente el registro", Toast.LENGTH_SHORT).show();
            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String codigo = txtclientes.getText().toString();
                String codigoVe = txtVehi.getText().toString();
                String Matri = txtMatri.getText().toString();
                String kilo = txtKilome.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Cliente",codigo);
                informacion.put("ID_Vehiculo",codigoVe);
                informacion.put("sMatricula",Matri);
                informacion.put("iKilometros",kilo);

                int cat = sqLiteDatabase.update("MD_ClienteVehiculo",informacion,"ID_Cliente="+codigo,null);
                    if (cat==1){
                    Toast.makeText(getApplicationContext(), "Se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! No se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });

        btnBusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

                String codigo = txtclientes.getText().toString();

                Cursor filas = sqLiteDatabase.rawQuery("select sMatricula, iKilometros " +
                        "from MD_ClienteVehiculo where ID_Cliente="+codigo, null);

                if(filas.moveToFirst()){
                    txtMatri.setText(filas.getString(1));
                    txtKilome.setText(filas.getString(2));
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se encontro este registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });

    }


    public void Retro2(View view){
        Intent Retro2 = new Intent(this, MenuPrincipal.class);
        startActivity(Retro2);
    }


}