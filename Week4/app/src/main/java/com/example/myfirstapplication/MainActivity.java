package com.example.myfirstapplication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        requestPermission();

        Bundle extras = getIntent().getExtras();
        String msg = extras.getString("message");
        TextView textView = findViewById(R.id.textViewOutput);
        textView.setText(msg);
    }
    public boolean requestPermission() {
        int REQUEST_PERMISSION = 3000;
        String permissions[] = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };
        boolean grantFinePermission =
                ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED;
        boolean grantCoarsePermission =
                ContextCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_GRANTED;
        if (!grantFinePermission && !grantCoarsePermission) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION);
        } else if (!grantFinePermission) {
            ActivityCompat.requestPermissions(this, new String[]{permissions[0]}, REQUEST_PERMISSION);
        } else if (!grantCoarsePermission) {
            ActivityCompat.requestPermissions(this, new String[]{permissions[1]}, REQUEST_PERMISSION);
        }
        return grantFinePermission && grantCoarsePermission;




    }
    public void displayMessage(View view) {
        TextView textView = findViewById(R.id.textViewOutput);
        EditText editText = findViewById(R.id.editTextInput);
        textView.setText(editText.getText());

        Toast.makeText(this, "OK button clicked.", Toast.LENGTH_LONG).show();
    }
}