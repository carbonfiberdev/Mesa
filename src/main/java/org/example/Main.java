package org.example;

import org.example.api.ReadFile;
import org.example.engine.Core;
import org.example.entities.Mesh;
import org.example.entities.Triangle;
import org.example.entities.Vertex;

public class Main {
    public void run(){
        Core.PrintLwjglVersion();
        Core.InitWindow(300,300, "Test");
        Core.InitOpengl();

        Triangle triangle1 = new Triangle(-0.5f, 0f);
        Triangle triangle2 = new Triangle(0.5f, 0f);

        Triangle[] triangles = new Triangle[2];
        triangles[0] = triangle1;
        triangles[1] = triangle2;

        Core.Loop(triangles);

        Core.Quit();
    }



    public static void main(String[] args) {
        new Main().run();
    }
}