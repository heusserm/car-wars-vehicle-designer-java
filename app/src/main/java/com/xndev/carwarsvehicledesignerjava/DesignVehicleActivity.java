package com.xndev.carwarsvehicledesignerjava;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xndev.carwarsvehicledesignerjava.model.BodyType;
import com.xndev.carwarsvehicledesignerjava.model.ChassisType;
import com.xndev.carwarsvehicledesignerjava.model.ElectricPowerPlant;
import com.xndev.carwarsvehicledesignerjava.model.GasEngine;
import com.xndev.carwarsvehicledesignerjava.model.GasTankType;
import com.xndev.carwarsvehicledesignerjava.model.MountedWeapon;
import com.xndev.carwarsvehicledesignerjava.model.SuspensionType;
import com.xndev.carwarsvehicledesignerjava.model.TireType;
import com.xndev.carwarsvehicledesignerjava.model.Vehicle;
import com.xndev.carwarsvehicledesignerjava.model.VehicleArmor;
import com.xndev.carwarsvehicledesignerjava.model.VehicleGarage;
import com.xndev.carwarsvehicledesignerjava.model.Weapon;
import com.xndev.carwarsvehicledesignerjava.util.VehicleCalculator;
import com.xndev.carwarsvehicledesignerjava.util.VehicleStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DesignVehicleActivity extends AppCompatActivity {

    private EditText editVehicleName;

    private Spinner spinnerBodyType;
    private Spinner spinnerChassis;
    private Spinner spinnerSuspension;
    private Spinner spinnerElectricPlant;
    private Spinner spinnerGasEngine;
    private Spinner spinnerGasTankType;
    private Spinner spinnerTireType;
    private Spinner spinnerWeapon;

    private LinearLayout layoutGasFields;
    private LinearLayout layoutMountedWeapons;
    private TextView textNoWeapons;

    private EditText editGasGallons;
    private EditText editArmorFront;
    private EditText editArmorBack;
    private EditText editArmorLeft;
    private EditText editArmorRight;
    private EditText editArmorTop;
    private EditText editArmorUnderbody;

    private TextView textTotalCost;
    private TextView textAmmoCost;
    private TextView textDriver;
    private TextView textSpaces;
    private TextView textWeight;
    private TextView textHandlingClass;
    private TextView textAcceleration;
    private TextView textTopSpeed;

    private final List<MountedWeapon> mountedWeapons = new ArrayList<>();
    private final VehicleArmor armor = new VehicleArmor();
    private VehicleStats latestStats;

    private final TextWatcher recalculateOnChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            recalculate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_vehicle);
        VehicleGarage.load(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Design New Vehicle");

        bindViews();
        setupSpinners();
        setupListeners();
        recalculate();
    }

    private void bindViews() {
        editVehicleName = findViewById(R.id.editVehicleName);

        spinnerBodyType = findViewById(R.id.spinnerBodyType);
        spinnerChassis = findViewById(R.id.spinnerChassis);
        spinnerSuspension = findViewById(R.id.spinnerSuspension);
        spinnerElectricPlant = findViewById(R.id.spinnerElectricPlant);
        spinnerGasEngine = findViewById(R.id.spinnerGasEngine);
        spinnerGasTankType = findViewById(R.id.spinnerGasTankType);
        spinnerTireType = findViewById(R.id.spinnerTireType);
        spinnerWeapon = findViewById(R.id.spinnerWeapon);

        layoutGasFields = findViewById(R.id.layoutGasFields);
        layoutMountedWeapons = findViewById(R.id.layoutMountedWeapons);
        textNoWeapons = findViewById(R.id.textNoWeapons);

        editGasGallons = findViewById(R.id.editGasGallons);
        editArmorFront = findViewById(R.id.editArmorFront);
        editArmorBack = findViewById(R.id.editArmorBack);
        editArmorLeft = findViewById(R.id.editArmorLeft);
        editArmorRight = findViewById(R.id.editArmorRight);
        editArmorTop = findViewById(R.id.editArmorTop);
        editArmorUnderbody = findViewById(R.id.editArmorUnderbody);

        textTotalCost = findViewById(R.id.textTotalCost);
        textAmmoCost = findViewById(R.id.textAmmoCost);
        textDriver = findViewById(R.id.textDriver);
        textSpaces = findViewById(R.id.textSpaces);
        textWeight = findViewById(R.id.textWeight);
        textHandlingClass = findViewById(R.id.textHandlingClass);
        textAcceleration = findViewById(R.id.textAcceleration);
        textTopSpeed = findViewById(R.id.textTopSpeed);
    }

    private <T> void setupSpinner(Spinner spinner, List<T> items) {
        ArrayAdapter<T> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupSpinners() {
        setupSpinner(spinnerBodyType, BodyType.ALL);
        setupSpinner(spinnerChassis, ChassisType.ALL);
        spinnerChassis.setSelection(1); // Standard
        setupSpinner(spinnerSuspension, SuspensionType.ALL);
        setupSpinner(spinnerElectricPlant, ElectricPowerPlant.ALL);
        setupSpinner(spinnerGasEngine, GasEngine.ALL);
        setupSpinner(spinnerGasTankType, GasTankType.ALL);
        setupSpinner(spinnerTireType, TireType.ALL);
        setupSpinner(spinnerWeapon, Weapon.ALL);
    }

    private void setupListeners() {
        RadioGroup radioGroup = findViewById(R.id.radioGroupPowerPlantType);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isElectric = checkedId == R.id.radioElectric;
            spinnerElectricPlant.setVisibility(isElectric ? View.VISIBLE : View.GONE);
            layoutGasFields.setVisibility(isElectric ? View.GONE : View.VISIBLE);
            recalculate();
        });

        AdapterView.OnItemSelectedListener onSelect = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };
        spinnerBodyType.setOnItemSelectedListener(onSelect);
        spinnerChassis.setOnItemSelectedListener(onSelect);
        spinnerSuspension.setOnItemSelectedListener(onSelect);
        spinnerElectricPlant.setOnItemSelectedListener(onSelect);
        spinnerGasEngine.setOnItemSelectedListener(onSelect);
        spinnerGasTankType.setOnItemSelectedListener(onSelect);
        spinnerTireType.setOnItemSelectedListener(onSelect);

        editGasGallons.addTextChangedListener(recalculateOnChange);
        editArmorFront.addTextChangedListener(armorWatcher(v -> armor.front = v));
        editArmorBack.addTextChangedListener(armorWatcher(v -> armor.back = v));
        editArmorLeft.addTextChangedListener(armorWatcher(v -> armor.left = v));
        editArmorRight.addTextChangedListener(armorWatcher(v -> armor.right = v));
        editArmorTop.addTextChangedListener(armorWatcher(v -> armor.top = v));
        editArmorUnderbody.addTextChangedListener(armorWatcher(v -> armor.underbody = v));

        setupArmorStepper(editArmorFront, R.id.buttonArmorFrontMinus, R.id.buttonArmorFrontPlus);
        setupArmorStepper(editArmorBack, R.id.buttonArmorBackMinus, R.id.buttonArmorBackPlus);
        setupArmorStepper(editArmorLeft, R.id.buttonArmorLeftMinus, R.id.buttonArmorLeftPlus);
        setupArmorStepper(editArmorRight, R.id.buttonArmorRightMinus, R.id.buttonArmorRightPlus);
        setupArmorStepper(editArmorTop, R.id.buttonArmorTopMinus, R.id.buttonArmorTopPlus);
        setupArmorStepper(editArmorUnderbody, R.id.buttonArmorUnderbodyMinus, R.id.buttonArmorUnderbodyPlus);

        findViewById(R.id.buttonAddWeapon).setOnClickListener(v -> addSelectedWeapon());
        findViewById(R.id.buttonCancel).setOnClickListener(v -> finish());
        findViewById(R.id.buttonSave).setOnClickListener(v -> saveVehicle());
    }

    private interface IntSetter {
        void set(int value);
    }

    private TextWatcher armorWatcher(IntSetter setter) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                int value = 0;
                try {
                    value = Integer.parseInt(s.toString());
                } catch (NumberFormatException ignored) {
                }
                setter.set(value);
                recalculate();
            }
        };
    }

    private void setupArmorStepper(EditText edit, int minusId, int plusId) {
        findViewById(minusId).setOnClickListener(v -> adjustInt(edit, -1));
        findViewById(plusId).setOnClickListener(v -> adjustInt(edit, 1));
    }

    private void adjustInt(EditText edit, int delta) {
        int current = 0;
        try {
            current = Integer.parseInt(edit.getText().toString());
        } catch (NumberFormatException ignored) {
        }
        edit.setText(String.valueOf(Math.max(0, current + delta)));
    }

    private void addSelectedWeapon() {
        Weapon weapon = (Weapon) spinnerWeapon.getSelectedItem();
        if (weapon == null) return;
        mountedWeapons.add(new MountedWeapon(weapon));
        rebuildMountedWeaponsList();
        recalculate();
    }

    private void rebuildMountedWeaponsList() {
        layoutMountedWeapons.removeAllViews();
        textNoWeapons.setVisibility(mountedWeapons.isEmpty() ? View.VISIBLE : View.GONE);

        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < mountedWeapons.size(); i++) {
            final int index = i;
            MountedWeapon mounted = mountedWeapons.get(i);
            Weapon weapon = mounted.weapon;
            View row = inflater.inflate(R.layout.item_mounted_weapon, layoutMountedWeapons, false);
            ((TextView) row.findViewById(R.id.textWeaponName)).setText(weapon.name);
            ((TextView) row.findViewById(R.id.textWeaponDetails)).setText(
                    String.format("Dam %s | $%d | %d lb | %s sp", weapon.damage, weapon.cost, weapon.weight, formatSpace(weapon.space)));

            View layoutAmmo = row.findViewById(R.id.layoutAmmo);
            if (weapon.ammoPerBox > 0) {
                layoutAmmo.setVisibility(View.VISIBLE);
                TextView textRounds = row.findViewById(R.id.textAmmoRounds);
                TextView textCost = row.findViewById(R.id.textAmmoCost);
                textRounds.setText(String.valueOf(mounted.ammoRounds));
                textCost.setText(String.format(Locale.US, "$%.0f", mounted.ammoCost()));
                row.findViewById(R.id.buttonAmmoMinus).setOnClickListener(v -> {
                    mounted.ammoRounds = Math.max(0, mounted.ammoRounds - weapon.ammoPerBox);
                    rebuildMountedWeaponsList();
                    recalculate();
                });
                row.findViewById(R.id.buttonAmmoPlus).setOnClickListener(v -> {
                    mounted.ammoRounds += weapon.ammoPerBox;
                    rebuildMountedWeaponsList();
                    recalculate();
                });
            } else {
                layoutAmmo.setVisibility(View.GONE);
            }

            row.findViewById(R.id.buttonRemoveWeapon).setOnClickListener(v -> {
                mountedWeapons.remove(index);
                rebuildMountedWeaponsList();
                recalculate();
            });
            layoutMountedWeapons.addView(row);
        }
    }

    private String formatSpace(double space) {
        if (space == Math.floor(space)) {
            return String.valueOf((int) space);
        }
        return String.format("%.2f", space);
    }

    private double parseDouble(EditText editText) {
        try {
            return Double.parseDouble(editText.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void recalculate() {
        VehicleCalculator.Input input = new VehicleCalculator.Input();
        input.body = (BodyType) spinnerBodyType.getSelectedItem();
        input.chassis = (ChassisType) spinnerChassis.getSelectedItem();
        input.suspension = (SuspensionType) spinnerSuspension.getSelectedItem();
        input.tire = (TireType) spinnerTireType.getSelectedItem();
        input.armor = armor;
        input.mountedWeapons = mountedWeapons;

        RadioGroup radioGroup = findViewById(R.id.radioGroupPowerPlantType);
        input.isElectric = radioGroup.getCheckedRadioButtonId() == R.id.radioElectric;
        if (input.isElectric) {
            input.electricPlant = (ElectricPowerPlant) spinnerElectricPlant.getSelectedItem();
        } else {
            input.gasEngine = (GasEngine) spinnerGasEngine.getSelectedItem();
            input.gasTankType = (GasTankType) spinnerGasTankType.getSelectedItem();
            input.gasGallons = parseDouble(editGasGallons);
        }

        if (input.body == null || input.chassis == null || input.suspension == null || input.tire == null) {
            return;
        }
        if (input.isElectric && input.electricPlant == null) return;
        if (!input.isElectric && (input.gasEngine == null || input.gasTankType == null)) return;

        latestStats = VehicleCalculator.compute(input);
        updateSummary(latestStats);
    }

    private void updateSummary(VehicleStats stats) {
        textTotalCost.setText(String.format("Total Cost: $%.0f", stats.totalCost));
        textAmmoCost.setText(String.format("  (includes $%.0f of ammo)", stats.ammoCost));
        textDriver.setText(String.format(Locale.US, "Driver: %d lb, %d sp (included above)", stats.driverWeight, stats.driverSpaces));

        boolean overSpace = stats.spacesAvailable < 0;
        textSpaces.setText(String.format("Spaces: %.1f used, %.1f available", stats.spacesUsed, stats.spacesAvailable));
        textSpaces.setTextColor(overSpace ? Color.RED : defaultTextColor());

        boolean overWeight = stats.weightAvailable() < 0;
        textWeight.setText(String.format("Weight: %.0f / %.0f lb max (%.0f available)",
                stats.totalWeight, stats.maxLoad, stats.weightAvailable()));
        textWeight.setTextColor(overWeight ? Color.RED : defaultTextColor());

        textHandlingClass.setText(String.format("Handling Class: %d", stats.handlingClass));

        textAcceleration.setText(stats.isUnderpowered
                ? "Acceleration: Underpowered – vehicle will not move"
                : String.format("Acceleration: %d mph", stats.acceleration));
        textAcceleration.setTextColor(stats.isUnderpowered ? Color.RED : defaultTextColor());

        textTopSpeed.setText(String.format("Top Speed: %.1f mph", stats.topSpeed));
    }

    private int defaultTextColor() {
        return textTotalCost.getCurrentTextColor();
    }

    private void saveVehicle() {
        if (latestStats == null) return;

        String name = editVehicleName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            name = "Unnamed Vehicle";
        }

        BodyType body = (BodyType) spinnerBodyType.getSelectedItem();
        ChassisType chassis = (ChassisType) spinnerChassis.getSelectedItem();
        String chassisSummary = String.format("%s %s", chassis.name, body.name);

        RadioGroup radioGroup = findViewById(R.id.radioGroupPowerPlantType);
        String powerPlantSummary;
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioElectric) {
            ElectricPowerPlant plant = (ElectricPowerPlant) spinnerElectricPlant.getSelectedItem();
            powerPlantSummary = plant != null ? plant.name : "Electric power plant";
        } else {
            GasEngine engine = (GasEngine) spinnerGasEngine.getSelectedItem();
            powerPlantSummary = engine != null ? engine.name : "Gas engine";
        }

        String notes = String.format(Locale.US,
                "Cost $%.0f (incl. $%.0f ammo) | %.0f lb | HC %d | Accel %s | Top speed %.1f mph | %d weapon(s) mounted",
                latestStats.totalCost, latestStats.ammoCost, latestStats.totalWeight, latestStats.handlingClass,
                latestStats.isUnderpowered ? "underpowered" : latestStats.acceleration + " mph",
                latestStats.topSpeed, mountedWeapons.size());

        TireType tire = (TireType) spinnerTireType.getSelectedItem();

        ArrayList<String> weaponLines = new ArrayList<>();
        for (MountedWeapon mounted : mountedWeapons) {
            Weapon weapon = mounted.weapon;
            String line = String.format(Locale.US, "%s (Dam %s) — $%d, %d lb, %s sp",
                    weapon.name, weapon.damage, weapon.cost, weapon.weight, formatSpace(weapon.space));
            if (weapon.ammoPerBox > 0) {
                line += String.format(Locale.US, " — Ammo: %d rounds ($%.0f)", mounted.ammoRounds, mounted.ammoCost());
            }
            weaponLines.add(line);
        }

        VehicleGarage.SAVED_VEHICLES.add(new Vehicle(name, chassisSummary, powerPlantSummary, notes,
                armor.front, armor.back, armor.left, armor.right, armor.top, armor.underbody, tire.dp, weaponLines));
        VehicleGarage.persist(this);
        Toast.makeText(this, name + " saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
