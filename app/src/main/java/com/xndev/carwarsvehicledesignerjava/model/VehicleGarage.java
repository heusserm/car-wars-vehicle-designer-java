package com.xndev.carwarsvehicledesignerjava.model;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** Vehicles saved by the user, persisted to SharedPreferences as JSON. */
public final class VehicleGarage {
    private static final String PREFS_NAME = "car_wars_vehicle_designer";
    private static final String KEY_SAVED_VEHICLES = "saved_vehicles";

    private VehicleGarage() {}

    public static final List<Vehicle> SAVED_VEHICLES = new ArrayList<>();

    private static boolean loaded = false;

    /** Loads persisted vehicles into SAVED_VEHICLES, once per process. */
    public static void load(Context context) {
        if (loaded) return;
        loaded = true;

        String json = prefs(context).getString(KEY_SAVED_VEHICLES, null);
        if (json == null) return;

        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                JSONArray weaponsArray = obj.getJSONArray("weapons");
                ArrayList<String> weapons = new ArrayList<>();
                for (int w = 0; w < weaponsArray.length(); w++) {
                    weapons.add(weaponsArray.getString(w));
                }
                ArrayList<String> accessories = new ArrayList<>();
                JSONArray accessoriesArray = obj.optJSONArray("accessories");
                if (accessoriesArray != null) {
                    for (int a = 0; a < accessoriesArray.length(); a++) {
                        accessories.add(accessoriesArray.getString(a));
                    }
                }
                SAVED_VEHICLES.add(new Vehicle(
                        obj.getString("name"),
                        obj.getString("bodyType"),
                        obj.getString("chassisType"),
                        obj.getString("suspensionType"),
                        obj.getString("powerPlant"),
                        obj.getString("notes"),
                        obj.getInt("armorFront"),
                        obj.getInt("armorBack"),
                        obj.getInt("armorLeft"),
                        obj.getInt("armorRight"),
                        obj.getInt("armorTop"),
                        obj.getInt("armorUnderbody"),
                        obj.getInt("tireDp"),
                        weapons,
                        obj.optBoolean("hasBodyArmor", false),
                        obj.optString("targetingComputer", "None"),
                        accessories,
                        obj.getDouble("totalCost"),
                        obj.getDouble("weight"),
                        obj.getInt("handlingClass"),
                        obj.getInt("acceleration"),
                        obj.getBoolean("isUnderpowered"),
                        obj.getDouble("topSpeed")
                ));
            }
        } catch (JSONException ignored) {
        }
    }

    /** Writes the current SAVED_VEHICLES list to SharedPreferences. */
    public static void persist(Context context) {
        JSONArray array = new JSONArray();
        for (Vehicle vehicle : SAVED_VEHICLES) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("name", vehicle.name);
                obj.put("bodyType", vehicle.bodyType);
                obj.put("chassisType", vehicle.chassisType);
                obj.put("suspensionType", vehicle.suspensionType);
                obj.put("powerPlant", vehicle.powerPlant);
                obj.put("notes", vehicle.notes);
                obj.put("armorFront", vehicle.armorFront);
                obj.put("armorBack", vehicle.armorBack);
                obj.put("armorLeft", vehicle.armorLeft);
                obj.put("armorRight", vehicle.armorRight);
                obj.put("armorTop", vehicle.armorTop);
                obj.put("armorUnderbody", vehicle.armorUnderbody);
                obj.put("tireDp", vehicle.tireDp);
                obj.put("weapons", new JSONArray(vehicle.weapons));
                obj.put("hasBodyArmor", vehicle.hasBodyArmor);
                obj.put("targetingComputer", vehicle.targetingComputer);
                obj.put("accessories", new JSONArray(vehicle.accessories));
                obj.put("totalCost", vehicle.totalCost);
                obj.put("weight", vehicle.weight);
                obj.put("handlingClass", vehicle.handlingClass);
                obj.put("acceleration", vehicle.acceleration);
                obj.put("isUnderpowered", vehicle.isUnderpowered);
                obj.put("topSpeed", vehicle.topSpeed);
            } catch (JSONException ignored) {
            }
            array.put(obj);
        }
        prefs(context).edit().putString(KEY_SAVED_VEHICLES, array.toString()).apply();
    }

    /** Removes a saved vehicle and persists the change. Has no effect on placeholder vehicles. */
    public static void delete(Context context, Vehicle vehicle) {
        SAVED_VEHICLES.remove(vehicle);
        persist(context);
    }

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
