package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.util.List;
import java.util.UUID;

import static com.estimote.coresdk.observation.region.RegionUtils.computeAccuracy;

public class CalibrationActivity extends AppCompatActivity {

    private BeaconManager beaconManager;
    private BeaconRegion region;

    private Double one;
    private Double two;
    private Double three;
    private Double four;
    private Double five;
    private Double six;

    private Integer bn_pressed;

    private void alertBox (final Integer bn){

        AlertDialog alertDialog = new AlertDialog.Builder(CalibrationActivity.this).create();
        alertDialog.setTitle("hi");
        alertDialog.setMessage("Do you want to calibrate node "+ bn + "?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Continue..", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                calibrate_node(bn);
            }
        });

        alertDialog.show();
    }

    private void clear(){
        one = null;
        two = null;
        three = null;
        four = null;
        five = null;
        six = null;
    }

    private void printNod(Node nod){
        System.out.println(nod.getMNumber());
        System.out.println(nod.getMOne());
        System.out.println(nod.getMTwo());
        System.out.println(nod.getMThree());
        System.out.println(nod.getMFour());
        System.out.println(nod.getMFive());
        System.out.println(nod.getMSix());
    }

    private void calibrate_node(final Integer nd){
        beaconManager = new BeaconManager(this);

        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion region, List<Beacon> list) {
                if (!list.isEmpty()) {

                    clear();
                    for (Beacon item : list){
                        switch (item.getMinor()){
                            case 2224: one = computeAccuracy(item);
                                break;
                            case 48918: four = computeAccuracy(item);
                                break;
                        }
                        Node nod = new Node(nd,one,two,three,four,five,six);
                        printNod(nod);
                        //mNodeViewModel.insert(nod);
                        TextView textView = findViewById(R.id.tvCenter);
                        textView.setText(item.getMinor()+ " - " + computeAccuracy(item));
                    }

                    System.out.println("---------------------------");
                }
                else {
                    System.out.println("I have nothing");
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        Button btn = findViewById(R.id.bnNodeList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalibrationActivity.this, NodeListActivity.class));
            }
        });

        //region = new BeaconRegion("ranged region",
        //        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

    }



    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

       /* beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });*/
    }

    @Override
    protected void onPause() {
        //beaconManager.stopRanging(region);

        super.onPause();
    }

}
