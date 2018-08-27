package com.zzz.prpp.thesis_v01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import static com.estimote.coresdk.observation.region.RegionUtils.computeAccuracy;

import java.util.List;
import java.util.UUID;


public class NewNodeActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.zzz.prpp.thesis_v01.REPLY";

    private BeaconManager beaconManager;
    private BeaconRegion region;

    private Double one = 100.0;
    private Double two = 100.0;
    private Double three = 100.0;
    private Double four = 100.0;
    private Double five = 100.0;
    private Double six = 100.0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_node);

        region = new BeaconRegion("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.BeaconRangingListener() {
            @Override
            public void onBeaconsDiscovered(BeaconRegion region, List<Beacon> list) {
                if (!list.isEmpty()) {
                    clear();
                    for (Beacon item : list) {
                        switch (item.getMinor()) {
                            case 2224:
                                one = computeAccuracy(item);
                                break;
                            case 46152:
                                two = computeAccuracy(item);
                                break;
                            case 41111:
                                three = computeAccuracy(item);
                                break;
                            case 48918:
                                four = computeAccuracy(item);
                                break;
                            case 48677:
                                five = computeAccuracy(item);
                                break;
                        }
                    }
                }
            }
        });

        final Spinner dropdown = findViewById(R.id.spinner1);
        Integer[] items = new Integer[]{1,2,3};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        final Button button = findViewById(R.id.bnCalibrate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                Integer selectedNumber = (Integer) dropdown.getSelectedItem();

                if (isEmpty()) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra("node", selectedNumber);
                    replyIntent.putExtra("1", one);
                    replyIntent.putExtra("2", two);
                    replyIntent.putExtra("3", three);
                    replyIntent.putExtra("4", four);
                    replyIntent.putExtra("5", five);
                    replyIntent.putExtra("6", six);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);
        super.onPause();
    }

    private Boolean isEmpty(){
        return one == 100.0 && two == 100.0 && three == 100.0 && four == 100.0 && five == 100.0 && six == 100.0;
    }

    private void clear() {
        one = 100.0;
        two = 100.0;
        three = 100.0;
        four = 100.0;
        five = 100.0;
        six = 100.0;
    }
}