package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class NavigationActivity extends AppCompatActivity {

    private NodeViewModel mNodeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final DrawView vw = new DrawView(this);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mNodeViewModel = ViewModelProviders.of(this).get(NodeViewModel.class);

        // Add an observer on the LiveData returned by getAllCoordinates.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mNodeViewModel.getAllCoordinates().observe(this, new Observer<List<Coordinate>>() {
            @Override
            public void onChanged(@Nullable List<Coordinate> coordinates) {
                vw.setCoordinates(coordinates);
            }
        });
    }
}
