/*
1) Envio de datos simples (nombre y correo del usuario) desde MainActivity hacia aqui
2) Crear SharedPreferents.
 */

package com.example.final_proyectoandroid2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class SharedPreferentsActivity extends AppCompatActivity {
    String nombre;
    String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferents);

        TextView tvNombre = (TextView) findViewById(R.id.tvNombrePreferencias);
        TextView tvCorreo = (TextView) findViewById(R.id.tvCorreoPreferencias);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardarPreferencias);
        EditText etSeudonomio = (EditText) findViewById(R.id.etSeudonomioPreferencias);
        EditText etEdad = (EditText) findViewById(R.id.etEdadPreferencias);

        // Recuperacion de envio de datos simples
        Bundle datos = getIntent().getExtras();
        nombre = datos.getString("nombre");
        correo = datos.getString("correo");

        tvNombre.setText(nombre);
        tvCorreo.setText(correo);

        // Obtener una referencia a SharedPreferents
        SharedPreferences myShared = getSharedPreferences("misDatos",MODE_PRIVATE);
        // Editar SharedPreferents a través de un objeto SharedPreferents.Editor
        SharedPreferences.Editor editor = myShared.edit();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seudonomio = etSeudonomio.toString();
                String edad = etEdad.toString();

                if(!seudonomio.isEmpty()){
                    if(!edad.isEmpty()){
                        // Agregar datos clave-valor
                        editor.putString("nombre",nombre);
                        editor.putString("correo",correo);
                        editor.putString("seudonimo",seudonomio);
                        editor.putString("edad",edad);
                        editor.commit();
                        Snackbar.make(v,"Preferencias guardadas",Snackbar.LENGTH_LONG);

                        // Iniciar la actividad MenuActivity
                        Intent intent = new Intent(SharedPreferentsActivity.this,MenuActivity.class);
                        startActivity(intent);
                        etSeudonomio.setText("");
                        etEdad.setText("");
                    }else{
                        Snackbar.make(v,"La edad no puede estar vacia",Snackbar.LENGTH_LONG);
                    }
                }else{
                    Snackbar.make(v,"El seudonimo no puede estar vacio",Snackbar.LENGTH_LONG);
                }
            }
        });


    }
}