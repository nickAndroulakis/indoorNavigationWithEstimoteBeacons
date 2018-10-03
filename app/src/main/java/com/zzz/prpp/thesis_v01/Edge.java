package com.zzz.prpp.thesis_v01;

public class Edge {
    private final Integer id;
    private final Vertex source;
    private final Vertex destination;
    private final float weight;

    public Edge(Integer id, Vertex source, Vertex destination, Float weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public Float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}
