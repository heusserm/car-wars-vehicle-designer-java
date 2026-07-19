package com.xndev.carwarsvehicledesignerjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xndev.carwarsvehicledesignerjava.model.VehicleGarage;
import com.xndev.carwarsvehicledesignerjava.util.VehicleCalculator;

import java.util.ArrayList;
import java.util.Locale;

public class VehicleDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_CHASSIS = "extra_chassis";
    public static final String EXTRA_POWER_PLANT = "extra_power_plant";
    public static final String EXTRA_NOTES = "extra_notes";
    public static final String EXTRA_ARMOR_FRONT = "extra_armor_front";
    public static final String EXTRA_ARMOR_BACK = "extra_armor_back";
    public static final String EXTRA_ARMOR_LEFT = "extra_armor_left";
    public static final String EXTRA_ARMOR_RIGHT = "extra_armor_right";
    public static final String EXTRA_ARMOR_TOP = "extra_armor_top";
    public static final String EXTRA_ARMOR_UNDERBODY = "extra_armor_underbody";
    public static final String EXTRA_TIRE_DP = "extra_tire_dp";
    public static final String EXTRA_WEAPONS = "extra_weapons";
    public static final String EXTRA_SAVED_INDEX = "extra_saved_index";

    private static final int MENU_ID_DELETE = 1;

    private String name;
    private int savedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        name = getIntent().getStringExtra(EXTRA_NAME);
        String chassis = getIntent().getStringExtra(EXTRA_CHASSIS);
        String powerPlant = getIntent().getStringExtra(EXTRA_POWER_PLANT);
        String notes = getIntent().getStringExtra(EXTRA_NOTES);
        int armorFront = getIntent().getIntExtra(EXTRA_ARMOR_FRONT, 0);
        int armorBack = getIntent().getIntExtra(EXTRA_ARMOR_BACK, 0);
        int armorLeft = getIntent().getIntExtra(EXTRA_ARMOR_LEFT, 0);
        int armorRight = getIntent().getIntExtra(EXTRA_ARMOR_RIGHT, 0);
        int armorTop = getIntent().getIntExtra(EXTRA_ARMOR_TOP, 0);
        int armorUnderbody = getIntent().getIntExtra(EXTRA_ARMOR_UNDERBODY, 0);
        int tireDp = getIntent().getIntExtra(EXTRA_TIRE_DP, 0);
        ArrayList<String> weapons = getIntent().getStringArrayListExtra(EXTRA_WEAPONS);
        savedIndex = getIntent().getIntExtra(EXTRA_SAVED_INDEX, -1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        ((TextView) findViewById(R.id.textChassis)).setText(chassis);
        ((TextView) findViewById(R.id.textPowerPlant)).setText(powerPlant);
        ((TextView) findViewById(R.id.textNotes)).setText(notes);

        ((TextView) findViewById(R.id.textArmorFront)).setText(String.format(Locale.US, "Front: %d DP", armorFront));
        ((TextView) findViewById(R.id.textArmorBack)).setText(String.format(Locale.US, "Back: %d DP", armorBack));
        ((TextView) findViewById(R.id.textArmorLeft)).setText(String.format(Locale.US, "Left: %d DP", armorLeft));
        ((TextView) findViewById(R.id.textArmorRight)).setText(String.format(Locale.US, "Right: %d DP", armorRight));
        ((TextView) findViewById(R.id.textArmorTop)).setText(String.format(Locale.US, "Top: %d DP", armorTop));
        ((TextView) findViewById(R.id.textArmorUnderbody)).setText(String.format(Locale.US, "Underbody: %d DP", armorUnderbody));
        ((TextView) findViewById(R.id.textTires)).setText(String.format(Locale.US, "Tires: %d DP each", tireDp));
        ((TextView) findViewById(R.id.textDriver)).setText(String.format(Locale.US, "Driver: %d lb, %d DP",
                VehicleCalculator.DRIVER_WEIGHT, VehicleCalculator.DRIVER_DP));

        String weaponsText = (weapons == null || weapons.isEmpty())
                ? "No weapons mounted."
                : TextUtils.join("\n\n", weapons);
        ((TextView) findViewById(R.id.textWeapons)).setText(weaponsText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (savedIndex >= 0) {
            MenuItem deleteItem = menu.add(Menu.NONE, MENU_ID_DELETE, Menu.NONE, "Delete");
            deleteItem.setIcon(android.R.drawable.ic_menu_delete);
            deleteItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == MENU_ID_DELETE) {
            confirmDelete();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete vehicle")
                .setMessage("Delete \"" + name + "\"? This can't be undone.")
                .setPositiveButton("Delete", (dialog, which) -> {
                    VehicleGarage.SAVED_VEHICLES.remove(savedIndex);
                    VehicleGarage.persist(this);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
