package com.ejercicio.examen4diferido;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.ejercicio.examen4diferido.Helper.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {

    EditText txtId, txtNom, txtApe, txtDire, txtCiu;
    Button btnInsert, btnActu, btnBorrar, btnBusqueda ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Examen diferido
        txtId =findViewById(R.id.txtCliente);
        txtNom = findViewById(R.id.txtNombre);
        txtApe=findViewById(R.id.txtApellido);
        txtDire=findViewById(R.id.txtDireccion);
        txtCiu=findViewById(R.id.txtCiudad);
        btnInsert = findViewById(R.id.btnInsertar);
        btnActu = findViewById(R.id.bntUpdate);
        btnBorrar = findViewById(R.id.bntDelete);
        btnBusqueda =findViewById(R.id.btnBuscar);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String cod = txtId.getText().toString();
                String nombre = txtNom.getText().toString();
                String apellido = txtApe.getText().toString();
                String Direcc = txtDire.getText().toString();
                String ciud = txtCiu.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Cliente",cod);
                informacion.put("sNombreCliente",nombre);
                informacion.put("sApellidosCliente",apellido);
                informacion.put("sDireccionCliente",Direcc);
                informacion.put("sCiudadcliente",ciud);

                sqLiteDatabase.insert("MD_Clientes",null,informacion);
                sqLiteDatabase.close();
                Toast.makeText(getApplicationContext(), "Se inserto correctamente el registro", Toast.LENGTH_SHORT).show();

            }

        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

                String cod = txtId.getText().toString();
                int cat = sqLiteDatabase.delete("MD_Clientes","ID_Cliente="+cod,null);
                sqLiteDatabase.close();
                txtId.setText("");
                txtNom.setText("");
                txtApe.setText("");
                txtDire.setText("");
                txtCiu.setText("");
                if (cat==1){
                    Toast.makeText(getApplicationContext(), "Se elimino correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! No se elimino correctamente el registro", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnActu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();
                String cod = txtId.getText().toString();
                String nombre = txtNom.getText().toString();
                String apellido = txtApe.getText().toString();
                String Direcc = txtDire.getText().toString();
                String ciud = txtCiu.getText().toString();

                ContentValues informacion = new ContentValues();
                informacion.put("ID_Cliente",cod);
                informacion.put("sNombreCliente",nombre);
                informacion.put("sApellidosCliente",apellido);
                informacion.put("sDireccionCliente",Direcc);
                informacion.put("sCiudadcliente",ciud);

                int cat = sqLiteDatabase.update("MD_Clientes",informacion,"ID_Cliente="+cod,null);

                if (cat==1){
                    Toast.makeText(getApplicationContext(), "Se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "ERROR! No se modifico correctamente el registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });

        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "registros",null, 2);
                SQLiteDatabase sqLiteDatabase = admin.getWritableDatabase();

                String cod = txtId.getText().toString();

                Cursor filas = sqLiteDatabase.rawQuery("select sNombreCliente, sApellidosCliente,sDireccionCliente,sCiudadCliente " +
                        "from MD_Clientes where ID_Cliente="+cod, null);

                if(filas.moveToFirst()){
                    txtNom.setText(filas.getString(0));
                    txtApe.setText(filas.getString(1));
                    txtDire.setText(filas.getString(2));
                    txtCiu.setText(filas.getString(3));
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se encontro este registro", Toast.LENGTH_SHORT).show();
                }
                sqLiteDatabase.close();
            }
        });




    }
    //MOVERSE AL MENU PRINCIPAL
    public void Menu(View view){
        Intent Menu = new Intent(this, MenuPrincipal.class);
        startActivity(Menu);
    }
}