package com.zzz.prpp.thesis_v01;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.estimote.coresdk.observation.region.RegionUtils.computeAccuracy;

public class NavigationActivity extends AppCompatActivity {

    private NodeViewModel mNodeViewModel;

    private BeaconManager beaconManager;
    private BeaconRegion region;
    public Integer closestNodeNumber;

    private Double one = 100.0;
    private Double two = 100.0;
    private Double three = 100.0;
    private Double four = 100.0;
    private Double five = 100.0;
    private Double six = 100.0;

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

        Node myNode = new Node(99,one,two,three,four,five, null);
        closestNodeNumber = calculateClosestNode(myNode);
    }

    private Integer calculateClosestNode(Node node){
        Integer closestNodeId = 0;
        double minDistance = 100.0;
        List<Node> nodeList = (List) mNodeViewModel.getAllNodes();
        for (Node nd : nodeList){
            double distance = Math.sqrt(Math.pow(node.getMOne()-nd.getMOne(),2)+Math.pow(node.getMTwo()-nd.getMTwo(),2)+Math.pow(node.getMThree()-nd.getMThree(),2)+Math.pow(node.getMFour()-nd.getMFour(),2)+Math.pow(node.getMFive()-nd.getMFive(),2));
            if (distance < minDistance) {closestNodeId = nd.getMNumber();}
        }
        return closestNodeId;
    }

    private void clear() {
        one = 100.0;
        two = 100.0;
        three = 100.0;
        four = 100.0;
        five = 100.0;
        six = 100.0;
    }

    public static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
