package com.example.hw12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Dialog dialog;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dbManager = new DBManager(new DoctorSQLiteHelper(this, "my_database.db", null,1));
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        dialog = new Dialog(this);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Басалыков РИБО-02-21 3вар");
        }

        showRecyclerViewChange();


    }

    public void showRecyclerViewChange() {
        RecyclerView rcView = findViewById(R.id.recyclerView);
        ArrayList<Doctor> doctors = dbManager.loadAllDoctorsFromDatabase();
        DoctorAdapter adapter = new DoctorAdapter(doctors);
        rcView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        rcView.setAdapter(adapter);
    }

    public void showCustomDialog() {
        dialog.setContentView(R.layout.add_doctor_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //setContentView(R.layout.add_doctor_dialog);
        EditText fNameView = dialog.findViewById(R.id.fName);
        EditText lNameView = dialog.findViewById(R.id.lName);
        EditText specView = dialog.findViewById(R.id.spec);
        Button btn = dialog.findViewById(R.id.addbtn);
        Switch sw = dialog.findViewById(R.id.switch1);

        btn.setOnClickListener(v -> {
            String fName = fNameView.getText().toString();
            String lName = lNameView.getText().toString();
            String spec = specView.getText().toString();
            Boolean switchState = sw.isChecked();
            if (this.dbManager != null) {
                if (!fName.isEmpty() && !lName.isEmpty() && !spec.isEmpty()) {
                    boolean result = dbManager.saveDoctorToDatabase(new Doctor(fName,lName,spec,switchState));
                    if (result) {
                        Toast.makeText(this,R.string.insert_success,Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this,R.string.insert_error,Toast.LENGTH_LONG).show();

                    }
                }
            }
            showRecyclerViewChange();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Log.i("HW12_TAG", "FILTER NORMAL");
                showCustomDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}