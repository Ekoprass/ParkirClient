package com.example.ekoprass.parkirclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EditParkiran extends AppCompatActivity {

    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parkiran);

        id=findViewById(R.id.tvIdParkiran);
        id.setText( getIntent().getStringExtra("id"));
    }
}
