package com.xndev.carwarsvehicledesignerjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.buttonViewVehicles).setOnClickListener(v ->
                startActivity(new Intent(this, VehicleListActivity.class)));

        findViewById(R.id.buttonDesignVehicle).setOnClickListener(v ->
                startActivity(new Intent(this, DesignVehicleActivity.class)));
    }
}
