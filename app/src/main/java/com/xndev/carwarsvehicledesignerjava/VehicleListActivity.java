package com.xndev.carwarsvehicledesignerjava;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xndev.carwarsvehicledesignerjava.model.Vehicle;
import com.xndev.carwarsvehicledesignerjava.model.VehicleGarage;

public class VehicleListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Vehicles");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView = findViewById(R.id.recyclerVehicles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        VehicleGarage.load(this);
        refreshList();
    }

    private void refreshList() {
        recyclerView.setAdapter(new VehicleAdapter(VehicleGarage.SAVED_VEHICLES, this::openDetail, this::confirmDelete));
    }

    private void confirmDelete(Vehicle vehicle) {
        new AlertDialog.Builder(this)
                .setTitle("Delete vehicle")
                .setMessage("Delete \"" + vehicle.name + "\"? This can't be undone.")
                .setPositiveButton("Delete", (dialog, which) -> {
                    VehicleGarage.delete(this, vehicle);
                    refreshList();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void openDetail(Vehicle vehicle) {
        Intent intent = new Intent(this, VehicleDetailActivity.class);
        intent.putExtra(VehicleDetailActivity.EXTRA_NAME, vehicle.name);
        intent.putExtra(VehicleDetailActivity.EXTRA_CHASSIS, vehicle.chassis);
        intent.putExtra(VehicleDetailActivity.EXTRA_POWER_PLANT, vehicle.powerPlant);
        intent.putExtra(VehicleDetailActivity.EXTRA_NOTES, vehicle.notes);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_FRONT, vehicle.armorFront);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_BACK, vehicle.armorBack);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_LEFT, vehicle.armorLeft);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_RIGHT, vehicle.armorRight);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_TOP, vehicle.armorTop);
        intent.putExtra(VehicleDetailActivity.EXTRA_ARMOR_UNDERBODY, vehicle.armorUnderbody);
        intent.putExtra(VehicleDetailActivity.EXTRA_TIRE_DP, vehicle.tireDp);
        intent.putStringArrayListExtra(VehicleDetailActivity.EXTRA_WEAPONS, vehicle.weapons);
        intent.putExtra(VehicleDetailActivity.EXTRA_SAVED_INDEX, VehicleGarage.SAVED_VEHICLES.indexOf(vehicle));
        startActivity(intent);
    }
}
