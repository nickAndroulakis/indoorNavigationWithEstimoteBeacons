package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class CalibrationActivity extends AppCompatActivity {

    private NodeViewModel mNodeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        final DrawNodesView dw = findViewById(R.id.dvDrawNodes);

        Button btn = findViewById(R.id.bnToNodeList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalibrationActivity.this, NodeListActivity.class));
            }
        });

        mNodeViewModel = ViewModelProviders.of(this).get(NodeViewModel.class);

        mNodeViewModel.getAllCoordinates().observe(this, new Observer<List<Coordinate>>() {
            @Override
            public void onChanged(@Nullable List<Coordinate> coordinates) {
                dw.setCoordinates(coordinates);
                dw.invalidate();
            }
        });
    }
}
