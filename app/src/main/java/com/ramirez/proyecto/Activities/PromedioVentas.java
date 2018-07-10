package com.ramirez.proyecto.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ramirez.proyecto.R;

public class PromedioVentas extends AppCompatActivity {


    ImageButton regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio_ventas);

        regresar = findViewById(R.id.boton_regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PromedioVentas.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
