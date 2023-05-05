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

public class Vehiculos extends AppCompatActivity {
EditText txtCodV, txtMarcas, txtModel;
Button btnInser, btnAc, btnElim,btnBusq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
        //LLAMANDO LOS TEXT
        txtCodV = findViewById(R.id.txtCodigo);
        txtMarcas = findViewById(R.id.txtMarca);
        txtModel = findViewById(R.id.txtModelo);
        btnInser = findViewById(R.id.btnRegistro);
        btnAc = findViewById(R.id.btnUpdate);
        btnElim = findViewById(R.id.btnElimi);
        btnBusq = findViewById(R.id.btnBuscarVe);

        btnInser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String codigo = txtCodV.getText().toString();
                String marcas = txtMarcas.getText().toString();
                String modelo = txtModel.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Vehiculo",codigo);
                informacion.put("sMarca",marcas);
                informacion.put("sModelo",modelo);

                sqLiteDatabase.insert("MD_Vehiculos",null,informacion);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "Se inserto correctamente el registro", Toast.LENGTH_SHORT).show();
            }
        });

        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

                String codigo = txtCodV.getText().toString();

                int cat = sqLiteDatabase.delete("MD_Vehiculos","ID_Vehiculo="+codigo,null);
                sqLiteDatabase.close();
                txtCodV.setText("");
                txtMarcas.setText("");
                txtModel.setText("");

                if (cat==1){
                    Toast.makeText(getApplicationContext(), "Se elimino correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! No se elimino correctamente el registro", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String codigo = txtCodV.getText().toString();
                String marcas = txtMarcas.getText().toString();
                String modelo = txtModel.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Vehiculo",codigo);
                informacion.put("sMarca",marcas);
                informacion.put("sModelo",modelo);

                int cat = sqLiteDatabase.update("MD_Vehiculos",informacion,"ID_Vehiculo="+codigo,null);

                if (cat==1){
                    Toast.makeText(getApplicationContext(), "Se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! No se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });

        btnBusq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

                String codigo = txtCodV.getText().toString();

                Cursor filas = sqLiteDatabase.rawQuery("select sMarca, sModelo " +
                        "from MD_Vehiculos where ID_Vehiculo="+codigo, null);

                if(filas.moveToFirst()){
                    txtMarcas.setText(filas.getString(0));
                    txtModel.setText(filas.getString(1));
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se encontro este registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });

    }
    public void Retro(View view){
        Intent Retro = new Intent(this, MenuPrincipal.class);
        startActivity(Retro);
    }

}