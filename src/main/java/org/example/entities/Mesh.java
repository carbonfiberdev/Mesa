package org.example.entities;

public class Mesh {
    public Vertex left;
    public Vertex right;
    public Vertex up;
    public Mesh(Vertex left, Vertex right, Vertex up) {
        this.left = left;
        this.right = right;
        this.up = up;
    }
}
