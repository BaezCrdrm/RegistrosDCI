package com.lania.mca18.registrosdci;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lania.mca18.model.Item;
import com.lania.mca18.model.Persona;
import com.lania.mca18.service.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RegistroESFragment extends Activity {
    private TextInputEditText val1;
    private TextInputEditText val2;

    Service service;
    Item chosenItemType;
    Item item;
    Persona persona;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro_e);
        final String valor=getIntent().getExtras().getString("code_qr");
        val1 = (TextInputEditText) findViewById(R.id.val_1);
        val1.setText(valor);
        if(persona!=null){
        persona= (Persona) service.getItem(valor,"Persona");
        val2= (TextInputEditText) findViewById(R.id.val_2);
        val2.setText(persona.getNombre());
        }else{
            Log.e("Error","no se carga el objeto");
        }

        Button button= (Button) findViewById(R.id.btn_mod);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle= new Bundle();
                bundle.putString("id",valor);
                Intent i = new Intent(getApplicationContext(),ModificarActivity.class);
                i.putExtra("id",valor);
                startActivity(i);

            }
        });

        Button btnEntrada = (Button) findViewById(R.id.btnRegistrarE);
        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String date = sdt.format(new Date());
                Toast.makeText(getApplicationContext(),date,Toast.LENGTH_LONG).show();
            }
        });



    }


}
