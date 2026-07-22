package com.xndev.carwarsvehicledesignerjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    public static final String EXTRA_BODY_TYPE = "extra_body_type";
    public static final String EXTRA_CHASSIS_TYPE = "extra_chassis_type";
    public static final String EXTRA_SUSPENSION_TYPE = "extra_suspension_type";
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
    public static final String EXTRA_HAS_BODY_ARMOR = "extra_has_body_armor";
    public static final String EXTRA_TARGETING_COMPUTER = "extra_targeting_computer";
    public static final String EXTRA_ACCESSORIES = "extra_accessories";
    public static final String EXTRA_TOTAL_COST = "extra_total_cost";
    public static final String EXTRA_WEIGHT = "extra_weight";
    public static final String EXTRA_HANDLING_CLASS = "extra_handling_class";
    public static final String EXTRA_ACCELERATION = "extra_acceleration";
    public static final String EXTRA_IS_UNDERPOWERED = "extra_is_underpowered";
    public static final String EXTRA_TOP_SPEED = "extra_top_speed";
    public static final String EXTRA_SAVED_INDEX = "extra_saved_index";

    private static final int MENU_ID_DELETE = 1;

    private String name;
    private int savedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        name = getIntent().getStringExtra(EXTRA_NAME);
        String bodyType = getIntent().getStringExtra(EXTRA_BODY_TYPE);
        String chassisType = getIntent().getStringExtra(EXTRA_CHASSIS_TYPE);
        String suspensionType = getIntent().getStringExtra(EXTRA_SUSPENSION_TYPE);
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
        boolean hasBodyArmor = getIntent().getBooleanExtra(EXTRA_HAS_BODY_ARMOR, false);
        String targetingComputer = getIntent().getStringExtra(EXTRA_TARGETING_COMPUTER);
        ArrayList<String> accessories = getIntent().getStringArrayListExtra(EXTRA_ACCESSORIES);
        double totalCost = getIntent().getDoubleExtra(EXTRA_TOTAL_COST, 0);
        double weight = getIntent().getDoubleExtra(EXTRA_WEIGHT, 0);
        int handlingClass = getIntent().getIntExtra(EXTRA_HANDLING_CLASS, 0);
        int acceleration = getIntent().getIntExtra(EXTRA_ACCELERATION, 0);
        boolean isUnderpowered = getIntent().getBooleanExtra(EXTRA_IS_UNDERPOWERED, false);
        double topSpeed = getIntent().getDoubleExtra(EXTRA_TOP_SPEED, 0);
        savedIndex = getIntent().getIntExtra(EXTRA_SAVED_INDEX, -1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        ((TextView) findViewById(R.id.textBodyType)).setText(String.format(Locale.US, "Body: %s", bodyType));
        ((TextView) findViewById(R.id.textChassisType)).setText(String.format(Locale.US, "Chassis: %s", chassisType));
        ((TextView) findViewById(R.id.textSuspensionType)).setText(String.format(Locale.US, "Suspension: %s", suspensionType));
        ((TextView) findViewById(R.id.textPowerPlant)).setText(powerPlant);

        ((TextView) findViewById(R.id.textArmorFront)).setText(String.format(Locale.US, "Front: %d DP", armorFront));
        ((TextView) findViewById(R.id.textArmorBack)).setText(String.format(Locale.US, "Back: %d DP", armorBack));
        ((TextView) findViewById(R.id.textArmorLeft)).setText(String.format(Locale.US, "Left: %d DP", armorLeft));
        ((TextView) findViewById(R.id.textArmorRight)).setText(String.format(Locale.US, "Right: %d DP", armorRight));
        ((TextView) findViewById(R.id.textArmorTop)).setText(String.format(Locale.US, "Top: %d DP", armorTop));
        ((TextView) findViewById(R.id.textArmorUnderbody)).setText(String.format(Locale.US, "Under: %d DP", armorUnderbody));
        ((TextView) findViewById(R.id.textTires)).setText(String.format(Locale.US, "Tires: %d DP each", tireDp));
        int driverDp = VehicleCalculator.BASE_DRIVER_DP + (hasBodyArmor ? VehicleCalculator.BODY_ARMOR_DP_BONUS : 0);
        ((TextView) findViewById(R.id.textDriver)).setText(String.format(Locale.US, "Driver: %d lb, %d DP%s",
                VehicleCalculator.DRIVER_WEIGHT, driverDp, hasBodyArmor ? " (body armor)" : ""));
        ((TextView) findViewById(R.id.textTargeting)).setText(String.format(Locale.US, "Targeting: %s",
                targetingComputer == null ? "None" : targetingComputer));

        String weaponsText = (weapons == null || weapons.isEmpty())
                ? "No weapons mounted."
                : TextUtils.join("\n\n", weapons);
        ((TextView) findViewById(R.id.textWeapons)).setText(weaponsText);

        String accessoriesText = (accessories == null || accessories.isEmpty())
                ? "No accessories."
                : TextUtils.join("\n", accessories);
        ((TextView) findViewById(R.id.textAccessories)).setText(accessoriesText);

        View notesSection = findViewById(R.id.layoutNotes);
        if (notes == null || notes.isEmpty()) {
            notesSection.setVisibility(View.GONE);
        } else {
            notesSection.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.textNotes)).setText(notes);
        }

        ((TextView) findViewById(R.id.textHandlingClass)).setText(String.format(Locale.US, "HC: %d", handlingClass));
        ((TextView) findViewById(R.id.textWeight)).setText(String.format(Locale.US, "Weight: %.0f lb", weight));
        ((TextView) findViewById(R.id.textAcceleration)).setText(isUnderpowered
                ? "Accel: Underpowered – vehicle will not move"
                : String.format(Locale.US, "Accel: %d mph", acceleration));
        ((TextView) findViewById(R.id.textTopSpeed)).setText(String.format(Locale.US, "Top Speed: %.1f mph", topSpeed));
        ((TextView) findViewById(R.id.textTotalCost)).setText(String.format(Locale.US, "Cost: $%.0f", totalCost));
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
