package com.ejercicio.examen4diferido.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TABLA CLIENTES
        sqLiteDatabase.execSQL("CREATE TABLE MD_Clientes (ID_Cliente int primary key, sNombreCliente text, sApellidosCliente text, sDireccionCliente, sCiudadCliente text)");

        //TABLA VEHICULOS
        sqLiteDatabase.execSQL("CREATE TABLE MD_Vehiculos (ID_Vehiculo int primary key, sMarca text, sModelo text)");

        //TABLA CLIENTE - VEHICULO
        sqLiteDatabase.execSQL("CREATE TABLE MD_ClienteVehiculo (ID_Cliente int, ID_Vehiculo int, sMatricula text, iKilometros text, " +
                "foreign key (ID_Cliente) references MD_Clientes(ID_Cliente), foreign key(ID_Vehiculo) references MD_Vehiculos(ID_Vehiculo))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
