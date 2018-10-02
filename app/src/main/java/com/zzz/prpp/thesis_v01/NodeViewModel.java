package com.zzz.prpp.thesis_v01;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NodeViewModel extends AndroidViewModel {

    private NodeRepository mRepository;
    private CoordinateRepository mCoordinateRepository;

    private LiveData<List<Node>> mAllNodes;
    private LiveData<List<Coordinate>> mAllCoordinates;
    //private List<Node> mAllNodesSimple;

    public NodeViewModel (Application application) {
        super(application);
        mRepository = new NodeRepository(application);
        mCoordinateRepository = new CoordinateRepository(application);
        mAllNodes = mRepository.getAllNodes();
        mAllCoordinates = mCoordinateRepository.getmAllCoordinates();
        //mAllNodesSimple = mRepository.getmAllNodesSimple();
    }

    LiveData<List<Node>> getAllNodes() { return mAllNodes; }
    LiveData<List<Coordinate>> getAllCoordinates() { return mAllCoordinates;}
    //List<Node> getmAllNodesSimple() { return mAllNodesSimple;}

    public void insert(Node node) { mRepository.insert(node); }
    public void insert(Coordinate coordinate) { mCoordinateRepository.insert(coordinate);}
}
