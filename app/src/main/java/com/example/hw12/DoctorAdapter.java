package com.example.hw12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {


    private ArrayList<Doctor> doctors;

    public DoctorAdapter(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.fNameView.setText(doctor.getfName());
        holder.lNameView.setText(doctor.getlName());
        holder.specialityView.setText(doctor.getSpeciality());
        holder.attView.setText(String.valueOf(doctor.isAttictated()));
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView fNameView;
        public final TextView lNameView;
        public final TextView specialityView;
        public final TextView attView;

        public ViewHolder(@NonNull View view) {
            super(view);
            fNameView = view.findViewById(R.id.firstName);
            lNameView = view.findViewById(R.id.lastName);
            specialityView = view.findViewById(R.id.speciality);
            attView = view.findViewById(R.id.attestat);
        }
    }


}
