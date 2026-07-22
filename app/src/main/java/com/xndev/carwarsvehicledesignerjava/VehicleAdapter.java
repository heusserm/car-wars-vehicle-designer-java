package com.xndev.carwarsvehicledesignerjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xndev.carwarsvehicledesignerjava.model.Vehicle;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    public interface OnVehicleClickListener {
        void onVehicleClick(Vehicle vehicle);
    }

    public interface OnVehicleDeleteListener {
        void onVehicleDelete(Vehicle vehicle);
    }

    private final List<Vehicle> vehicles;
    private final OnVehicleClickListener clickListener;
    private final OnVehicleDeleteListener deleteListener;

    public VehicleAdapter(List<Vehicle> vehicles, OnVehicleClickListener clickListener,
                           OnVehicleDeleteListener deleteListener) {
        this.vehicles = vehicles;
        this.clickListener = clickListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.name.setText(vehicle.name);
        holder.chassis.setText(vehicle.chassisType + " " + vehicle.bodyType);
        holder.content.setOnClickListener(v -> clickListener.onVehicleClick(vehicle));
        holder.deleteButton.setOnClickListener(v -> deleteListener.onVehicleDelete(vehicle));
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final View content;
        final TextView name;
        final TextView chassis;
        final ImageButton deleteButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.layoutVehicleContent);
            name = itemView.findViewById(R.id.textVehicleName);
            chassis = itemView.findViewById(R.id.textVehicleChassis);
            deleteButton = itemView.findViewById(R.id.buttonDeleteVehicle);
        }
    }
}
