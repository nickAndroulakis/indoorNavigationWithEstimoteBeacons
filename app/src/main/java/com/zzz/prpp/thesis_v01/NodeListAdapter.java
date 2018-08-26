package com.zzz.prpp.thesis_v01;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class NodeListAdapter extends RecyclerView.Adapter<NodeListAdapter.NodeViewHolder> {

    class NodeViewHolder extends RecyclerView.ViewHolder {
        private final TextView nodeItemView;

        private NodeViewHolder(View itemView) {
            super(itemView);
            nodeItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Node> mNodes; // Cached copy of words

    NodeListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public NodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NodeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NodeViewHolder holder, int position) {
        Node current = mNodes.get(position);
        holder.nodeItemView.setText(current.getNode());
    }

    void setNodes(List<Node> nodes){
        mNodes = nodes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mNodes has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNodes != null)
            return mNodes.size();
        else return 0;
    }
}


