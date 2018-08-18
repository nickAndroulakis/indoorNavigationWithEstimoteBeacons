package com.zzz.prpp.thesis_v01;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import java.util.List;

public class NodeViewModel extends AndroidViewModel {

    private NodeRepository mRepository;

    private List<Node> mAllNodes;

    public NodeViewModel (Application application) {
        super(application);
        mRepository = new NodeRepository(application);
        mAllNodes = mRepository.getAllNodes();
    }

    List<Node> getAllNodes() { return mAllNodes; }

    public void insert(Node node) { mRepository.insert(node); }
}
